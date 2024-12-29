package net.luckystudio.cozyhome.entity.custom;

import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.custom.abstracts.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.Box;
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


    // Runs when
    @Override
    protected void addPassenger(Entity passenger) {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getWorld().getBlockState(pos);
        if (state.getBlock() instanceof AbstractSeatBlock seatBlock) {
            passenger.setYaw(seatBlock.setRiderRotation(this));
            super.addPassenger(passenger);
        }
    }
    /**
     This method handles the unique functionality of some chairs when a player dismounts them.
     In this case it turns off the DETECTED_PLAYER and OMINOUS values in the Ominous Chair
     */
    @Override
    public void stopRiding() {
        World world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        if (world.getBlockState(pos).getBlock() == ModBlocks.OMINOUS_CHAIR) {
            BlockState state = world.getBlockState(pos);
            world.setBlockState(pos, state
                    .with(ModProperties.OMINOUS, OminousBlock.INACTIVE));
            world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_VAULT_DEACTIVATE, SoundCategory.BLOCKS, 1, 1, true);
        } else {
            super.stopRiding();
        }
    }
/**
     This method makes sure the dismount location is valid.
     This is the same as the pig class, maybe try and access it instead?
     Also, this method handles the despawning of the entity when the player dismounts
 */
    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Direction direction = this.getMovementDirection();
        if (direction.getAxis() != Direction.Axis.Y) {
            int[][] is = Dismounting.getDismountOffsets(direction);
            BlockPos blockPos = this.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (EntityPose entityPose : passenger.getPoses()) {
                Box box = passenger.getBoundingBox(entityPose);

                for (int[] js : is) {
                    mutable.set(blockPos.getX() + js[0], blockPos.getY(), blockPos.getZ() + js[1]);
                    double d = this.getWorld().getDismountHeight(mutable);
                    if (Dismounting.canDismountInBlock(d)) {
                        Vec3d vec3d = Vec3d.ofCenter(mutable, d);
                        if (Dismounting.canPlaceEntityAt(this.getWorld(), passenger, box.offset(vec3d))) {
                            passenger.setPose(entityPose);
                            this.remove(RemovalReason.DISCARDED);
                            return vec3d;
                        }
                    }
                }
            }
        }
        this.remove(RemovalReason.DISCARDED);
        return super.updatePassengerForDismount(passenger);
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        if (passenger instanceof PlayerEntity player) {
            // Get the yaw of the entity and the player
            float entityYaw = this.getYaw();
            // If the yaw difference exceeds the threshold, rotate the player's body
            passenger.setBodyYaw(entityYaw);
            // Update the player's position relative to the entity
            passenger.setPos(this.getX(), this.getY() - 0.5f + getHeightOffset(), this.getZ());
        }
    }



    private float getHeightOffset() {
        BlockPos pos = this.getBlockPos();
        BlockState state = getWorld().getBlockState(pos);
        if (state.getBlock() instanceof AbstractSeatBlock abstractSeatBlock) {
            return abstractSeatBlock.getSeatHeight(state);
        }
        return 0f;
    }

    @Override
    protected BlockPos getPosWithYOffset(float offset) {
        BlockPos pos = this.getBlockPos();
        BlockState state = getWorld().getBlockState(pos);
        if (state.getBlock() instanceof AbstractSeatBlock abstractSeatBlock) {
            return new BlockPos((int) this.getX(), (int) (this.getY() + abstractSeatBlock.getSeatHeight(state)),(int)  this.getZ());
        }
        return super.getPosWithYOffset(offset);
    }

    // This allows the seat entity to get moved by a piston
    @Override
    protected Vec3d adjustMovementForPiston(Vec3d movement) {
        return super.adjustMovementForPiston(movement);
    }
}
