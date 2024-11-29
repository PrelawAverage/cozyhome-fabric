package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.ModBurnableBlocks;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.luckystudio.cozyhome.item.ModFuels;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.screen.MirrorScreen;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
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
		ModBlockEntityTypes.registerBlockEntities();
		ModItemGroups.registerModItemGroups();
		ModFuels.registerFuels();
		ModBurnableBlocks.registerFlammableBlocks();
		ModSoundEvents.registerSounds();
		ModDataComponents.registerModDataComponents();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}