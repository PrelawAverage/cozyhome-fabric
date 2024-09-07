package net.luckystudio.cozyhome.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.PlankedWallBlock;
import net.luckystudio.cozyhome.item.ModItemGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Add Blocks Here
    public static final Block OAK_PLANKED_WALL = registerBlock("oak_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    // Register Block Method
    private static Block registerBlock(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CozyHome.MOD_ID, name), block);
    }

    // Helper Method
    private static void registerBlockItems(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        CozyHome.LOGGER.info("Registering ModBlocks for " + CozyHome.MOD_ID);
    }
}

