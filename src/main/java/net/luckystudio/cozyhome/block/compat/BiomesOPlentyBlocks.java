package net.luckystudio.cozyhome.block.compat;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.primary.*;
import net.luckystudio.cozyhome.block.primary.secondary.DyeVatBlock;
import net.luckystudio.cozyhome.block.primary.secondary.DyeableLampBlock;
import net.luckystudio.cozyhome.block.primary.secondary.tertiary.DyeableChairBlock;
import net.luckystudio.cozyhome.block.primary.secondary.tertiary.DyeableSofaBlock;
import net.luckystudio.cozyhome.block.special.MangroveLanternBlock;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.interfaces.SinkBehavior;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BiomesOPlentyBlocks {
    // Add Blocks Here

    // Counters

    // Storage Counters

    // Sink Counters

    // Glass

    // Chairs

    // Lamps

    // Sofas

    // Wall Mirrors

    // Wall Mirrors

    // Tables

    // Desks

    // Fountains

    // Fountains Sprouts

    // Beams

    // Register Block Method
    private static Block registerBlock(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CozyHome.MOD_ID, name), block);
    }

    // Helper Method for Register Block Method
    private static void registerBlockItems(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    // Registering Blocks
    public static void registerModBlocks(){
        CozyHome.LOGGER.info("Registering ModBlocks for " + CozyHome.MOD_ID);
    }
}

