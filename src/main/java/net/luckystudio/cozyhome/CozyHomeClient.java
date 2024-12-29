package net.luckystudio.cozyhome;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.renderer.blockrenders.*;
import net.luckystudio.cozyhome.block.renderer.models.*;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.luckystudio.cozyhome.client.ModRenderLayers;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.client.SeatRenderer;
import net.luckystudio.cozyhome.entity.model.*;
import net.luckystudio.cozyhome.item.renderer.ChairItemRenderer;
import net.luckystudio.cozyhome.item.renderer.SofaItemRenderer;
import net.luckystudio.cozyhome.item.renderer.WallClockItemRenderer;
import net.luckystudio.cozyhome.screen.drawer.DrawerScreen;
import net.luckystudio.cozyhome.screen.drawer.DrawerScreenHandler;
import net.luckystudio.cozyhome.screen.storage_counter.StorageCounterScreen;
import net.luckystudio.cozyhome.screen.storage_counter.StorageCounterScreenHandler;
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

    public static final ScreenHandlerType<DrawerScreenHandler> DRAWER_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER, CozyHome.id("drawer"), new ScreenHandlerType<>(DrawerScreenHandler::new, FeatureSet.empty()));

    @Override
    public void onInitializeClient() {
        ModEntityModelLayers.registerEntityModelLayers();

        HandledScreens.register(STORAGE_COUNTER_SCREEN_HANDLER, StorageCounterScreen::new);
        HandledScreens.register(DRAWER_SCREEN_HANDLER, DrawerScreen::new);

        EntityRendererRegistry.register(ModEntities.SEAT_ENTITY, SeatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SEAT, SeatEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.TELESCOPE, TelescopeModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.TELESCOPE_BLOCK_ENTITY, TelescopeBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SOFA, SofaModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.SOFA_BLOCK_ENTITY, SofaBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SOFA_CUSHION, SofaCushionModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CHAIR, ChairModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.CHAIR_BLOCK_ENTITY, ChairBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CUSHION, CushionModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.GRANDFATHER_CLOCK, GrandfatherClockModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.GRANDFATHER_CLOCK_BLOCK_ENTITY, GrandfatherClockBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.WALL_CLOCK, WallClockModel::getTexturedModelData);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY, WallClockBlockEntityRenderer::new);

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
                ModBlocks.IRON_CHAIR,
                ModBlocks.GLASS_CHAIR,
                ModBlocks.UNDEAD_CHAIR,
                ModBlocks.OMINOUS_CHAIR
        };
        for (ItemConvertible chair : chairItems) {
            BuiltinItemRendererRegistry.INSTANCE.register(chair, new ChairItemRenderer());
        }

        ItemConvertible[] sofaItems = {
                ModBlocks.OAK_SOFA,
                ModBlocks.SPRUCE_SOFA,
                ModBlocks.BIRCH_SOFA,
                ModBlocks.JUNGLE_SOFA,
                ModBlocks.ACACIA_SOFA,
                ModBlocks.DARK_OAK_SOFA,
                ModBlocks.MANGROVE_SOFA,
                ModBlocks.CHERRY_SOFA,
                ModBlocks.BAMBOO_SOFA,
                ModBlocks.CRIMSON_SOFA,
                ModBlocks.WARPED_SOFA
        };
        for (ItemConvertible sofa : sofaItems) {
            BuiltinItemRendererRegistry.INSTANCE.register(sofa, new SofaItemRenderer());
        }

        ItemConvertible[] wallClockItems = {
                ModBlocks.OAK_WALL_CLOCK,
                ModBlocks.SPRUCE_WALL_CLOCK,
                ModBlocks.BIRCH_WALL_CLOCK,
                ModBlocks.JUNGLE_WALL_CLOCK,
                ModBlocks.ACACIA_WALL_CLOCK,
                ModBlocks.DARK_OAK_WALL_CLOCK,
                ModBlocks.MANGROVE_WALL_CLOCK,
                ModBlocks.CHERRY_WALL_CLOCK,
                ModBlocks.BAMBOO_WALL_CLOCK,
                ModBlocks.CRIMSON_WALL_CLOCK,
                ModBlocks.WARPED_WALL_CLOCK,
                ModBlocks.IRON_WALL_CLOCK,
                ModBlocks.GLASS_WALL_CLOCK,
                ModBlocks.UNDEAD_WALL_CLOCK,
                ModBlocks.OMINOUS_WALL_CLOCK
        };
        for (ItemConvertible wallClock : wallClockItems) {
            BuiltinItemRendererRegistry.INSTANCE.register(wallClock, new WallClockItemRenderer());
        }
        ModRenderLayers.registerBlockRenderLayers();
        ModRenderLayers.registerColorProviders();
    }
}