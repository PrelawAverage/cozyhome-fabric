package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum OminousBlock implements StringIdentifiable {
    INACTIVE("inactive"),
    ACTIVE("active"),
    OMINOUS("ominous");

    private final String name;

    private OminousBlock(final String name) {
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
