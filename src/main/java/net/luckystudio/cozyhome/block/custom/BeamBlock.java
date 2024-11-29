package net.luckystudio.cozyhome.block.custom;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.interfaces.Strippable;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.EnumMap;
import java.util.Map;

public class BeamBlock extends ConnectingBlock implements Waterloggable, Strippable {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final Direction[] FACINGS = Direction.values();
    private static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ImmutableMap.of(
            Direction.NORTH, NORTH,
            Direction.EAST, EAST,
            Direction.SOUTH, SOUTH,
            Direction.WEST, WEST,
            Direction.UP, UP,
            Direction.DOWN, DOWN
    );

    @Override
    public MapCodec<BeamBlock> getCodec() {
        return null;
    }

    public BeamBlock(float radius, Settings settings) {
        super(radius, settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(NORTH, false)
                        .with(EAST, false)
                        .with(SOUTH, false)
                        .with(WEST, false)
                        .with(UP, false)
                        .with(DOWN, false)
                        .with(WATERLOGGED, false)
        );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);

        // Start with the base shape centered on the block face determined by FACING
        VoxelShape baseShape = createFaceCenteredShape(facing.getOpposite(), 4.0); // 8x8x8 cube (4 radius)

        // Add shapes for each connection
        for (Direction direction : FACINGS) {
            if (state.get(FACING_PROPERTIES.get(direction))) {
                baseShape = VoxelShapes.union(baseShape, createFaceCenteredShape(direction, 4.0));
            }
        }

        return baseShape;
    }

    private VoxelShape createFaceCenteredShape(Direction direction, double radius) {
        double min = 8.0 - radius; // Center the 8x8x8 cube
        double max = 8.0 + radius;

        return switch (direction) {
            case UP -> Block.createCuboidShape(min, 8.0, min, max, 16.0, max);
            case DOWN -> Block.createCuboidShape(min, 0.0, min, max, 8.0, max);
            case NORTH -> Block.createCuboidShape(min, min, 0.0, max, max, 8.0);
            case SOUTH -> Block.createCuboidShape(min, min, 8.0, max, max, 16.0);
            case WEST -> Block.createCuboidShape(0.0, min, min, 8.0, max, max);
            case EAST -> Block.createCuboidShape(8.0, min, min, 16.0, max, max);
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return Strippable.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        state = state
                .withIfExists(FACING, ctx.getSide())
                .with(WATERLOGGED, bl);
        return updateConnectionProperties(ctx.getWorld(), ctx.getBlockPos(), state);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return updateConnectionProperties(world, pos, state);
    }

    public static BlockState updateConnectionProperties(WorldAccess world, BlockPos pos, BlockState state) {
        Map<Direction, BlockState> blockStates = getBlockDirections(world, pos);

        for (Direction direction : Direction.values()) {
            state = state.withIfExists(FACING_PROPERTIES.get(direction), isConnectableFace(state, blockStates.get(direction), world, pos, direction));
        }

        return state;
    }

    private static boolean isConnectableFace(BlockState originBlock, BlockState targetBlock, WorldAccess world, BlockPos pos, Direction currentDirection) {
        if (originBlock.get(FACING) == currentDirection.getOpposite()) return false;

        if (targetBlock.isAir()) return Boolean.FALSE;

        if (originBlock.get(FACING) == currentDirection || originBlock.get(FACING) == currentDirection.getOpposite()) {
            if (targetBlock.isSideSolidFullSquare(world, pos, currentDirection)) return Boolean.TRUE;
            if (isLongBeam(targetBlock) || isSameAxis(targetBlock, currentDirection)) return Boolean.TRUE;
            return Boolean.FALSE;
        }

        if (!isLongBeam(originBlock, world, pos)) return Boolean.FALSE;

        if (targetBlock.isSideSolid(world, pos, currentDirection.getOpposite(), SideShapeType.CENTER)) return Boolean.TRUE;

//        if (targetBlock.isSideSolidFullSquare(world, pos, currentDirection.getOpposite())) return Boolean.TRUE;

        if (!isBeamBlock(targetBlock.getBlock())) return Boolean.FALSE;

        if (isSameAxis(targetBlock, currentDirection)) return Boolean.TRUE;

        return Boolean.FALSE;
    }

    private static boolean isLongBeam(BlockState originBlock, WorldAccess world, BlockPos pos) {
        Direction frontFacing = originBlock.get(FACING);
        Direction backFacing = frontFacing.getOpposite();

        BlockState frontBlock = world.getBlockState(pos.offset(frontFacing));
        BlockState backBlock = world.getBlockState(pos.offset(backFacing));

        boolean isFrontConnected;
        boolean isBackConnected;

        if (frontBlock.isAir()) return Boolean.FALSE;

        isFrontConnected = frontBlock.isSideSolidFullSquare(world, pos, frontFacing.getOpposite());
        isBackConnected = backBlock.isSideSolidFullSquare(world, pos, backFacing.getOpposite()) || backBlock.isAir();

        if (isBeamBlock(frontBlock.getBlock())) {
            if (isSameAxis(frontBlock, originBlock.get(FACING))) isFrontConnected = true;
            if (!isSameAxis(frontBlock, originBlock.get(FACING)) && isLongBeam(frontBlock)) isFrontConnected = true;
        }

        if (isBeamBlock(backBlock.getBlock())) {
            if (isSameAxis(backBlock, originBlock.get(FACING))) isBackConnected = true;
            if (!isSameAxis(backBlock, originBlock.get(FACING)) && isLongBeam(backBlock)) isBackConnected = true;
        }

        return isFrontConnected && isBackConnected;
    }

    private static boolean isSameAxis(BlockState targetBlock, Direction currentDirection) {
        if (!isBeamBlock(targetBlock.getBlock())) return false;
        return currentDirection == targetBlock.get(FACING) || currentDirection == targetBlock.get(FACING).getOpposite();
    }

    private static boolean isLongBeam(BlockState targetBlock) {
        if (!isBeamBlock(targetBlock.getBlock())) return false;
        return targetBlock.get(getFacingProperty(targetBlock.get(FACING)));
    }

    private static BooleanProperty getFacingProperty(Direction direction) {
        return switch (direction) {
            case Direction.UP -> UP;
            case Direction.DOWN -> DOWN;
            case Direction.WEST -> WEST;
            case Direction.EAST -> EAST;
            case Direction.NORTH -> NORTH;
            case Direction.SOUTH -> SOUTH;
        };
    }

    private static boolean isBeamBlock(Block block) {
        return block instanceof BeamBlock;
    }

    // Testing if the beam can be straight
    public static Map<Direction, BlockState> getBlockDirections(WorldAccess world, BlockPos pos) {
        Map<Direction, BlockState> blockStates = new EnumMap<>(Direction.class);

        for (Direction direction : Direction.values()) {
            blockStates.put(direction, world.getBlockState(pos.offset(direction)));
        }
        return blockStates;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
