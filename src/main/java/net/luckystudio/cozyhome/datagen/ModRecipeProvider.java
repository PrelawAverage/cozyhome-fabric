package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
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

    public static void offerTableRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 4)
                .pattern("###")
                .pattern("@ @")
                .pattern("@ @")
                .input('@', input1)
                .input('#', input2)
                .criterion(hasItem(input1), conditionsFromItem(input1))
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    public static void offerChairRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 4)
                .pattern("#  ")
                .pattern("@@@")
                .pattern("# #")
                .input('#', input1)
                .input('@', input2)
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

    public static void offerLampRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2, ItemConvertible input3) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 1)
                .pattern("#")
                .pattern("@")
                .pattern("C")
                .input('#', input)
                .input('@', input2)
                .input('C', input3)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerSofaRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 1)
                .pattern("@@")
                .pattern("@@")
                .pattern("##")
                .input('@', Items.WHITE_WOOL)
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerCouchRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 1)
                .pattern("@@")
                .pattern("##")
                .input('@', Items.WHITE_WOOL)
                .input('#', input)
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
    public static void offerSinkRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 1)
                .pattern("@@ ")
                .pattern("# #")
                .pattern("###")
                .input('@', input1)
                .input('#', input2)
                .criterion(hasItem(input2), conditionsFromItem(input2))
                .offerTo(exporter);
    }

    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerLargeStumpRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 6)
                .pattern("###")
                .pattern(" # ")
                .pattern(" # ")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerChimneyRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("# #")
                .pattern("# #")
                .pattern("# #")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }


    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerCushionRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 1)
                .pattern(" # ")
                .pattern("#@#")
                .pattern(" # ")
                .input('@', input)
                .input('#', input2)
                .criterion(hasItem(Items.SPYGLASS), conditionsFromItem(Items.SPYGLASS))
                .offerTo(exporter);
    }

    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerTelescopeRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.TELESCOPE, 1)
                .pattern(" # ")
                .pattern(" @ ")
                .pattern("@ @")
                .input('#', Items.SPYGLASS)
                .input('@', Items.COPPER_INGOT)
                .criterion(hasItem(Items.SPYGLASS), conditionsFromItem(Items.SPYGLASS))
                .offerTo(exporter);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // Counters
        offerCounterRecipe(exporter, ModBlocks.OAK_COUNTER, Blocks.BRICKS, Blocks.OAK_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.SPRUCE_COUNTER, Blocks.POLISHED_ANDESITE, Blocks.SPRUCE_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.BIRCH_COUNTER, Blocks.POLISHED_GRANITE, Blocks.BIRCH_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.JUNGLE_COUNTER, Blocks.POLISHED_GRANITE, Blocks.JUNGLE_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.ACACIA_COUNTER, Blocks.POLISHED_DIORITE, Blocks.ACACIA_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.DARK_OAK_COUNTER, Blocks.DEEPSLATE_TILES, Blocks.DARK_OAK_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.MANGROVE_COUNTER, Blocks.PACKED_MUD, Blocks.MANGROVE_PLANKS);
        offerCounterRecipe(exporter, ModBlocks.CHERRY_COUNTER, Blocks.CALCITE, Blocks.CHERRY_PLANKS);
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
        offerStorageCounterRecipe(exporter, ModBlocks.CHERRY_STORAGE_COUNTER, Blocks.CALCITE, Blocks.CHERRY_PLANKS);
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
        offerSinkCounterRecipe(exporter, ModBlocks.CHERRY_SINK_COUNTER, Blocks.CALCITE, Blocks.CHERRY_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.BAMBOO_SINK_COUNTER, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.CRIMSON_SINK_COUNTER, Blocks.NETHER_WART_BLOCK, Blocks.CRIMSON_PLANKS);
        offerSinkCounterRecipe(exporter, ModBlocks.WARPED_SINK_COUNTER, Blocks.WARPED_WART_BLOCK, Blocks.WARPED_PLANKS);

        // Tables
        offerTableRecipe(exporter, ModBlocks.OAK_TABLE, Blocks.OAK_PLANKS, Blocks.OAK_SLAB);
        offerTableRecipe(exporter, ModBlocks.SPRUCE_TABLE, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB);
        offerTableRecipe(exporter, ModBlocks.BIRCH_TABLE, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB);
        offerTableRecipe(exporter, ModBlocks.JUNGLE_TABLE, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB);
        offerTableRecipe(exporter, ModBlocks.ACACIA_TABLE, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB);
        offerTableRecipe(exporter, ModBlocks.DARK_OAK_TABLE, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB);
        offerTableRecipe(exporter, ModBlocks.MANGROVE_TABLE, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB);
        offerTableRecipe(exporter, ModBlocks.CHERRY_TABLE, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB);
        offerTableRecipe(exporter, ModBlocks.BAMBOO_TABLE, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB);
        offerTableRecipe(exporter, ModBlocks.CRIMSON_TABLE, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB);
        offerTableRecipe(exporter, ModBlocks.WARPED_TABLE, Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB);
        offerTableRecipe(exporter, ModBlocks.IRON_TABLE, Items.IRON_INGOT, Items.IRON_NUGGET);
        offerTableRecipe(exporter, ModBlocks.GLASS_TABLE, Blocks.GLASS, Blocks.GLASS_PANE);

        // Chairs
        offerChairRecipe(exporter, ModBlocks.OAK_CHAIR, Blocks.OAK_PLANKS, Blocks.OAK_SLAB);
        offerChairRecipe(exporter, ModBlocks.SPRUCE_CHAIR, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB);
        offerChairRecipe(exporter, ModBlocks.BIRCH_CHAIR, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB);
        offerChairRecipe(exporter, ModBlocks.JUNGLE_CHAIR, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB);
        offerChairRecipe(exporter, ModBlocks.ACACIA_CHAIR, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB);
        offerChairRecipe(exporter, ModBlocks.DARK_OAK_CHAIR, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB);
        offerChairRecipe(exporter, ModBlocks.MANGROVE_CHAIR, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB);
        offerChairRecipe(exporter, ModBlocks.CHERRY_CHAIR, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB);
        offerChairRecipe(exporter, ModBlocks.BAMBOO_CHAIR, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB);
        offerChairRecipe(exporter, ModBlocks.CRIMSON_CHAIR, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB);
        offerChairRecipe(exporter, ModBlocks.WARPED_CHAIR, Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB);

        // Wall Clocks
        offerWallClockRecipe(exporter, ModBlocks.OAK_WALL_CLOCK, Blocks.OAK_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.SPRUCE_WALL_CLOCK, Blocks.SPRUCE_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.BIRCH_WALL_CLOCK, Blocks.BIRCH_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.JUNGLE_WALL_CLOCK, Blocks.JUNGLE_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.ACACIA_WALL_CLOCK, Blocks.ACACIA_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.DARK_OAK_WALL_CLOCK, Blocks.DARK_OAK_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.MANGROVE_WALL_CLOCK, Blocks.MANGROVE_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.CHERRY_WALL_CLOCK, Blocks.CHERRY_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.BAMBOO_WALL_CLOCK, Blocks.BAMBOO_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.CRIMSON_WALL_CLOCK, Blocks.CRIMSON_SLAB);
        offerWallClockRecipe(exporter, ModBlocks.WARPED_WALL_CLOCK, Blocks.WARPED_SLAB);

        // Grandfather Clocks
        offerGrandfatherClockRecipe(exporter, ModBlocks.OAK_GRANDFATHER_CLOCK, ModBlocks.OAK_WALL_CLOCK, Blocks.OAK_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.SPRUCE_GRANDFATHER_CLOCK, ModBlocks.SPRUCE_WALL_CLOCK, Blocks.SPRUCE_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.BIRCH_GRANDFATHER_CLOCK, ModBlocks.BIRCH_WALL_CLOCK, Blocks.BIRCH_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.JUNGLE_GRANDFATHER_CLOCK, ModBlocks.JUNGLE_WALL_CLOCK, Blocks.JUNGLE_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.ACACIA_GRANDFATHER_CLOCK, ModBlocks.ACACIA_WALL_CLOCK, Blocks.ACACIA_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.DARK_OAK_GRANDFATHER_CLOCK, ModBlocks.DARK_OAK_WALL_CLOCK, Blocks.DARK_OAK_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.MANGROVE_GRANDFATHER_CLOCK, ModBlocks.MANGROVE_WALL_CLOCK, Blocks.MANGROVE_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.CHERRY_GRANDFATHER_CLOCK, ModBlocks.CHERRY_WALL_CLOCK, Blocks.CHERRY_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.BAMBOO_GRANDFATHER_CLOCK, ModBlocks.BAMBOO_WALL_CLOCK, Blocks.BAMBOO_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.CRIMSON_GRANDFATHER_CLOCK, ModBlocks.CRIMSON_WALL_CLOCK, Blocks.CRIMSON_PLANKS);
        offerGrandfatherClockRecipe(exporter, ModBlocks.WARPED_GRANDFATHER_CLOCK, ModBlocks.WARPED_WALL_CLOCK, Blocks.WARPED_PLANKS);

        // Lamps
        offerLampRecipe(exporter, ModBlocks.OAK_LAMP, Blocks.WHITE_WOOL, Blocks.LANTERN, Blocks.OAK_PLANKS);
        offerLampRecipe(exporter, ModBlocks.SPRUCE_LAMP, Blocks.WHITE_WOOL, Blocks.LANTERN, Blocks.SPRUCE_PLANKS);
        offerLampRecipe(exporter, ModBlocks.BIRCH_LAMP, Blocks.WHITE_WOOL, Blocks.LANTERN, Blocks.BIRCH_PLANKS);
        offerLampRecipe(exporter, ModBlocks.JUNGLE_LAMP, Blocks.WHITE_WOOL, Blocks.CANDLE, Blocks.JUNGLE_PLANKS);
        offerLampRecipe(exporter, ModBlocks.ACACIA_LAMP, Blocks.WHITE_WOOL, Blocks.LANTERN, Blocks.ACACIA_PLANKS);
        offerLampRecipe(exporter, ModBlocks.DARK_OAK_LAMP, Blocks.WHITE_WOOL, Blocks.LANTERN, Blocks.DARK_OAK_PLANKS);
        offerLampRecipe(exporter, ModBlocks.MANGROVE_LAMP, Items.PAPER, Blocks.CANDLE, Blocks.MANGROVE_PLANKS);
        offerLampRecipe(exporter, ModBlocks.CHERRY_LAMP, Blocks.WHITE_WOOL, Blocks.LANTERN, Blocks.CHERRY_PLANKS);
        offerLampRecipe(exporter, ModBlocks.BAMBOO_LAMP, Blocks.BAMBOO_PLANKS, Blocks.CANDLE, Blocks.BAMBOO_PLANKS);
        offerLampRecipe(exporter, ModBlocks.CRIMSON_LAMP, Blocks.CRIMSON_FUNGUS, Blocks.CRIMSON_NYLIUM, Blocks.FLOWER_POT);
        offerLampRecipe(exporter, ModBlocks.WARPED_LAMP, Blocks.WARPED_FUNGUS, Blocks.WARPED_NYLIUM, Blocks.FLOWER_POT);

        // Sofas
        offerSofaRecipe(exporter, ModBlocks.OAK_SOFA, Blocks.OAK_SLAB);
        offerSofaRecipe(exporter, ModBlocks.SPRUCE_SOFA, Blocks.SPRUCE_SLAB);
        offerSofaRecipe(exporter, ModBlocks.BIRCH_SOFA, Blocks.BIRCH_SLAB);
        offerSofaRecipe(exporter, ModBlocks.JUNGLE_SOFA, Blocks.JUNGLE_SLAB);
        offerSofaRecipe(exporter, ModBlocks.ACACIA_SOFA, Blocks.ACACIA_SLAB);
        offerSofaRecipe(exporter, ModBlocks.DARK_OAK_SOFA, Blocks.DARK_OAK_SLAB);
        offerSofaRecipe(exporter, ModBlocks.MANGROVE_SOFA, Blocks.MANGROVE_SLAB);
        offerSofaRecipe(exporter, ModBlocks.CHERRY_SOFA, Blocks.CHERRY_SLAB);
        offerSofaRecipe(exporter, ModBlocks.BAMBOO_SOFA, Blocks.BAMBOO_SLAB);
        offerSofaRecipe(exporter, ModBlocks.CRIMSON_SOFA, Blocks.CRIMSON_SLAB);
        offerSofaRecipe(exporter, ModBlocks.WARPED_SOFA, Blocks.WARPED_SLAB);

        // Couches
        offerCouchRecipe(exporter, ModBlocks.OAK_COUCH, Blocks.OAK_SLAB);
        offerCouchRecipe(exporter, ModBlocks.SPRUCE_COUCH, Blocks.SPRUCE_SLAB);
        offerCouchRecipe(exporter, ModBlocks.BIRCH_COUCH, Blocks.BIRCH_SLAB);
        offerCouchRecipe(exporter, ModBlocks.JUNGLE_COUCH, Blocks.JUNGLE_SLAB);
        offerCouchRecipe(exporter, ModBlocks.ACACIA_COUCH, Blocks.ACACIA_SLAB);
        offerCouchRecipe(exporter, ModBlocks.DARK_OAK_COUCH, Blocks.DARK_OAK_SLAB);
        offerCouchRecipe(exporter, ModBlocks.MANGROVE_COUCH, Blocks.MANGROVE_SLAB);
        offerCouchRecipe(exporter, ModBlocks.CHERRY_COUCH, Blocks.CHERRY_SLAB);
        offerCouchRecipe(exporter, ModBlocks.BAMBOO_COUCH, Blocks.BAMBOO_SLAB);
        offerCouchRecipe(exporter, ModBlocks.CRIMSON_COUCH, Blocks.CRIMSON_SLAB);
        offerCouchRecipe(exporter, ModBlocks.WARPED_COUCH, Blocks.WARPED_SLAB);

        // Desks
        offerDeskRecipe(exporter, ModBlocks.OAK_DESK, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.SPRUCE_DESK, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.BIRCH_DESK, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.JUNGLE_DESK, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.ACACIA_DESK, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.DARK_OAK_DESK, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.MANGROVE_DESK, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.CHERRY_DESK, Blocks.CHERRY_SLAB, Blocks.CHERRY_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.BAMBOO_DESK, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.CRIMSON_DESK, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
        offerDeskRecipe(exporter, ModBlocks.WARPED_DESK, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);

        // Drawers
        offerDrawerRecipe(exporter, ModBlocks.OAK_DRAWER, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.SPRUCE_DRAWER, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.BIRCH_DRAWER, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.JUNGLE_DRAWER, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.ACACIA_DRAWER, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.DARK_OAK_DRAWER, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.MANGROVE_DRAWER, Blocks.MANGROVE_SLAB, Blocks.MANGROVE_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.CHERRY_DRAWER, Blocks.CHERRY_SLAB, Blocks.CHERRY_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.BAMBOO_DRAWER, Blocks.BAMBOO_SLAB, Blocks.BAMBOO_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.CRIMSON_DRAWER, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
        offerDrawerRecipe(exporter, ModBlocks.WARPED_DRAWER, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);

        // Wall Mirrors
        offerWallMirrorRecipe(exporter, ModBlocks.OAK_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.OAK_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.SPRUCE_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.SPRUCE_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.BIRCH_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.BIRCH_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.ACACIA_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.ACACIA_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.DARK_OAK_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.DARK_OAK_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.MANGROVE_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.MANGROVE_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.CHERRY_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.CHERRY_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.BAMBOO_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.BAMBOO_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.CRIMSON_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.CRIMSON_PLANKS);
        offerWallMirrorRecipe(exporter, ModBlocks.WARPED_WALL_MIRROR, Items.AMETHYST_SHARD, Blocks.WARPED_PLANKS);

        // Sinks
        offerSinkRecipe(exporter, ModBlocks.STONE_BRICK_SINK, Items.IRON_NUGGET, Blocks.STONE_BRICKS);
        offerSinkRecipe(exporter, ModBlocks.MOSSY_STONE_BRICK_SINK, Items.IRON_NUGGET, Blocks.MOSSY_STONE_BRICKS);
        offerSinkRecipe(exporter, ModBlocks.GRANITE_SINK, Items.IRON_NUGGET, Blocks.POLISHED_GRANITE);
        offerSinkRecipe(exporter, ModBlocks.DIORITE_SINK, Items.IRON_NUGGET, Blocks.POLISHED_DIORITE);
        offerSinkRecipe(exporter, ModBlocks.ANDESITE_SINK, Items.IRON_NUGGET, Blocks.POLISHED_ANDESITE);
        offerSinkRecipe(exporter, ModBlocks.DEEPSLATE_SINK, Items.IRON_NUGGET, Blocks.POLISHED_DEEPSLATE);
        offerSinkRecipe(exporter, ModBlocks.CALCITE_SINK, Items.IRON_NUGGET, Blocks.CALCITE);
        offerSinkRecipe(exporter, ModBlocks.TUFF_SINK, Items.IRON_NUGGET, Blocks.TUFF);
        offerSinkRecipe(exporter, ModBlocks.BRICK_SINK, Items.IRON_NUGGET, Blocks.BRICKS);
        offerSinkRecipe(exporter, ModBlocks.MUD_SINK, Items.IRON_NUGGET, Blocks.MUD);
        offerSinkRecipe(exporter, ModBlocks.SANDSTONE_SINK, Items.IRON_NUGGET, Blocks.SANDSTONE);
        offerSinkRecipe(exporter, ModBlocks.RED_SANDSTONE_SINK, Items.IRON_NUGGET, Blocks.RED_SANDSTONE);
        offerSinkRecipe(exporter, ModBlocks.PRISMARINE_SINK, Items.IRON_NUGGET, Blocks.PRISMARINE);
        offerSinkRecipe(exporter, ModBlocks.NETHER_BRICK_SINK, Items.IRON_NUGGET, Blocks.NETHER_BRICKS);
        offerSinkRecipe(exporter, ModBlocks.RED_NETHER_BRICK_SINK, Items.IRON_NUGGET, Blocks.RED_NETHER_BRICKS);
        offerSinkRecipe(exporter, ModBlocks.BLACKSTONE_SINK, Items.IRON_NUGGET, Blocks.BLACKSTONE);
        offerSinkRecipe(exporter, ModBlocks.ENDSTONE_SINK, Items.IRON_NUGGET, Blocks.END_STONE);
        offerSinkRecipe(exporter, ModBlocks.PURPUR_SINK, Items.IRON_NUGGET, Blocks.PURPUR_BLOCK);
        offerSinkRecipe(exporter, ModBlocks.IRON_SINK, Items.IRON_NUGGET, Blocks.IRON_BARS);

        // Large Stumps
        offerLargeStumpRecipe(exporter, ModBlocks.OAK_LARGE_STUMP, Blocks.OAK_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.SPRUCE_LARGE_STUMP, Blocks.SPRUCE_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.BIRCH_LARGE_STUMP, Blocks.BIRCH_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.JUNGLE_LARGE_STUMP, Blocks.JUNGLE_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.ACACIA_LARGE_STUMP, Blocks.ACACIA_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.DARK_OAK_LARGE_STUMP, Blocks.DARK_OAK_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.MANGROVE_LARGE_STUMP, Blocks.MANGROVE_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.CHERRY_LARGE_STUMP, Blocks.CHERRY_LOG);
        offerLargeStumpRecipe(exporter, ModBlocks.BAMBOO_LARGE_STUMP, Blocks.BAMBOO);
        offerLargeStumpRecipe(exporter, ModBlocks.CRIMSON_LARGE_STUMP, Blocks.CRIMSON_STEM);
        offerLargeStumpRecipe(exporter, ModBlocks.WARPED_LARGE_STUMP, Blocks.WARPED_STEM);

        // Chimneys
        offerChimneyRecipe(exporter, ModBlocks.STONE_BRICK_CHIMNEY, Blocks.STONE_BRICKS);
        offerChimneyRecipe(exporter, ModBlocks.MOSSY_STONE_BRICK_CHIMNEY, Blocks.MOSSY_STONE_BRICKS);
        offerChimneyRecipe(exporter, ModBlocks.GRANITE_CHIMNEY, Blocks.POLISHED_GRANITE);
        offerChimneyRecipe(exporter, ModBlocks.DIORITE_CHIMNEY, Blocks.POLISHED_DIORITE);
        offerChimneyRecipe(exporter, ModBlocks.ANDESITE_CHIMNEY, Blocks.POLISHED_ANDESITE);
        offerChimneyRecipe(exporter, ModBlocks.DEEPSLATE_CHIMNEY, Blocks.POLISHED_DEEPSLATE);
        offerChimneyRecipe(exporter, ModBlocks.TUFF_CHIMNEY, Blocks.TUFF);
        offerChimneyRecipe(exporter, ModBlocks.BRICK_CHIMNEY, Blocks.BRICKS);
        offerChimneyRecipe(exporter, ModBlocks.MUD_CHIMNEY, Blocks.MUD);
        offerChimneyRecipe(exporter, ModBlocks.SANDSTONE_CHIMNEY, Blocks.SANDSTONE);
        offerChimneyRecipe(exporter, ModBlocks.RED_SANDSTONE_CHIMNEY, Blocks.RED_SANDSTONE);
        offerChimneyRecipe(exporter, ModBlocks.PRISMARINE_CHIMNEY, Blocks.PRISMARINE);
        offerChimneyRecipe(exporter, ModBlocks.NETHER_BRICK_CHIMNEY, Blocks.NETHER_BRICKS);
        offerChimneyRecipe(exporter, ModBlocks.RED_NETHER_BRICK_CHIMNEY, Blocks.RED_NETHER_BRICKS);
        offerChimneyRecipe(exporter, ModBlocks.BLACKSTONE_CHIMNEY, Blocks.BLACKSTONE);
        offerChimneyRecipe(exporter, ModBlocks.ENDSTONE_CHIMNEY, Blocks.END_STONE);
        offerChimneyRecipe(exporter, ModBlocks.PURPUR_CHIMNEY, Blocks.PURPUR_BLOCK);

        offerCushionRecipe(exporter, ModItems.CUSHION, Blocks.WHITE_WOOL, Items.STRING);
        offerCushionRecipe(exporter, ModItems.HAY_CUSHION, Blocks.HAY_BLOCK, Items.STRING);

        offerTelescopeRecipe(exporter);
    }
}
