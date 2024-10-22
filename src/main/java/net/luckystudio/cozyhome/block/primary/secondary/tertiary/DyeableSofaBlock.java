package net.luckystudio.cozyhome.block.primary.secondary.tertiary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.primary.secondary.DyeableSeatBlock;
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

public class DyeableSofaBlock extends DyeableSeatBlock {
    public static final MapCodec<DyeableSofaBlock> CODEC = createCodec(DyeableSofaBlock::new);
    public static final IntProperty ROTATION = Properties.ROTATION;

    public DyeableSofaBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends DyeableSofaBlock> getCodec() {
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
