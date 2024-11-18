package net.luckystudio.cozyhome.block.primary.secondary.tertiary;

import net.luckystudio.cozyhome.block.primary.secondary.ChairBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OminousChairBlock extends ChairBlock {
    public static final BooleanProperty DETECTED_PLAYER = ModProperties.DETECTED_PLAYER;
    public static final BooleanProperty OMINOUS = Properties.OMINOUS;
    public OminousChairBlock(ChairType chairType, Settings settings) {
        super(chairType, settings);
        this.stateManager.getDefaultState()
                .with(DETECTED_PLAYER, false)
                .with(OMINOUS, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DETECTED_PLAYER, OMINOUS);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(DETECTED_PLAYER, false)
                .with(OMINOUS, false);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && entity.hasVehicle()) {
            boolean isOminous = livingEntity.hasStatusEffect(StatusEffects.BAD_OMEN);
            boolean stateOminous = state.get(OMINOUS);
            boolean stateDetected = state.get(DETECTED_PLAYER);
            // Play ominous sound if the entity has BAD_OMEN effect and the block state is not ominous
            if (isOminous && !stateOminous) {
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, SoundCategory.BLOCKS, 1, 1, true);
            } else if (!stateDetected) {
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_VAULT_ACTIVATE, SoundCategory.BLOCKS, 1, 1, true);
            }
            // Update block state with detected player and ominous values
            world.setBlockState(pos, state.with(DETECTED_PLAYER, true).with(OMINOUS, isOminous));
            // Apply damage and sound if the block is ominous
            if (stateOminous) {
                if (livingEntity.hurtTime <= 0) {
                    entity.damage(world.getDamageSources().magic(), 2.0F);
                    world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH, SoundCategory.BLOCKS, 1, 1, true);
                }
            }
        }
    }
}
