package net.luckystudio.cozyhome.block.util;

import net.minecraft.util.StringIdentifiable;

public enum StackableBlock implements StringIdentifiable {
    SINGLE("single"),
    HEAD("head"),
    MIDDLE("middle"),
    TAIL("tail");

    private final String name;

    private StackableBlock(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
