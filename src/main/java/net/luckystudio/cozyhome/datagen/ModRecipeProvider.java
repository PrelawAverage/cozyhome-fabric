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
    public static void offerLampRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 1)
                .pattern("@")
                .pattern("#")
                .input('@', input1)
                .input('#', Items.LANTERN)
                .criterion(hasItem(Items.LANTERN), conditionsFromItem(Items.LANTERN))
                .offerTo(exporter);
    }
    // Criterion needs to be fixed to take in a tag instead of a singular item, instead it should be tag planks
    public static void offerCounterRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2) {
        ShapedRecipeJsonBuilder.create(
                        RecipeCategory.BUILDING_BLOCKS,
                        output, 3)
                .pattern("@@@")
                .pattern("# #")
                .pattern("###")
                .input('@', input1)
                .input('#', input2)
                .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
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

        // Lamps
        offerLampRecipe(exporter, ModBlocks.WHITE_LAMP, Items.RED_WOOL);
        offerLampRecipe(exporter, ModBlocks.ORANGE_LAMP, Items.ORANGE_WOOL);
        offerLampRecipe(exporter, ModBlocks.MAGENTA_LAMP, Items.MAGENTA_WOOL);
        offerLampRecipe(exporter, ModBlocks.LIGHT_BLUE_LAMP, Items.LIGHT_BLUE_WOOL);
        offerLampRecipe(exporter, ModBlocks.YELLOW_LAMP, Items.YELLOW_WOOL);
        offerLampRecipe(exporter, ModBlocks.LIME_LAMP, Items.LIME_WOOL);
        offerLampRecipe(exporter, ModBlocks.PINK_LAMP, Items.PINK_WOOL);
        offerLampRecipe(exporter, ModBlocks.GRAY_LAMP, Items.GRAY_WOOL);
        offerLampRecipe(exporter, ModBlocks.LIGHT_GRAY_LAMP, Items.LIGHT_GRAY_WOOL);
        offerLampRecipe(exporter, ModBlocks.CYAN_LAMP, Items.CYAN_WOOL);
        offerLampRecipe(exporter, ModBlocks.PURPLE_LAMP, Items.PURPLE_WOOL);
        offerLampRecipe(exporter, ModBlocks.BLUE_LAMP, Items.BLUE_WOOL);
        offerLampRecipe(exporter, ModBlocks.BROWN_LAMP, Items.BROWN_WOOL);
        offerLampRecipe(exporter, ModBlocks.GREEN_LAMP, Items.GREEN_WOOL);
        offerLampRecipe(exporter, ModBlocks.RED_LAMP, Items.RED_WOOL);
        offerLampRecipe(exporter, ModBlocks.BLACK_LAMP, Items.BLACK_WOOL);
    }
}
