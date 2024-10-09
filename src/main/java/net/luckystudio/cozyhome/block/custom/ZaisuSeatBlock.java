package net.luckystudio.cozyhome.block.custom;

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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class ZaisuSeatBlock extends AbstractSeatBlock {
    public static final MapCodec<ZaisuSeatBlock> CODEC = createCodec(ZaisuSeatBlock::new);
    public static final IntProperty ROTATION = Properties.ROTATION;

    public ZaisuSeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(ROTATION, 0));
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.1f;
    }

    @Override
    protected MapCodec<? extends AbstractSeatBlock> getCodec() {
        return CODEC;
    }

    private static final VoxelShape SHAPE = ZaisuSeatBlock.createCuboidShape(2,0,2,14,2,14);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int rotation = RotationPropertyHelper.fromYaw(ctx.getPlayerYaw());
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState()
                        .with(ROTATION, rotation);
            }
        }
        return null;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }
}
