package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum ContainsBlock implements StringIdentifiable {
    NONE("none"),
    GRASS("grass"),
    LAVA("lava"),
    WATER("water"),
    POWDER_SNOW("powder_snow");

    private final String name;

    private ContainsBlock(final String name) {
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
