package net.luckystudio.cozyhome.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.BlockRenderView;
import net.minecraft.util.math.BlockPos;

public class ModRenderLayers {

    public static void registerBlockRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                // Lamps
                ModBlocks.OAK_LAMP,
                ModBlocks.SPRUCE_LAMP,
                // Lanterns
                ModBlocks.MANGROVE_LANTERN,
                // Sofas
                ModBlocks.OAK_SOFA,
                // Counters
                ModBlocks.OAK_COUNTER,
                ModBlocks.OAK_SINK_COUNTER,
                ModBlocks.OAK_STORAGE_COUNTER,
                ModBlocks.SPRUCE_COUNTER,
                ModBlocks.SPRUCE_SINK_COUNTER,
                ModBlocks.SPRUCE_STORAGE_COUNTER,
                ModBlocks.BIRCH_COUNTER,
                ModBlocks.BIRCH_SINK_COUNTER,
                ModBlocks.BIRCH_STORAGE_COUNTER,
                ModBlocks.JUNGLE_COUNTER,
                ModBlocks.JUNGLE_SINK_COUNTER,
                ModBlocks.JUNGLE_STORAGE_COUNTER,
                ModBlocks.ACACIA_COUNTER,
                ModBlocks.ACACIA_SINK_COUNTER,
                ModBlocks.ACACIA_STORAGE_COUNTER,
                ModBlocks.DARK_OAK_COUNTER,
                ModBlocks.DARK_OAK_SINK_COUNTER,
                ModBlocks.DARK_OAK_STORAGE_COUNTER,
                ModBlocks.MANGROVE_COUNTER,
                ModBlocks.MANGROVE_SINK_COUNTER,
                ModBlocks.MANGROVE_STORAGE_COUNTER,
                ModBlocks.CHERRY_COUNTER,
                ModBlocks.CHERRY_SINK_COUNTER,
                ModBlocks.CHERRY_STORAGE_COUNTER,
                ModBlocks.BAMBOO_COUNTER,
                ModBlocks.BAMBOO_SINK_COUNTER,
                ModBlocks.BAMBOO_STORAGE_COUNTER,
                ModBlocks.CRIMSON_COUNTER,
                ModBlocks.CRIMSON_SINK_COUNTER,
                ModBlocks.CRIMSON_STORAGE_COUNTER,
                ModBlocks.WARPED_COUNTER,
                ModBlocks.WARPED_SINK_COUNTER,
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
                // Wall Clocks
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
                // Fountains
                ModBlocks.STONE_BRICK_FOUNTAIN,
                ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN,
                ModBlocks.GRANITE_FOUNTAIN,
                ModBlocks.DIORITE_FOUNTAIN,
                ModBlocks.ANDESITE_FOUNTAIN,
                ModBlocks.DEEPSLATE_FOUNTAIN,
                ModBlocks.TUFF_FOUNTAIN,
                ModBlocks.BRICK_FOUNTAIN,
                ModBlocks.MUD_FOUNTAIN,
                ModBlocks.SANDSTONE_FOUNTAIN,
                ModBlocks.RED_SANDSTONE_FOUNTAIN,
                ModBlocks.PRISMARINE_FOUNTAIN,
                ModBlocks.NETHER_BRICK_FOUNTAIN,
                ModBlocks.RED_NETHER_BRICK_FOUNTAIN,
                ModBlocks.BLACKSTONE_FOUNTAIN,
                ModBlocks.ENDSTONE_FOUNTAIN,
                ModBlocks.PURPUR_FOUNTAIN,
                // Beams
                ModBlocks.OAK_BEAM,
                ModBlocks.SPRUCE_BEAM,
                ModBlocks.BIRCH_BEAM,
                ModBlocks.JUNGLE_BEAM,
                ModBlocks.ACACIA_BEAM,
                ModBlocks.DARK_OAK_BEAM,
                ModBlocks.MANGROVE_BEAM,
                ModBlocks.CHERRY_BEAM,
                ModBlocks.BAMBOO_BEAM,
                ModBlocks.CRIMSON_BEAM,
                ModBlocks.WARPED_BEAM,
                // Tables
                ModBlocks.OAK_TABLE,
                ModBlocks.SPRUCE_TABLE,
                ModBlocks.BIRCH_TABLE,
                ModBlocks.JUNGLE_TABLE,
                ModBlocks.ACACIA_TABLE,
                ModBlocks.DARK_OAK_TABLE,
                ModBlocks.MANGROVE_TABLE,
                ModBlocks.CHERRY_TABLE,
                ModBlocks.BAMBOO_TABLE,
                ModBlocks.CRIMSON_TABLE,
                ModBlocks.WARPED_TABLE,
                ModBlocks.GLASS_TABLE,
                // Chandeliers
                ModBlocks.OAK_CHANDELIER,
                ModBlocks.SPRUCE_CHANDELIER,
                ModBlocks.BIRCH_CHANDELIER,
                ModBlocks.JUNGLE_CHANDELIER,
                ModBlocks.ACACIA_CHANDELIER,
                ModBlocks.DARK_OAK_CHANDELIER,
                ModBlocks.MANGROVE_CHANDELIER,
                ModBlocks.CHERRY_CHANDELIER,
                ModBlocks.BAMBOO_CHANDELIER,
                ModBlocks.CRIMSON_CHANDELIER,
                ModBlocks.WARPED_CHANDELIER,
                // Glass
                ModBlocks.GOLD_FRAMED_GLASS,
                ModBlocks.GOLD_FRAMED_GLASS_PANE,
                // Misc
                ModBlocks.DYE_VAT,
                ModBlocks.TELESCOPE
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.AUTUMN_STAINED_GLASS,
                ModBlocks.AUTUMN_STAINED_GLASS_PANE
        );
    }

    public static void registerColorProviders() {
        // Gives blocks the water color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                        BiomeColors.getWaterColor(world, pos),
                ModBlocks.OAK_SINK_COUNTER,
                ModBlocks.SPRUCE_SINK_COUNTER,
                ModBlocks.BIRCH_SINK_COUNTER,
                ModBlocks.JUNGLE_SINK_COUNTER,
                ModBlocks.ACACIA_SINK_COUNTER,
                ModBlocks.DARK_OAK_SINK_COUNTER,
                ModBlocks.MANGROVE_SINK_COUNTER,
                ModBlocks.CHERRY_SINK_COUNTER,
                ModBlocks.BAMBOO_SINK_COUNTER,
                ModBlocks.CRIMSON_SINK_COUNTER,
                ModBlocks.WARPED_SINK_COUNTER
        );

        // Blocks that can hold water or others
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                        ModBlockUtilities.getColorFromContainsState(state, world, pos),
                ModBlocks.STONE_BRICK_FOUNTAIN,
                ModBlocks.STONE_BRICK_FOUNTAIN_SPROUT,
                ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN,
                ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN_SPROUT,
                ModBlocks.GRANITE_FOUNTAIN,
                ModBlocks.GRANITE_FOUNTAIN_SPROUT,
                ModBlocks.DIORITE_FOUNTAIN,
                ModBlocks.DIORITE_FOUNTAIN_SPROUT,
                ModBlocks.ANDESITE_FOUNTAIN,
                ModBlocks.ANDESITE_FOUNTAIN_SPROUT,
                ModBlocks.DEEPSLATE_FOUNTAIN,
                ModBlocks.DEEPSLATE_FOUNTAIN_SPROUT,
                ModBlocks.TUFF_FOUNTAIN,
                ModBlocks.TUFF_FOUNTAIN_SPROUT,
                ModBlocks.BRICK_FOUNTAIN,
                ModBlocks.BRICK_FOUNTAIN_SPROUT,
                ModBlocks.MUD_FOUNTAIN,
                ModBlocks.MUD_FOUNTAIN_SPROUT,
                ModBlocks.SANDSTONE_FOUNTAIN,
                ModBlocks.SANDSTONE_FOUNTAIN_SPROUT,
                ModBlocks.RED_SANDSTONE_FOUNTAIN,
                ModBlocks.RED_SANDSTONE_FOUNTAIN_SPROUT,
                ModBlocks.PRISMARINE_FOUNTAIN,
                ModBlocks.PRISMARINE_FOUNTAIN_SPROUT,
                ModBlocks.NETHER_BRICK_FOUNTAIN,
                ModBlocks.NETHER_BRICK_FOUNTAIN_SPROUT,
                ModBlocks.RED_NETHER_BRICK_FOUNTAIN,
                ModBlocks.RED_NETHER_BRICK_FOUNTAIN_SPROUT,
                ModBlocks.BLACKSTONE_FOUNTAIN,
                ModBlocks.BLACKSTONE_FOUNTAIN_SPROUT,
                ModBlocks.ENDSTONE_FOUNTAIN,
                ModBlocks.ENDSTONE_FOUNTAIN_SPROUT,
                ModBlocks.PURPUR_FOUNTAIN,
                ModBlocks.PURPUR_FOUNTAIN_SPROUT,
                ModBlocks.FALLING_LIQUID
        );

        // Renders the colors on the Items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ModColorHandler.getItemColor(stack),
                ModBlocks.OAK_LAMP.asItem(),
                ModBlocks.SPRUCE_LAMP.asItem()
        );
    }

    private static int getColorFromContainsState(BlockState state, BlockRenderView world, BlockPos pos) {
        // Your logic for determining the color
        return 0xFFFFFF;
    }
}

