package net.luckystudio.cozyhome.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.LampBlock;
import net.luckystudio.cozyhome.block.custom.PlankedWallBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ModBlocks {
    private static Block createPlankedWall(BlockSoundGroup soundGroup) {
        return new PlankedWallBlock(
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
        );
    }
    private static Block createLampBlock(MapColor color) {
        return new LampBlock(
                AbstractBlock.Settings.create()
                        .nonOpaque()
                        .luminance(createLightLevelFromLitBlockState(9))
                        .strength(0.6f)
                        .burnable()
                        .sounds(BlockSoundGroup.LANTERN)
        );
    }

    // Add Blocks Here
    public static final Block OAK_PLANKED_WALL = registerBlock("oak_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_PLANKED_WALL = registerBlock("spruce_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block BIRCH_PLANKED_WALL = registerBlock("birch_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_PLANKED_WALL = registerBlock("jungle_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block ACACIA_PLANKED_WALL = registerBlock("acacia_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_PLANKED_WALL = registerBlock("dark_oak_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_PLANKED_WALL = registerBlock("mangrove_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block CHERRY_PLANKED_WALL = registerBlock("cherry_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block BAMBOO_PLANKED_WALL = registerBlock("bamboo_planked_wall", createPlankedWall(BlockSoundGroup.WOOD));
    public static final Block CRIMSON_PLANKED_WALL = registerBlock("crimson_planked_wall", createPlankedWall(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_PLANKED_WALL = registerBlock("warped_planked_wall", createPlankedWall(BlockSoundGroup.NETHER_WOOD));

    public static final Block AUTUMN_STAINED_WINDOW = registerBlock("autumn_stained_window",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block AUTUMN_STAINED_WINDOW_PANE = registerBlock("autumn_stained_window_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE)));

    // Lamps
    public static final Block WHITE_LAMP = registerBlock("white_lamp", createLampBlock(MapColor.WHITE));
    public static final Block ORANGE_LAMP = registerBlock("orange_lamp", createLampBlock(MapColor.ORANGE));
    public static final Block MAGENTA_LAMP = registerBlock("magenta_lamp", createLampBlock(MapColor.MAGENTA));
    public static final Block LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp", createLampBlock(MapColor.LIGHT_BLUE));
    public static final Block YELLOW_LAMP = registerBlock("yellow_lamp", createLampBlock(MapColor.YELLOW));
    public static final Block LIME_LAMP = registerBlock("lime_lamp", createLampBlock(MapColor.LIME));
    public static final Block PINK_LAMP = registerBlock("pink_lamp", createLampBlock(MapColor.PINK));
    public static final Block GRAY_LAMP = registerBlock("gray_lamp",  createLampBlock(MapColor.GRAY));
    public static final Block LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp", createLampBlock(MapColor.LIGHT_GRAY));
    public static final Block CYAN_LAMP = registerBlock("cyan_lamp", createLampBlock(MapColor.CYAN));
    public static final Block PURPLE_LAMP = registerBlock("purple_lamp", createLampBlock(MapColor.PURPLE));
    public static final Block BLUE_LAMP = registerBlock("blue_lamp", createLampBlock(MapColor.BLUE));
    public static final Block BROWN_LAMP = registerBlock("brown_lamp", createLampBlock(MapColor.BROWN));
    public static final Block GREEN_LAMP = registerBlock("green_lamp", createLampBlock(MapColor.GREEN));
    public static final Block RED_LAMP = registerBlock("red_lamp", createLampBlock(MapColor.RED));
    public static final Block BLACK_LAMP = registerBlock("black_lamp", createLampBlock(MapColor.BLACK));

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

