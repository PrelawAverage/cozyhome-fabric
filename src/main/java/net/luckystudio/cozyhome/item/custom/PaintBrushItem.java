package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BrushItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class PaintBrushItem extends BrushItem {

    private static final Formatting CAPTION = Formatting.GRAY;

    public PaintBrushItem(Settings settings) {
        super(settings);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks >= 0 && user instanceof PlayerEntity playerEntity) {
            HitResult hitResult = this.getHitResult(playerEntity);
            if (hitResult instanceof BlockHitResult blockHitResult && hitResult.getType() == HitResult.Type.BLOCK) {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks + 1;
                boolean bl = i % 10 == 5;
                if (bl) {
                    BlockPos blockPos = blockHitResult.getBlockPos();
                    BlockState blockState = world.getBlockState(blockPos);
                    Arm arm = user.getActiveHand() == Hand.MAIN_HAND ? playerEntity.getMainArm() : playerEntity.getMainArm().getOpposite();
                    if (blockState.hasBlockBreakParticles() && blockState.getRenderType() != BlockRenderType.INVISIBLE) {
                        this.addDustParticles(world, blockHitResult, blockState, user.getRotationVec(0.0F), arm);
                    }

                    SoundEvent soundEvent;
                    if (blockState.getBlock() instanceof BrushableBlock brushableBlock) {
                        soundEvent = brushableBlock.getBrushingSound();
                    } else {
                        soundEvent = SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC;
                    }

                    world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                    if (!world.isClient() && world.getBlockEntity(blockPos) instanceof BrushableBlockEntity brushableBlockEntity) {
                        boolean bl2 = brushableBlockEntity.brush(world.getTime(), playerEntity, blockHitResult.getSide());
                        if (bl2) {
                            EquipmentSlot equipmentSlot = stack.equals(playerEntity.getEquippedStack(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                            stack.damage(1, user, equipmentSlot);
                        }
                    }
                }

                return;
            }
            user.stopUsingItem();
        } else {
            user.stopUsingItem();
        }
    }

    private HitResult getHitResult(PlayerEntity user) {
        return ProjectileUtil.getCollision(user, entity -> !entity.isSpectator() && entity.canHit(), user.getBlockInteractionRange());
    }

    private void addDustParticles(World world, BlockHitResult hitResult, BlockState state, Vec3d userRotation, Arm arm) {
        double d = 3.0;
        int i = arm == Arm.RIGHT ? 1 : -1;
        int j = world.getRandom().nextBetweenExclusive(7, 12);
        BlockStateParticleEffect blockStateParticleEffect = new BlockStateParticleEffect(ParticleTypes.BLOCK, state);
        Direction direction = hitResult.getSide();
        PaintBrushItem.DustParticlesOffset dustParticlesOffset = PaintBrushItem.DustParticlesOffset.fromSide(userRotation, direction);
        Vec3d vec3d = hitResult.getPos();

        for (int k = 0; k < j; k++) {
            world.addParticle(
                    blockStateParticleEffect,
                    vec3d.x - (double)(direction == Direction.WEST ? 1.0E-6F : 0.0F),
                    vec3d.y,
                    vec3d.z - (double)(direction == Direction.NORTH ? 1.0E-6F : 0.0F),
                    dustParticlesOffset.xd() * (double)i * 3.0 * world.getRandom().nextDouble(),
                    0.0,
                    dustParticlesOffset.zd() * (double)i * 3.0 * world.getRandom().nextDouble()
            );
        }
    }
    record DustParticlesOffset(double xd, double yd, double zd) {

        public static PaintBrushItem.DustParticlesOffset fromSide(Vec3d userRotation, Direction side) {
            double d = 0.0;

            return switch (side) {
                case DOWN, UP -> new PaintBrushItem.DustParticlesOffset(userRotation.getZ(), 0.0, -userRotation.getX());
                case NORTH -> new PaintBrushItem.DustParticlesOffset(1.0, 0.0, -0.1);
                case SOUTH -> new PaintBrushItem.DustParticlesOffset(-1.0, 0.0, 0.1);
                case WEST -> new PaintBrushItem.DustParticlesOffset(-0.1, 0.0, -1.0);
                case EAST -> new PaintBrushItem.DustParticlesOffset(0.1, 0.0, 1.0);
            };
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.on_interacted_with_dyeable_block").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.sets_block_color")));
    }
}
