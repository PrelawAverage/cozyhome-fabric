package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyComponentsLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
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
        addDrop(ModBlocks.SPRUCE_STORAGE_COUNTER);
        addDrop(ModBlocks.BIRCH_STORAGE_COUNTER);
        addDrop(ModBlocks.JUNGLE_STORAGE_COUNTER);
        addDrop(ModBlocks.ACACIA_STORAGE_COUNTER);
        addDrop(ModBlocks.DARK_OAK_STORAGE_COUNTER);
        addDrop(ModBlocks.MANGROVE_STORAGE_COUNTER);
        addDrop(ModBlocks.CHERRY_STORAGE_COUNTER);
        addDrop(ModBlocks.BAMBOO_STORAGE_COUNTER);
        addDrop(ModBlocks.CRIMSON_STORAGE_COUNTER);
        addDrop(ModBlocks.WARPED_STORAGE_COUNTER);

        // Lamps

        // Sofas
        addDrop(ModBlocks.WHITE_SOFA);

        // Wall Mirrors
        addDrop(ModBlocks.OAK_WALL_MIRROR);
        addDrop(ModBlocks.SPRUCE_WALL_MIRROR);
        addDrop(ModBlocks.BIRCH_WALL_MIRROR);
        addDrop(ModBlocks.JUNGLE_WALL_MIRROR);
        addDrop(ModBlocks.ACACIA_WALL_MIRROR);
        addDrop(ModBlocks.DARK_OAK_WALL_MIRROR);
        addDrop(ModBlocks.MANGROVE_WALL_MIRROR);
        addDrop(ModBlocks.CHERRY_WALL_MIRROR);
        addDrop(ModBlocks.BAMBOO_WALL_MIRROR);
        addDrop(ModBlocks.CRIMSON_WALL_MIRROR);
        addDrop(ModBlocks.WARPED_WALL_MIRROR);
    }

    public LootTable.Builder dyeableDrop(Block drop) {
        return LootTable.builder()
                .pool(
                        this.addSurvivesExplosionCondition(
                                drop,
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(drop)
                                                        .apply(
                                                                CopyComponentsLootFunction.builder(CopyComponentsLootFunction.Source.BLOCK_ENTITY)
                                                                        .include(DataComponentTypes.BLOCK_ENTITY_DATA)
                                                        )
                                        )
                        )
                );
    }
}
