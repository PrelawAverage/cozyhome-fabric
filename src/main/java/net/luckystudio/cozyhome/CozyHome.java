package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.luckystudio.cozyhome.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CozyHome implements ModInitializer {
	public static final String MOD_ID = "cozyhome";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();

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