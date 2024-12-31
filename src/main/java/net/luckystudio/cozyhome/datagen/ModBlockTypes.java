package net.luckystudio.cozyhome.datagen;

import net.minecraft.util.StringIdentifiable;

/**
 * An enum representing the different types of block folders inside models to generate to hold and group model files into.
 */
public enum ModBlockTypes implements StringIdentifiable {
    CHIMNEY("chimney"),
    TABLE("table"),
    FOUNTAIN("fountain"),
    PLANE("pane");

    private final String name;

    private ModBlockTypes(final String name) {
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
