package net.luckystudio.cozyhome.block.abstracts;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.blocks.TuckableBlock;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AbstractTuckableBlock extends AbstractSeatBlock implements TuckableBlock{
    public static final MapCodec<AbstractTuckableBlock> CODEC = createCodec(AbstractTuckableBlock::new);

    @Override
    public MapCodec<AbstractTuckableBlock> getCodec() {
        return CODEC;
    }

    public AbstractTuckableBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;
        if (TuckableBlock.isFacingDirection(state, world, pos)) {
            ActionResult actionResult = TuckableBlock.tryTuck(state, world, pos, player);
            if (!actionResult.equals(ActionResult.PASS)) return actionResult;
        }
        return super.onUse(state, world, pos, player, hit);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TUCKED);
    }
}
