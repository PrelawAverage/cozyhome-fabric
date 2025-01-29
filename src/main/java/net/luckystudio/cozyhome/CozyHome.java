package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.interfaces.SinkBehavior;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.luckystudio.cozyhome.util.ModFlammableBlocks;
import net.luckystudio.cozyhome.util.ModFuels;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CozyHome implements ModInitializer {
	public static final String MOD_ID = "cozyhome";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		SinkBehavior.registerBehavior();
		ModBlockEntityTypes.registerBlockEntities();
		ModItemGroups.registerModItemGroups();
		ModFuels.registerFuels();
		ModFlammableBlocks.registerFlammables();
		ModSoundEvents.registerSounds();
		ModDataComponents.registerModDataComponents();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}