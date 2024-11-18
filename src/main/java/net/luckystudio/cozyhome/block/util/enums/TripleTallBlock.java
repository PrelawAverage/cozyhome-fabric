package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum TripleTallBlock implements StringIdentifiable {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom");

    private final String type;

    private TripleTallBlock(final String type) {
        this.type = type;
    }

    @Override
    public String asString() {
        return this.type;
    }
}
