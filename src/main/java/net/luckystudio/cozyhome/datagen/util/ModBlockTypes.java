package net.luckystudio.cozyhome.datagen.util;

import net.minecraft.util.StringIdentifiable;

/**
 * An enum representing the different types of block folders inside models to generate to hold and group model files into.
 */
public enum ModBlockTypes implements StringIdentifiable {
    COUNTER("counter"),
    GRANDFATHER_CLOCK("grandfather_clock"),
    CHIMNEY("chimney"),
    TABLE("table"),
    FOUNTAIN("fountain"),
    LARGE_STUMP("large_stump"),
    FLAT("flat"),
    LAMP("lamp"),
    COUCH("couch"),
    DRAWER("drawer"),
    DESK("desk"),
    WALL_MIRROR("wall_mirror"),
    SINK("sink"),
    BATHTUB("bathtub"),;

    private final String name;

    ModBlockTypes(final String name) {
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
