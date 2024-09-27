package net.luckystudio.cozyhome.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.*;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.function.ToIntFunction;

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

    public static final Block OAK_COUNTER = registerBlock("oak_counter",
            new CounterBlock(Blocks.OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_COUNTER = registerBlock("spruce_counter",
            new CounterBlock(Blocks.SPRUCE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final Block BIRCH_COUNTER = registerBlock("birch_counter",
            new CounterBlock(Blocks.BIRCH_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block JUNGLE_COUNTER = registerBlock("jungle_counter",
            new CounterBlock(Blocks.JUNGLE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block ACACIA_COUNTER = registerBlock("acacia_counter",
            new CounterBlock(Blocks.ACACIA_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final Block DARK_OAK_COUNTER = registerBlock("dark_oak_counter",
            new CounterBlock(Blocks.DARK_OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Block MANGROVE_COUNTER = registerBlock("mangrove_counter",
            new CounterBlock(Blocks.MANGROVE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));
    public static final Block CHERRY_COUNTER = registerBlock("cherry_counter",
            new CounterBlock(Blocks.CHERRY_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));
    public static final Block BAMBOO_COUNTER = registerBlock("bamboo_counter",
            new CounterBlock(Blocks.BAMBOO_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block CRIMSON_COUNTER = registerBlock("crimson_counter",
            new CounterBlock(Blocks.CRIMSON_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_COUNTER = registerBlock("warped_counter",
            new CounterBlock(Blocks.WARPED_PLANKS.getDefaultState(), AbstractBlock.Settings.create().burnable()));

    public static final Block AUTUMN_STAINED_WINDOW = registerBlock("autumn_stained_window",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block AUTUMN_STAINED_WINDOW_PANE = registerBlock("autumn_stained_window_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE)));

    // Lamps
    public static final Block WHITE_LAMP = registerBlock("white_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block ORANGE_LAMP = registerBlock("orange_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block MAGENTA_LAMP = registerBlock("magenta_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.MAGENTA).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block YELLOW_LAMP = registerBlock("yellow_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block LIME_LAMP = registerBlock("lime_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIME).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block PINK_LAMP = registerBlock("pink_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block GRAY_LAMP = registerBlock("gray_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block CYAN_LAMP = registerBlock("cyan_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block PURPLE_LAMP = registerBlock("purple_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block BLUE_LAMP = registerBlock("blue_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block BROWN_LAMP = registerBlock("brown_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block GREEN_LAMP = registerBlock("green_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block RED_LAMP = registerBlock("red_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.RED).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );
    public static final Block BLACK_LAMP = registerBlock("black_lamp",
            new LampBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).luminance(createLightLevelFromLitBlockState(9)).strength(.1F).sounds(BlockSoundGroup.WOOL))
    );

    public static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return state -> state.get(Properties.LIT) ? litLevel : 0;
    }

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

