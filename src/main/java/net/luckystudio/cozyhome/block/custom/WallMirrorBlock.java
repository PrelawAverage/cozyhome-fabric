package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.CozyHomeClient;
import net.luckystudio.cozyhome.block.entity.StorageCounterBlockEntity;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.luckystudio.cozyhome.screen.MirrorScreen;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
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
    public static final EnumProperty<VerticalLinearConnectionBlock> STACKABLE_BLOCK = ModProperties.VERTICAL_CONNECTION;
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

    public WallMirrorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(STACKABLE_BLOCK, VerticalLinearConnectionBlock.SINGLE)
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, STACKABLE_BLOCK, WATERLOGGED);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.offset(state.get(FACING).getOpposite())).isSolid();
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();

        for (Direction direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }
        return null;
    }
    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        // Check if the block can remain in place
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }

        // Schedule fluid tick if waterlogged
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        // Update stackable state
        updateStackableState(world, pos, state);

        // Return updated state
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    // Helper method to update the stackable state
    private void updateStackableState(WorldAccess world, BlockPos pos, BlockState state) {
        BlockPos blockPosAbove = pos.up();
        BlockPos blockPosBelow = pos.down();

        BlockState relativeHeadBlock = world.getBlockState(blockPosAbove);
        BlockState relativeTailBlock = world.getBlockState(blockPosBelow);

        VerticalLinearConnectionBlock linearConnectionBlockType = getLinearConnectionBlockType(state, relativeHeadBlock, relativeTailBlock);
        BlockState updatedState = state.with(STACKABLE_BLOCK, linearConnectionBlockType);

        world.setBlockState(pos, updatedState, 3); // Use flags for block updates
    }

    // Determines the type of connection based on neighbors
    private VerticalLinearConnectionBlock getLinearConnectionBlockType(BlockState state, BlockState blockAbove, BlockState blockBelow) {
        boolean above = blockAbove.getBlock() == state.getBlock() && blockAbove.get(FACING) == state.get(FACING);
        boolean below = blockBelow.getBlock() == state.getBlock() && blockBelow.get(FACING) == state.get(FACING);

        if (above && below) return VerticalLinearConnectionBlock.MIDDLE;
        if (above) return VerticalLinearConnectionBlock.TAIL;
        if (below) return VerticalLinearConnectionBlock.HEAD;
        return VerticalLinearConnectionBlock.SINGLE;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

//    @Override
//    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//        boolean type = state.get(STACKABLE_BLOCK) != VerticalLinearConnectionBlock.SINGLE;
//        // Check if the world is client-side
//        if (world.isClient()) {
//            // This is safe to call only on the client-side
//            MinecraftClient.getInstance().setScreen(new MirrorScreen(player, type));
//            return ActionResult.CONSUME;
//        }
//        // If it's the server-side, we don't perform client actions
//        return ActionResult.SUCCESS;
//    }
}
