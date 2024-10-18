package net.luckystudio.cozyhome.block.type;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.LinearConnectionBlock;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class WallMirrorBlock extends HorizontalFacingBlock implements Waterloggable{
    public static final MapCodec<WallMirrorBlock> CODEC = createCodec(WallMirrorBlock::new);
    public static final EnumProperty<LinearConnectionBlock> STACKABLE_BLOCK = ModProperties.LINEAR_CONNECTION;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(14, 0, 0, 16, 16, 16));
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 14, 16, 16, 16));
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 2, 16, 16));
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 16, 16, 2));

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }
    public WallMirrorBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(STACKABLE_BLOCK, LinearConnectionBlock.SINGLE)
                .with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> null;
        };
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (state.get(WATERLOGGED)) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!ctx.canReplaceExisting()) {
            BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));
            if (blockState.isOf(this) && blockState.get(FACING) == ctx.getSide()) {
                return null;
            }
        }

        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.with(FACING, direction.getOpposite());
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, STACKABLE_BLOCK, WATERLOGGED);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if(world.isClient) return;
        BlockPos blockPosAbove = pos.up();
        BlockPos blockPosBelow = pos.down();
        BlockState relativeHeadBlock = world.getBlockState(blockPosAbove);
        BlockState relativeTailBlock = world.getBlockState(blockPosBelow);
        LinearConnectionBlock LinearConnectionBlockType = getLinearConnectionBlockType(state, relativeHeadBlock, relativeTailBlock);
        BlockState updatedState = state.with(STACKABLE_BLOCK, LinearConnectionBlockType);
        world.setBlockState(pos, updatedState, 3);
    }

    private LinearConnectionBlock getLinearConnectionBlockType(BlockState state, BlockState getBlockAbove, BlockState getBlockBelow) {
        boolean above = getBlockAbove.getBlock() == state.getBlock() && getBlockAbove.get(FACING) == state.get(FACING);
        boolean below = getBlockBelow.getBlock() == state.getBlock() && getBlockBelow.get(FACING) == state.get(FACING);
        if (above && below) return LinearConnectionBlock.MIDDLE;
        if (above) return LinearConnectionBlock.TAIL;
        if (below) return LinearConnectionBlock.HEAD;
        return LinearConnectionBlock.SINGLE;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
