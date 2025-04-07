package net.luckystudio.cozyhome.block.custom.horizontal_connecting_blocks;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class LargeStumpBlock extends AbstractHorizontalConnectingBlock implements ConnectingBlock {

    public static final MapCodec<LargeStumpBlock> CODEC = createCodec(LargeStumpBlock::new);

    @Override
    public MapCodec<LargeStumpBlock> getCodec() {
        return CODEC;
    }

    public static final VoxelShape TOP = Block.createCuboidShape(0, 10, 0, 16, 16, 16);

    public LargeStumpBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = TOP;
        shape = VoxelShapes.union(shape, Block.createCuboidShape(
                state.get(WEST) ? 0 : 4,
                0,
                state.get(NORTH) ? 0 : 4,
                state.get(EAST) ? 16 : 12,
                10,
                state.get(SOUTH) ? 16 : 12));

        // Chip away corners based on diagonal connections
        if (!state.get(NORTH_EAST)) shape = VoxelShapes.combine(shape, Block.createCuboidShape(12, 0, 0, 16, 10, 4), BooleanBiFunction.ONLY_FIRST);
        if (!state.get(NORTH_WEST)) shape = VoxelShapes.combine(shape, Block.createCuboidShape(0, 0, 0, 4, 10, 4), BooleanBiFunction.ONLY_FIRST);
        if (!state.get(SOUTH_EAST)) shape = VoxelShapes.combine(shape, Block.createCuboidShape(12, 0, 12, 16, 10, 16), BooleanBiFunction.ONLY_FIRST);
        if (!state.get(SOUTH_WEST)) shape = VoxelShapes.combine(shape, Block.createCuboidShape(0, 0, 12, 4, 10, 16), BooleanBiFunction.ONLY_FIRST);
        return shape;
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() instanceof LargeStumpBlock;
    }
}
