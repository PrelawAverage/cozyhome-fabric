package net.luckystudio.cozyhome.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ModItemGroups {

    public static final ItemGroup COZY_HOME = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CozyHome.MOD_ID, "cozyhome"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.OAK_PLANKED_WALL))
                    .displayName(Text.translatable("itemgroup.cozyhome.cozyhome"))
                    .entries((displayContext, entries) -> {
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
                    }).build());

    public static void registerModItemGroups() {
        CozyHome.LOGGER.info("Registering Item Groups for " + CozyHome.MOD_ID);
    }
}
