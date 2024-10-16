package net.luckystudio.cozyhome;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.client.SeatRenderer;
import net.luckystudio.cozyhome.entity.model.SeatEntityModel;
import net.luckystudio.cozyhome.screen.StorageCounterScreen;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CozyHomeClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_SEAT_LAYER = new EntityModelLayer(Identifier.of("cozyhome", "seat"), "main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SEAT_ENTITY, SeatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_SEAT_LAYER, SeatEntityModel::getTexturedModelData);
        HandledScreens.register(CozyHome.STORAGE_COUNTER_SCREEN_HANDLER, StorageCounterScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                // Chairs
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

                // Lamps
                ModBlocks.OAK_LAMP,

                // Lanterns
                ModBlocks.MANGROVE_LANTERN,

                // Sofas
                ModBlocks.WHITE_SOFA,
                ModBlocks.ORANGE_SOFA,
                ModBlocks.MAGENTA_SOFA,
                ModBlocks.LIGHT_BLUE_SOFA,
                ModBlocks.YELLOW_SOFA,
                ModBlocks.LIME_SOFA,
                ModBlocks.PINK_SOFA,
                ModBlocks.GRAY_SOFA,
                ModBlocks.LIGHT_GRAY_SOFA,
                ModBlocks.CYAN_SOFA,
                ModBlocks.PURPLE_SOFA,
                ModBlocks.BLUE_SOFA,
                ModBlocks.BROWN_SOFA,
                ModBlocks.GREEN_SOFA,
                ModBlocks.RED_SOFA,
                ModBlocks.BLACK_SOFA,
                ModBlocks.MANGROVE_ZAISU,

                // Counters
                ModBlocks.OAK_COUNTER,
                ModBlocks.SPRUCE_COUNTER,
                ModBlocks.BIRCH_COUNTER,
                ModBlocks.JUNGLE_COUNTER,
                ModBlocks.ACACIA_COUNTER,
                ModBlocks.DARK_OAK_COUNTER,
                ModBlocks.MANGROVE_COUNTER,
                ModBlocks.CHERRY_COUNTER,
                ModBlocks.BAMBOO_COUNTER,
                ModBlocks.CRIMSON_COUNTER,
                ModBlocks.WARPED_COUNTER,

                // Storage Counters
                ModBlocks.OAK_STORAGE_COUNTER,
                ModBlocks.SPRUCE_STORAGE_COUNTER,
                ModBlocks.BIRCH_STORAGE_COUNTER,
                ModBlocks.JUNGLE_STORAGE_COUNTER,
                ModBlocks.ACACIA_STORAGE_COUNTER,
                ModBlocks.DARK_OAK_STORAGE_COUNTER,
                ModBlocks.MANGROVE_STORAGE_COUNTER,
                ModBlocks.CHERRY_STORAGE_COUNTER,
                ModBlocks.BAMBOO_STORAGE_COUNTER,
                ModBlocks.CRIMSON_STORAGE_COUNTER,
                ModBlocks.WARPED_STORAGE_COUNTER,

                // Wall Mirrors
                ModBlocks.OAK_WALL_MIRROR,
                ModBlocks.SPRUCE_WALL_MIRROR,
                ModBlocks.BIRCH_WALL_MIRROR,
                ModBlocks.JUNGLE_WALL_MIRROR,
                ModBlocks.ACACIA_WALL_MIRROR,
                ModBlocks.DARK_OAK_WALL_MIRROR,
                ModBlocks.MANGROVE_WALL_MIRROR,
                ModBlocks.CHERRY_WALL_MIRROR,
                ModBlocks.BAMBOO_WALL_MIRROR,
                ModBlocks.CRIMSON_WALL_MIRROR,
                ModBlocks.WARPED_WALL_MIRROR,
                ModBlocks.DYE_VAT
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.AUTUMN_STAINED_WINDOW,
                ModBlocks.AUTUMN_STAINED_WINDOW_PANE
        );

        // Renders the colors on the Blocks
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && view.getBlockEntityRenderData(pos) instanceof Integer integer ? integer : 0xFFFFFF, ModBlocks.DYE_VAT);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && view.getBlockEntityRenderData(pos) instanceof Integer integer ? integer : 0xFFFFFF, ModBlocks.OAK_LAMP);
        // Renders the colors on the Items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ModColorHandler.getItemColor(stack), ModBlocks.OAK_LAMP.asItem());
    }
}