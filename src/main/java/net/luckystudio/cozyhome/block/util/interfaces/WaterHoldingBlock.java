package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface WaterHoldingBlock {

    float getLiquidLevelHeight(BlockState state);

    boolean hasLiquidToPull(BlockState state, World world, BlockPos pos);

    ContainsBlock getLiquidToPull(BlockState state, World world, BlockPos pos);

    boolean isFull(BlockState state);

    void addLiquid(BlockState state, World world, BlockPos pos, ContainsBlock contains);

    void removeLiquid(BlockState state, World world, BlockPos pos);
}