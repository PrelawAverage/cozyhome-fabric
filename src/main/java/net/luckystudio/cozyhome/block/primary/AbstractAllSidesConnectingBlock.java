package net.luckystudio.cozyhome.block.primary;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractAllSidesConnectingBlock extends Block {
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty NORTH_EAST = ModProperties.NORTH_EAST;
    public static final BooleanProperty NORTH_WEST = ModProperties.NORTH_WEST;
    public static final BooleanProperty SOUTH_EAST = ModProperties.SOUTH_EAST;
    public static final BooleanProperty SOUTH_WEST = ModProperties.SOUTH_WEST;
    public AbstractAllSidesConnectingBlock(Settings settings) {
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
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        BlockPos pos = ctx.getBlockPos();
        WorldAccess world = ctx.getWorld();

        return state
                .with(NORTH, checkDirectionalNeighbor(Direction.NORTH, world, pos))
                .with(EAST, checkDirectionalNeighbor(Direction.EAST, world, pos))
                .with(SOUTH, checkDirectionalNeighbor(Direction.SOUTH, world, pos))
                .with(WEST, checkDirectionalNeighbor(Direction.WEST, world, pos))
                .with(NORTH_EAST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.EAST, world, pos))
                .with(NORTH_WEST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.WEST, world, pos))
                .with(SOUTH_EAST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.EAST, world, pos))
                .with(SOUTH_WEST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.WEST, world, pos));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state
                .with(NORTH, checkDirectionalNeighbor(Direction.NORTH, world, pos))
                .with(EAST, checkDirectionalNeighbor(Direction.EAST, world, pos))
                .with(SOUTH, checkDirectionalNeighbor(Direction.SOUTH, world, pos))
                .with(WEST, checkDirectionalNeighbor(Direction.WEST, world, pos))
                .with(NORTH_EAST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.EAST, world, pos))
                .with(NORTH_WEST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.WEST, world, pos))
                .with(SOUTH_EAST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.EAST, world, pos))
                .with(SOUTH_WEST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.WEST, world, pos));
    }

    private boolean checkDirectionalNeighbor(Direction direction, WorldAccess world, BlockPos pos) {
        BlockPos targetPos = pos.offset(direction);
        return isMatchingBlock(world.getBlockState(targetPos));
    }

    private boolean checkDiagonalNeighbor(BlockState state, Direction direction1, Direction direction2, WorldAccess world, BlockPos pos) {
        // Ensure both adjacent directions (e.g., NORTH and EAST) are set to true in the state
        BooleanProperty property1 = getDirectionalProperty(direction1);
        BooleanProperty property2 = getDirectionalProperty(direction2);

        if (!state.get(property1) || !state.get(property2)) return false;

        // Check the diagonal position offset by direction1 and direction2
        BlockPos targetPos = pos.offset(direction1).offset(direction2);
        return isMatchingBlock(world.getBlockState(targetPos));
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

    private boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() instanceof AbstractAllSidesConnectingBlock;
    }
}
