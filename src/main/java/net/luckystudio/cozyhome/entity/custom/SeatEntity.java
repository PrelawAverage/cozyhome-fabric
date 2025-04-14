package net.luckystudio.cozyhome.entity.custom;

import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.custom.bathtub.BathTubBlock;
import net.luckystudio.cozyhome.block.custom.telescope.TelescopeBlock;
import net.luckystudio.cozyhome.block.custom.telescope.TelescopeBlockEntity;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class SeatEntity extends Entity {

    public SeatEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        return ActionResult.PASS;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    // Can player ride entity
    @Override
    protected boolean canStartRiding(Entity entity) {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        World world = this.getWorld();
        Entity entity = this.getFirstPassenger();
        // Delete the entity if no player is riding it
        if (entity instanceof LivingEntity livingEntity) {
            if (world.getBlockState(getBlockPos()).getBlock() instanceof BathTubBlock && world.getBlockState(getBlockPos().down()).getBlock() == Blocks.MAGMA_BLOCK) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0)); // 3 seconds (60 ticks), level 1
            }
            // Aiming the telescope
            if (isOffsettingBlock()) {
                if (this.getWorld().getBlockEntity(this.getBlockPos()) instanceof TelescopeBlockEntity telescopeBlockEntity) {
                    BlockState telescopeBlockState = this.getWorld().getBlockState(this.getBlockPos());
                    telescopeBlockEntity.setYaw(livingEntity.getYaw() + 90);
                    telescopeBlockEntity.setPitch(-livingEntity.getPitch());
                    TelescopeBlock.isFacingMoon(world, telescopeBlockState, getBlockPos(), livingEntity.getYaw(), -livingEntity.getPitch());
                    telescopeBlockEntity.markDirty();
                }
            }
        }
    }

    // Runs when
    @Override
    protected void addPassenger(Entity passenger) {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getWorld().getBlockState(pos);
        if (state.getBlock() instanceof SeatBlock) {
            passenger.setYaw(this.getYaw());
            super.addPassenger(passenger);
        }
    }
/**
     This method makes sure the dismount location is valid.
     This is the same as the pig class, maybe try and access it instead?
     Also, this method handles the despawning of the entity when the player dismounts
 */
    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        this.remove(RemovalReason.DISCARDED);
        return super.updatePassengerForDismount(passenger);
    }

    @Override
    public void remove(RemovalReason reason) {
        World world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        if (world.getBlockState(pos).getBlock() instanceof SeatBlock) {
            BlockState state = world.getBlockState(pos);
            world.setBlockState(pos, state.with(Properties.TRIGGERED, false));
        }
        super.remove(reason);
    }

    @Override
    protected Vec3d getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        Vec3d attachmentPoint = super.getPassengerAttachmentPos(passenger, dimensions, scaleFactor);
        if (passenger instanceof PlayerEntity) {
            float yaw = passenger.getYaw();
            if (Float.isNaN(yaw)) yaw = 0.0F;

            float riderYaw = yaw + 180.0F;
            double radians = Math.toRadians(riderYaw);

            double offsetX = isOffsettingBlock() ? -Math.sin(radians) : 0.0;
            double offsetZ = isOffsettingBlock() ? Math.cos(radians) : 0.0;

            double yOffset = getHeightOffset();

            // Apply the offset
            return attachmentPoint.add(offsetX, yOffset - 1, offsetZ);
        }
        return super.getPassengerAttachmentPos(passenger, dimensions, scaleFactor);
    }

    private float getHeightOffset() {
        BlockPos pos = this.getBlockPos();
        BlockState state = getWorld().getBlockState(pos);
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            return seatBlock.getSeatHeight(state);
        }
        return 0f;
    }

    // This allows the seat entity to get moved by a piston
    @Override
    protected Vec3d adjustMovementForPiston(Vec3d movement) {
        return super.adjustMovementForPiston(movement);
    }

    private boolean isOffsettingBlock() {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getWorld().getBlockState(pos);
        return state.getBlock() == ModBlocks.TELESCOPE;
    }
}
