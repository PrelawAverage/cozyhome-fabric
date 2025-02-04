package net.luckystudio.cozyhome.item;

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModBannerPatternTags {
    public static final TagKey<BannerPattern> WANDERING_TRADER_PATTERN_ITEM = of("pattern_item/wandering_trader");

    private static TagKey<BannerPattern> of(String id) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, CozyHome.id(id));
    }

    public static void registerModBannerPatternTags() {
        CozyHome.LOGGER.info("Registering Mod Banner Patterns for " + CozyHome.MOD_ID);
    }
}
