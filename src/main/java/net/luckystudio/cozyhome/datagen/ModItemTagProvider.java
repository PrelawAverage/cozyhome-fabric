package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.item.ModItems;
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
        getOrCreateTagBuilder(ItemTags.DYEABLE)
                .add(ModItems.CUSHION)
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
                .add(ModBlocks.WARPED_SOFA.asItem());
                ;
    }
}
