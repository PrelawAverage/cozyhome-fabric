package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface SeatBlock {
    static void sitDown(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        // Creates a new entity
        SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
        // Sets it's location
        seat.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);

        seat.setYaw(setSeatRotation(world, pos));
        seat.setAngles(setSeatRotation(world, pos), 0);

        world.spawnEntity(seat);

        player.startRiding(seat);
    }
    private static float setSeatRotation(World world, BlockPos pos) {
        BlockState seatBlock = world.getBlockState(pos);
        return (seatBlock.get(Properties.ROTATION) * 22.5f) + 180f;
    }
    static float setRiderRotation(Entity entity) {
        return entity.getYaw();
    }

}
