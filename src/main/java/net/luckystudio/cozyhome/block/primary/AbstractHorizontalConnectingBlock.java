package net.luckystudio.cozyhome.block.primary;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHorizontalConnectingBlock extends Block {
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;
    public AbstractHorizontalConnectingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(NORTH, false)
                        .with(EAST, false)
                        .with(SOUTH, false)
                        .with(WEST, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        System.out.println(ModProperties.CONTAINS);
        return this.getDefaultState()
                .with(NORTH, checkDirection(ctx.getWorld(), ctx.getBlockPos(), Direction.NORTH))
                .with(EAST, checkDirection(ctx.getWorld(), ctx.getBlockPos(), Direction.EAST))
                .with(SOUTH, checkDirection(ctx.getWorld(), ctx.getBlockPos(), Direction.SOUTH))
                .with(WEST, checkDirection(ctx.getWorld(), ctx.getBlockPos(), Direction.WEST));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state
                .with(NORTH, checkDirection(world, pos, Direction.NORTH))
                .with(EAST, checkDirection(world, pos, Direction.EAST))
                .with(SOUTH, checkDirection(world, pos, Direction.SOUTH))
                .with(WEST, checkDirection(world, pos, Direction.WEST));
    }

    private boolean checkDirection(WorldAccess world, BlockPos pos, Direction direction) {
        return world.getBlockState(pos.offset(direction)).getBlock() instanceof AbstractHorizontalConnectingBlock;
    }
}
