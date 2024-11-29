package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum HorizontalLinearConnectionBlock implements StringIdentifiable {
    SINGLE("single"),
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right"),
    LEFT_DIFF("left_diff"),
    MIDDLE_DIFF("middle_diff"),
    RIGHT_DIFF("right_diff"),
    LEFT_DIFF_LEFT("left_diff_left"),
    RIGHT_DIFF_RIGHT("right_diff_right");

    private final String name;

    private HorizontalLinearConnectionBlock(final String name) {
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
