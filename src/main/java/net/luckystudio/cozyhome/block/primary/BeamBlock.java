package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.enums.LinearConnectionBlock;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

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
        return withConnectionPropertiesCTX(ctx, ctx.getWorld(), ctx.getBlockPos(), this.getDefaultState());
    }

    public static BlockState withConnectionPropertiesCTX(ItemPlacementContext ctx, BlockView world, BlockPos pos, BlockState state) {
        Direction direction = ctx.getSide();
        return state.with(FACING, direction)
                .withIfExists(DOWN, canBeStraight(ctx.getWorld(), pos, state))
                .withIfExists(UP, canBeStraight(ctx.getWorld(), pos, state))
                .withIfExists(NORTH, canBeStraight(ctx.getWorld(), pos, state))
                .withIfExists(EAST, canBeStraight(ctx.getWorld(), pos, state))
                .withIfExists(SOUTH, canBeStraight(ctx.getWorld(), pos, state))
                .withIfExists(WEST, canBeStraight(ctx.getWorld(), pos, state));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return withConnectionProperties(world, pos, state);
    }

    public static BlockState withConnectionProperties(WorldAccess world, BlockPos pos, BlockState state) {
        BlockState blockStateBelow = world.getBlockState(pos.down());
        BlockState blockStateUp = world.getBlockState(pos.up());
        BlockState blockStateNorth = world.getBlockState(pos.north());
        BlockState blockStateEast = world.getBlockState(pos.east());
        BlockState blockStateSouth = world.getBlockState(pos.south());
        BlockState blockStateWest = world.getBlockState(pos.west());
        return state.withIfExists(DOWN, giveDirectionState(state, blockStateBelow ,world, pos, Direction.UP))
                .withIfExists(UP, giveDirectionState(state, blockStateUp ,world, pos, Direction.DOWN))
                .withIfExists(NORTH, giveDirectionState(state, blockStateNorth, world, pos, Direction.SOUTH))
                .withIfExists(EAST, giveDirectionState(state, blockStateEast, world, pos, Direction.WEST))
                .withIfExists(SOUTH, giveDirectionState(state, blockStateSouth, world, pos, Direction.NORTH))
                .withIfExists(WEST, giveDirectionState(state, blockStateWest, world, pos, Direction.EAST));
    }

    private static boolean giveDirectionState(BlockState mainBlock, BlockState locationCheck, WorldAccess world, BlockPos pos, Direction OppositeOfDirectionFacing) {
        Direction directionOfMainBlock = mainBlock.get(FACING);
        // To make sure we don't set the side that is equal to the original(FACING) true
        return (directionOfMainBlock != OppositeOfDirectionFacing) && isConnectableFace(world, pos, locationCheck, mainBlock, directionOfMainBlock);
    }

    private static boolean isConnectableFace(WorldAccess world, BlockPos pos, BlockState locationCheck, BlockState mainBlock, Direction directionChecking) {
        Direction OppositeDirection = directionChecking.getOpposite();
        // Checks if the block has a solid face
        return locationCheck.isSideSolidFullSquare(world, pos, OppositeDirection) && canBeStraight(world, pos, mainBlock);
    }

    // Testing if the beam is straight
    private static boolean canBeStraight(WorldAccess world, BlockPos pos, BlockState state) {
        Direction dir = state.get(FACING);
        BlockState blockStateBelow = world.getBlockState(pos.down());
        BlockState blockStateUp = world.getBlockState(pos.up());
        BlockState blockStateNorth = world.getBlockState(pos.north());
        BlockState blockStateEast = world.getBlockState(pos.east());
        BlockState blockStateSouth = world.getBlockState(pos.south());
        BlockState blockStateWest = world.getBlockState(pos.west());
        return switch (dir) {
            case UP -> blockStateUp.isSideSolidFullSquare(world, pos, dir.getOpposite());
            case DOWN -> blockStateBelow.isSideSolidFullSquare(world, pos, dir.getOpposite());
            case NORTH -> blockStateNorth.isSideSolidFullSquare(world, pos, dir.getOpposite());
            case SOUTH -> blockStateSouth.isSideSolidFullSquare(world, pos, dir.getOpposite());
            case WEST -> blockStateWest.isSideSolidFullSquare(world, pos, dir.getOpposite());
            case EAST -> blockStateEast.isSideSolidFullSquare(world, pos, dir.getOpposite());
        };
    }

    private static boolean isBeamBlock(Block block) {
        return block instanceof BeamBlock;
    }
}
