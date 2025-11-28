package net.luckystudio.cozyhome.datagen.util;

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum ModItemTemplates implements Supplier<Identifier> {
    CHAIR(CozyHome.id("item/template/template_chair")),
    SOFA(CozyHome.id("item/template/template_sofa")),
    WALL_CLOCK(CozyHome.id("item/template/template_wall_clock")),
    BATHTUB(CozyHome.id("item/template/template_bathtub")),;

    private final Identifier name;

    ModItemTemplates(final Identifier name) {
        this.name = name;
    }

    public String toString() {
        return this.name.toString();
    }

    @Override
    public Identifier get() {
        return name;
    }
}
