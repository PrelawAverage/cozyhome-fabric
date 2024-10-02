package net.luckystudio.cozyhome.entity.custom;

import net.luckystudio.cozyhome.block.util.blocks.SeatBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientCommonPacketListener;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

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

    @Override
    protected boolean canStartRiding(Entity entity) {
        return true;
    }

    @Override
    protected void addPassenger(Entity passenger) {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getWorld().getBlockState(pos);
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            passenger.setYaw(seatBlock.setRiderRotation(state, this));
            super.addPassenger(passenger);
        }
    }

    @Override
    public Vec3d getPassengerRidingPos(Entity passenger) {
        return super.getPassengerRidingPos(passenger);
    }

    //    @Override
//    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
//        if (this.hasPassenger(passenger)) {
//            // Update passenger position and rotation
//            passenger.setBodyYaw(this.getYaw());
//            passenger.setPos(this.getX(), this.getY(), this.getZ());
//        }
//    }

    // This method makes sure the dismount location is valid.
    // This is the same as the pig class, maybe try and access it instead?
    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Direction direction = this.getMovementDirection();
        if (direction.getAxis() == Direction.Axis.Y) {
            this.remove(RemovalReason.DISCARDED);
            return super.updatePassengerForDismount(passenger);
        } else {
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
            this.remove(RemovalReason.DISCARDED);
            return super.updatePassengerForDismount(passenger);
        }
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        if (passenger instanceof PlayerEntity player) {
            // Get the yaw of the entity and the player
            float entityYaw = this.getYaw();

            // If the yaw difference exceeds the threshold, rotate the player's body
            passenger.setBodyYaw(entityYaw);

            // Update the player's position relative to the entity
            passenger.setPos(this.getX(), this.getY(), this.getZ());
        }
    }

    // This allows the seat entity to get moved by a piston
    @Override
    protected Vec3d adjustMovementForPiston(Vec3d movement) {
        return super.adjustMovementForPiston(movement);
    }
}
