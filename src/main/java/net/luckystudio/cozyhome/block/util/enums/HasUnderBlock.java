package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum HasUnderBlock implements StringIdentifiable {
    NONE("none"),
    FLAT("flat"),
    LOWERED("lowered"),
    FALLING("falling"),
    DEEP("deep");

    private final String name;

    private HasUnderBlock(final String name) {
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
