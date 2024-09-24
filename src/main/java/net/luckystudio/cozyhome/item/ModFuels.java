package net.luckystudio.cozyhome.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;

public class ModFuels {
    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(ModBlocks.OAK_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.SPRUCE_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.BIRCH_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.JUNGLE_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.ACACIA_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.DARK_OAK_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.MANGROVE_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.CHERRY_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.BAMBOO_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.CRIMSON_PLANKED_WALL, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.WARPED_PLANKED_WALL, 300);
    }
}
