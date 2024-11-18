package net.luckystudio.cozyhome;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.renderer.blockrenders.GrandfatherClockRenderer;
import net.luckystudio.cozyhome.block.renderer.blockrenders.TelescopeBlockEntityRenderer;
import net.luckystudio.cozyhome.block.renderer.models.GrandfatherClockModel;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.luckystudio.cozyhome.block.renderer.blockrenders.ChairBlockEntityRenderer;
import net.luckystudio.cozyhome.block.renderer.models.ChairModel;
import net.luckystudio.cozyhome.client.ModRenderLayers;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.client.SeatRenderer;
import net.luckystudio.cozyhome.entity.model.*;
import net.luckystudio.cozyhome.item.renderer.ChairItemRenderer;
import net.luckystudio.cozyhome.screen.StorageCounterScreen;
import net.luckystudio.cozyhome.screen.StorageCounterScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;

@Environment(EnvType.CLIENT)
public class CozyHomeClient implements ClientModInitializer {

    public static final ScreenHandlerType<StorageCounterScreenHandler> STORAGE_COUNTER_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER, CozyHome.id("storage_counter"), new ScreenHandlerType<>(StorageCounterScreenHandler::new, FeatureSet.empty()));

    @Override
    public void onInitializeClient() {
        ModEntityModelLayers.registerEntityModelLayers();
        HandledScreens.register(STORAGE_COUNTER_SCREEN_HANDLER, StorageCounterScreen::new);

        EntityRendererRegistry.register(ModEntities.SEAT_ENTITY, SeatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SEAT, SeatEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.TELESCOPE, TelescopeModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.TELESCOPE_BLOCK_ENTITY, TelescopeBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CHAIR, ChairModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.CHAIR_BLOCK_ENTITY, ChairBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CUSHION, CushionModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.GRANDFATHER_CLOCK, GrandfatherClockModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.GRANDFATHER_CLOCK_BLOCK_ENTITY, GrandfatherClockRenderer::new);

        ItemConvertible[] chairItems = {
                ModBlocks.OAK_CHAIR,
                ModBlocks.SPRUCE_CHAIR,
                ModBlocks.BIRCH_CHAIR,
                ModBlocks.JUNGLE_CHAIR,
                ModBlocks.ACACIA_CHAIR,
                ModBlocks.DARK_OAK_CHAIR,
                ModBlocks.MANGROVE_CHAIR,
                ModBlocks.CHERRY_CHAIR,
                ModBlocks.BAMBOO_CHAIR,
                ModBlocks.CRIMSON_CHAIR,
                ModBlocks.WARPED_CHAIR,
                ModBlocks.PRINCESS_CHAIR,
                ModBlocks.IRON_CHAIR,
                ModBlocks.GLASS_CHAIR,
                ModBlocks.UNDEAD_CHAIR,
                ModBlocks.OMINOUS_CHAIR
        };
        for (ItemConvertible chair : chairItems) {
            BuiltinItemRendererRegistry.INSTANCE.register(chair, new ChairItemRenderer());
        }

        ModRenderLayers.registerBlockRenderLayers();
        ModRenderLayers.registerColorProviders();
    }
}