package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class MobBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MobBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ModTags.Blocks.TUCKABLE)
                .add(Blocks.AIR)
                .add(Blocks.SCAFFOLDING);
        getOrCreateTagBuilder(ModTags.Blocks.TUCKABLE_DIRECTIONAL)
                .add(ModBlocks.DARK_OAK_PLANKED_WALL);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.OAK_PLANKED_WALL)
                .add(ModBlocks.SPRUCE_PLANKED_WALL)
                .add(ModBlocks.BIRCH_PLANKED_WALL)
                .add(ModBlocks.JUNGLE_PLANKED_WALL)
                .add(ModBlocks.ACACIA_PLANKED_WALL)
                .add(ModBlocks.DARK_OAK_PLANKED_WALL)
                .add(ModBlocks.MANGROVE_PLANKED_WALL)
                .add(ModBlocks.CHERRY_PLANKED_WALL)
                .add(ModBlocks.BAMBOO_PLANKED_WALL)
                .add(ModBlocks.CRIMSON_PLANKED_WALL)
                .add(ModBlocks.WARPED_PLANKED_WALL);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.OAK_PLANKED_WALL)
                .add(ModBlocks.SPRUCE_PLANKED_WALL)
                .add(ModBlocks.BIRCH_PLANKED_WALL)
                .add(ModBlocks.JUNGLE_PLANKED_WALL)
                .add(ModBlocks.ACACIA_PLANKED_WALL)
                .add(ModBlocks.DARK_OAK_PLANKED_WALL)
                .add(ModBlocks.MANGROVE_PLANKED_WALL)
                .add(ModBlocks.CHERRY_PLANKED_WALL)
                .add(ModBlocks.BAMBOO_PLANKED_WALL)
                .add(ModBlocks.CRIMSON_PLANKED_WALL)
                .add(ModBlocks.WARPED_PLANKED_WALL);
    }
}
