package net.luckystudio.cozyhome.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.luckystudio.cozyhome.block.ModBlocks;

public class ModFlammableBlocks {
    public static void registerFlammables() {
        FlammableBlockRegistry flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();

        // PLANKED WALLS
        flammableBlockRegistry.add(ModBlocks.OAK_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_PLANKED_WALL, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_PLANKED_WALL, 5, 20);

        // COUNTERS
        flammableBlockRegistry.add(ModBlocks.OAK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.OAK_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_STORAGE_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.OAK_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_SINK_COUNTER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_SINK_COUNTER, 5, 20);

        // TABLES
        flammableBlockRegistry.add(ModBlocks.OAK_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_TABLE, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_TABLE, 5, 20);

        // CHAIRS
        flammableBlockRegistry.add(ModBlocks.OAK_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.BIRCH_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.ACACIA_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.CHERRY_CHAIR, 5, 15);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_CHAIR, 5, 15);

        // WALL_CLOCKS
        flammableBlockRegistry.add(ModBlocks.OAK_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_WALL_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_WALL_CLOCK, 5, 20);

        // GRANDFATHER_CLOCKS
        flammableBlockRegistry.add(ModBlocks.OAK_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_GRANDFATHER_CLOCK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_GRANDFATHER_CLOCK, 5, 20);

        // SOFAS
        flammableBlockRegistry.add(ModBlocks.OAK_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_SOFA, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_SOFA, 5, 20);

        // DESKS
        flammableBlockRegistry.add(ModBlocks.OAK_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_DESK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_DESK, 5, 20);

        // DRAWERS
        flammableBlockRegistry.add(ModBlocks.OAK_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_DRAWER, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_DRAWER, 5, 20);

        // WALL_MIRRORS
        flammableBlockRegistry.add(ModBlocks.OAK_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_WALL_MIRROR, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_WALL_MIRROR, 5, 20);

        // TOOL_RACKS
        flammableBlockRegistry.add(ModBlocks.OAK_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_TOOL_RACK, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_TOOL_RACK, 5, 20);

        // BEAMS
        flammableBlockRegistry.add(ModBlocks.OAK_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.SPRUCE_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BIRCH_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.JUNGLE_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.ACACIA_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.DARK_OAK_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.MANGROVE_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.CHERRY_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.BAMBOO_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_OAK_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_SPRUCE_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_BIRCH_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_JUNGLE_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_ACACIA_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_DARK_OAK_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_MANGROVE_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_CHERRY_BEAM, 5, 20);
        flammableBlockRegistry.add(ModBlocks.STRIPPED_BAMBOO_BEAM, 5, 20);

    }
}
