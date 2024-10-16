package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum LinearConnectionBlock implements StringIdentifiable {
    SINGLE("single"),
    HEAD("head"),
    MIDDLE("middle"),
    TAIL("tail");

    private final String name;

    private LinearConnectionBlock(final String name) {
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
