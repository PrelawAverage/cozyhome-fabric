package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.special.DynastyLanternBlock;
import net.luckystudio.cozyhome.block.special.DynastySeatBlock;
import net.luckystudio.cozyhome.block.type.*;
import net.minecraft.block.*;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.ToIntFunction;

public class ModBlocks {
    private static Block createPlankedWall(BlockSoundGroup soundGroup) {
        return new PlankedWallBlock(
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }
    private static Block createLampBlock(MapColor color) {
        return new LampBlock(
                AbstractBlock.Settings.create()
                        .mapColor(color)
                        .nonOpaque()
                        .luminance(createLightLevelFromLitBlockState(9))
                        .emissiveLighting(ModBlocks::ifLit)
                        .breakInstantly()
                        .strength(0.6f)
                        .burnable()
                        .sounds(BlockSoundGroup.LANTERN));
    }
    private static Block createSofaBlock(MapColor color) {
        return new SofaBlock(
                AbstractBlock.Settings.create()
                        .mapColor(color)
                        .nonOpaque()
                        .hardness(1f)
                        .strength(1f)
                        .burnable()
                        .sounds(BlockSoundGroup.WOOL));
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

    // Counters
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
            new CounterBlock(Blocks.WARPED_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));

    public static final Block AUTUMN_STAINED_WINDOW = registerBlock("autumn_stained_window",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block AUTUMN_STAINED_WINDOW_PANE = registerBlock("autumn_stained_window_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE)));

    // Chairs
    public static final Block OAK_CHAIR = registerBlock("oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block WHITE_OAK_CHAIR = registerBlock("white_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block ORANGE_OAK_CHAIR = registerBlock("orange_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block MAGENTA_OAK_CHAIR = registerBlock("magenta_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block LIGHT_BLUE_OAK_CHAIR = registerBlock("light_blue_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block YELLOW_OAK_CHAIR = registerBlock("yellow_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block LIME_OAK_CHAIR = registerBlock("lime_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block PINK_OAK_CHAIR = registerBlock("pink_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block GRAY_OAK_CHAIR = registerBlock("gray_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block LIGHT_GRAY_OAK_CHAIR = registerBlock("light_gray_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CYAN_OAK_CHAIR = registerBlock("cyan_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block PURPLE_OAK_CHAIR = registerBlock("purple_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BLUE_OAK_CHAIR = registerBlock("blue_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BROWN_OAK_CHAIR = registerBlock("brown_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block GREEN_OAK_CHAIR = registerBlock("green_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block RED_OAK_CHAIR = registerBlock("red_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BLACK_OAK_CHAIR = registerBlock("black_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_CHAIR = registerBlock("spruce_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final Block BIRCH_CHAIR = registerBlock("birch_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block JUNGLE_CHAIR = registerBlock("jungle_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block ACACIA_CHAIR = registerBlock("acacia_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final Block DARK_OAK_CHAIR = registerBlock("dark_oak_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Block MANGROVE_CHAIR = registerBlock("mangrove_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));
    public static final Block CHERRY_CHAIR = registerBlock("cherry_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));
    public static final Block BAMBOO_CHAIR = registerBlock("bamboo_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block CRIMSON_CHAIR = registerBlock("crimson_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_CHAIR = registerBlock("warped_chair",
            new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));

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

    // Sofas
    public static final Block WHITE_SOFA = registerBlock("white_sofa", createSofaBlock(MapColor.WHITE));
    public static final Block ORANGE_SOFA = registerBlock("orange_sofa", createSofaBlock(MapColor.ORANGE));
    public static final Block MAGENTA_SOFA = registerBlock("magenta_sofa", createSofaBlock(MapColor.MAGENTA));
    public static final Block LIGHT_BLUE_SOFA = registerBlock("light_blue_sofa", createSofaBlock(MapColor.LIGHT_BLUE));
    public static final Block YELLOW_SOFA = registerBlock("yellow_sofa", createSofaBlock(MapColor.YELLOW));
    public static final Block LIME_SOFA = registerBlock("lime_sofa", createSofaBlock(MapColor.LIME));
    public static final Block PINK_SOFA = registerBlock("pink_sofa", createSofaBlock(MapColor.PINK));
    public static final Block GRAY_SOFA = registerBlock("gray_sofa",  createSofaBlock(MapColor.GRAY));
    public static final Block LIGHT_GRAY_SOFA = registerBlock("light_gray_sofa", createSofaBlock(MapColor.LIGHT_GRAY));
    public static final Block CYAN_SOFA = registerBlock("cyan_sofa", createSofaBlock(MapColor.CYAN));
    public static final Block PURPLE_SOFA = registerBlock("purple_sofa", createSofaBlock(MapColor.PURPLE));
    public static final Block BLUE_SOFA = registerBlock("blue_sofa", createSofaBlock(MapColor.BLUE));
    public static final Block BROWN_SOFA = registerBlock("brown_sofa", createSofaBlock(MapColor.BROWN));
    public static final Block GREEN_SOFA = registerBlock("green_sofa", createSofaBlock(MapColor.GREEN));
    public static final Block RED_SOFA = registerBlock("red_sofa", createSofaBlock(MapColor.RED));
    public static final Block BLACK_SOFA = registerBlock("black_sofa", createSofaBlock(MapColor.BLACK));

    // Wall Mirrors
    public static final Block OAK_WALL_MIRROR = registerBlock("oak_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block SPRUCE_WALL_MIRROR = registerBlock("spruce_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block BIRCH_WALL_MIRROR = registerBlock("birch_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block JUNGLE_WALL_MIRROR = registerBlock("jungle_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block ACACIA_WALL_MIRROR = registerBlock("acacia_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block DARK_OAK_WALL_MIRROR = registerBlock("dark_oak_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block MANGROVE_WALL_MIRROR = registerBlock("mangrove_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block CHERRY_WALL_MIRROR = registerBlock("cherry_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block BAMBOO_WALL_MIRROR = registerBlock("bamboo_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block CRIMSON_WALL_MIRROR = registerBlock("crimson_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    public static final Block WARPED_WALL_MIRROR = registerBlock("warped_wall_mirror", new WallMirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));

    // Dynasty Set
    public static final Block DYNASTY_LAMP = registerBlock("dynasty_lamp",
            createLampBlock(MapColor.DULL_RED));
    public static final Block DYNASTY_LANTERN = registerBlock("dynasty_lantern",
            new DynastyLanternBlock(AbstractBlock.Settings.create()
                    .luminance(createLightLevelFromLitBlockState(9))
                    .nonOpaque()
                    .emissiveLighting(ModBlocks::ifLit)));
    public static final Block DYNASTY_CHAIR = registerBlock("dynasty_chair", new DynastySeatBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));

    // Princess Set
    public static final Block PRINCESS_CHAIR = registerBlock("princess_chair", new GenericChairBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)));

    public static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return state -> state.get(Properties.LIT) ? litLevel : 0;
    }

    public static boolean ifLit(BlockState state, BlockView world, BlockPos pos) {
        return state.get(Properties.LIT);
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

