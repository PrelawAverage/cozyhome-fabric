package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.EnumMap;
import java.util.Map;

public class BeamBlock extends ConnectingBlock {
    public static final DirectionProperty FACING = Properties.FACING;
    private static final Direction[] FACINGS = Direction.values();
    protected final VoxelShape[] facingsToShape;
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
        );
        this.facingsToShape = this.generateFacingsToShapeMap(radius);
    }

    private VoxelShape[] generateFacingsToShapeMap(float radius) {
        float f = 0.5F - radius;
        float g = 0.5F + radius;
        VoxelShape voxelShape = Block.createCuboidShape(
                f * 16.0F, f * 16.0F, f * 16.0F, g * 16.0F, g * 16.0F, g * 16.0F
        );
        VoxelShape[] voxelShapes = new VoxelShape[FACINGS.length];

        for (int i = 0; i < FACINGS.length; i++) {
            Direction direction = FACINGS[i];
            voxelShapes[i] = VoxelShapes.cuboid(
                    0.5 + Math.min(-radius, (double)direction.getOffsetX() * 0.5),
                    0.5 + Math.min(-radius, (double)direction.getOffsetY() * 0.5),
                    0.5 + Math.min(-radius, (double)direction.getOffsetZ() * 0.5),
                    0.5 + Math.max(radius, (double)direction.getOffsetX() * 0.5),
                    0.5 + Math.max(radius, (double)direction.getOffsetY() * 0.5),
                    0.5 + Math.max(radius, (double)direction.getOffsetZ() * 0.5)
            );
        }

        VoxelShape[] voxelShapes2 = new VoxelShape[64];

        for (int j = 0; j < 64; j++) {
            VoxelShape voxelShape2 = voxelShape;

            for (int k = 0; k < FACINGS.length; k++) {
                if ((j & 1 << k) != 0) {
                    voxelShape2 = VoxelShapes.union(voxelShape2, voxelShapes[k]);
                }
            }

            voxelShapes2[j] = voxelShape2;
        }

        return voxelShapes2;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.facingsToShape[this.getConnectionMask(state)];
    }

    protected int getConnectionMask(BlockState state) {
        int i = 0;

        for (int j = 0; j < FACINGS.length; j++) {
            if ((Boolean)state.get((Property)FACING_PROPERTIES.get(FACINGS[j]))) {
                i |= 1 << j;
            }
        }

        return i;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return updateConnectionPropertiesCTX(ctx, ctx.getBlockPos(), this.getDefaultState());
    }

    public static BlockState updateConnectionPropertiesCTX(ItemPlacementContext ctx, BlockPos pos, BlockState state) {
        state = state.with(FACING, ctx.getSide());

        for (Direction direction: Direction.values()) {
            state = state.withIfExists(FACING_PROPERTIES.get(direction), canBeStraight(ctx.getWorld(), pos, state));
        }

        return state;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return updateConnectionProperties(world, pos, state);
    }

    public static BlockState updateConnectionProperties(WorldAccess world, BlockPos pos, BlockState state) {
        Map<Direction, BlockState> blockStates = getBlockDirections(world, pos);

        for (Direction direction : Direction.values()) {
            Direction opposite = direction.getOpposite();
            state = state.withIfExists(FACING_PROPERTIES.get(direction), giveDirectionState(state, blockStates.get(direction), world, pos, opposite));
        }

        return state;
    }

    public static Map<Direction, BlockState> getBlockDirections(WorldAccess world, BlockPos pos) {
        Map<Direction, BlockState> blockStates = new EnumMap<>(Direction.class);

        for (Direction direction : Direction.values()) {
            blockStates.put(direction, world.getBlockState(pos.offset(direction)));
        }

        return blockStates;
    }

    private static boolean giveDirectionState(BlockState mainBlock, BlockState locationCheck, WorldAccess world, BlockPos pos, Direction OppositeOfDirectionFacing) {
        Direction directionOfMainBlock = mainBlock.get(FACING);
        boolean flag1 =directionOfMainBlock != OppositeOfDirectionFacing;
        boolean flag2 = isConnectableFace(world, pos, locationCheck, mainBlock, directionOfMainBlock);

        // To make sure we don't set the side that is equal to the original(FACING) true
        return flag1 && flag2;
    }

    private static boolean isConnectableFace(WorldAccess world, BlockPos pos, BlockState locationCheck, BlockState mainBlock, Direction directionChecking) {
        Direction OppositeDirection = directionChecking.getOpposite();

        // Checks if the block has a solid face
        return locationCheck.isSideSolidFullSquare(world, pos, OppositeDirection) && canBeStraight(world, pos, mainBlock);
    }

    // Testing if the beam can be straight
    private static boolean canBeStraight(WorldAccess world, BlockPos pos, BlockState state) {
        Map<Direction, BlockState> blockStates = getBlockDirections(world, pos);

        BlockState blockState = blockStates.get(state.get(FACING));

        Direction oppositeDirection = state.get(FACING).getOpposite();

        return blockState.isSideSolidFullSquare(world, pos, oppositeDirection);
    }

    private static boolean isBeamBlock(Block block) {
        return block instanceof BeamBlock;
    }
}
