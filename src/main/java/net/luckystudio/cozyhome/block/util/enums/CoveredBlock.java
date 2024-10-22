package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum CoveredBlock implements StringIdentifiable {
    NONE("none"),
    GENERIC("generic"),
    HAY("hay"),
    TRADER("trader");

    private final String name;

    private CoveredBlock(final String name) {
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
