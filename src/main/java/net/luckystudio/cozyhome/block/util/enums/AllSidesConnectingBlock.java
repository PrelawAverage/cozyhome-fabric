package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum AllSidesConnectingBlock implements StringIdentifiable {
    SINGLE("single"),
    CORNER("corner"),
    CORNER_PIECE("corner_piece"),
    DOUBLE("double"),
    INNER_CORNER("inner_corner"),
    MIDDLE("middle"),
    SIDE("side");

    private final String name;

    private AllSidesConnectingBlock(final String name) {
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
