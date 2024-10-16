package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // This allows our planked walls to be used in any recipe that uses planks like making sticks and such.
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.OAK_PLANKED_WALL.asItem())
                .add(ModBlocks.SPRUCE_PLANKED_WALL.asItem())
                .add(ModBlocks.BIRCH_PLANKED_WALL.asItem())
                .add(ModBlocks.JUNGLE_PLANKED_WALL.asItem())
                .add(ModBlocks.ACACIA_PLANKED_WALL.asItem())
                .add(ModBlocks.DARK_OAK_PLANKED_WALL.asItem())
                .add(ModBlocks.MANGROVE_PLANKED_WALL.asItem())
                .add(ModBlocks.CHERRY_PLANKED_WALL.asItem())
                .add(ModBlocks.BAMBOO_PLANKED_WALL.asItem())
                .add(ModBlocks.CRIMSON_PLANKED_WALL.asItem())
                .add(ModBlocks.WARPED_PLANKED_WALL.asItem());
        getOrCreateTagBuilder(ModTags.Items.LAMPS)
//        .add(ModBlocks.WHITE_LAMP.asItem())
//        .add(ModBlocks.ORANGE_LAMP.asItem())
//        .add(ModBlocks.MAGENTA_LAMP.asItem())
//        .add(ModBlocks.LIGHT_BLUE_LAMP.asItem())
//        .add(ModBlocks.YELLOW_LAMP.asItem())
//        .add(ModBlocks.LIME_LAMP.asItem())
//        .add(ModBlocks.PINK_LAMP.asItem())
//        .add(ModBlocks.GRAY_LAMP.asItem())
//        .add(ModBlocks.LIGHT_GRAY_LAMP.asItem())
//        .add(ModBlocks.CYAN_LAMP.asItem())
//        .add(ModBlocks.PURPLE_LAMP.asItem())
//        .add(ModBlocks.BLUE_LAMP.asItem())
//        .add(ModBlocks.BROWN_LAMP.asItem())
//        .add(ModBlocks.GREEN_LAMP.asItem())
//        .add(ModBlocks.RED_LAMP.asItem())
//        .add(ModBlocks.BLACK_LAMP.asItem())
        ;
    }
}
