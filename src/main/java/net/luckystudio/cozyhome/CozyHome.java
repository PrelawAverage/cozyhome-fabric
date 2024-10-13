package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.entity.ModBlockEntities;
import net.luckystudio.cozyhome.block.util.ModBurnableBlocks;
import net.luckystudio.cozyhome.item.ModFuels;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.screen.StorageCounterScreenHandler;
import net.luckystudio.cozyhome.sound.ModSounds;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CozyHome implements ModInitializer {
	public static final String MOD_ID = "cozyhome";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ScreenHandlerType<StorageCounterScreenHandler> STORAGE_COUNTER_SCREEN_HANDLER = Registry.register(
			Registries.SCREEN_HANDLER, Identifier.of("cozyhome", "storage_counter"), new ScreenHandlerType<>(StorageCounterScreenHandler::new, FeatureSet.empty()));

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		if (FabricLoader.getInstance().isModLoaded("ecologics")) {
			ModBlocks.registerModBlocks();
		}

		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModItemGroups.registerModItemGroups();
		ModFuels.registerFuels();
		ModBurnableBlocks.registerFlammableBlocks();
		ModSounds.registerSounds();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}