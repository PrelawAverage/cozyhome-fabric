package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

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
}
