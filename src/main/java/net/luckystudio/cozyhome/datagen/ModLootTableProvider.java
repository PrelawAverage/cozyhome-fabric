package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.custom.clocks.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.TripleTallBlock;
import net.minecraft.block.Block;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        // Blocks that save the color they are dyed do not get registered here as they had a custom method that determines their drops

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

        // Sink Counters
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

        // Wall Clocks
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

        // Grandfather Clocks
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

        // Desk
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

        // Drawer
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

        // Tools Racks
        addDrop(ModBlocks.OAK_TOOL_RACK);
        addDrop(ModBlocks.SPRUCE_TOOL_RACK);
        addDrop(ModBlocks.BIRCH_TOOL_RACK);
        addDrop(ModBlocks.JUNGLE_TOOL_RACK);
        addDrop(ModBlocks.ACACIA_TOOL_RACK);
        addDrop(ModBlocks.DARK_OAK_TOOL_RACK);
        addDrop(ModBlocks.MANGROVE_TOOL_RACK);
        addDrop(ModBlocks.CHERRY_TOOL_RACK);
        addDrop(ModBlocks.BAMBOO_TOOL_RACK);
        addDrop(ModBlocks.CRIMSON_TOOL_RACK);
        addDrop(ModBlocks.WARPED_TOOL_RACK);
        addDrop(ModBlocks.IRON_TOOL_RACK);
        addDrop(ModBlocks.GLASS_TOOL_RACK);
        addDrop(ModBlocks.UNDEAD_TOOL_RACK);
        addDrop(ModBlocks.OMINOUS_TOOL_RACK);

        // Beams
        addDrop(ModBlocks.OAK_BEAM);
        addDrop(ModBlocks.SPRUCE_BEAM);
        addDrop(ModBlocks.BIRCH_BEAM);
        addDrop(ModBlocks.JUNGLE_BEAM);
        addDrop(ModBlocks.ACACIA_BEAM);
        addDrop(ModBlocks.DARK_OAK_BEAM);
        addDrop(ModBlocks.MANGROVE_BEAM);
        addDrop(ModBlocks.CHERRY_BEAM);
        addDrop(ModBlocks.BAMBOO_BEAM);
        addDrop(ModBlocks.CRIMSON_BEAM);
        addDrop(ModBlocks.WARPED_BEAM);
        addDrop(ModBlocks.STRIPPED_OAK_BEAM);
        addDrop(ModBlocks.STRIPPED_SPRUCE_BEAM);
        addDrop(ModBlocks.STRIPPED_BIRCH_BEAM);
        addDrop(ModBlocks.STRIPPED_JUNGLE_BEAM);
        addDrop(ModBlocks.STRIPPED_ACACIA_BEAM);
        addDrop(ModBlocks.STRIPPED_DARK_OAK_BEAM);
        addDrop(ModBlocks.STRIPPED_MANGROVE_BEAM);
        addDrop(ModBlocks.STRIPPED_CHERRY_BEAM);
        addDrop(ModBlocks.STRIPPED_BAMBOO_BEAM);
        addDrop(ModBlocks.STRIPPED_CRIMSON_BEAM);
        addDrop(ModBlocks.STRIPPED_WARPED_BEAM);
    }
    // This will drop only the bottom part of triple tall blocks that are one block together
    private void addTripleTallBlockDrop(Block block) {
        addDrop(block, dropsWithProperty(block, ModProperties.TRIPLE_TALL_BLOCK, TripleTallBlock.BOTTOM));
    }
}
