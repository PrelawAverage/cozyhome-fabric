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

                        entries.add(ModBlocks.DYE_VAT);
                        entries.add(ModItems.PAINT_BRUSH);
                        // Planked Walls
                        entries.add(ModBlocks.OAK_PLANKED_WALL);
                        entries.add(ModBlocks.SPRUCE_PLANKED_WALL);
                        entries.add(ModBlocks.BIRCH_PLANKED_WALL);
                        entries.add(ModBlocks.JUNGLE_PLANKED_WALL);
                        entries.add(ModBlocks.ACACIA_PLANKED_WALL);
                        entries.add(ModBlocks.DARK_OAK_PLANKED_WALL);
                        entries.add(ModBlocks.MANGROVE_PLANKED_WALL);
                        entries.add(ModBlocks.CHERRY_PLANKED_WALL);
                        entries.add(ModBlocks.BAMBOO_PLANKED_WALL);
                        entries.add(ModBlocks.CRIMSON_PLANKED_WALL);
                        entries.add(ModBlocks.WARPED_PLANKED_WALL);

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

                        // Lamps
                        entries.add(ModBlocks.OAK_LAMP);

                        // Sofas
                        entries.add(ModBlocks.OAK_SOFA);

                        // Specials (Mangrove)
//                        entries.add(ModBlocks.MANGROVE_LAMP);
                        entries.add(ModBlocks.MANGROVE_LANTERN);
                        entries.add(ModBlocks.MANGROVE_ZAISU);

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

                        // Compat with Ecologics Mod - https://www.curseforge.com/minecraft/mc-mods/ecologics
                        if (FabricLoader.getInstance().isModLoaded("ecologics")) {

                        }
                        // Compat with Biomes O Plenty Mod - https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty
                        if (FabricLoader.getInstance().isModLoaded("biomesoplenty")) {

                        }
                    }).build());

    public static void registerModItemGroups() {
        CozyHome.LOGGER.info("Registering Item Groups for " + CozyHome.MOD_ID);
    }
}
