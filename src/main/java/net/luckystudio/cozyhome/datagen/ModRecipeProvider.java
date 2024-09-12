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

    public static void getPlankedWallRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(
                category,
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
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OAK_PLANKED_WALL, Blocks.OAK_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPRUCE_PLANKED_WALL, Blocks.SPRUCE_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIRCH_PLANKED_WALL, Blocks.BIRCH_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNGLE_PLANKED_WALL, Blocks.JUNGLE_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ACACIA_PLANKED_WALL, Blocks.ACACIA_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_OAK_PLANKED_WALL, Blocks.DARK_OAK_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANGROVE_PLANKED_WALL, Blocks.MANGROVE_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BAMBOO_PLANKED_WALL, Blocks.BAMBOO_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRIMSON_PLANKED_WALL, Blocks.CRIMSON_PLANKS);
        getPlankedWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WARPED_PLANKED_WALL, Blocks.WARPED_PLANKS);

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
    }
}
