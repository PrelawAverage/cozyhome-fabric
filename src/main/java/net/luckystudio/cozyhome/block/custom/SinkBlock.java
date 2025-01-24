package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SinkBlock extends AbstractSinkBlock {
    public static final MapCodec<SinkBlock> CODEC = createCodec(SinkBlock::new);

    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(1, 8, 1, 15, 14, 15));

    public SinkBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.combine(SHAPE, Block.createCuboidShape(3, 10, 3, 13, 16, 13), BooleanBiFunction.ONLY_FIRST);
    }

    @Override
    public float getWaterLevel(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 1 -> 0.688f;
            case 2 -> 0.813f;
            case 3 -> 0.938f;
            default -> 0.125f;
        };
    }
}
