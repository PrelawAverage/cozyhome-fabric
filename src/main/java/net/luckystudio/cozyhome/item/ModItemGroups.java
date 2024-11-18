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
                    .icon(() -> new ItemStack(ModBlocks.OAK_LAMP))
                    .displayName(Text.translatable("itemgroup.cozyhome.cozyhome"))
                    .entries((displayContext, entries) -> {

                        // Oak
                        entries.add(ModBlocks.OAK_PLANKED_WALL);
                        entries.add(ModBlocks.OAK_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.OAK_BEAM);
                        entries.add(ModBlocks.OAK_COUNTER);
                        entries.add(ModBlocks.OAK_SINK_COUNTER);
                        entries.add(ModBlocks.OAK_STORAGE_COUNTER);
                        entries.add(ModBlocks.OAK_CHAIR);
                        entries.add(ModBlocks.OAK_WALL_MIRROR);
                        entries.add(ModBlocks.OAK_WALL_CLOCK);
                        entries.add(ModBlocks.OAK_LAMP);
                        entries.add(ModBlocks.OAK_SOFA);
                        entries.add(ModBlocks.OAK_TABLE);
                        entries.add(ModBlocks.OAK_DESK);

                        // Spruce
                        entries.add(ModBlocks.SPRUCE_PLANKED_WALL);
                        entries.add(ModBlocks.SPRUCE_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.SPRUCE_BEAM);
                        entries.add(ModBlocks.SPRUCE_COUNTER);
                        entries.add(ModBlocks.SPRUCE_SINK_COUNTER);
                        entries.add(ModBlocks.SPRUCE_STORAGE_COUNTER);
                        entries.add(ModBlocks.SPRUCE_CHAIR);
                        entries.add(ModBlocks.SPRUCE_WALL_MIRROR);
                        entries.add(ModBlocks.SPRUCE_WALL_CLOCK);
                        entries.add(ModBlocks.SPRUCE_LAMP);
                        entries.add(ModBlocks.SPRUCE_SOFA);
                        entries.add(ModBlocks.SPRUCE_TABLE);
                        entries.add(ModBlocks.SPRUCE_DESK);

                        // Birch
                        entries.add(ModBlocks.BIRCH_PLANKED_WALL);
                        entries.add(ModBlocks.BIRCH_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.BIRCH_BEAM);
                        entries.add(ModBlocks.BIRCH_COUNTER);
                        entries.add(ModBlocks.BIRCH_SINK_COUNTER);
                        entries.add(ModBlocks.BIRCH_STORAGE_COUNTER);
                        entries.add(ModBlocks.BIRCH_CHAIR);
                        entries.add(ModBlocks.BIRCH_WALL_MIRROR);
                        entries.add(ModBlocks.BIRCH_WALL_CLOCK);
                        entries.add(ModBlocks.BIRCH_LAMP);
                        entries.add(ModBlocks.BIRCH_SOFA);
                        entries.add(ModBlocks.BIRCH_TABLE);
                        entries.add(ModBlocks.BIRCH_DESK);

                        // Jungle
                        entries.add(ModBlocks.JUNGLE_PLANKED_WALL);
                        entries.add(ModBlocks.JUNGLE_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.JUNGLE_BEAM);
                        entries.add(ModBlocks.JUNGLE_COUNTER);
                        entries.add(ModBlocks.JUNGLE_SINK_COUNTER);
                        entries.add(ModBlocks.JUNGLE_STORAGE_COUNTER);
                        entries.add(ModBlocks.JUNGLE_CHAIR);
                        entries.add(ModBlocks.JUNGLE_WALL_MIRROR);
                        entries.add(ModBlocks.JUNGLE_WALL_CLOCK);
                        entries.add(ModBlocks.JUNGLE_LAMP);
                        entries.add(ModBlocks.JUNGLE_SOFA);
                        entries.add(ModBlocks.JUNGLE_TABLE);
                        entries.add(ModBlocks.JUNGLE_DESK);

                        // Acacia
                        entries.add(ModBlocks.ACACIA_PLANKED_WALL);
                        entries.add(ModBlocks.ACACIA_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.ACACIA_BEAM);
                        entries.add(ModBlocks.ACACIA_COUNTER);
                        entries.add(ModBlocks.ACACIA_SINK_COUNTER);
                        entries.add(ModBlocks.ACACIA_STORAGE_COUNTER);
                        entries.add(ModBlocks.ACACIA_CHAIR);
                        entries.add(ModBlocks.ACACIA_WALL_MIRROR);
                        entries.add(ModBlocks.ACACIA_WALL_CLOCK);
                        entries.add(ModBlocks.ACACIA_LAMP);
                        entries.add(ModBlocks.ACACIA_SOFA);
                        entries.add(ModBlocks.ACACIA_TABLE);
                        entries.add(ModBlocks.ACACIA_DESK);

                        // Dark Oak
                        entries.add(ModBlocks.DARK_OAK_PLANKED_WALL);
                        entries.add(ModBlocks.DARK_OAK_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.DARK_OAK_BEAM);
                        entries.add(ModBlocks.DARK_OAK_COUNTER);
                        entries.add(ModBlocks.DARK_OAK_SINK_COUNTER);
                        entries.add(ModBlocks.DARK_OAK_STORAGE_COUNTER);
                        entries.add(ModBlocks.DARK_OAK_CHAIR);
                        entries.add(ModBlocks.DARK_OAK_WALL_MIRROR);
                        entries.add(ModBlocks.DARK_OAK_WALL_CLOCK);
                        entries.add(ModBlocks.DARK_OAK_LAMP);
                        entries.add(ModBlocks.DARK_OAK_SOFA);
                        entries.add(ModBlocks.DARK_OAK_TABLE);
                        entries.add(ModBlocks.DARK_OAK_DESK);

                        // Mangrove
                        entries.add(ModBlocks.MANGROVE_PLANKED_WALL);
                        entries.add(ModBlocks.MANGROVE_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.MANGROVE_BEAM);
                        entries.add(ModBlocks.MANGROVE_COUNTER);
                        entries.add(ModBlocks.MANGROVE_SINK_COUNTER);
                        entries.add(ModBlocks.MANGROVE_STORAGE_COUNTER);
                        entries.add(ModBlocks.MANGROVE_CHAIR);
                        entries.add(ModBlocks.MANGROVE_WALL_MIRROR);
                        entries.add(ModBlocks.MANGROVE_WALL_CLOCK);
                        entries.add(ModBlocks.MANGROVE_LAMP);
                        entries.add(ModBlocks.MANGROVE_SOFA);
                        entries.add(ModBlocks.MANGROVE_TABLE);
                        entries.add(ModBlocks.MANGROVE_DESK);

                        // Cherry
                        entries.add(ModBlocks.CHERRY_PLANKED_WALL);
                        entries.add(ModBlocks.CHERRY_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.CHERRY_BEAM);
                        entries.add(ModBlocks.CHERRY_COUNTER);
                        entries.add(ModBlocks.CHERRY_SINK_COUNTER);
                        entries.add(ModBlocks.CHERRY_STORAGE_COUNTER);
                        entries.add(ModBlocks.CHERRY_CHAIR);
                        entries.add(ModBlocks.CHERRY_WALL_MIRROR);
                        entries.add(ModBlocks.CHERRY_WALL_CLOCK);
                        entries.add(ModBlocks.CHERRY_LAMP);
                        entries.add(ModBlocks.CHERRY_SOFA);
                        entries.add(ModBlocks.CHERRY_TABLE);
                        entries.add(ModBlocks.CHERRY_DESK);

                        // Bamboo
                        entries.add(ModBlocks.BAMBOO_PLANKED_WALL);
                        entries.add(ModBlocks.BAMBOO_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.BAMBOO_BEAM);
                        entries.add(ModBlocks.BAMBOO_COUNTER);
                        entries.add(ModBlocks.BAMBOO_SINK_COUNTER);
                        entries.add(ModBlocks.BAMBOO_STORAGE_COUNTER);
                        entries.add(ModBlocks.BAMBOO_CHAIR);
                        entries.add(ModBlocks.BAMBOO_WALL_MIRROR);
                        entries.add(ModBlocks.BAMBOO_WALL_CLOCK);
                        entries.add(ModBlocks.BAMBOO_LAMP);
                        entries.add(ModBlocks.BAMBOO_SOFA);
                        entries.add(ModBlocks.BAMBOO_TABLE);
                        entries.add(ModBlocks.BAMBOO_DESK);

                        // Crimson
                        entries.add(ModBlocks.CRIMSON_PLANKED_WALL);
                        entries.add(ModBlocks.CRIMSON_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.CRIMSON_BEAM);
                        entries.add(ModBlocks.CRIMSON_COUNTER);
                        entries.add(ModBlocks.CRIMSON_SINK_COUNTER);
                        entries.add(ModBlocks.CRIMSON_STORAGE_COUNTER);
                        entries.add(ModBlocks.CRIMSON_CHAIR);
                        entries.add(ModBlocks.CRIMSON_WALL_MIRROR);
                        entries.add(ModBlocks.CRIMSON_WALL_CLOCK);
                        entries.add(ModBlocks.CRIMSON_LAMP);
                        entries.add(ModBlocks.CRIMSON_SOFA);
                        entries.add(ModBlocks.CRIMSON_TABLE);
                        entries.add(ModBlocks.CRIMSON_DESK);

                        // Warped
                        entries.add(ModBlocks.WARPED_PLANKED_WALL);
                        entries.add(ModBlocks.WARPED_GRANDFATHER_CLOCK);
                        entries.add(ModBlocks.WARPED_BEAM);
                        entries.add(ModBlocks.WARPED_COUNTER);
                        entries.add(ModBlocks.WARPED_SINK_COUNTER);
                        entries.add(ModBlocks.WARPED_STORAGE_COUNTER);
                        entries.add(ModBlocks.WARPED_CHAIR);
                        entries.add(ModBlocks.WARPED_WALL_MIRROR);
                        entries.add(ModBlocks.WARPED_WALL_CLOCK);
                        entries.add(ModBlocks.WARPED_LAMP);
                        entries.add(ModBlocks.WARPED_SOFA);
                        entries.add(ModBlocks.WARPED_TABLE);
                        entries.add(ModBlocks.WARPED_DESK);

                        // Specials (Mangrove)
                        entries.add(ModBlocks.MANGROVE_LANTERN);

                        // Fountains
                        entries.add(ModBlocks.STONE_BRICK_FOUNTAIN);
                        entries.add(ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN);
                        entries.add(ModBlocks.GRANITE_FOUNTAIN);
                        entries.add(ModBlocks.DIORITE_FOUNTAIN);
                        entries.add(ModBlocks.ANDESITE_FOUNTAIN);
                        entries.add(ModBlocks.DEEPSLATE_FOUNTAIN);
                        entries.add(ModBlocks.TUFF_FOUNTAIN);
                        entries.add(ModBlocks.BRICK_FOUNTAIN);
                        entries.add(ModBlocks.MUD_FOUNTAIN);
                        entries.add(ModBlocks.SANDSTONE_FOUNTAIN);
                        entries.add(ModBlocks.RED_SANDSTONE_FOUNTAIN);
                        entries.add(ModBlocks.PRISMARINE_FOUNTAIN);
                        entries.add(ModBlocks.NETHER_BRICK_FOUNTAIN);
                        entries.add(ModBlocks.RED_NETHER_BRICK_FOUNTAIN);
                        entries.add(ModBlocks.BLACKSTONE_FOUNTAIN);
                        entries.add(ModBlocks.ENDSTONE_FOUNTAIN);
                        entries.add(ModBlocks.PURPUR_FOUNTAIN);

                        // Glass
                        entries.add(ModBlocks.AUTUMN_STAINED_GLASS);
                        entries.add(ModBlocks.AUTUMN_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.GOLD_FRAMED_GLASS);
                        entries.add(ModBlocks.GOLD_FRAMED_GLASS_PANE);

                        // Misc. Blocks/Items
                        entries.add(ModBlocks.IRON_CHAIR);
                        entries.add(ModBlocks.GLASS_CHAIR);
                        entries.add(ModBlocks.UNDEAD_CHAIR);
                        entries.add(ModBlocks.OMINOUS_CHAIR);
                        entries.add(ModBlocks.TELESCOPE);
                        entries.add(ModBlocks.DYE_VAT);
                        entries.add(ModItems.PAINT_BRUSH);
                        entries.add(ModItems.CUSHION);
                        entries.add(ModItems.HAY_CUSHION);
                        entries.add(ModItems.TRADER_CUSHION);

                        // Compatibility with Ecologics Mod - https://www.curseforge.com/minecraft/mc-mods/ecologics
                        if (FabricLoader.getInstance().isModLoaded("ecologics")) {

                        }
                        // Compatibility with Biomes O Plenty Mod - https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty
                        if (FabricLoader.getInstance().isModLoaded("biomesoplenty")) {

                        }
                    }).build());

    public static void registerModItemGroups() {
        CozyHome.LOGGER.info("Registering Item Groups for " + CozyHome.MOD_ID);
    }
}
