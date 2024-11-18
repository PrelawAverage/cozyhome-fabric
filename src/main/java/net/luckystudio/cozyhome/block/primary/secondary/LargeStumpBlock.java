package net.luckystudio.cozyhome.block.primary.secondary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.primary.AbstractAllSidesConnectingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class LargeStumpBlock extends AbstractAllSidesConnectingBlock {

    public static final VoxelShape TOP = Block.createCuboidShape(0, 10, 0, 16, 16, 16);
    public static final VoxelShape BASE = Block.createCuboidShape(4, 0, 4, 12, 16, 12);
    public static final VoxelShape SHAPE = VoxelShapes.union(TOP, BASE);

    public LargeStumpBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
        );
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
