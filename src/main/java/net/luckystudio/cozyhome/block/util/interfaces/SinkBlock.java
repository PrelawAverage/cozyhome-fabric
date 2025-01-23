package net.luckystudio.cozyhome.block.util.interfaces;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.custom.counters.AbstractSinkBlock;
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

    public static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(1, 8, 1, 15, 14, 15),
            BooleanBiFunction.AND);

    public SinkBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
