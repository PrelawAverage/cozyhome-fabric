package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.*;
import net.luckystudio.cozyhome.block.custom.chairs.ChairBlock;
import net.luckystudio.cozyhome.block.custom.counters.CounterBlock;
import net.luckystudio.cozyhome.block.custom.counters.SinkCounterBlock;
import net.luckystudio.cozyhome.block.custom.counters.StorageCounterBlock;
import net.luckystudio.cozyhome.block.custom.clocks.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.custom.clocks.OminousGrandfatherClockBlock;
import net.luckystudio.cozyhome.block.custom.clocks.OminousWallClockBlock;
import net.luckystudio.cozyhome.block.custom.clocks.WallClockBlock;
import net.luckystudio.cozyhome.block.custom.chairs.OminousChairBlock;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.interfaces.SinkBehavior;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.biome.Biome;

import java.util.Map;
import java.util.Set;

public class ModBlocks {

    // Map associating keywords with rarities
    private static final Map<Rarity, Set<String>> RARITY_KEYWORDS = Map.of(
            Rarity.UNCOMMON, Set.of("undead", "ominous"),
            Rarity.RARE, Set.of("ancient", "royal"),
            Rarity.EPIC, Set.of("legendary", "arcane")
    );

    private static Block createPlankedWallBlock(BlockSoundGroup soundGroup) {
        return new PlankedWallBlock(
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup));
    }

    private static Block createCounterBlock(Block block, Boolean bool) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(block);
        if (bool) {
            settings.requiresTool();
        }
        return new CounterBlock(settings);
    }

    private static Block createStorageCounterBlock(Block block, Boolean bool) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(block);
        if (bool) {
            settings.requiresTool();
        }
        return new StorageCounterBlock(settings);
    }

    private static Block createChair(ChairBlock.ChairType chairType, BlockSoundGroup soundGroup) {
        return new ChairBlock(chairType,
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createTable(Block block) {
        return new ShelfTableBlock(
                AbstractBlock.Settings.copy(block).dynamicBounds());
    }

    private static Block createDesk(Block block) {
        return new DeskBlock(
                AbstractBlock.Settings.copy(block));
    }

    private static Block createDrawer(Block block) {
        return new DrawerBlock(block.getDefaultState(),
                AbstractBlock.Settings.copy(block));
    }

    private static Block createWallClock(WallClockBlock.ClockType clockType, Block block) {
        return new WallClockBlock(clockType,
                AbstractBlock.Settings.copy(block)
                        .breakInstantly()
                        .dynamicBounds());
    }

    private static Block createGrandfatherClock(GrandfatherClockBlock.GrandfatherClockType grandfatherClockType, BlockSoundGroup soundGroup) {
        return new GrandfatherClockBlock(grandfatherClockType,
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createToolRack(BlockSoundGroup soundGroup) {
        return new ToolRackBlock(AbstractBlock.Settings.create()
                        .breakInstantly()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createFountain(float hardness, float resistance, BlockSoundGroup soundGroup) {
        return new FountainBlock(
                AbstractBlock.Settings.create()
                        .luminance(ModBlockUtilities.createLightLevelFromContainsBlockState(15))
                        .solid()
                        .hardness(hardness)
                        .strength(resistance)
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createFountainSprout(float hardness, float resistance, BlockSoundGroup soundGroup) {
        return new FountainSproutBlock(
                AbstractBlock.Settings.create()
                        .luminance(ModBlockUtilities.createLightLevelFromContainsBlockState(15))
                        .solid()
                        .hardness(hardness)
                        .strength(resistance)
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createBeam(Block block) {
        return new BeamBlock(.25f,
                AbstractBlock.Settings.copy(block));
    }

    // Add Blocks Here
    public static final Block OAK_PLANKED_WALL = registerBlock("oak_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_PLANKED_WALL = registerBlock("spruce_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block BIRCH_PLANKED_WALL = registerBlock("birch_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_PLANKED_WALL = registerBlock("jungle_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block ACACIA_PLANKED_WALL = registerBlock("acacia_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_PLANKED_WALL = registerBlock("dark_oak_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_PLANKED_WALL = registerBlock("mangrove_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block CHERRY_PLANKED_WALL = registerBlock("cherry_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block BAMBOO_PLANKED_WALL = registerBlock("bamboo_planked_wall", createPlankedWallBlock(BlockSoundGroup.WOOD));
    public static final Block CRIMSON_PLANKED_WALL = registerBlock("crimson_planked_wall", createPlankedWallBlock(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_PLANKED_WALL = registerBlock("warped_planked_wall", createPlankedWallBlock(BlockSoundGroup.NETHER_WOOD));

    // Counters
    public static final Block OAK_COUNTER = registerBlock("oak_counter", createCounterBlock(Blocks.OAK_PLANKS, false));
    public static final Block SPRUCE_COUNTER = registerBlock("spruce_counter", createCounterBlock(Blocks.SPRUCE_PLANKS, false));
    public static final Block BIRCH_COUNTER = registerBlock("birch_counter", createCounterBlock(Blocks.BIRCH_PLANKS, false));
    public static final Block JUNGLE_COUNTER = registerBlock("jungle_counter", createCounterBlock(Blocks.JUNGLE_PLANKS, false));
    public static final Block ACACIA_COUNTER = registerBlock("acacia_counter", createCounterBlock(Blocks.ACACIA_PLANKS, false));
    public static final Block DARK_OAK_COUNTER = registerBlock("dark_oak_counter", createCounterBlock(Blocks.DARK_OAK_PLANKS, false));
    public static final Block MANGROVE_COUNTER = registerBlock("mangrove_counter", createCounterBlock(Blocks.MANGROVE_PLANKS, false));
    public static final Block CHERRY_COUNTER = registerBlock("cherry_counter", createCounterBlock(Blocks.CHERRY_PLANKS, false));
    public static final Block BAMBOO_COUNTER = registerBlock("bamboo_counter", createCounterBlock(Blocks.BAMBOO_PLANKS, false));
    public static final Block CRIMSON_COUNTER = registerBlock("crimson_counter", createCounterBlock(Blocks.CRIMSON_PLANKS, false));
    public static final Block WARPED_COUNTER = registerBlock("warped_counter", createCounterBlock(Blocks.WARPED_PLANKS, false));

    // Storage Counters
    public static final Block OAK_STORAGE_COUNTER = registerBlock("oak_storage_counter", createStorageCounterBlock(Blocks.OAK_PLANKS, false));
    public static final Block SPRUCE_STORAGE_COUNTER = registerBlock("spruce_storage_counter", createStorageCounterBlock(Blocks.SPRUCE_PLANKS, false));
    public static final Block BIRCH_STORAGE_COUNTER = registerBlock("birch_storage_counter", createStorageCounterBlock(Blocks.BIRCH_PLANKS, false));
    public static final Block JUNGLE_STORAGE_COUNTER = registerBlock("jungle_storage_counter", createStorageCounterBlock(Blocks.JUNGLE_PLANKS, false));
    public static final Block ACACIA_STORAGE_COUNTER = registerBlock("acacia_storage_counter", createStorageCounterBlock(Blocks.ACACIA_PLANKS, false));
    public static final Block DARK_OAK_STORAGE_COUNTER = registerBlock("dark_oak_storage_counter", createStorageCounterBlock(Blocks.DARK_OAK_PLANKS, false));
    public static final Block MANGROVE_STORAGE_COUNTER = registerBlock("mangrove_storage_counter", createStorageCounterBlock(Blocks.MANGROVE_PLANKS, false));
    public static final Block CHERRY_STORAGE_COUNTER = registerBlock("cherry_storage_counter", createStorageCounterBlock(Blocks.CHERRY_PLANKS, false));
    public static final Block BAMBOO_STORAGE_COUNTER = registerBlock("bamboo_storage_counter", createStorageCounterBlock(Blocks.BAMBOO_PLANKS, false));
    public static final Block CRIMSON_STORAGE_COUNTER = registerBlock("crimson_storage_counter", createStorageCounterBlock(Blocks.CRIMSON_PLANKS, false));
    public static final Block WARPED_STORAGE_COUNTER = registerBlock("warped_storage_counter", createStorageCounterBlock(Blocks.WARPED_PLANKS, false));

    // Sink Counters
    public static final Block OAK_SINK_COUNTER = registerBlock("oak_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block SPRUCE_SINK_COUNTER = registerBlock("spruce_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block BIRCH_SINK_COUNTER = registerBlock("birch_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block JUNGLE_SINK_COUNTER = registerBlock("jungle_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block ACACIA_SINK_COUNTER = registerBlock("acacia_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block DARK_OAK_SINK_COUNTER = registerBlock("dark_oak_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block MANGROVE_SINK_COUNTER = registerBlock("mangrove_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block CHERRY_SINK_COUNTER = registerBlock("cherry_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block BAMBOO_SINK_COUNTER = registerBlock("bamboo_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block CRIMSON_SINK_COUNTER = registerBlock("crimson_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block WARPED_SINK_COUNTER = registerBlock("warped_sink_counter",
            new SinkCounterBlock(Biome.Precipitation.RAIN, SinkBehavior.EMPTY_CAULDRON_BEHAVIOR, AbstractBlock.Settings.copy(Blocks.CAULDRON)));

    // Glass
    public static final Block AUTUMN_STAINED_GLASS = registerBlock("autumn_stained_glass",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block AUTUMN_STAINED_GLASS_PANE = registerBlock("autumn_stained_glass_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE)));
    public static final Block GOLD_FRAMED_GLASS = registerBlock("gold_framed_glass",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block GOLD_FRAMED_GLASS_PANE = registerBlock("gold_framed_glass_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE)));

    // Chairs
    public static final Block OAK_CHAIR = registerBlock("oak_chair", createChair(ChairBlock.Type.OAK, BlockSoundGroup.WOOD));
    public static final Block SPRUCE_CHAIR = registerBlock("spruce_chair", createChair(ChairBlock.Type.SPRUCE, BlockSoundGroup.WOOD));
    public static final Block BIRCH_CHAIR = registerBlock("birch_chair", createChair(ChairBlock.Type.BIRCH, BlockSoundGroup.WOOD));
    public static final Block JUNGLE_CHAIR = registerBlock("jungle_chair", createChair(ChairBlock.Type.JUNGLE, BlockSoundGroup.WOOD));
    public static final Block ACACIA_CHAIR = registerBlock("acacia_chair", createChair(ChairBlock.Type.ACACIA, BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_CHAIR = registerBlock("dark_oak_chair", createChair(ChairBlock.Type.DARK_OAK, BlockSoundGroup.WOOD));
    public static final Block MANGROVE_CHAIR = registerBlock("mangrove_chair", createChair(ChairBlock.Type.MANGROVE, BlockSoundGroup.WOOD));
    public static final Block CHERRY_CHAIR = registerBlock("cherry_chair", createChair(ChairBlock.Type.CHERRY, BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_CHAIR = registerBlock("bamboo_chair", createChair(ChairBlock.Type.BAMBOO, BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_CHAIR = registerBlock("crimson_chair", createChair(ChairBlock.Type.CRIMSON, BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_CHAIR = registerBlock("warped_chair", createChair(ChairBlock.Type.WARPED, BlockSoundGroup.NETHER_WOOD));
    public static final Block IRON_CHAIR = registerBlock("iron_chair", createChair(ChairBlock.Type.IRON, BlockSoundGroup.METAL));
    public static final Block GLASS_CHAIR = registerBlock("glass_chair", createChair(ChairBlock.Type.GLASS, BlockSoundGroup.GLASS));
    public static final Block UNDEAD_CHAIR = registerBlock("undead_chair", createChair(ChairBlock.Type.UNDEAD, BlockSoundGroup.VAULT));
    public static final Block OMINOUS_CHAIR = registerBlock("ominous_chair", new OminousChairBlock(ChairBlock.Type.OMINOUS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TRIAL_SPAWNER)));

//    // Lamps
//    public static final Block OAK_LAMP = registerBlock("oak_lamp", createLampBlock());
//    public static final Block SPRUCE_LAMP = registerBlock("spruce_lamp", createLampBlock());
//    public static final Block BIRCH_LAMP = registerBlock("birch_lamp", createLampBlock());
//    public static final Block JUNGLE_LAMP = registerBlock("jungle_lamp", createLampBlock());
//    public static final Block ACACIA_LAMP = registerBlock("acacia_lamp", createLampBlock());
//    public static final Block DARK_OAK_LAMP = registerBlock("dark_oak_lamp", createLampBlock());
//    public static final Block MANGROVE_LAMP = registerBlock("mangrove_lamp", createLampBlock());
//    public static final Block CHERRY_LAMP = registerBlock("cherry_lamp", createLampBlock());
//    public static final Block BAMBOO_LAMP = registerBlock("bamboo_lamp", createLampBlock());
//    public static final Block CRIMSON_LAMP = registerBlock("crimson_lamp", createLampBlock());
//    public static final Block WARPED_LAMP = registerBlock("warped_lamp", createLampBlock());

//    // Sofas
//    public static final Block OAK_SOFA = registerBlock("oak_sofa", createSofaBlock());
//    public static final Block SPRUCE_SOFA = registerBlock("spruce_sofa", createSofaBlock());
//    public static final Block BIRCH_SOFA = registerBlock("birch_sofa", createSofaBlock());
//    public static final Block JUNGLE_SOFA = registerBlock("jungle_sofa", createSofaBlock());
//    public static final Block ACACIA_SOFA = registerBlock("acacia_sofa", createSofaBlock());
//    public static final Block DARK_OAK_SOFA = registerBlock("dark_oak_sofa", createSofaBlock());
//    public static final Block MANGROVE_SOFA = registerBlock("mangrove_sofa", createSofaBlock());
//    public static final Block CHERRY_SOFA = registerBlock("cherry_sofa", createSofaBlock());
//    public static final Block BAMBOO_SOFA = registerBlock("bamboo_sofa", createSofaBlock());
//    public static final Block CRIMSON_SOFA = registerBlock("crimson_sofa", createSofaBlock());
//    public static final Block WARPED_SOFA = registerBlock("warped_sofa", createSofaBlock());

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

    // Wall Clocks
    public static final Block OAK_WALL_CLOCK = registerBlock("oak_wall_clock", createWallClock(WallClockBlock.Type.OAK, Blocks.OAK_PLANKS));
    public static final Block SPRUCE_WALL_CLOCK = registerBlock("spruce_wall_clock", createWallClock(WallClockBlock.Type.SPRUCE, Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_WALL_CLOCK = registerBlock("birch_wall_clock", createWallClock(WallClockBlock.Type.BIRCH, Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_WALL_CLOCK = registerBlock("jungle_wall_clock", createWallClock(WallClockBlock.Type.JUNGLE, Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_WALL_CLOCK = registerBlock("acacia_wall_clock", createWallClock(WallClockBlock.Type.ACACIA, Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_WALL_CLOCK = registerBlock("dark_oak_wall_clock", createWallClock(WallClockBlock.Type.DARK_OAK, Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_WALL_CLOCK = registerBlock("mangrove_wall_clock", createWallClock(WallClockBlock.Type.MANGROVE, Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_WALL_CLOCK = registerBlock("cherry_wall_clock", createWallClock(WallClockBlock.Type.CHERRY, Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_WALL_CLOCK = registerBlock("bamboo_wall_clock", createWallClock(WallClockBlock.Type.BAMBOO, Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_WALL_CLOCK = registerBlock("crimson_wall_clock", createWallClock(WallClockBlock.Type.CRIMSON, Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_WALL_CLOCK = registerBlock("warped_wall_clock", createWallClock(WallClockBlock.Type.WARPED, Blocks.WARPED_PLANKS));
    public static final Block IRON_WALL_CLOCK = registerBlock("iron_wall_clock", createWallClock(WallClockBlock.Type.IRON, Blocks.IRON_BLOCK));
    public static final Block GLASS_WALL_CLOCK = registerBlock("glass_wall_clock", createWallClock(WallClockBlock.Type.GLASS, Blocks.GLASS));
    public static final Block UNDEAD_WALL_CLOCK = registerBlock("undead_wall_clock", new WallClockBlock(WallClockBlock.Type.UNDEAD,
            AbstractBlock.Settings.create().breakInstantly().sounds(BlockSoundGroup.VAULT)));
    public static final Block OMINOUS_WALL_CLOCK = registerBlock("ominous_wall_clock", new OminousWallClockBlock(WallClockBlock.Type.OMINOUS,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.TRIAL_SPAWNER).luminance(ModBlockUtilities.createLightLevelFromOminousBehaviour(12))));

    // Tables
    public static final Block OAK_TABLE = registerBlock("oak_table", createTable(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_TABLE = registerBlock("spruce_table", createTable(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_TABLE = registerBlock("birch_table", createTable(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_TABLE = registerBlock("jungle_table", createTable(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_TABLE = registerBlock("acacia_table", createTable(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_TABLE = registerBlock("dark_oak_table", createTable(Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_TABLE = registerBlock("mangrove_table", createTable(Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_TABLE = registerBlock("cherry_table", createTable(Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_TABLE = registerBlock("bamboo_table", createTable(Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_TABLE = registerBlock("crimson_table", createTable(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_TABLE = registerBlock("warped_table", createTable(Blocks.WARPED_PLANKS));
    public static final Block IRON_TABLE = registerBlock("iron_table", createTable(Blocks.IRON_BLOCK));
    public static final Block GLASS_TABLE = registerBlock("glass_table", createTable(Blocks.IRON_BLOCK));
    public static final Block UNDEAD_TABLE = registerBlock("undead_table", new ShelfTableBlock(
            AbstractBlock.Settings.create()
                    .hardness(5)
                    .resistance(5)
                    .sounds(BlockSoundGroup.VAULT).dynamicBounds()));
    public static final Block OMINOUS_TABLE = registerBlock("ominous_table", new ShelfTableBlock(
            AbstractBlock.Settings.create()
                    .hardness(5)
                    .resistance(5)
                    .sounds(BlockSoundGroup.TRIAL_SPAWNER).dynamicBounds()));

    // Desks
    public static final Block OAK_DESK = registerBlock("oak_desk", createDesk(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_DESK = registerBlock("spruce_desk", createDesk(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_DESK = registerBlock("birch_desk", createDesk(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_DESK = registerBlock("jungle_desk", createDesk(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_DESK = registerBlock("acacia_desk", createDesk(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_DESK = registerBlock("dark_oak_desk", createDesk(Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_DESK = registerBlock("mangrove_desk", createDesk(Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_DESK = registerBlock("cherry_desk", createDesk(Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_DESK = registerBlock("bamboo_desk", createDesk(Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_DESK = registerBlock("crimson_desk", createDesk(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_DESK = registerBlock("warped_desk", createDesk(Blocks.WARPED_PLANKS));

    // Drawers
    public static final Block OAK_DRAWER = registerBlock("oak_drawer", createDrawer(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_DRAWER = registerBlock("spruce_drawer", createDrawer(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_DRAWER = registerBlock("birch_drawer", createDrawer(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_DRAWER = registerBlock("jungle_drawer", createDrawer(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_DRAWER = registerBlock("acacia_drawer", createDrawer(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_DRAWER = registerBlock("dark_oak_drawer", createDrawer(Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_DRAWER = registerBlock("mangrove_drawer", createDrawer(Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_DRAWER = registerBlock("cherry_drawer", createDrawer(Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_DRAWER = registerBlock("bamboo_drawer", createDrawer(Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_DRAWER = registerBlock("crimson_drawer", createDrawer(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_DRAWER = registerBlock("warped_drawer", createDrawer(Blocks.WARPED_PLANKS));

    // Fountains
    public static final Block STONE_BRICK_FOUNTAIN = registerBlock("stone_brick_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block MOSSY_STONE_BRICK_FOUNTAIN = registerBlock("mossy_stone_brick_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block GRANITE_FOUNTAIN = registerBlock("granite_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DIORITE_FOUNTAIN = registerBlock("diorite_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block ANDESITE_FOUNTAIN = registerBlock("andesite_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DEEPSLATE_FOUNTAIN = registerBlock("deepslate_fountain", createFountain(3,6, BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block TUFF_FOUNTAIN = registerBlock("tuff_fountain", createFountain(1.5f,6, BlockSoundGroup.POLISHED_TUFF));
    public static final Block BRICK_FOUNTAIN = registerBlock("brick_fountain", createFountain(2,6, BlockSoundGroup.STONE));
    public static final Block MUD_FOUNTAIN = registerBlock("mud_fountain", createFountain(1.5f,3, BlockSoundGroup.MUD_BRICKS));
    public static final Block SANDSTONE_FOUNTAIN = registerBlock("sandstone_fountain", createFountain(2,6, BlockSoundGroup.STONE));
    public static final Block RED_SANDSTONE_FOUNTAIN = registerBlock("red_sandstone_fountain", createFountain(2,6, BlockSoundGroup.STONE));
    public static final Block PRISMARINE_FOUNTAIN = registerBlock("prismarine_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block NETHER_BRICK_FOUNTAIN = registerBlock("nether_brick_fountain", createFountain(2,6, BlockSoundGroup.NETHER_BRICKS));
    public static final Block RED_NETHER_BRICK_FOUNTAIN = registerBlock("red_nether_brick_fountain", createFountain(2,6, BlockSoundGroup.NETHER_BRICKS));
    public static final Block BLACKSTONE_FOUNTAIN = registerBlock("blackstone_fountain", createFountain(1.5f,6, BlockSoundGroup.GILDED_BLACKSTONE));
    public static final Block ENDSTONE_FOUNTAIN = registerBlock("endstone_fountain", createFountain(3,9, BlockSoundGroup.STONE));
    public static final Block PURPUR_FOUNTAIN = registerBlock("purpur_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));

    // Fountains Sprouts
    public static final Block STONE_BRICK_FOUNTAIN_SPROUT = registerBlock("stone_brick_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));
    public static final Block MOSSY_STONE_BRICK_FOUNTAIN_SPROUT = registerBlock("mossy_stone_brick_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));
    public static final Block GRANITE_FOUNTAIN_SPROUT = registerBlock("granite_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DIORITE_FOUNTAIN_SPROUT = registerBlock("diorite_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));
    public static final Block ANDESITE_FOUNTAIN_SPROUT = registerBlock("andesite_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DEEPSLATE_FOUNTAIN_SPROUT = registerBlock("deepslate_fountain_sprout", createFountainSprout(3,6, BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block TUFF_FOUNTAIN_SPROUT = registerBlock("tuff_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.POLISHED_TUFF));
    public static final Block BRICK_FOUNTAIN_SPROUT = registerBlock("brick_fountain_sprout", createFountainSprout(2,6, BlockSoundGroup.STONE));
    public static final Block MUD_FOUNTAIN_SPROUT = registerBlock("mud_fountain_sprout", createFountainSprout(1.5f,3, BlockSoundGroup.MUD_BRICKS));
    public static final Block SANDSTONE_FOUNTAIN_SPROUT = registerBlock("sandstone_fountain_sprout", createFountainSprout(2,6, BlockSoundGroup.STONE));
    public static final Block RED_SANDSTONE_FOUNTAIN_SPROUT = registerBlock("red_sandstone_fountain_sprout", createFountainSprout(2,6, BlockSoundGroup.STONE));
    public static final Block PRISMARINE_FOUNTAIN_SPROUT = registerBlock("prismarine_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));
    public static final Block NETHER_BRICK_FOUNTAIN_SPROUT = registerBlock("nether_brick_fountain_sprout", createFountainSprout(2,6, BlockSoundGroup.NETHER_BRICKS));
    public static final Block RED_NETHER_BRICK_FOUNTAIN_SPROUT = registerBlock("red_nether_brick_fountain_sprout", createFountainSprout(2,6, BlockSoundGroup.NETHER_BRICKS));
    public static final Block BLACKSTONE_FOUNTAIN_SPROUT = registerBlock("blackstone_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.GILDED_BLACKSTONE));
    public static final Block ENDSTONE_FOUNTAIN_SPROUT = registerBlock("endstone_fountain_sprout", createFountainSprout(3,9, BlockSoundGroup.STONE));
    public static final Block PURPUR_FOUNTAIN_SPROUT = registerBlock("purpur_fountain_sprout", createFountainSprout(1.5f,6, BlockSoundGroup.STONE));

    public static final Block FALLING_LIQUID = registerBlock("falling_liquid", new FallingLiquidBlock(AbstractBlock.Settings.create()
            .replaceable()
            .luminance(ModBlockUtilities.createLightLevelFromContainsBlockState(15))));

    // Beams
    public static final Block OAK_BEAM = registerBlock("oak_beam", createBeam(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_BEAM = registerBlock("spruce_beam", createBeam(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_BEAM = registerBlock("birch_beam", createBeam(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_BEAM = registerBlock("jungle_beam", createBeam(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_BEAM = registerBlock("acacia_beam", createBeam(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_BEAM = registerBlock("dark_oak_beam", createBeam(Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_BEAM = registerBlock("mangrove_beam", createBeam(Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_BEAM = registerBlock("cherry_beam", createBeam(Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_BEAM = registerBlock("bamboo_beam", createBeam(Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_BEAM = registerBlock("crimson_beam", createBeam(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_BEAM = registerBlock("warped_beam", createBeam(Blocks.WARPED_PLANKS));
    public static final Block STRIPPED_OAK_BEAM = registerBlock("stripped_oak_beam", createBeam(Blocks.OAK_PLANKS));
    public static final Block STRIPPED_SPRUCE_BEAM = registerBlock("stripped_spruce_beam", createBeam(Blocks.SPRUCE_PLANKS));
    public static final Block STRIPPED_BIRCH_BEAM = registerBlock("stripped_birch_beam", createBeam(Blocks.BIRCH_PLANKS));
    public static final Block STRIPPED_JUNGLE_BEAM = registerBlock("stripped_jungle_beam", createBeam(Blocks.JUNGLE_PLANKS));
    public static final Block STRIPPED_ACACIA_BEAM = registerBlock("stripped_acacia_beam", createBeam(Blocks.ACACIA_PLANKS));
    public static final Block STRIPPED_DARK_OAK_BEAM = registerBlock("stripped_dark_oak_beam", createBeam(Blocks.DARK_OAK_PLANKS));
    public static final Block STRIPPED_MANGROVE_BEAM = registerBlock("stripped_mangrove_beam", createBeam(Blocks.MANGROVE_PLANKS));
    public static final Block STRIPPED_CHERRY_BEAM = registerBlock("stripped_cherry_beam", createBeam(Blocks.CHERRY_PLANKS));
    public static final Block STRIPPED_BAMBOO_BEAM = registerBlock("stripped_bamboo_beam", createBeam(Blocks.BAMBOO_PLANKS));
    public static final Block STRIPPED_CRIMSON_BEAM = registerBlock("stripped_crimson_beam", createBeam(Blocks.CRIMSON_PLANKS));
    public static final Block STRIPPED_WARPED_BEAM = registerBlock("stripped_warped_beam", createBeam(Blocks.WARPED_PLANKS));

//    // Large Stumps
//    public static final Block OAK_LARGE_STUMP = registerBlock("oak_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block SPRUCE_LARGE_STUMP = registerBlock("spruce_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block BIRCH_LARGE_STUMP = registerBlock("birch_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block JUNGLE_LARGE_STUMP = registerBlock("jungle_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block ACACIA_LARGE_STUMP = registerBlock("acacia_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block DARK_OAK_LARGE_STUMP = registerBlock("dark_oak_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block MANGROVE_LARGE_STUMP = registerBlock("mangrove_large_stump", createLargeStump(BlockSoundGroup.WOOD));
//    public static final Block CHERRY_LARGE_STUMP = registerBlock("cherry_large_stump", createLargeStump(BlockSoundGroup.CHERRY_WOOD));
//    public static final Block BAMBOO_LARGE_STUMP = registerBlock("bamboo_large_stump", createLargeStump(BlockSoundGroup.BAMBOO_WOOD));
//    public static final Block CRIMSON_LARGE_STUMP = registerBlock("crimson_large_stump", createLargeStump(BlockSoundGroup.NETHER_WOOD));
//    public static final Block WARPED_LARGE_STUMP = registerBlock("warped_large_stump", createLargeStump(BlockSoundGroup.NETHER_WOOD));

    // Chairs
    public static final Block OAK_GRANDFATHER_CLOCK = registerBlock("oak_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.OAK, BlockSoundGroup.WOOD));
    public static final Block SPRUCE_GRANDFATHER_CLOCK = registerBlock("spruce_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.SPRUCE, BlockSoundGroup.WOOD));
    public static final Block BIRCH_GRANDFATHER_CLOCK = registerBlock("birch_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.BIRCH, BlockSoundGroup.WOOD));
    public static final Block JUNGLE_GRANDFATHER_CLOCK = registerBlock("jungle_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.JUNGLE, BlockSoundGroup.WOOD));
    public static final Block ACACIA_GRANDFATHER_CLOCK = registerBlock("acacia_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.ACACIA, BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_GRANDFATHER_CLOCK = registerBlock("dark_oak_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.DARK_OAK, BlockSoundGroup.WOOD));
    public static final Block MANGROVE_GRANDFATHER_CLOCK = registerBlock("mangrove_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.MANGROVE, BlockSoundGroup.WOOD));
    public static final Block CHERRY_GRANDFATHER_CLOCK = registerBlock("cherry_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.CHERRY, BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_GRANDFATHER_CLOCK = registerBlock("bamboo_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.BAMBOO, BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_GRANDFATHER_CLOCK = registerBlock("crimson_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.CRIMSON, BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_GRANDFATHER_CLOCK = registerBlock("warped_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.WARPED, BlockSoundGroup.NETHER_WOOD));
    public static final Block IRON_GRANDFATHER_CLOCK = registerBlock("iron_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.IRON, BlockSoundGroup.METAL));
    public static final Block GLASS_GRANDFATHER_CLOCK = registerBlock("glass_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.GLASS, BlockSoundGroup.GLASS));
    public static final Block UNDEAD_GRANDFATHER_CLOCK = registerBlock("undead_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.UNDEAD, BlockSoundGroup.VAULT));
    public static final Block OMINOUS_GRANDFATHER_CLOCK = registerBlock("ominous_grandfather_clock", new OminousGrandfatherClockBlock(GrandfatherClockBlock.Type.OMINOUS,
                    AbstractBlock.Settings.create()
                            .hardness(5)
                            .resistance(5)
                            .sounds(BlockSoundGroup.TRIAL_SPAWNER)
                            .luminance(ModBlockUtilities.createLightLevelFromOminousBehaviour(12))));

    // Tool Racks
    public static final Block OAK_TOOL_RACK = registerBlock("oak_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_TOOL_RACK = registerBlock("spruce_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block BIRCH_TOOL_RACK = registerBlock("birch_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_TOOL_RACK = registerBlock("jungle_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block ACACIA_TOOL_RACK = registerBlock("acacia_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_TOOL_RACK = registerBlock("dark_oak_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_TOOL_RACK = registerBlock("mangrove_tool_rack", createToolRack(BlockSoundGroup.WOOD));
    public static final Block CHERRY_TOOL_RACK = registerBlock("cherry_tool_rack", createToolRack(BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_TOOL_RACK = registerBlock("bamboo_tool_rack", createToolRack(BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_TOOL_RACK = registerBlock("crimson_tool_rack", createToolRack(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_TOOL_RACK = registerBlock("warped_tool_rack", createToolRack(BlockSoundGroup.NETHER_WOOD));
    public static final Block IRON_TOOL_RACK = registerBlock("iron_tool_rack", createToolRack(BlockSoundGroup.METAL));
    public static final Block GLASS_TOOL_RACK = registerBlock("glass_tool_rack", createToolRack(BlockSoundGroup.GLASS));
    public static final Block UNDEAD_TOOL_RACK = registerBlock("undead_tool_rack", createToolRack(BlockSoundGroup.VAULT));
    public static final Block OMINOUS_TOOL_RACK = registerBlock("ominous_tool_rack", createToolRack(BlockSoundGroup.TRIAL_SPAWNER));

    public static final Block TELESCOPE = registerBlock("telescope", new TelescopeBlock(AbstractBlock.Settings.create()
            .breakInstantly()
            .mapColor(DyeColor.ORANGE)
            .sounds(BlockSoundGroup.COPPER)));

    // Register Block Method
    private static Block registerBlock(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CozyHome.MOD_ID, name), block);
    }

    // Helper Method for Register Block Method (Handles rarity dynamically)
    private static void registerBlockItems(String name, Block block) {
        Rarity rarity = determineRarity(name);
        Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name),
                new BlockItem(block, new Item.Settings().rarity(rarity)));
    }

    // Determines the rarity based on keywords in the name
    private static Rarity determineRarity(String name) {
        for (var entry : RARITY_KEYWORDS.entrySet()) {
            for (String keyword : entry.getValue()) {
                if (name.contains(keyword)) {
                    return entry.getKey();
                }
            }
        }
        return Rarity.COMMON; // Default rarity
    }

    // Registering Blocks
    public static void registerModBlocks(){
        CozyHome.LOGGER.info("Registering ModBlocks for " + CozyHome.MOD_ID);
    }
}

