package net.luckystudio.cozyhome;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class CozyHomeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

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
