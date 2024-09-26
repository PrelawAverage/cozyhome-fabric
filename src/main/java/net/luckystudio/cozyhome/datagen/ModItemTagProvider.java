package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
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
    }
}
