package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.luckystudio.cozyhome.block.ModBlockEntities;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBurnableBlocks;
import net.luckystudio.cozyhome.item.ModFuels;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CozyHome implements ModInitializer {
	public static final String MOD_ID = "cozyhome";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		if (FabricLoader.getInstance().isModLoaded("ecologics")) {
			ModBlocks.registerModBlocks();
		}
		ModBlockEntities.registerBlockEntities();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();
		ModFuels.registerFuels();
		ModBurnableBlocks.registerFlammableBlocks();
		ModSounds.registerSounds();
	}
}