package net.luckystudio.cozyhome;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.client.SeatRenderer;
import net.luckystudio.cozyhome.entity.model.SeatEntityModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CozyHomeClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_SEAT_LAYER = new EntityModelLayer(Identifier.of("cozyhome", "seat"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SEAT_ENTITY, SeatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_SEAT_LAYER, SeatEntityModel::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
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
                ModBlocks.WARPED_CHAIR);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.WHITE_LAMP,
                ModBlocks.ORANGE_LAMP,
                ModBlocks.MAGENTA_LAMP,
                ModBlocks.LIGHT_BLUE_LAMP,
                ModBlocks.YELLOW_LAMP,
                ModBlocks.LIME_LAMP,
                ModBlocks.PINK_LAMP,
                ModBlocks.GRAY_LAMP,
                ModBlocks.LIGHT_GRAY_LAMP,
                ModBlocks.CYAN_LAMP,
                ModBlocks.PURPLE_LAMP,
                ModBlocks.BLUE_LAMP,
                ModBlocks.BROWN_LAMP,
                ModBlocks.GREEN_LAMP,
                ModBlocks.RED_LAMP,
                ModBlocks.BLACK_LAMP,

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.AUTUMN_STAINED_WINDOW,
                ModBlocks.AUTUMN_STAINED_WINDOW_PANE,
                ModBlocks.WHITE_LAMP,
                ModBlocks.ORANGE_LAMP,
                ModBlocks.MAGENTA_LAMP,
                ModBlocks.LIGHT_BLUE_LAMP,
                ModBlocks.YELLOW_LAMP,
                ModBlocks.LIME_LAMP,
                ModBlocks.PINK_LAMP,
                ModBlocks.GRAY_LAMP,
                ModBlocks.LIGHT_GRAY_LAMP,
                ModBlocks.CYAN_LAMP,
                ModBlocks.PURPLE_LAMP,
                ModBlocks.BLUE_LAMP,
                ModBlocks.BROWN_LAMP,
                ModBlocks.GREEN_LAMP,
                ModBlocks.RED_LAMP,
                ModBlocks.BLACK_LAMP);
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
                ModBlocks.WARPED_COUNTER
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.AUTUMN_STAINED_WINDOW,
                ModBlocks.AUTUMN_STAINED_WINDOW_PANE
        );
    }
}
