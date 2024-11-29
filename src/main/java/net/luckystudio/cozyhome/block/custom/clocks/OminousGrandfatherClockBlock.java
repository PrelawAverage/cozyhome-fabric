package net.luckystudio.cozyhome.block.custom.clocks;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.luckystudio.cozyhome.block.util.enums.TripleTallBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OminousGrandfatherClockBlock extends GrandfatherClockBlock {
    public static final EnumProperty<OminousBlock> OMINOUS = ModProperties.OMINOUS;

    public OminousGrandfatherClockBlock(GrandfatherClockType grandfatherClockType, Settings settings) {
        super(grandfatherClockType, settings);
        this.stateManager.getDefaultState()
                .with(OMINOUS, OminousBlock.INACTIVE);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(OMINOUS);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(OMINOUS, OminousBlock.INACTIVE);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), withWaterloggedState(world, pos.up(), this.getDefaultState()
                .with(TRIPLE_TALL_BLOCK, TripleTallBlock.MIDDLE)
                .with(ROTATION, state.get(ROTATION))
                .with(OMINOUS, OminousBlock.INACTIVE)), Block.NOTIFY_ALL);
        world.setBlockState(pos.up(2), withWaterloggedState(world, pos.up(2), this.getDefaultState()
                .with(TRIPLE_TALL_BLOCK, TripleTallBlock.TOP)
                .with(ROTATION, state.get(ROTATION))
                .with(OMINOUS, OminousBlock.INACTIVE)), Block.NOTIFY_ALL);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.hasStatusEffect(StatusEffects.BAD_OMEN)) {
            player.sendMessage(Text.literal("§3☠☠☠"), true);
            player.removeStatusEffect(StatusEffects.BAD_OMEN);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.TRIAL_OMEN, 200), null);
            world.setBlockState(pos, state.with(OMINOUS, OminousBlock.OMINOUS));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hit);
    }
}