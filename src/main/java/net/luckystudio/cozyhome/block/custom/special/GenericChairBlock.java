package net.luckystudio.cozyhome.block.custom.special;

import net.luckystudio.cozyhome.block.custom.AbstractChairBlock;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GenericChairBlock extends AbstractChairBlock {

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

    public GenericChairBlock(Settings settings) {
        super(settings);
    }
    // This is the hit-box of the block, we are applying our VoxelShape to it.
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
}
