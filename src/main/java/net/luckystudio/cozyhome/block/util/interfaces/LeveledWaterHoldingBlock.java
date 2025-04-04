package net.luckystudio.cozyhome.block.util.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface LeveledWaterHoldingBlock {
    float getWaterLevel(BlockState state);
    boolean hasWaterToPull(BlockState state, World world, BlockPos pos);
}

