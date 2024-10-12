package net.luckystudio.cozyhome.block.type;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.abstracts.AbstractSeatBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class SofaBlock extends AbstractSeatBlock {
    public static final MapCodec<SofaBlock> CODEC = createCodec(SofaBlock::new);
    public static final IntProperty ROTATION = Properties.ROTATION;

    public SofaBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends AbstractSeatBlock> getCodec() {
        return null;
    }

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.4f;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw()));
    }
}
