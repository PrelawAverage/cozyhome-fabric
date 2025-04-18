package net.luckystudio.cozyhome.block.custom.horizontal_connecting_blocks;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.interfaces.AllSidesConnectingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHorizontalConnectingBlock extends Block implements Waterloggable, AllSidesConnectingBlock {
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty NORTH_EAST = ModProperties.NORTH_EAST;
    public static final BooleanProperty NORTH_WEST = ModProperties.NORTH_WEST;
    public static final BooleanProperty SOUTH_EAST = ModProperties.SOUTH_EAST;
    public static final BooleanProperty SOUTH_WEST = ModProperties.SOUTH_WEST;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public AbstractHorizontalConnectingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(NORTH, false)
                        .with(EAST, false)
                        .with(SOUTH, false)
                        .with(WEST, false)
                        .with(NORTH_EAST, false)
                        .with(NORTH_WEST, false)
                        .with(SOUTH_EAST, false)
                        .with(SOUTH_WEST, false)
                        .with(WATERLOGGED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST, WATERLOGGED);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        BlockPos pos = ctx.getBlockPos();
        WorldAccess world = ctx.getWorld();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        return state
                .with(NORTH, checkDirectionalNeighbor(state, Direction.NORTH, world, pos))
                .with(EAST, checkDirectionalNeighbor(state, Direction.EAST, world, pos))
                .with(SOUTH, checkDirectionalNeighbor(state, Direction.SOUTH, world, pos))
                .with(WEST, checkDirectionalNeighbor(state, Direction.WEST, world, pos))
                .with(NORTH_EAST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.EAST, world, pos))
                .with(NORTH_WEST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.WEST, world, pos))
                .with(SOUTH_EAST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.EAST, world, pos))
                .with(SOUTH_WEST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.WEST, world, pos))
                .with(WATERLOGGED, bl);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return state
                .with(NORTH, checkDirectionalNeighbor(state, Direction.NORTH, world, pos))
                .with(EAST, checkDirectionalNeighbor(state, Direction.EAST, world, pos))
                .with(SOUTH, checkDirectionalNeighbor(state, Direction.SOUTH, world, pos))
                .with(WEST, checkDirectionalNeighbor(state, Direction.WEST, world, pos))
                .with(NORTH_EAST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.EAST, world, pos))
                .with(NORTH_WEST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.WEST, world, pos))
                .with(SOUTH_EAST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.EAST, world, pos))
                .with(SOUTH_WEST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.WEST, world, pos));
    }

    private boolean checkDirectionalNeighbor(BlockState state, Direction direction, WorldAccess world, BlockPos pos) {
        BlockPos targetPos = pos.offset(direction);
        return isMatchingBlock(state, world.getBlockState(targetPos));
    }

    private boolean checkDiagonalNeighbor(BlockState state, Direction direction1, Direction direction2, WorldAccess world, BlockPos pos) {
        // Ensure both adjacent directions (e.g., NORTH and EAST) are set to true in the state
        BooleanProperty property1 = getDirectionalProperty(direction1);
        BooleanProperty property2 = getDirectionalProperty(direction2);

        if (!state.get(property1) || !state.get(property2)) return false;

        // Check the diagonal position offset by direction1 and direction2
        BlockPos targetPos = pos.offset(direction1).offset(direction2);
        return isMatchingBlock(state, world.getBlockState(targetPos));
    }

    private BooleanProperty getDirectionalProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction for diagonal neighbor check");
        };
    }

    @Override
    public boolean isMatchingBlock(BlockState state, BlockState targetState) {
        return targetState.getBlock() == this;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                return state
                        .with(NORTH, state.get(SOUTH))
                        .with(SOUTH, state.get(NORTH))
                        .with(EAST, state.get(WEST))
                        .with(WEST, state.get(EAST))
                        .with(NORTH_EAST, state.get(SOUTH_WEST))
                        .with(NORTH_WEST, state.get(SOUTH_EAST))
                        .with(SOUTH_EAST, state.get(NORTH_WEST))
                        .with(SOUTH_WEST, state.get(NORTH_EAST));
            case COUNTERCLOCKWISE_90:
                return state
                        .with(NORTH, state.get(EAST))
                        .with(SOUTH, state.get(WEST))
                        .with(EAST, state.get(SOUTH))
                        .with(WEST, state.get(NORTH))
                        .with(NORTH_EAST, state.get(SOUTH_EAST))
                        .with(SOUTH_EAST, state.get(SOUTH_WEST))
                        .with(SOUTH_WEST, state.get(NORTH_WEST))
                        .with(NORTH_WEST, state.get(NORTH_EAST));
            case CLOCKWISE_90:
                return state
                        .with(NORTH, state.get(WEST))
                        .with(SOUTH, state.get(EAST))
                        .with(EAST, state.get(NORTH))
                        .with(WEST, state.get(SOUTH))
                        .with(NORTH_EAST, state.get(NORTH_WEST))
                        .with(NORTH_WEST, state.get(SOUTH_WEST))
                        .with(SOUTH_WEST, state.get(SOUTH_EAST))
                        .with(SOUTH_EAST, state.get(NORTH_EAST));
            default:
                return state;
        }
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT:
                return state
                        .with(NORTH, state.get(SOUTH))
                        .with(SOUTH, state.get(NORTH))
                        .with(NORTH_EAST, state.get(SOUTH_EAST))
                        .with(SOUTH_EAST, state.get(NORTH_EAST))
                        .with(NORTH_WEST, state.get(SOUTH_WEST))
                        .with(SOUTH_WEST, state.get(NORTH_WEST));
            case FRONT_BACK:
                return state
                        .with(EAST, state.get(WEST))
                        .with(WEST, state.get(EAST))
                        .with(NORTH_EAST, state.get(NORTH_WEST))
                        .with(NORTH_WEST, state.get(NORTH_EAST))
                        .with(SOUTH_EAST, state.get(SOUTH_WEST))
                        .with(SOUTH_WEST, state.get(SOUTH_EAST));
            default:
                return super.mirror(state, mirror);
        }
    }
}
