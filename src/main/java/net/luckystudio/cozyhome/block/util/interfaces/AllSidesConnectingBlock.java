package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.block.custom.includes_liquid.FountainBlock;
import net.minecraft.block.BlockState;

/**
 * Implement into any block that will connect to other blocks
 */
public interface AllSidesConnectingBlock {
    boolean isMatchingBlock(BlockState state, BlockState targetState);
}
