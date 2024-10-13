package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // Planked Walls
        addDrop(ModBlocks.OAK_PLANKED_WALL);
        addDrop(ModBlocks.SPRUCE_PLANKED_WALL);
        addDrop(ModBlocks.BIRCH_PLANKED_WALL);
        addDrop(ModBlocks.JUNGLE_PLANKED_WALL);
        addDrop(ModBlocks.ACACIA_PLANKED_WALL);
        addDrop(ModBlocks.DARK_OAK_PLANKED_WALL);
        addDrop(ModBlocks.MANGROVE_PLANKED_WALL);
        addDrop(ModBlocks.CHERRY_PLANKED_WALL);
        addDrop(ModBlocks.BAMBOO_PLANKED_WALL);
        addDrop(ModBlocks.CRIMSON_PLANKED_WALL);
        addDrop(ModBlocks.WARPED_PLANKED_WALL);

        // Counters
        addDrop(ModBlocks.OAK_COUNTER);
        addDrop(ModBlocks.SPRUCE_COUNTER);
        addDrop(ModBlocks.BIRCH_COUNTER);
        addDrop(ModBlocks.JUNGLE_COUNTER);
        addDrop(ModBlocks.ACACIA_COUNTER);
        addDrop(ModBlocks.DARK_OAK_COUNTER);
        addDrop(ModBlocks.MANGROVE_COUNTER);
        addDrop(ModBlocks.CHERRY_COUNTER);
        addDrop(ModBlocks.BAMBOO_COUNTER);
        addDrop(ModBlocks.CRIMSON_COUNTER);
        addDrop(ModBlocks.WARPED_COUNTER);

        // Storage Counters
        addDrop(ModBlocks.OAK_STORAGE_COUNTER);

        // Lamps
        addDrop(ModBlocks.WHITE_LAMP);
        addDrop(ModBlocks.ORANGE_LAMP);
        addDrop(ModBlocks.MAGENTA_LAMP);
        addDrop(ModBlocks.LIGHT_BLUE_LAMP);
        addDrop(ModBlocks.YELLOW_LAMP);
        addDrop(ModBlocks.LIME_LAMP);
        addDrop(ModBlocks.PINK_LAMP);
        addDrop(ModBlocks.GRAY_LAMP);
        addDrop(ModBlocks.LIGHT_GRAY_LAMP);
        addDrop(ModBlocks.CYAN_LAMP);
        addDrop(ModBlocks.PURPLE_LAMP);
        addDrop(ModBlocks.BLUE_LAMP);
        addDrop(ModBlocks.BROWN_LAMP);
        addDrop(ModBlocks.GREEN_LAMP);
        addDrop(ModBlocks.RED_LAMP);
        addDrop(ModBlocks.BLACK_LAMP);

        // Sofas
        addDrop(ModBlocks.WHITE_SOFA);
        addDrop(ModBlocks.ORANGE_SOFA);
        addDrop(ModBlocks.MAGENTA_SOFA);
        addDrop(ModBlocks.LIGHT_BLUE_SOFA);
        addDrop(ModBlocks.YELLOW_SOFA);
        addDrop(ModBlocks.LIME_SOFA);
        addDrop(ModBlocks.PINK_SOFA);
        addDrop(ModBlocks.GRAY_SOFA);
        addDrop(ModBlocks.LIGHT_GRAY_SOFA);
        addDrop(ModBlocks.CYAN_SOFA);
        addDrop(ModBlocks.PURPLE_SOFA);
        addDrop(ModBlocks.BLUE_SOFA);
        addDrop(ModBlocks.BROWN_SOFA);
        addDrop(ModBlocks.GREEN_SOFA);
        addDrop(ModBlocks.RED_SOFA);
        addDrop(ModBlocks.BLACK_SOFA);
    }
}
