package net.luckystudio.cozyhome.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.type.DyedColorComponent;

@Environment(EnvType.CLIENT)
public class ModRenderLayers {

    public static void registerBlockRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                // Counters
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
                ModBlocks.WARPED_SINK_COUNTER,
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
                ModBlocks.IRON_TABLE,
                ModBlocks.GLASS_TABLE,
                ModBlocks.OMINOUS_TABLE,
                ModBlocks.UNDEAD_TABLE,
                // Lamps
                ModBlocks.OAK_LAMP,
                ModBlocks.SPRUCE_LAMP,
                ModBlocks.BIRCH_LAMP,
                ModBlocks.JUNGLE_LAMP,
                ModBlocks.ACACIA_LAMP,
                ModBlocks.DARK_OAK_LAMP,
                ModBlocks.MANGROVE_LAMP,
                ModBlocks.CHERRY_LAMP,
                ModBlocks.BAMBOO_LAMP,
                ModBlocks.CRIMSON_LAMP,
                ModBlocks.WARPED_LAMP,
                ModBlocks.IRON_LAMP,
                ModBlocks.GLASS_LAMP,
                ModBlocks.UNDEAD_LAMP,
                ModBlocks.OMINOUS_LAMP,
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
//                ModBlocks.STONE_BRICK_FOUNTAIN,
//                ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN,
//                ModBlocks.GRANITE_FOUNTAIN,
//                ModBlocks.DIORITE_FOUNTAIN,
//                ModBlocks.ANDESITE_FOUNTAIN,
//                ModBlocks.DEEPSLATE_FOUNTAIN,
//                ModBlocks.TUFF_FOUNTAIN,
//                ModBlocks.BRICK_FOUNTAIN,
//                ModBlocks.MUD_FOUNTAIN,
//                ModBlocks.SANDSTONE_FOUNTAIN,
//                ModBlocks.RED_SANDSTONE_FOUNTAIN,
//                ModBlocks.PRISMARINE_FOUNTAIN,
//                ModBlocks.NETHER_BRICK_FOUNTAIN,
//                ModBlocks.RED_NETHER_BRICK_FOUNTAIN,
//                ModBlocks.BLACKSTONE_FOUNTAIN,
//                ModBlocks.ENDSTONE_FOUNTAIN,
//                ModBlocks.PURPUR_FOUNTAIN,
                // Misc
                ModBlocks.TELESCOPE
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent()
        );
    }

    public static void registerColorProviders() {
        // Gives blocks the water color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                {
                    assert world != null;
                    return BiomeColors.getWaterColor(world, pos);
                }
        );

        // Blocks that can hold water or others
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                        ModBlockUtilities.getColorFromContainsState(state, world, pos),
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
                ModBlocks.WARPED_SINK_COUNTER,
                ModBlocks.STONE_BRICK_SINK,
                ModBlocks.MOSSY_STONE_BRICK_SINK,
                ModBlocks.GRANITE_SINK,
                ModBlocks.DIORITE_SINK,
                ModBlocks.ANDESITE_SINK,
                ModBlocks.DEEPSLATE_SINK,
                ModBlocks.CALCITE_SINK,
                ModBlocks.TUFF_SINK,
                ModBlocks.BRICK_SINK,
                ModBlocks.MUD_SINK,
                ModBlocks.SANDSTONE_SINK,
                ModBlocks.RED_SANDSTONE_SINK,
                ModBlocks.PRISMARINE_SINK,
                ModBlocks.NETHER_BRICK_SINK,
                ModBlocks.RED_NETHER_BRICK_SINK,
                ModBlocks.BLACKSTONE_SINK,
                ModBlocks.ENDSTONE_SINK,
                ModBlocks.PURPUR_SINK,
                ModBlocks.IRON_SINK,
                ModBlocks.GOLD_SINK,
                ModBlocks.STONE_BRICK_BATHTUB,
                ModBlocks.MOSSY_STONE_BRICK_BATHTUB,
                ModBlocks.GRANITE_BATHTUB,
                ModBlocks.DIORITE_BATHTUB,
                ModBlocks.ANDESITE_BATHTUB,
                ModBlocks.DEEPSLATE_BATHTUB,
                ModBlocks.CALCITE_BATHTUB,
                ModBlocks.TUFF_BATHTUB,
                ModBlocks.BRICK_BATHTUB,
                ModBlocks.MUD_BATHTUB,
                ModBlocks.SANDSTONE_BATHTUB,
                ModBlocks.RED_SANDSTONE_BATHTUB,
                ModBlocks.PRISMARINE_BATHTUB,
                ModBlocks.NETHER_BRICK_BATHTUB,
                ModBlocks.RED_NETHER_BRICK_BATHTUB,
                ModBlocks.BLACKSTONE_BATHTUB,
                ModBlocks.ENDSTONE_BATHTUB,
                ModBlocks.PURPUR_BATHTUB,
                ModBlocks.IRON_BATHTUB,
                ModBlocks.GOLD_BATHTUB,
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

        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> DyedColorComponent.getColor(stack, -17170434)),
                ModItems.CUSHION,
                ModBlocks.OAK_COUCH,
                ModBlocks.SPRUCE_COUCH,
                ModBlocks.BIRCH_COUCH,
                ModBlocks.JUNGLE_COUCH,
                ModBlocks.ACACIA_COUCH,
                ModBlocks.DARK_OAK_COUCH,
                ModBlocks.MANGROVE_COUCH,
                ModBlocks.CHERRY_COUCH,
                ModBlocks.BAMBOO_COUCH,
                ModBlocks.CRIMSON_COUCH,
                ModBlocks.WARPED_COUCH,
                ModBlocks.OAK_LAMP,
                ModBlocks.SPRUCE_LAMP,
                ModBlocks.BIRCH_LAMP,
                ModBlocks.JUNGLE_LAMP,
                ModBlocks.ACACIA_LAMP,
                ModBlocks.DARK_OAK_LAMP,
                ModBlocks.MANGROVE_LAMP,
                ModBlocks.CHERRY_LAMP,
                ModBlocks.BAMBOO_LAMP,
                ModBlocks.CRIMSON_LAMP,
                ModBlocks.WARPED_LAMP,
                ModBlocks.IRON_LAMP,
                ModBlocks.GLASS_LAMP,
                ModBlocks.UNDEAD_LAMP,
                ModBlocks.OMINOUS_LAMP
        );

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                    assert view != null;
                    return ModColorHandler.getBlockColor(view.getBlockEntity(pos), -17170434);
                },
                ModBlocks.OAK_COUCH,
                ModBlocks.SPRUCE_COUCH,
                ModBlocks.BIRCH_COUCH,
                ModBlocks.JUNGLE_COUCH,
                ModBlocks.ACACIA_COUCH,
                ModBlocks.DARK_OAK_COUCH,
                ModBlocks.MANGROVE_COUCH,
                ModBlocks.CHERRY_COUCH,
                ModBlocks.BAMBOO_COUCH,
                ModBlocks.CRIMSON_COUCH,
                ModBlocks.WARPED_COUCH,
                ModBlocks.OAK_LAMP,
                ModBlocks.SPRUCE_LAMP,
                ModBlocks.BIRCH_LAMP,
                ModBlocks.JUNGLE_LAMP,
                ModBlocks.ACACIA_LAMP,
                ModBlocks.DARK_OAK_LAMP,
                ModBlocks.MANGROVE_LAMP,
                ModBlocks.CHERRY_LAMP,
                ModBlocks.BAMBOO_LAMP,
                ModBlocks.CRIMSON_LAMP,
                ModBlocks.WARPED_LAMP,
                ModBlocks.IRON_LAMP,
                ModBlocks.GLASS_LAMP,
                ModBlocks.UNDEAD_LAMP,
                ModBlocks.OMINOUS_LAMP
        );
    }
}

