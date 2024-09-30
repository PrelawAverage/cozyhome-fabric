package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class MobBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MobBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                // Adding Planked Walls
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
                .add(ModBlocks.WARPED_PLANKED_WALL)

                // Adding Counters (For axe and pickaxe)
                .add(ModBlocks.OAK_COUNTER)
                .add(ModBlocks.SPRUCE_COUNTER)
                .add(ModBlocks.BIRCH_COUNTER)
                .add(ModBlocks.JUNGLE_COUNTER)
                .add(ModBlocks.ACACIA_COUNTER)
                .add(ModBlocks.DARK_OAK_COUNTER)
                .add(ModBlocks.MANGROVE_COUNTER)
                .add(ModBlocks.CHERRY_COUNTER)
                .add(ModBlocks.BAMBOO_COUNTER)
                .add(ModBlocks.CRIMSON_COUNTER)
                .add(ModBlocks.WARPED_COUNTER)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                // Adding Counters (For axe and pickaxe)
                .add(ModBlocks.OAK_COUNTER)
                .add(ModBlocks.SPRUCE_COUNTER)
                .add(ModBlocks.BIRCH_COUNTER)
                .add(ModBlocks.JUNGLE_COUNTER)
                .add(ModBlocks.ACACIA_COUNTER)
                .add(ModBlocks.DARK_OAK_COUNTER)
                .add(ModBlocks.MANGROVE_COUNTER)
                .add(ModBlocks.CHERRY_COUNTER)
                .add(ModBlocks.BAMBOO_COUNTER)
                .add(ModBlocks.CRIMSON_COUNTER)
                .add(ModBlocks.WARPED_COUNTER)
        ;
    }
}
