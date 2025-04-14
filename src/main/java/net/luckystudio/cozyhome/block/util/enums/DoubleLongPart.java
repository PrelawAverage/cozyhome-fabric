package net.luckystudio.cozyhome.block.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum DoubleLongPart implements StringIdentifiable {
        FRONT("front"),
        BACK("back");

        private final String name;

        private DoubleLongPart(final String name) {
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
