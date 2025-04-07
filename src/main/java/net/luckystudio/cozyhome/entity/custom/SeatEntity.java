package net.luckystudio.cozyhome.entity.custom;

import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.custom.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.custom.telescope.TelescopeBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
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

    @Override
    public void tick() {
        super.tick();
        World world = this.getWorld();
        Entity entity = this.getFirstPassenger();
        if (!world.isClient) {
            if (entity == null) {
                this.remove(RemovalReason.DISCARDED);
            } else {
                if (isTelescopeBlock()) {
                    if (this.getWorld().getBlockEntity(this.getBlockPos()) instanceof TelescopeBlockEntity telescopeBlockEntity && entity != null) {
                        telescopeBlockEntity.setYaw(entity.getYaw() - 90);
                        telescopeBlockEntity.setPitch(-entity.getPitch());
                        telescopeBlockEntity.markDirty();
                    }
                }
            }
        }
    }

    // Runs when
    @Override
    protected void addPassenger(Entity passenger) {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getWorld().getBlockState(pos);
        if (state.getBlock() instanceof AbstractSeatBlock || isTelescopeBlock()) {
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
                            World world = this.getWorld();
                            BlockPos pos = this.getBlockPos();
                            if (world.getBlockState(pos).getBlock() instanceof AbstractSeatBlock) {
                                BlockState state = world.getBlockState(pos);
                                world.setBlockState(pos, state.with(Properties.TRIGGERED, false));
                            }
                            this.remove(RemovalReason.DISCARDED);
                            return vec3d;
                        }
                    }
                }
            }
        }
        World world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        if (world.getBlockState(pos).getBlock() instanceof AbstractSeatBlock) {
            BlockState state = world.getBlockState(pos);
            world.setBlockState(pos, state.with(Properties.TRIGGERED, false));
        }
        this.remove(RemovalReason.DISCARDED);
        return super.updatePassengerForDismount(passenger);
    }

    @Override
    public void remove(RemovalReason reason) {
        World world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        if (world.getBlockState(pos).getBlock() instanceof AbstractSeatBlock) {
            BlockState state = world.getBlockState(pos);
            world.setBlockState(pos, state.with(Properties.TRIGGERED, false));
        }
        super.remove(reason);
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        super.updatePassengerPosition(passenger, positionUpdater);
        if (passenger instanceof PlayerEntity) {
            // Update the player's position relative to the entity
            float riderYaw = passenger.getYaw() + 180; // Degrees
            double radians = Math.toRadians(riderYaw);
            double offsetX = isTelescopeBlock() ? -Math.sin(radians) : 0;
            double offsetZ = isTelescopeBlock() ? Math.cos(radians) : 0;
            passenger.setPos(this.getX() + offsetX, this.getY() - 0.5F + getHeightOffset(), this.getZ() + offsetZ);
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

    // This allows the seat entity to get moved by a piston
    @Override
    protected Vec3d adjustMovementForPiston(Vec3d movement) {
        return super.adjustMovementForPiston(movement);
    }

    private boolean isTelescopeBlock() {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getWorld().getBlockState(pos);
        return state.getBlock() == ModBlocks.TELESCOPE;
    }
}
