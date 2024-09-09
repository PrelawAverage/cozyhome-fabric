package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ChairBlock extends HorizontalFacingBlock {
    private static final VoxelShape BASE_SHAPE = ChairBlock.createCuboidShape(2,0,2,14,10,14);
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1.0, 10.0, 0.0, 5.333333, 14.0, 16.0),
            Block.createCuboidShape(5.333333, 12.0, 0.0, 9.666667, 16.0, 16.0),
            Block.createCuboidShape(9.666667, 14.0, 0.0, 14.0, 18.0, 16.0),
            BASE_SHAPE
    );
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 10.0, 1.0, 16.0, 14.0, 5.333333),
            Block.createCuboidShape(0.0, 12.0, 5.333333, 16.0, 16.0, 9.666667),
            Block.createCuboidShape(0.0, 14.0, 9.666667, 16.0, 18.0, 14.0),
            BASE_SHAPE
    );
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(10.666667, 10.0, 0.0, 15.0, 14.0, 16.0),
            Block.createCuboidShape(6.333333, 12.0, 0.0, 10.666667, 16.0, 16.0),
            Block.createCuboidShape(2.0, 14.0, 0.0, 6.333333, 18.0, 16.0),
            BASE_SHAPE
    );
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 10.0, 10.666667, 16.0, 14.0, 15.0),
            Block.createCuboidShape(0.0, 12.0, 6.333333, 16.0, 16.0, 10.666667),
            Block.createCuboidShape(0.0, 14.0, 2.0, 16.0, 18.0, 6.333333),
            BASE_SHAPE
    );
    public ChairBlock(Settings settings) {
        super(settings);
    }

    // This is the hitbox of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch ((Direction)state.get(FACING)) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return BASE_SHAPE;
        }
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
}
