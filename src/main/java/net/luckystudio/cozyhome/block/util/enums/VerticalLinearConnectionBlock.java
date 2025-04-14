package net.luckystudio.cozyhome.block.util.enums;

import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public enum VerticalLinearConnectionBlock implements StringIdentifiable {
    SINGLE("single"),
    HEAD("head"),
    MIDDLE("middle"),
    TAIL("tail");

    private final String name;

    private VerticalLinearConnectionBlock(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static VerticalLinearConnectionBlock setVerticalConnection(BlockState state, WorldAccess world, BlockPos pos) {
        if (state.getBlock() instanceof ConnectingBlock connectingBlock) {
            boolean isMatchingBlockAbove = connectingBlock.isMatchingBlock(world.getBlockState(pos.up()));
            boolean isMatchingBlockBelow = connectingBlock.isMatchingBlock(world.getBlockState(pos.down()));

            if (isMatchingBlockAbove && isMatchingBlockBelow) return VerticalLinearConnectionBlock.MIDDLE;
            if (isMatchingBlockAbove) return VerticalLinearConnectionBlock.TAIL;
            if (isMatchingBlockBelow) return VerticalLinearConnectionBlock.HEAD;
        }
        return VerticalLinearConnectionBlock.SINGLE;
    }
}
