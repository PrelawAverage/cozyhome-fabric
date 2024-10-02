package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.blocks.SeatBlock;
import net.luckystudio.cozyhome.block.util.blocks.TuckableBlock;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChairBlock extends SeatBlock implements TuckableBlock {

    private static final VoxelShape BASE_SHAPE = ChairBlock.createCuboidShape(2,0,2,14,10,14);
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

    public ChairBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(TUCKED, Boolean.FALSE));
    }

    // This is the hitbox of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                if (state.get(TUCKED)) {
                    return TUCKED_NORTH;
                } else {
                    return NORTH_SHAPE;
                }
            case EAST:
                if (state.get(TUCKED)) {
                    return TUCKED_EAST;
                } else {
                    return EAST_SHAPE;
                }
            case SOUTH:
                if (state.get(TUCKED)) {
                    return TUCKED_SOUTH;
                } else {
                    return SOUTH_SHAPE;
                }
            case WEST:
                if (state.get(TUCKED)) {
                    return TUCKED_WEST;
                } else {
                    return WEST_SHAPE;
                }
            default:
                return BASE_SHAPE;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(TUCKED, false);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;
        ActionResult actionResult = TuckableBlock.tryTuck(state, world, pos, player);

        if (!actionResult.equals(ActionResult.PASS)) return actionResult;

        this.rotate(state, BlockRotation.CLOCKWISE_180);

        return super.onUse(state, world, pos, player, hit);
    }

    // This is needed or the block will just be invisible
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TUCKED);
    }
}
