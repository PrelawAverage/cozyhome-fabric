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

                .add(ModBlocks.WHITE_SOFA)
                .add(ModBlocks.ORANGE_SOFA)
                .add(ModBlocks.MAGENTA_SOFA)
                .add(ModBlocks.LIGHT_BLUE_SOFA)
                .add(ModBlocks.YELLOW_SOFA)
                .add(ModBlocks.LIME_SOFA)
                .add(ModBlocks.PINK_SOFA)
                .add(ModBlocks.GRAY_SOFA)
                .add(ModBlocks.LIGHT_GRAY_SOFA)
                .add(ModBlocks.CYAN_SOFA)
                .add(ModBlocks.PURPLE_SOFA)
                .add(ModBlocks.BLUE_SOFA)
                .add(ModBlocks.BROWN_SOFA)
                .add(ModBlocks.GREEN_SOFA)
                .add(ModBlocks.RED_SOFA)
                .add(ModBlocks.BLACK_SOFA);
    }
}
