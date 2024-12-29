package net.luckystudio.cozyhome.block.util.interfaces;

import net.minecraft.block.BlockState;

public interface ConnectingBlock {
    boolean isMatchingBlock(BlockState targetState);
}
