package net.luckystudio.cozyhome.block.custom.counters;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SinkCounterBlock extends AbstractSinkBlock {
    public static final MapCodec<SinkCounterBlock> CODEC = createCodec(SinkCounterBlock::new);

    public SinkCounterBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends SinkCounterBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);
        return VoxelShapes.combine(
                VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(
                        direction == Direction.EAST ? 2 : 0,
                        0,
                        direction == Direction.SOUTH ? 2 : 0,
                        direction == Direction.WEST ? 14 : 16,
                        12,
                        direction == Direction.NORTH ? 14 : 16)),
                Block.createCuboidShape(3, 2, 3, 13, 16, 13),
                BooleanBiFunction.ONLY_FIRST);
    }
}
