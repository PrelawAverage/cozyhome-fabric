package net.luckystudio.cozyhome.block.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface SeatBlock {
        float getSeatRotation(BlockState state, World world, BlockPos pos);

        float getSeatHeight(BlockState state);
}
