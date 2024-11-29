package net.luckystudio.cozyhome.block.custom.chairs;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OminousChairBlock extends ChairBlock {
    public static final EnumProperty<OminousBlock> OMINOUS = ModProperties.OMINOUS;
    public OminousChairBlock(ChairType chairType, Settings settings) {
        super(chairType, settings);
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
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && entity.hasVehicle()) {
            boolean isPlayerOminous = livingEntity.hasStatusEffect(StatusEffects.BAD_OMEN);
            boolean isStateOminous = state.get(OMINOUS) == OminousBlock.OMINOUS;
            // Play ominous sound if the entity has BAD_OMEN effect and the block state is not ominous
            if (isPlayerOminous && !isStateOminous) {
                world.setBlockState(pos, state.with(OMINOUS, OminousBlock.OMINOUS), 3);
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, SoundCategory.BLOCKS, 1, 1, true);
            } else if (state.get(OMINOUS) != OminousBlock.ACTIVE) {
                world.setBlockState(pos, state.with(OMINOUS, OminousBlock.ACTIVE), 3);
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_VAULT_ACTIVATE, SoundCategory.BLOCKS, 1, 1, true);
            }
            // Apply damage and sound if the block is ominous
            if (isStateOminous) {
                if (livingEntity.hurtTime <= 0) {
                    entity.damage(world.getDamageSources().magic(), 2.0F);
                    world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH, SoundCategory.BLOCKS, 1, 1, true);
                }
            }
        }
    }
}
