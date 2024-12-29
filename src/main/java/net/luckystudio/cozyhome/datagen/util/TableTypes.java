package net.luckystudio.cozyhome.datagen.util;

import net.minecraft.util.StringIdentifiable;

public enum TableTypes implements StringIdentifiable {
    GENERIC(""),
    SHELF("shelf_"),
    SOLID("solid_");

    private final String name;

    private TableTypes(final String name) {
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
