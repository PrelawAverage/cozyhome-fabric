package net.luckystudio.cozyhome.block.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;

public class ModBurnableBlocks {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        registry.add(ModBlocks.OAK_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.SPRUCE_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.BIRCH_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.JUNGLE_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.ACACIA_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.DARK_OAK_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.MANGROVE_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.CHERRY_PLANKED_WALL, 5, 20);
        registry.add(ModBlocks.BAMBOO_PLANKED_WALL, 5, 20);

        registry.add(ModBlocks.WHITE_LAMP, 5, 20);
        registry.add(ModBlocks.LIGHT_GRAY_LAMP, 5, 20);
        registry.add(ModBlocks.GRAY_LAMP, 5, 20);
        registry.add(ModBlocks.BLACK_LAMP, 5, 20);
        registry.add(ModBlocks.BROWN_LAMP, 5, 20);
        registry.add(ModBlocks.RED_LAMP, 5, 20);
        registry.add(ModBlocks.ORANGE_LAMP, 5, 20);
        registry.add(ModBlocks.YELLOW_LAMP, 5, 20);
        registry.add(ModBlocks.LIME_LAMP, 5, 20);
        registry.add(ModBlocks.GREEN_LAMP, 5, 20);
        registry.add(ModBlocks.CYAN_LAMP, 5, 20);
        registry.add(ModBlocks.LIGHT_BLUE_LAMP, 5, 20);
        registry.add(ModBlocks.BLUE_LAMP, 5, 20);
        registry.add(ModBlocks.PURPLE_LAMP, 5, 20);
        registry.add(ModBlocks.MAGENTA_LAMP, 5, 20);
        registry.add(ModBlocks.PINK_LAMP, 5, 20);
/*
        registry.add(ModBlocks.OAK_COUNTER, 5, 20);
        registry.add(ModBlocks.SPRUCE_COUNTER, 5, 20);
        registry.add(ModBlocks.BIRCH_COUNTER, 5, 20);
        registry.add(ModBlocks.JUNGLE_COUNTER, 5, 20);
        registry.add(ModBlocks.ACACIA_COUNTER, 5, 20);
        registry.add(ModBlocks.DARK_OAK_COUNTER, 5, 20);
        registry.add(ModBlocks.MANGROVE_COUNTER, 5, 20);
        registry.add(ModBlocks.CHERRY_COUNTER, 5, 20);
        registry.add(ModBlocks.BAMBOO_COUNTER, 5, 20);
        registry.add(ModBlocks.CRIMSON_COUNTER, 5, 20);
        registry.add(ModBlocks.WARPED_COUNTER, 5, 20);
 */
    }
}
