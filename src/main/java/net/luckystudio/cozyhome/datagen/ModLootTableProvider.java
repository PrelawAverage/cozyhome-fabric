package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.TripleTallBlock;
import net.minecraft.block.Block;
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

        // Blocks that save the color they are dyed do not get registered here as they had a custom method that determines their drops
        // and that method is called in the block's constructor.

        // COUNTERS
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

        // STORAGE COUNTERS
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

        // SINK COUNTERS
        addDrop(ModBlocks.OAK_SINK_COUNTER);
        addDrop(ModBlocks.SPRUCE_SINK_COUNTER);
        addDrop(ModBlocks.BIRCH_SINK_COUNTER);
        addDrop(ModBlocks.JUNGLE_SINK_COUNTER);
        addDrop(ModBlocks.ACACIA_SINK_COUNTER);
        addDrop(ModBlocks.DARK_OAK_SINK_COUNTER);
        addDrop(ModBlocks.MANGROVE_SINK_COUNTER);
        addDrop(ModBlocks.CHERRY_SINK_COUNTER);
        addDrop(ModBlocks.BAMBOO_SINK_COUNTER);
        addDrop(ModBlocks.CRIMSON_SINK_COUNTER);
        addDrop(ModBlocks.WARPED_SINK_COUNTER);

        // TABLES
        addDrop(ModBlocks.OAK_TABLE);
        addDrop(ModBlocks.SPRUCE_TABLE);
        addDrop(ModBlocks.BIRCH_TABLE);
        addDrop(ModBlocks.JUNGLE_TABLE);
        addDrop(ModBlocks.ACACIA_TABLE);
        addDrop(ModBlocks.DARK_OAK_TABLE);
        addDrop(ModBlocks.MANGROVE_TABLE);
        addDrop(ModBlocks.CHERRY_TABLE);
        addDrop(ModBlocks.BAMBOO_TABLE);
        addDrop(ModBlocks.CRIMSON_TABLE);
        addDrop(ModBlocks.WARPED_TABLE);
        addDrop(ModBlocks.IRON_TABLE);
        addDrop(ModBlocks.GLASS_TABLE);
        addDrop(ModBlocks.UNDEAD_TABLE);
        addDrop(ModBlocks.OMINOUS_TABLE);

        // CHAIRS
        addDrop(ModBlocks.OAK_CHAIR);
        addDrop(ModBlocks.SPRUCE_CHAIR);
        addDrop(ModBlocks.BIRCH_CHAIR);
        addDrop(ModBlocks.JUNGLE_CHAIR);
        addDrop(ModBlocks.ACACIA_CHAIR);
        addDrop(ModBlocks.DARK_OAK_CHAIR);
        addDrop(ModBlocks.MANGROVE_CHAIR);
        addDrop(ModBlocks.CHERRY_CHAIR);
        addDrop(ModBlocks.BAMBOO_CHAIR);
        addDrop(ModBlocks.CRIMSON_CHAIR);
        addDrop(ModBlocks.WARPED_CHAIR);
        addDrop(ModBlocks.IRON_CHAIR);
        addDrop(ModBlocks.GLASS_CHAIR);
        addDrop(ModBlocks.UNDEAD_CHAIR);
        addDrop(ModBlocks.OMINOUS_CHAIR);

        // WALL CLOCK
        addDrop(ModBlocks.OAK_WALL_CLOCK);
        addDrop(ModBlocks.SPRUCE_WALL_CLOCK);
        addDrop(ModBlocks.BIRCH_WALL_CLOCK);
        addDrop(ModBlocks.JUNGLE_WALL_CLOCK);
        addDrop(ModBlocks.ACACIA_WALL_CLOCK);
        addDrop(ModBlocks.DARK_OAK_WALL_CLOCK);
        addDrop(ModBlocks.MANGROVE_WALL_CLOCK);
        addDrop(ModBlocks.CHERRY_WALL_CLOCK);
        addDrop(ModBlocks.BAMBOO_WALL_CLOCK);
        addDrop(ModBlocks.CRIMSON_WALL_CLOCK);
        addDrop(ModBlocks.WARPED_WALL_CLOCK);
        addDrop(ModBlocks.IRON_WALL_CLOCK);
        addDrop(ModBlocks.GLASS_WALL_CLOCK);
        addDrop(ModBlocks.UNDEAD_WALL_CLOCK);
        addDrop(ModBlocks.OMINOUS_WALL_CLOCK);

        // GRANDFATHER CLOCK
        addTripleTallBlockDrop(ModBlocks.OAK_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.SPRUCE_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.BIRCH_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.JUNGLE_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.ACACIA_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.DARK_OAK_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.MANGROVE_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.CHERRY_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.BAMBOO_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.CRIMSON_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.WARPED_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.IRON_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.GLASS_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.UNDEAD_GRANDFATHER_CLOCK);
        addTripleTallBlockDrop(ModBlocks.OMINOUS_GRANDFATHER_CLOCK);

        // LAMP
        addDyedBlockDrop(ModBlocks.OAK_LAMP);
        addDyedBlockDrop(ModBlocks.SPRUCE_LAMP);
        addDyedBlockDrop(ModBlocks.BIRCH_LAMP);
        addDyedBlockDrop(ModBlocks.JUNGLE_LAMP);
        addDyedBlockDrop(ModBlocks.ACACIA_LAMP);
        addDyedBlockDrop(ModBlocks.DARK_OAK_LAMP);
        addDyedBlockDrop(ModBlocks.MANGROVE_LAMP);
        addDyedBlockDrop(ModBlocks.CHERRY_LAMP);
        addDyedBlockDrop(ModBlocks.BAMBOO_LAMP);
        addDyedBlockDrop(ModBlocks.CRIMSON_LAMP);
        addDyedBlockDrop(ModBlocks.WARPED_LAMP);
        addDyedBlockDrop(ModBlocks.IRON_LAMP);
        addDyedBlockDrop(ModBlocks.GLASS_LAMP);
        addDyedBlockDrop(ModBlocks.UNDEAD_LAMP);
        addDrop(ModBlocks.OMINOUS_LAMP);

        // SOFA
        addDyedBlockDrop(ModBlocks.OAK_SOFA);
        addDyedBlockDrop(ModBlocks.SPRUCE_SOFA);
        addDyedBlockDrop(ModBlocks.BIRCH_SOFA);
        addDyedBlockDrop(ModBlocks.JUNGLE_SOFA);
        addDyedBlockDrop(ModBlocks.ACACIA_SOFA);
        addDyedBlockDrop(ModBlocks.DARK_OAK_SOFA);
        addDyedBlockDrop(ModBlocks.MANGROVE_SOFA);
        addDyedBlockDrop(ModBlocks.CHERRY_SOFA);
        addDyedBlockDrop(ModBlocks.BAMBOO_SOFA);
        addDyedBlockDrop(ModBlocks.CRIMSON_SOFA);
        addDyedBlockDrop(ModBlocks.WARPED_SOFA);

        // COUCH
        addDyedBlockDrop(ModBlocks.OAK_COUCH);
        addDyedBlockDrop(ModBlocks.SPRUCE_COUCH);
        addDyedBlockDrop(ModBlocks.BIRCH_COUCH);
        addDyedBlockDrop(ModBlocks.JUNGLE_COUCH);
        addDyedBlockDrop(ModBlocks.ACACIA_COUCH);
        addDyedBlockDrop(ModBlocks.DARK_OAK_COUCH);
        addDyedBlockDrop(ModBlocks.MANGROVE_COUCH);
        addDyedBlockDrop(ModBlocks.CHERRY_COUCH);
        addDyedBlockDrop(ModBlocks.BAMBOO_COUCH);
        addDyedBlockDrop(ModBlocks.CRIMSON_COUCH);
        addDyedBlockDrop(ModBlocks.WARPED_COUCH);

        // DESK
        addDrop(ModBlocks.OAK_DESK);
        addDrop(ModBlocks.SPRUCE_DESK);
        addDrop(ModBlocks.BIRCH_DESK);
        addDrop(ModBlocks.JUNGLE_DESK);
        addDrop(ModBlocks.ACACIA_DESK);
        addDrop(ModBlocks.DARK_OAK_DESK);
        addDrop(ModBlocks.MANGROVE_DESK);
        addDrop(ModBlocks.CHERRY_DESK);
        addDrop(ModBlocks.BAMBOO_DESK);
        addDrop(ModBlocks.CRIMSON_DESK);
        addDrop(ModBlocks.WARPED_DESK);

        // DRAWER
        addDrop(ModBlocks.OAK_DRAWER);
        addDrop(ModBlocks.SPRUCE_DRAWER);
        addDrop(ModBlocks.BIRCH_DRAWER);
        addDrop(ModBlocks.JUNGLE_DRAWER);
        addDrop(ModBlocks.ACACIA_DRAWER);
        addDrop(ModBlocks.DARK_OAK_DRAWER);
        addDrop(ModBlocks.MANGROVE_DRAWER);
        addDrop(ModBlocks.CHERRY_DRAWER);
        addDrop(ModBlocks.BAMBOO_DRAWER);
        addDrop(ModBlocks.CRIMSON_DRAWER);
        addDrop(ModBlocks.WARPED_DRAWER);

        // WALL MIRROR
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

        // SINKS
        addDrop(ModBlocks.STONE_BRICK_SINK);
        addDrop(ModBlocks.MOSSY_STONE_BRICK_SINK);
        addDrop(ModBlocks.GRANITE_SINK);
        addDrop(ModBlocks.DIORITE_SINK);
        addDrop(ModBlocks.ANDESITE_SINK);
        addDrop(ModBlocks.DEEPSLATE_SINK);
        addDrop(ModBlocks.CALCITE_SINK);
        addDrop(ModBlocks.TUFF_SINK);
        addDrop(ModBlocks.BRICK_SINK);
        addDrop(ModBlocks.MUD_SINK);
        addDrop(ModBlocks.SANDSTONE_SINK);
        addDrop(ModBlocks.RED_SANDSTONE_SINK);
        addDrop(ModBlocks.PRISMARINE_SINK);
        addDrop(ModBlocks.NETHER_BRICK_SINK);
        addDrop(ModBlocks.RED_NETHER_BRICK_SINK);
        addDrop(ModBlocks.BLACKSTONE_SINK);
        addDrop(ModBlocks.ENDSTONE_SINK);
        addDrop(ModBlocks.PURPUR_SINK);
        addDrop(ModBlocks.IRON_SINK);

        // CHIMNEY
        addDrop(ModBlocks.STONE_BRICK_CHIMNEY);
        addDrop(ModBlocks.MOSSY_STONE_BRICK_CHIMNEY);
        addDrop(ModBlocks.GRANITE_CHIMNEY);
        addDrop(ModBlocks.DIORITE_CHIMNEY);
        addDrop(ModBlocks.ANDESITE_CHIMNEY);
        addDrop(ModBlocks.DEEPSLATE_CHIMNEY);
        addDrop(ModBlocks.CALCITE_CHIMNEY);
        addDrop(ModBlocks.TUFF_CHIMNEY);
        addDrop(ModBlocks.BRICK_CHIMNEY);
        addDrop(ModBlocks.MUD_CHIMNEY);
        addDrop(ModBlocks.SANDSTONE_CHIMNEY);
        addDrop(ModBlocks.RED_SANDSTONE_CHIMNEY);
        addDrop(ModBlocks.PRISMARINE_CHIMNEY);
        addDrop(ModBlocks.NETHER_BRICK_CHIMNEY);
        addDrop(ModBlocks.RED_NETHER_BRICK_CHIMNEY);
        addDrop(ModBlocks.BLACKSTONE_CHIMNEY);
        addDrop(ModBlocks.ENDSTONE_CHIMNEY);
        addDrop(ModBlocks.PURPUR_CHIMNEY);

        // FOUNTAINS
        addDrop(ModBlocks.STONE_BRICK_FOUNTAIN);
        addDrop(ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN);
        addDrop(ModBlocks.GRANITE_FOUNTAIN);
        addDrop(ModBlocks.DIORITE_FOUNTAIN);
        addDrop(ModBlocks.ANDESITE_FOUNTAIN);
        addDrop(ModBlocks.DEEPSLATE_FOUNTAIN);
        addDrop(ModBlocks.CALCITE_FOUNTAIN);
        addDrop(ModBlocks.TUFF_FOUNTAIN);
        addDrop(ModBlocks.BRICK_FOUNTAIN);
        addDrop(ModBlocks.MUD_FOUNTAIN);
        addDrop(ModBlocks.SANDSTONE_FOUNTAIN);
        addDrop(ModBlocks.RED_SANDSTONE_FOUNTAIN);
        addDrop(ModBlocks.PRISMARINE_FOUNTAIN);
        addDrop(ModBlocks.NETHER_BRICK_FOUNTAIN);
        addDrop(ModBlocks.RED_NETHER_BRICK_FOUNTAIN);
        addDrop(ModBlocks.BLACKSTONE_FOUNTAIN);
        addDrop(ModBlocks.ENDSTONE_FOUNTAIN);
        addDrop(ModBlocks.PURPUR_FOUNTAIN);

        // FOUNTAIN SPROUTS
        addDrop(ModBlocks.STONE_BRICK_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.GRANITE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.DIORITE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.ANDESITE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.DEEPSLATE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.CALCITE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.TUFF_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.BRICK_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.MUD_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.SANDSTONE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.RED_SANDSTONE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.PRISMARINE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.NETHER_BRICK_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.RED_NETHER_BRICK_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.BLACKSTONE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.ENDSTONE_FOUNTAIN_SPROUT);
        addDrop(ModBlocks.PURPUR_FOUNTAIN_SPROUT);

        addDrop(ModBlocks.TELESCOPE);
    }

    // This will drop only the bottom part of triple tall blocks that are one block together
    private void addDyedBlockDrop(Block block) {
        addDrop(block, dropWithDyedComponent(block));
    }

    public LootTable.Builder dropWithDyedComponent(Block drop) {
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
                                                                        .include(DataComponentTypes.DYED_COLOR)
                                                        )
                                        )
                        )
                );
    }

    // This will drop only the bottom part of triple tall blocks that are one block together
    private void addTripleTallBlockDrop(Block block) {
        addDrop(block, dropsWithProperty(block, ModProperties.TRIPLE_TALL_BLOCK, TripleTallBlock.BOTTOM));
    }
}
