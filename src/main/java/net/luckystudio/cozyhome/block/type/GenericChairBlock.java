package net.luckystudio.cozyhome.block.type;

import net.luckystudio.cozyhome.block.abstracts.AbstractTuckableBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.blockstates.CoveredBlock;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class GenericChairBlock extends AbstractTuckableBlock implements Waterloggable {
    public static final BooleanProperty TUCKED = ModProperties.TUCKED;
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<CoveredBlock> COVER = ModProperties.COVER;

    public GenericChairBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(ROTATION, 0)
                .with(TUCKED, false)
                .with(COVER, CoveredBlock.NONE)
                .with(WATERLOGGED, Boolean.FALSE)
        );
    }

    private static final VoxelShape BASE_SHAPE = GenericChairBlock.createCuboidShape(2,0,2,14,10,14);
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 10, 12, 14, 24, 14),
            BASE_SHAPE);
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 10, 2, 4, 24, 14),
            BASE_SHAPE);
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 10, 2, 14, 24, 4),
            BASE_SHAPE);
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(12, 10, 2, 14, 24, 14),
            BASE_SHAPE);
    public static final VoxelShape TUCKED_NORTH = VoxelShapes.union(
            Block.createCuboidShape(2, 0, -8, 14, 10, 4),
            Block.createCuboidShape(2, 10, 2, 14, 24, 4));
    public static final VoxelShape TUCKED_EAST = VoxelShapes.union(
            Block.createCuboidShape(12, 0, 2, 24, 10, 14),
            Block.createCuboidShape(12, 10, 2, 14, 24, 14));
    public static final VoxelShape TUCKED_SOUTH = VoxelShapes.union(
            Block.createCuboidShape(2, 0, 12, 14, 10, 24),
            Block.createCuboidShape(2, 10, 12, 14, 24, 14));
    public static final VoxelShape TUCKED_WEST = VoxelShapes.union(
            Block.createCuboidShape(-8, 0, 2, 4, 10, 14),
            Block.createCuboidShape(2, 10, 2, 4, 24, 14));

    // Will use these later, maybe
    public static final VoxelShape ROT_1 = VoxelShapes.union(
            Block.createCuboidShape(9, 0, 13, 12, 24, 16),
            Block.createCuboidShape(6, 0, 12, 9, 24, 14),
            Block.createCuboidShape(3, 0, 11, 6, 24, 13),
            Block.createCuboidShape(0, 0, 9, 3, 24, 12),
            BASE_SHAPE);
    public static final VoxelShape ROT_2 = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 7, 2, 24, 9),
            Block.createCuboidShape(7, 0, 14, 9, 24, 16),
            Block.createCuboidShape(6, 0, 13, 7, 24, 14),
            Block.createCuboidShape(5, 0, 12, 6, 24, 13),
            Block.createCuboidShape(4, 0, 11, 5, 24, 12),
            Block.createCuboidShape(2, 0, 9, 3, 24, 10),
            Block.createCuboidShape(3, 0, 10, 4, 24, 11),
            BASE_SHAPE);

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(ROTATION)) {
            case 0:
                if (state.get(TUCKED)) {
                    return TUCKED_NORTH;
                } else {
                    return NORTH_SHAPE;
                }
            case 4:
                if (state.get(TUCKED)) {
                    return TUCKED_EAST;
                } else {
                    return EAST_SHAPE;
                }
            case 8:
                if (state.get(TUCKED)) {
                    return TUCKED_SOUTH;
                } else {
                    return SOUTH_SHAPE;
                }
            case 12:
                if (state.get(TUCKED)) {
                    return TUCKED_WEST;
                } else {
                    return WEST_SHAPE;
                }
            case null, default:
                return BASE_SHAPE;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int rotation = RotationPropertyHelper.fromYaw(ctx.getPlayerYaw());
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState()
                        .with(ROTATION, rotation)
                        .with(TUCKED, false)
                        .with(COVER, CoveredBlock.NONE)
                        .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
        }
        return null;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, TUCKED, COVER, WATERLOGGED);
    }
}
