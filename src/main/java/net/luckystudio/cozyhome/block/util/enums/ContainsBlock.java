package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum ContainsBlock implements StringIdentifiable {
    NONE("none"),
    WATER("water"),
    LAVA("lava");

    private final String name;

    ContainsBlock(final String name) {
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
