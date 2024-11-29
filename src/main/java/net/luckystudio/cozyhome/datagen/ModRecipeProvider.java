package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

// Criterion is the necessary function needed to unlock the recipe.

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void offerPlankedWallRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                output, 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerCounterRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("@@@")
                .pattern("# #")
                .pattern("###")
                .input('@', input1)
                .input('#', input2)
                .criterion(hasItem(input1), conditionsFromItem(input1))
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    public static void offerStorageCounterRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("@@@")
                .pattern("#C#")
                .pattern("###")
                .input('@', input1)
                .input('#', input2)
                .input('C', Items.CHEST)
                .criterion(hasItem(input1), conditionsFromItem(input1))
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    public static void offerSinkCounterRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("@@@")
                .pattern("#C#")
                .pattern("###")
                .input('@', input1)
                .input('#', input2)
                .input('C', Items.CAULDRON)
                .criterion(hasItem(input1), conditionsFromItem(input1))
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    public static void offerWallClockRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern(" # ")
                .pattern("#C#")
                .pattern(" # ")
                .input('#', input)
                .input('C', Items.CLOCK)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerGrandfatherClockRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern(" @ ")
                .pattern(" # ")
                .pattern(" # ")
                .input('@', input)
                .input('#', input2)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerDeskRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("@@@")
                .pattern("# #")
                .pattern("# #")
                .input('@', input1)
                .input('#', input2)
                .criterion(hasItem(input1), conditionsFromItem(input1))
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    public static void offerDrawerRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("@@@")
                .pattern("#C#")
                .pattern("# #")
                .input('@', input1)
                .input('#', input2)
                .input('C', Items.CHEST)
                .criterion(hasItem(input1), conditionsFromItem(input1))
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerWallMirrorRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("###")
                .pattern("#@#")
                .pattern("###")
                .input('@', input1)
                .input('#', input2)
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerToolRackRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("#@#")
                .input('@', input)
                .input('#', input2)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerBeamRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // Planked Walls
        offerPlankedWallRecipe(exporter, ModBlocks.OAK_PLANKED_WALL, Blocks.OAK_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.SPRUCE_PLANKED_WALL, Blocks.SPRUCE_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.BIRCH_PLANKED_WALL, Blocks.BIRCH_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.JUNGLE_PLANKED_WALL, Blocks.JUNGLE_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.ACACIA_PLANKED_WALL, Blocks.ACACIA_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.DARK_OAK_PLANKED_WALL, Blocks.DARK_OAK_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.MANGROVE_PLANKED_WALL, Blocks.MANGROVE_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.BAMBOO_PLANKED_WALL, Blocks.BAMBOO_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.CRIMSON_PLANKED_WALL, Blocks.CRIMSON_PLANKS);
        offerPlankedWallRecipe(exporter, ModBlocks.WARPED_PLANKED_WALL, Blocks.WARPED_PLANKS);

        // Planked Walls to regular vanilla planks
        offerSingleOutputShapelessRecipe(exporter, Items.OAK_PLANKS, ModBlocks.OAK_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.SPRUCE_PLANKS, ModBlocks.SPRUCE_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.BIRCH_PLANKS, ModBlocks.BIRCH_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.JUNGLE_PLANKS, ModBlocks.JUNGLE_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.ACACIA_PLANKS, ModBlocks.ACACIA_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.MANGROVE_PLANKS, ModBlocks.MANGROVE_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.CHERRY_PLANKS, ModBlocks.CHERRY_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.BAMBOO_PLANKS, ModBlocks.BAMBOO_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.CRIMSON_PLANKS, ModBlocks.CRIMSON_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());
        offerSingleOutputShapelessRecipe(exporter, Items.WARPED_PLANKS, ModBlocks.WARPED_PLANKED_WALL, RecipeCategory.BUILDING_BLOCKS.getName());

        // Counters
        offerCounterRecipe(exporter, ModBlocks.OAK_COUNTER, Blocks.BRICKS, Blocks.OAK_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.SPRUCE_COUNTER, Blocks.POLISHED_ANDESITE, Blocks.SPRUCE_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.BIRCH_COUNTER, Blocks.POLISHED_GRANITE, Blocks.BIRCH_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.JUNGLE_COUNTER, Blocks.POLISHED_GRANITE, Blocks.JUNGLE_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.ACACIA_COUNTER, Blocks.POLISHED_DIORITE, Blocks.ACACIA_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.DARK_OAK_COUNTER, Blocks.DEEPSLATE_TILES, Blocks.DARK_OAK_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.MANGROVE_COUNTER, Blocks.PACKED_MUD, Blocks.MANGROVE_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.BAMBOO_COUNTER, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.CRIMSON_COUNTER, Blocks.NETHER_WART_BLOCK, Blocks.CRIMSON_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.WARPED_COUNTER, Blocks.WARPED_WART_BLOCK, Blocks.WARPED_PLANKS);

        // Storage Counters
        offerStorageCounterRecipe(exporter, ModBlocks.OAK_STORAGE_COUNTER, Blocks.BRICKS, Blocks.OAK_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.SPRUCE_STORAGE_COUNTER, Blocks.POLISHED_ANDESITE, Blocks.SPRUCE_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.BIRCH_STORAGE_COUNTER, Blocks.POLISHED_GRANITE, Blocks.BIRCH_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.JUNGLE_STORAGE_COUNTER, Blocks.POLISHED_GRANITE, Blocks.JUNGLE_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.ACACIA_STORAGE_COUNTER, Blocks.POLISHED_DIORITE, Blocks.ACACIA_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.DARK_OAK_STORAGE_COUNTER, Blocks.DEEPSLATE_TILES, Blocks.DARK_OAK_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.MANGROVE_STORAGE_COUNTER, Blocks.PACKED_MUD, Blocks.MANGROVE_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.BAMBOO_STORAGE_COUNTER, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.CRIMSON_STORAGE_COUNTER, Blocks.NETHER_WART_BLOCK, Blocks.CRIMSON_PLANKS);
        offerStorageCounterRecipe(exporter, ModBlocks.WARPED_STORAGE_COUNTER, Blocks.WARPED_WART_BLOCK, Blocks.WARPED_PLANKS);

        // Sink Counters
        offerSinkCounterRecipe(exporter, ModBlocks.OAK_SINK_COUNTER, Blocks.BRICKS, Blocks.OAK_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.SPRUCE_SINK_COUNTER, Blocks.POLISHED_ANDESITE, Blocks.SPRUCE_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.BIRCH_SINK_COUNTER, Blocks.POLISHED_GRANITE, Blocks.BIRCH_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.JUNGLE_SINK_COUNTER, Blocks.POLISHED_GRANITE, Blocks.JUNGLE_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.ACACIA_SINK_COUNTER, Blocks.POLISHED_DIORITE, Blocks.ACACIA_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.DARK_OAK_SINK_COUNTER, Blocks.DEEPSLATE_TILES, Blocks.DARK_OAK_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.MANGROVE_SINK_COUNTER, Blocks.PACKED_MUD, Blocks.MANGROVE_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.BAMBOO_SINK_COUNTER, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.CRIMSON_SINK_COUNTER, Blocks.NETHER_WART_BLOCK, Blocks.CRIMSON_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.WARPED_SINK_COUNTER, Blocks.WARPED_WART_BLOCK, Blocks.WARPED_PLANKS);

        // Wall Clock
        offerWallClockRecipe(exporter, ModBlocks.OAK_WALL_CLOCK, Blocks.OAK_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.SPRUCE_WALL_CLOCK, Blocks.SPRUCE_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.BIRCH_WALL_CLOCK, Blocks.BIRCH_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.JUNGLE_WALL_CLOCK, Blocks.JUNGLE_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.ACACIA_WALL_CLOCK, Blocks.ACACIA_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.DARK_OAK_WALL_CLOCK, Blocks.DARK_OAK_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.MANGROVE_WALL_CLOCK, Blocks.MANGROVE_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.BAMBOO_WALL_CLOCK, Blocks.BAMBOO_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.CRIMSON_WALL_CLOCK, Blocks.CRIMSON_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.WARPED_WALL_CLOCK, Blocks.WARPED_SLAB);

        // Grandfather Clock
        offerGrandfatherClockRecipe(exporter, ModBlocks.OAK_GRANDFATHER_CLOCK, ModBlocks.OAK_WALL_CLOCK, Blocks.OAK_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.SPRUCE_GRANDFATHER_CLOCK, ModBlocks.SPRUCE_WALL_CLOCK, Blocks.SPRUCE_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.BIRCH_GRANDFATHER_CLOCK, ModBlocks.BIRCH_WALL_CLOCK, Blocks.BIRCH_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.JUNGLE_GRANDFATHER_CLOCK, ModBlocks.JUNGLE_WALL_CLOCK, Blocks.JUNGLE_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.ACACIA_GRANDFATHER_CLOCK, ModBlocks.ACACIA_WALL_CLOCK, Blocks.ACACIA_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.DARK_OAK_GRANDFATHER_CLOCK, ModBlocks.DARK_OAK_WALL_CLOCK, Blocks.DARK_OAK_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.MANGROVE_GRANDFATHER_CLOCK, ModBlocks.MANGROVE_WALL_CLOCK, Blocks.MANGROVE_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.BAMBOO_GRANDFATHER_CLOCK, ModBlocks.BAMBOO_WALL_CLOCK, Blocks.BAMBOO_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.CRIMSON_GRANDFATHER_CLOCK, ModBlocks.CRIMSON_WALL_CLOCK, Blocks.CRIMSON_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.WARPED_GRANDFATHER_CLOCK, ModBlocks.WARPED_WALL_CLOCK, Blocks.WARPED_PLANKS);

        // Desk
        offerDeskRecipe(exporter, ModBlocks.OAK_DESK, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.SPRUCE_DESK, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.BIRCH_DESK, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.JUNGLE_DESK, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.ACACIA_DESK, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.DARK_OAK_DESK, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.MANGROVE_DESK, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.BAMBOO_DESK, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.CRIMSON_DESK, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.WARPED_DESK, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);

        // Drawer
        offerDrawerRecipe(exporter, ModBlocks.OAK_DRAWER, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.SPRUCE_DRAWER, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.BIRCH_DRAWER, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.JUNGLE_DRAWER, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.ACACIA_DRAWER, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.DARK_OAK_DRAWER, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.MANGROVE_DRAWER, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.BAMBOO_DRAWER, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.CRIMSON_DRAWER, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.WARPED_DRAWER, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);

        // Tool Racks
        offerToolRackRecipe(exporter, ModBlocks.OAK_TOOL_RACK, Blocks.OAK_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.SPRUCE_TOOL_RACK, Blocks.SPRUCE_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.BIRCH_TOOL_RACK, Blocks.BIRCH_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.JUNGLE_TOOL_RACK, Blocks.JUNGLE_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.ACACIA_TOOL_RACK, Blocks.ACACIA_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.DARK_OAK_TOOL_RACK, Blocks.DARK_OAK_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.MANGROVE_TOOL_RACK, Blocks.MANGROVE_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.BAMBOO_TOOL_RACK, Blocks.BAMBOO_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.CRIMSON_TOOL_RACK, Blocks.CRIMSON_SLAB, Items.IRON_INGOT);
        offerToolRackRecipe(exporter, ModBlocks.WARPED_TOOL_RACK, Blocks.WARPED_SLAB, Items.IRON_INGOT);

        // Beams
        offerBeamRecipe(exporter, ModBlocks.OAK_BEAM, Blocks.OAK_LOG);
        offerBeamRecipe(exporter, ModBlocks.SPRUCE_BEAM, Blocks.SPRUCE_LOG);
        offerBeamRecipe(exporter, ModBlocks.BIRCH_BEAM, Blocks.BIRCH_LOG);
        offerBeamRecipe(exporter, ModBlocks.JUNGLE_BEAM, Blocks.JUNGLE_LOG);
        offerBeamRecipe(exporter, ModBlocks.ACACIA_BEAM, Blocks.ACACIA_LOG);
        offerBeamRecipe(exporter, ModBlocks.DARK_OAK_BEAM, Blocks.DARK_OAK_LOG);
        offerBeamRecipe(exporter, ModBlocks.MANGROVE_BEAM, Blocks.MANGROVE_LOG);
        offerBeamRecipe(exporter, ModBlocks.BAMBOO_BEAM, Blocks.BAMBOO_BLOCK);
        offerBeamRecipe(exporter, ModBlocks.CRIMSON_BEAM, Blocks.CRIMSON_STEM);
        offerBeamRecipe(exporter, ModBlocks.WARPED_BEAM, Blocks.WARPED_STEM);

        // Wall Mirrors
        offerWallMirrorRecipe(exporter, ModBlocks.OAK_WALL_MIRROR, Blocks.GLASS ,Blocks.OAK_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.SPRUCE_WALL_MIRROR, Blocks.GLASS ,Blocks.SPRUCE_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.BIRCH_WALL_MIRROR, Blocks.GLASS ,Blocks.BIRCH_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.ACACIA_WALL_MIRROR, Blocks.GLASS ,Blocks.ACACIA_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.DARK_OAK_WALL_MIRROR, Blocks.GLASS ,Blocks.DARK_OAK_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.MANGROVE_WALL_MIRROR, Blocks.GLASS ,Blocks.MANGROVE_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.CHERRY_WALL_MIRROR, Blocks.GLASS ,Blocks.CHERRY_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.BAMBOO_WALL_MIRROR, Blocks.GLASS ,Blocks.BAMBOO_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.CRIMSON_WALL_MIRROR, Blocks.GLASS ,Blocks.CRIMSON_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.WARPED_WALL_MIRROR, Blocks.GLASS ,Blocks.WARPED_PLANKS);
    }
}
