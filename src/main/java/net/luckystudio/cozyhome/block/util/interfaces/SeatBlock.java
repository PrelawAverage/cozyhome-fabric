package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface SeatBlock {

        // This method is used to get the rotation of the player when sitting on the seat
        float getSeatRotation(BlockState state, World world, BlockPos pos);

        // This method is used to get the height of the seat they are sitting on
        float getSeatHeight(BlockState state);

        // SIT DOWN BOI
        static ItemActionResult sitDown(BlockState state, World world, BlockPos pos, PlayerEntity player) {
                if (world.isClient) return ItemActionResult.SUCCESS;
                if (state.getBlock() instanceof SeatBlock seatBlock) {
                        if (!state.get(Properties.TRIGGERED)) {
                                world.setBlockState(pos, state.with(Properties.TRIGGERED, true));
                                // Creates a new entity
                                SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
                                // Sets it's location
                                seat.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);

                                seat.setYaw(seatBlock.getSeatRotation(state, world, pos));
                                seat.setAngles(seatBlock.getSeatRotation(state, world, pos), 0);

                                world.spawnEntity(seat);

                                player.startRiding(seat);
                                return ItemActionResult.SUCCESS;
                        }
                }
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
}
