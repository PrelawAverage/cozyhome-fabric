package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
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
	}
}