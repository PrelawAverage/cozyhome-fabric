package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.item.ModItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.DYEABLE)
                .add(ModItems.PAINT_BRUSH)
                .add(ModItems.CUSHION)

                .add(ModBlocks.OAK_LAMP.asItem())
                .add(ModBlocks.SPRUCE_LAMP.asItem())
                .add(ModBlocks.BIRCH_LAMP.asItem())
                .add(ModBlocks.JUNGLE_LAMP.asItem())
                .add(ModBlocks.ACACIA_LAMP.asItem())
                .add(ModBlocks.DARK_OAK_LAMP.asItem())
                .add(ModBlocks.MANGROVE_LAMP.asItem())
                .add(ModBlocks.CHERRY_LAMP.asItem())
                .add(ModBlocks.BAMBOO_LAMP.asItem())
                .add(ModBlocks.CRIMSON_LAMP.asItem())
                .add(ModBlocks.WARPED_LAMP.asItem())
                .add(ModBlocks.IRON_LAMP.asItem())
                .add(ModBlocks.GLASS_LAMP.asItem())
                .add(ModBlocks.UNDEAD_LAMP.asItem())
                .add(ModBlocks.OMINOUS_LAMP.asItem())

                .add(ModBlocks.OAK_SOFA.asItem())
                .add(ModBlocks.SPRUCE_SOFA.asItem())
                .add(ModBlocks.BIRCH_SOFA.asItem())
                .add(ModBlocks.JUNGLE_SOFA.asItem())
                .add(ModBlocks.ACACIA_SOFA.asItem())
                .add(ModBlocks.DARK_OAK_SOFA.asItem())
                .add(ModBlocks.MANGROVE_SOFA.asItem())
                .add(ModBlocks.CHERRY_SOFA.asItem())
                .add(ModBlocks.BAMBOO_SOFA.asItem())
                .add(ModBlocks.CRIMSON_SOFA.asItem())
                .add(ModBlocks.WARPED_SOFA.asItem())

                .add(ModBlocks.OAK_COUCH.asItem())
                .add(ModBlocks.SPRUCE_COUCH.asItem())
                .add(ModBlocks.BIRCH_COUCH.asItem())
                .add(ModBlocks.JUNGLE_COUCH.asItem())
                .add(ModBlocks.ACACIA_COUCH.asItem())
                .add(ModBlocks.DARK_OAK_COUCH.asItem())
                .add(ModBlocks.MANGROVE_COUCH.asItem())
                .add(ModBlocks.CHERRY_COUCH.asItem())
                .add(ModBlocks.BAMBOO_COUCH.asItem())
                .add(ModBlocks.CRIMSON_COUCH.asItem())
                .add(ModBlocks.WARPED_COUCH.asItem())
        ;
    }
}
