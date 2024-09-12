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
    public static final Block SPRUCE_PLANKED_WALL = registerBlock("spruce_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final Block BIRCH_PLANKED_WALL = registerBlock("birch_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block JUNGLE_PLANKED_WALL = registerBlock("jungle_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block ACACIA_PLANKED_WALL = registerBlock("acacia_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final Block DARK_OAK_PLANKED_WALL = registerBlock("dark_oak_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Block MANGROVE_PLANKED_WALL = registerBlock("mangrove_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));
    public static final Block CHERRY_PLANKED_WALL = registerBlock("cherry_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));
    public static final Block BAMBOO_PLANKED_WALL = registerBlock("bamboo_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block CRIMSON_PLANKED_WALL = registerBlock("crimson_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_PLANKED_WALL = registerBlock("warped_planked_wall",
            new PlankedWallBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));

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

