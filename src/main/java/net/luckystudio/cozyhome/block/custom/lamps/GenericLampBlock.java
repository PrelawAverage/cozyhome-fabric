package net.luckystudio.cozyhome.block.custom.lamps;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GenericLampBlock extends AbstractLampBlock {
    public static final MapCodec<GenericLampBlock> CODEC = createCodec(GenericLampBlock::new);
    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(4, 4, 4, 12, 14, 12);
    public static final VoxelShape BOTTOM_PIECE = Block.createCuboidShape(4, 0, 4, 12, 2, 12);

    public static final VoxelShape SINGLE_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 14, 12);
    public static final VoxelShape TOP_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(6, 0, 6, 10, 4, 10));
    public static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(6, 0, 6, 10, 16, 10);
    public static final VoxelShape BOTTOM_SHAPE = VoxelShapes.union(BOTTOM_PIECE, Block.createCuboidShape(6, 2, 6, 10, 16, 10));

    public GenericLampBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(CONNECTION)) {
            case HEAD -> TOP_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            case TAIL -> BOTTOM_SHAPE;
            default -> SINGLE_SHAPE;
        };
    }
}
