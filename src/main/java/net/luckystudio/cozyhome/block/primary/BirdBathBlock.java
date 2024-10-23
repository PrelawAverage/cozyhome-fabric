package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class BirdBathBlock extends HorizontalFacingBlock {
    public static final MapCodec<BirdBathBlock> CODEC = createCodec(BirdBathBlock::new);
    public static final BooleanProperty CONTAINS = ModProperties.CONTAINS;

    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(0, 10, 0, 16, 16, 16);
    public static final VoxelShape TOP_PIECE_VOID = Block.createCuboidShape(2, 14, 2, 14, 16, 14);
    public static final VoxelShape TOP = VoxelShapes.combine(TOP_PIECE, TOP_PIECE_VOID, BooleanBiFunction.ONLY_FIRST);
    public static final VoxelShape MIDDLE = Block.createCuboidShape(4, 2, 4, 12, 10, 12);
    public static final VoxelShape BASE = Block.createCuboidShape(2, 0, 2, 14, 2, 14);
    public static final VoxelShape SHAPE = VoxelShapes.union(TOP, MIDDLE, BASE);

    public BirdBathBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(CONTAINS, false));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONTAINS);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing());
        return blockState.with(CONTAINS, false);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }
}
