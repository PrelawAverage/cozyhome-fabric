package net.luckystudio.cozyhome.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeatEntity extends Entity {

    public SeatEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

//    @Override
//    public ActionResult interact(PlayerEntity player, Hand hand) {
//        if (player.shouldCancelInteraction()) {
//            return ActionResult.PASS;
//        } else if (this.hasPassengers()) {
//            return ActionResult.PASS;
//        } else if (!this.getWorld().isClient) {
//            return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
//        } else {
//            return ActionResult.SUCCESS;
//        }
//    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
