package net.luckystudio.cozyhome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.util.ModFlammableBlocks;
import net.luckystudio.cozyhome.util.ModFuels;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CozyHome implements ModInitializer {
	public static final String MOD_ID = "cozyhome";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		ModBlockEntityTypes.registerBlockEntities();
		ModItemGroups.registerModItemGroups();
		ModFuels.registerFuels();
		ModFlammableBlocks.registerFlammables();
		ModSoundEvents.registerSounds();
		ModDataComponents.registerModDataComponents();

		// Registering Villager Trades
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 5, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 26),
					new ItemStack(ModBlocks.TELESCOPE, 1),
					2,
					5,
					0.5f));
		});

		// Wandering trader now sells trader themed furniture
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 6),
					new ItemStack(ModItems.TRADER_CUSHION, 1),
					8,
					5,
					0.5f));
		});
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}