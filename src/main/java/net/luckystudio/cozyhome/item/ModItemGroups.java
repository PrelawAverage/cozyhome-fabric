package net.luckystudio.cozyhome.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup COZY_HOME =
            Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CozyHome.MOD_ID, "cozyhome"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.OAK_CHAIR))
                    .displayName(Text.translatable("itemgroup.cozyhome.cozyhome"))
                    .entries((displayContext, entries) -> {

                        // Counters
                        entries.add(ModBlocks.OAK_COUNTER);
                        entries.add(ModBlocks.SPRUCE_COUNTER);
                        entries.add(ModBlocks.BIRCH_COUNTER);
                        entries.add(ModBlocks.JUNGLE_COUNTER);
                        entries.add(ModBlocks.ACACIA_COUNTER);
                        entries.add(ModBlocks.DARK_OAK_COUNTER);
                        entries.add(ModBlocks.MANGROVE_COUNTER);
                        entries.add(ModBlocks.CHERRY_COUNTER);
                        entries.add(ModBlocks.BAMBOO_COUNTER);
                        entries.add(ModBlocks.CRIMSON_COUNTER);
                        entries.add(ModBlocks.WARPED_COUNTER);

                        // Storage Counters
                        entries.add(ModBlocks.OAK_STORAGE_COUNTER);
                        entries.add(ModBlocks.SPRUCE_STORAGE_COUNTER);
                        entries.add(ModBlocks.BIRCH_STORAGE_COUNTER);
                        entries.add(ModBlocks.JUNGLE_STORAGE_COUNTER);
                        entries.add(ModBlocks.ACACIA_STORAGE_COUNTER);
                        entries.add(ModBlocks.DARK_OAK_STORAGE_COUNTER);
                        entries.add(ModBlocks.MANGROVE_STORAGE_COUNTER);
                        entries.add(ModBlocks.CHERRY_STORAGE_COUNTER);
                        entries.add(ModBlocks.BAMBOO_STORAGE_COUNTER);
                        entries.add(ModBlocks.CRIMSON_STORAGE_COUNTER);
                        entries.add(ModBlocks.WARPED_STORAGE_COUNTER);

                        // Sink Counters
                        entries.add(ModBlocks.OAK_SINK_COUNTER);
                        entries.add(ModBlocks.SPRUCE_SINK_COUNTER);
                        entries.add(ModBlocks.BIRCH_SINK_COUNTER);
                        entries.add(ModBlocks.JUNGLE_SINK_COUNTER);
                        entries.add(ModBlocks.ACACIA_SINK_COUNTER);
                        entries.add(ModBlocks.DARK_OAK_SINK_COUNTER);
                        entries.add(ModBlocks.MANGROVE_SINK_COUNTER);
                        entries.add(ModBlocks.CHERRY_SINK_COUNTER);
                        entries.add(ModBlocks.BAMBOO_SINK_COUNTER);
                        entries.add(ModBlocks.CRIMSON_SINK_COUNTER);
                        entries.add(ModBlocks.WARPED_SINK_COUNTER);

                        // Tables
                        entries.add(ModBlocks.OAK_TABLE);
                        entries.add(ModBlocks.SPRUCE_TABLE);
                        entries.add(ModBlocks.BIRCH_TABLE);
                        entries.add(ModBlocks.JUNGLE_TABLE);
                        entries.add(ModBlocks.ACACIA_TABLE);
                        entries.add(ModBlocks.DARK_OAK_TABLE);
                        entries.add(ModBlocks.MANGROVE_TABLE);
                        entries.add(ModBlocks.CHERRY_TABLE);
                        entries.add(ModBlocks.BAMBOO_TABLE);
                        entries.add(ModBlocks.CRIMSON_TABLE);
                        entries.add(ModBlocks.WARPED_TABLE);
                        entries.add(ModBlocks.IRON_TABLE);
                        entries.add(ModBlocks.GLASS_TABLE);
                        entries.add(ModBlocks.UNDEAD_TABLE);
                        entries.add(ModBlocks.OMINOUS_TABLE);

                        // Chairs
                        entries.add(ModBlocks.OAK_CHAIR);
                        entries.add(ModBlocks.SPRUCE_CHAIR);
                        entries.add(ModBlocks.BIRCH_CHAIR);
                        entries.add(ModBlocks.JUNGLE_CHAIR);
                        entries.add(ModBlocks.ACACIA_CHAIR);
                        entries.add(ModBlocks.DARK_OAK_CHAIR);
                        entries.add(ModBlocks.MANGROVE_CHAIR);
                        entries.add(ModBlocks.CHERRY_CHAIR);
                        entries.add(ModBlocks.BAMBOO_CHAIR);
                        entries.add(ModBlocks.CRIMSON_CHAIR);
                        entries.add(ModBlocks.WARPED_CHAIR);
                        entries.add(ModBlocks.IRON_CHAIR);
                        entries.add(ModBlocks.GLASS_CHAIR);
                        entries.add(ModBlocks.UNDEAD_CHAIR);
                        entries.add(ModBlocks.OMINOUS_CHAIR);

                        // Wall Clocks
                        entries.add(ModBlocks.OAK_WALL_CLOCK);
                        entries.add(ModBlocks.SPRUCE_WALL_CLOCK);
                        entries.add(ModBlocks.BIRCH_WALL_CLOCK);
                        entries.add(ModBlocks.JUNGLE_WALL_CLOCK);
                        entries.add(ModBlocks.ACACIA_WALL_CLOCK);
                        entries.add(ModBlocks.DARK_OAK_WALL_CLOCK);
                        entries.add(ModBlocks.MANGROVE_WALL_CLOCK);
                        entries.add(ModBlocks.CHERRY_WALL_CLOCK);
                        entries.add(ModBlocks.BAMBOO_WALL_CLOCK);
                        entries.add(ModBlocks.CRIMSON_WALL_CLOCK);
                        entries.add(ModBlocks.WARPED_WALL_CLOCK);
                        entries.add(ModBlocks.IRON_WALL_CLOCK);
                        entries.add(ModBlocks.GLASS_WALL_CLOCK);
                        entries.add(ModBlocks.UNDEAD_WALL_CLOCK);
                        entries.add(ModBlocks.OMINOUS_WALL_CLOCK);

                        // Grandfather Clocks
                        entries.add(ModBlocks.OAK_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.SPRUCE_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.BIRCH_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.JUNGLE_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.ACACIA_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.DARK_OAK_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.MANGROVE_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.CHERRY_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.BAMBOO_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.CRIMSON_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.WARPED_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.IRON_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.GLASS_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.UNDEAD_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.OMINOUS_GRANDFATHER_CLOCK);

                        // Lamps
                        entries.add(ModBlocks.OAK_LAMP);
                        entries.add(ModBlocks.SPRUCE_LAMP);
                        entries.add(ModBlocks.BIRCH_LAMP);
                        entries.add(ModBlocks.JUNGLE_LAMP);
                        entries.add(ModBlocks.ACACIA_LAMP);
                        entries.add(ModBlocks.DARK_OAK_LAMP);
                        entries.add(ModBlocks.MANGROVE_LAMP);
                        entries.add(ModBlocks.CHERRY_LAMP);
                        entries.add(ModBlocks.BAMBOO_LAMP);
                        entries.add(ModBlocks.CRIMSON_LAMP);
                        entries.add(ModBlocks.WARPED_LAMP);
                        entries.add(ModBlocks.IRON_LAMP);
                        entries.add(ModBlocks.GLASS_LAMP);
                        entries.add(ModBlocks.UNDEAD_LAMP);
                        entries.add(ModBlocks.OMINOUS_LAMP);

                        // Sofas
                        entries.add(ModBlocks.OAK_SOFA);
                        entries.add(ModBlocks.SPRUCE_SOFA);
                        entries.add(ModBlocks.BIRCH_SOFA);
                        entries.add(ModBlocks.JUNGLE_SOFA);
                        entries.add(ModBlocks.ACACIA_SOFA);
                        entries.add(ModBlocks.DARK_OAK_SOFA);
                        entries.add(ModBlocks.MANGROVE_SOFA);
                        entries.add(ModBlocks.CHERRY_SOFA);
                        entries.add(ModBlocks.BAMBOO_SOFA);
                        entries.add(ModBlocks.CRIMSON_SOFA);
                        entries.add(ModBlocks.WARPED_SOFA);

                        // Couches
                        entries.add(ModBlocks.OAK_COUCH);
                        entries.add(ModBlocks.SPRUCE_COUCH);
                        entries.add(ModBlocks.BIRCH_COUCH);
                        entries.add(ModBlocks.JUNGLE_COUCH);
                        entries.add(ModBlocks.ACACIA_COUCH);
                        entries.add(ModBlocks.DARK_OAK_COUCH);
                        entries.add(ModBlocks.MANGROVE_COUCH);
                        entries.add(ModBlocks.CHERRY_COUCH);
                        entries.add(ModBlocks.BAMBOO_COUCH);
                        entries.add(ModBlocks.CRIMSON_COUCH);
                        entries.add(ModBlocks.WARPED_COUCH);

                        // Desks
                        entries.add(ModBlocks.OAK_DESK);
                        entries.add(ModBlocks.SPRUCE_DESK);
                        entries.add(ModBlocks.BIRCH_DESK);
                        entries.add(ModBlocks.JUNGLE_DESK);
                        entries.add(ModBlocks.ACACIA_DESK);
                        entries.add(ModBlocks.DARK_OAK_DESK);
                        entries.add(ModBlocks.MANGROVE_DESK);
                        entries.add(ModBlocks.CHERRY_DESK);
                        entries.add(ModBlocks.BAMBOO_DESK);
                        entries.add(ModBlocks.CRIMSON_DESK);
                        entries.add(ModBlocks.WARPED_DESK);

                        // Drawer
                        entries.add(ModBlocks.OAK_DRAWER);
                        entries.add(ModBlocks.SPRUCE_DRAWER);
                        entries.add(ModBlocks.BIRCH_DRAWER);
                        entries.add(ModBlocks.JUNGLE_DRAWER);
                        entries.add(ModBlocks.ACACIA_DRAWER);
                        entries.add(ModBlocks.DARK_OAK_DRAWER);
                        entries.add(ModBlocks.MANGROVE_DRAWER);
                        entries.add(ModBlocks.CHERRY_DRAWER);
                        entries.add(ModBlocks.BAMBOO_DRAWER);
                        entries.add(ModBlocks.CRIMSON_DRAWER);
                        entries.add(ModBlocks.WARPED_DRAWER);

                        // Wall Mirrors
                        entries.add(ModBlocks.OAK_WALL_MIRROR);
                        entries.add(ModBlocks.SPRUCE_WALL_MIRROR);
                        entries.add(ModBlocks.BIRCH_WALL_MIRROR);
                        entries.add(ModBlocks.JUNGLE_WALL_MIRROR);
                        entries.add(ModBlocks.ACACIA_WALL_MIRROR);
                        entries.add(ModBlocks.DARK_OAK_WALL_MIRROR);
                        entries.add(ModBlocks.MANGROVE_WALL_MIRROR);
                        entries.add(ModBlocks.CHERRY_WALL_MIRROR);
                        entries.add(ModBlocks.BAMBOO_WALL_MIRROR);
                        entries.add(ModBlocks.CRIMSON_WALL_MIRROR);
                        entries.add(ModBlocks.WARPED_WALL_MIRROR);

                        // SINKS
                        entries.add(ModBlocks.STONE_BRICK_SINK);
                        entries.add(ModBlocks.MOSSY_STONE_BRICK_SINK);
                        entries.add(ModBlocks.GRANITE_SINK);
                        entries.add(ModBlocks.DIORITE_SINK);
                        entries.add(ModBlocks.ANDESITE_SINK);
                        entries.add(ModBlocks.DEEPSLATE_SINK);
                        entries.add(ModBlocks.TUFF_SINK);
                        entries.add(ModBlocks.BRICK_SINK);
                        entries.add(ModBlocks.MUD_SINK);
                        entries.add(ModBlocks.SANDSTONE_SINK);
                        entries.add(ModBlocks.RED_SANDSTONE_SINK);
                        entries.add(ModBlocks.PRISMARINE_SINK);
                        entries.add(ModBlocks.NETHER_BRICK_SINK);
                        entries.add(ModBlocks.RED_NETHER_BRICK_SINK);
                        entries.add(ModBlocks.BLACKSTONE_SINK);
                        entries.add(ModBlocks.ENDSTONE_SINK);
                        entries.add(ModBlocks.PURPUR_SINK);
                        entries.add(ModBlocks.IRON_SINK);

                        // Large Stump
                        entries.add(ModBlocks.OAK_LARGE_STUMP);
                        entries.add(ModBlocks.SPRUCE_LARGE_STUMP);
                        entries.add(ModBlocks.BIRCH_LARGE_STUMP);
                        entries.add(ModBlocks.JUNGLE_LARGE_STUMP);
                        entries.add(ModBlocks.ACACIA_LARGE_STUMP);
                        entries.add(ModBlocks.DARK_OAK_LARGE_STUMP);
                        entries.add(ModBlocks.MANGROVE_LARGE_STUMP);
                        entries.add(ModBlocks.CHERRY_LARGE_STUMP);
                        entries.add(ModBlocks.BAMBOO_LARGE_STUMP);
                        entries.add(ModBlocks.CRIMSON_LARGE_STUMP);
                        entries.add(ModBlocks.WARPED_LARGE_STUMP);

//                        // Fountains
//                        entries.add(ModBlocks.STONE_BRICK_FOUNTAIN);
//                        entries.add(ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN);
//                        entries.add(ModBlocks.GRANITE_FOUNTAIN);
//                        entries.add(ModBlocks.DIORITE_FOUNTAIN);
//                        entries.add(ModBlocks.ANDESITE_FOUNTAIN);
//                        entries.add(ModBlocks.DEEPSLATE_FOUNTAIN);
//                        entries.add(ModBlocks.TUFF_FOUNTAIN);
//                        entries.add(ModBlocks.BRICK_FOUNTAIN);
//                        entries.add(ModBlocks.MUD_FOUNTAIN);
//                        entries.add(ModBlocks.SANDSTONE_FOUNTAIN);
//                        entries.add(ModBlocks.RED_SANDSTONE_FOUNTAIN);
//                        entries.add(ModBlocks.PRISMARINE_FOUNTAIN);
//                        entries.add(ModBlocks.NETHER_BRICK_FOUNTAIN);
//                        entries.add(ModBlocks.RED_NETHER_BRICK_FOUNTAIN);
//                        entries.add(ModBlocks.BLACKSTONE_FOUNTAIN);
//                        entries.add(ModBlocks.ENDSTONE_FOUNTAIN);
//                        entries.add(ModBlocks.PURPUR_FOUNTAIN);
//
//                        // Fountain Sprouts
//                        entries.add(ModBlocks.STONE_BRICK_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.GRANITE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.DIORITE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.ANDESITE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.DEEPSLATE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.TUFF_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.BRICK_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.MUD_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.SANDSTONE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.RED_SANDSTONE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.PRISMARINE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.NETHER_BRICK_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.RED_NETHER_BRICK_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.BLACKSTONE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.ENDSTONE_FOUNTAIN_SPROUT);
//                        entries.add(ModBlocks.PURPUR_FOUNTAIN_SPROUT);
//
//                        // Chimneys
//                        entries.add(ModBlocks.STONE_BRICK_CHIMNEY);
//                        entries.add(ModBlocks.MOSSY_STONE_BRICK_CHIMNEY);
//                        entries.add(ModBlocks.GRANITE_CHIMNEY);
//                        entries.add(ModBlocks.DIORITE_CHIMNEY);
//                        entries.add(ModBlocks.ANDESITE_CHIMNEY);
//                        entries.add(ModBlocks.DEEPSLATE_CHIMNEY);
//                        entries.add(ModBlocks.TUFF_CHIMNEY);
//                        entries.add(ModBlocks.BRICK_CHIMNEY);
//                        entries.add(ModBlocks.MUD_CHIMNEY);
//                        entries.add(ModBlocks.SANDSTONE_CHIMNEY);
//                        entries.add(ModBlocks.RED_SANDSTONE_CHIMNEY);
//                        entries.add(ModBlocks.PRISMARINE_CHIMNEY);
//                        entries.add(ModBlocks.NETHER_BRICK_CHIMNEY);
//                        entries.add(ModBlocks.RED_NETHER_BRICK_CHIMNEY);
//                        entries.add(ModBlocks.BLACKSTONE_CHIMNEY);
//                        entries.add(ModBlocks.ENDSTONE_CHIMNEY);
//                        entries.add(ModBlocks.PURPUR_CHIMNEY);
                        // Misc. Blocks/Items
                        entries.add(ModBlocks.TELESCOPE);
                        entries.add(ModItems.PAINT_BRUSH);
                        entries.add(ModItems.CUSHION);
                        entries.add(ModItems.HAY_CUSHION);
                        entries.add(ModItems.TRADER_CUSHION);

                        // Compatibility with Ecologics Mod - https://www.curseforge.com/minecraft/mc-mods/ecologics
                        FabricLoader.getInstance().isModLoaded("ecologics");
                        // Compatibility with Biomes O Plenty Mod - https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty
                        FabricLoader.getInstance().isModLoaded("biomesoplenty");
                    }).build());

    public static void registerModItemGroups() {
        CozyHome.LOGGER.info("Registering Item Groups for " + CozyHome.MOD_ID);
    }
}
