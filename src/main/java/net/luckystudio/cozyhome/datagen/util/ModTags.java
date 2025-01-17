package net.luckystudio.cozyhome.datagen.util;

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> CAN_TUCK_UNDER = of("can_tuck_under");

        private static TagKey<Block> of(String id) {
            return TagKey.of(RegistryKeys.BLOCK, CozyHome.id(id));
        }
    }

    public static class Items {

        private static TagKey<Item> of(String id) {
            return TagKey.of(RegistryKeys.ITEM, CozyHome.id(id));
        }
    }
}
