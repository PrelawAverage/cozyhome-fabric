package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.primary.*;
import net.luckystudio.cozyhome.block.primary.secondary.ChairBlock;
import net.luckystudio.cozyhome.block.primary.secondary.DyeVatBlock;
import net.luckystudio.cozyhome.block.primary.secondary.DyeableLampBlock;
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
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

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
    private static Block createChair(ChairBlock.ChairType chairType, BlockSoundGroup soundGroup) {
        return new ChairBlock(chairType,
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }
private static Block createColorLampBlock() {
    return new DyeableLampBlock(
            AbstractBlock.Settings.create()
                    .luminance(ModBlockUtilities.createLightLevelFromLitBlockState(9))
                    .emissiveLighting(ModBlockUtilities::ifLit)
                    .nonOpaque()
                    .burnable()
                    .sounds(BlockSoundGroup.LANTERN));
    }
    private static Block createSofaBlock() {
        return new DyeableSofaBlock(
                AbstractBlock.Settings.create()
                        .mapColor(MapColor.WHITE)
                        .nonOpaque()
                        .hardness(1f)
                        .strength(1f)
                        .burnable()
                        .sounds(BlockSoundGroup.WOOL));
    }

    private static Block createTable(BlockSoundGroup soundGroup) {
        return new PlankedWallBlock(
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createDesk(BlockSoundGroup soundGroup) {
        return new PlankedWallBlock(
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createWallClock(BlockSoundGroup soundGroup) {
        return new ClockBlock(
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .burnable()
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

    private static Block createBeam(BlockSoundGroup soundGroup) {
        return new BeamBlock(.25f,
                AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(3)
                        .requiresTool()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createLargeStump(BlockSoundGroup soundGroup) {
        return new LargeStumpBlock(AbstractBlock.Settings.create()
                        .hardness(2)
                        .strength(2)
                        .burnable()
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    public static final Block DYE_VAT = registerBlock("dye_vat",
            new DyeVatBlock(Blocks.CAULDRON.getDefaultState(), AbstractBlock.Settings.create()
                    .strength(2.5f, 2.5f)
                    .nonOpaque()
                    .requiresTool()
                    .dynamicBounds()));

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

    // Storage Counters
    public static final Block OAK_STORAGE_COUNTER = registerBlock("oak_storage_counter",
            new StorageCounterBlock(Blocks.OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_STORAGE_COUNTER = registerBlock("spruce_storage_counter",
            new StorageCounterBlock(Blocks.SPRUCE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final Block BIRCH_STORAGE_COUNTER = registerBlock("birch_storage_counter",
            new StorageCounterBlock(Blocks.BIRCH_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block JUNGLE_STORAGE_COUNTER = registerBlock("jungle_storage_counter",
            new StorageCounterBlock(Blocks.JUNGLE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block ACACIA_STORAGE_COUNTER = registerBlock("acacia_storage_counter",
            new StorageCounterBlock(Blocks.ACACIA_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final Block DARK_OAK_STORAGE_COUNTER = registerBlock("dark_oak_storage_counter",
            new StorageCounterBlock(Blocks.DARK_OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Block MANGROVE_STORAGE_COUNTER = registerBlock("mangrove_storage_counter",
            new StorageCounterBlock(Blocks.MANGROVE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));
    public static final Block CHERRY_STORAGE_COUNTER = registerBlock("cherry_storage_counter",
            new StorageCounterBlock(Blocks.CHERRY_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));
    public static final Block BAMBOO_STORAGE_COUNTER = registerBlock("bamboo_storage_counter",
            new StorageCounterBlock(Blocks.BAMBOO_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block CRIMSON_STORAGE_COUNTER = registerBlock("crimson_storage_counter",
            new StorageCounterBlock(Blocks.CRIMSON_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_STORAGE_COUNTER = registerBlock("warped_storage_counter",
            new StorageCounterBlock(Blocks.WARPED_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));

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
    public static final Block PRINCESS_CHAIR = registerBlock("princess_chair", createChair(ChairBlock.Type.PRINCESS, BlockSoundGroup.WOOD));
    public static final Block UNDEAD_CHAIR = registerBlock("undead_chair", createChair(ChairBlock.Type.UNDEAD, BlockSoundGroup.VAULT));
    public static final Block TRIAL_CHAIR = registerBlock("trial_chair", createChair(ChairBlock.Type.TRIAL, BlockSoundGroup.TRIAL_SPAWNER));

    // Lamps
    public static final Block OAK_LAMP = registerBlock("oak_lamp", createColorLampBlock());
    public static final Block SPRUCE_LAMP = registerBlock("spruce_lamp", createColorLampBlock());
    public static final Block BIRCH_LAMP = registerBlock("birch_lamp", createColorLampBlock());
    public static final Block JUNGLE_LAMP = registerBlock("jungle_lamp", createColorLampBlock());
    public static final Block ACACIA_LAMP = registerBlock("acacia_lamp", createColorLampBlock());
    public static final Block DARK_OAK_LAMP = registerBlock("dark_oak_lamp", createColorLampBlock());
    public static final Block MANGROVE_LAMP = registerBlock("mangrove_lamp", createColorLampBlock());
    public static final Block CHERRY_LAMP = registerBlock("cherry_lamp", createColorLampBlock());
    public static final Block BAMBOO_LAMP = registerBlock("bamboo_lamp", createColorLampBlock());
    public static final Block CRIMSON_LAMP = registerBlock("crimson_lamp", createColorLampBlock());
    public static final Block WARPED_LAMP = registerBlock("warped_lamp", createColorLampBlock());

    public static final Block MANGROVE_LANTERN = registerBlock("mangrove_lantern",
            new MangroveLanternBlock(AbstractBlock.Settings.create()
                    .luminance(ModBlockUtilities.createLightLevelFromLitBlockState(9))
                    .nonOpaque()
                    .emissiveLighting(ModBlockUtilities::ifLit)));

    // Sofas
    public static final Block OAK_SOFA = registerBlock("oak_sofa", createSofaBlock());
    public static final Block SPRUCE_SOFA = registerBlock("spruce_sofa", createSofaBlock());
    public static final Block BIRCH_SOFA = registerBlock("birch_sofa", createSofaBlock());
    public static final Block JUNGLE_SOFA = registerBlock("jungle_sofa", createSofaBlock());
    public static final Block ACACIA_SOFA = registerBlock("acacia_sofa", createSofaBlock());
    public static final Block DARK_OAK_SOFA = registerBlock("dark_oak_sofa", createSofaBlock());
    public static final Block MANGROVE_SOFA = registerBlock("mangrove_sofa", createSofaBlock());
    public static final Block CHERRY_SOFA = registerBlock("cherry_sofa", createSofaBlock());
    public static final Block BAMBOO_SOFA = registerBlock("bamboo_sofa", createSofaBlock());
    public static final Block CRIMSON_SOFA = registerBlock("crimson_sofa", createSofaBlock());
    public static final Block WARPED_SOFA = registerBlock("warped_sofa", createSofaBlock());

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

    // Wall Mirrors
    public static final Block OAK_WALL_CLOCK = registerBlock("oak_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_WALL_CLOCK = registerBlock("spruce_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block BIRCH_WALL_CLOCK = registerBlock("birch_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_WALL_CLOCK = registerBlock("jungle_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block ACACIA_WALL_CLOCK = registerBlock("acacia_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_WALL_CLOCK = registerBlock("dark_oak_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_WALL_CLOCK = registerBlock("mangrove_wall_clock", createWallClock(BlockSoundGroup.WOOD));
    public static final Block CHERRY_WALL_CLOCK = registerBlock("cherry_wall_clock", createWallClock(BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_WALL_CLOCK = registerBlock("bamboo_wall_clock", createWallClock(BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_WALL_CLOCK = registerBlock("crimson_wall_clock", createWallClock(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_WALL_CLOCK = registerBlock("warped_wall_clock", createWallClock(BlockSoundGroup.NETHER_WOOD));

    // Tables
    public static final Block OAK_TABLE = registerBlock("oak_table", createTable(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_TABLE = registerBlock("spruce_table", createTable(BlockSoundGroup.WOOD));
    public static final Block BIRCH_TABLE = registerBlock("birch_table", createTable(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_TABLE = registerBlock("jungle_table", createTable(BlockSoundGroup.WOOD));
    public static final Block ACACIA_TABLE = registerBlock("acacia_table", createTable(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_TABLE = registerBlock("dark_oak_table", createTable(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_TABLE = registerBlock("mangrove_table", createTable(BlockSoundGroup.WOOD));
    public static final Block CHERRY_TABLE = registerBlock("cherry_table", createTable(BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_TABLE = registerBlock("bamboo_table", createTable(BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_TABLE = registerBlock("crimson_table", createTable(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_TABLE = registerBlock("warped_table", createTable(BlockSoundGroup.NETHER_WOOD));

    // Desks
    public static final Block OAK_DESK = registerBlock("oak_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_DESK = registerBlock("spruce_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block BIRCH_DESK = registerBlock("birch_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_DESK = registerBlock("jungle_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block ACACIA_DESK = registerBlock("acacia_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_DESK = registerBlock("dark_oak_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_DESK = registerBlock("mangrove_desk", createDesk(BlockSoundGroup.WOOD));
    public static final Block CHERRY_DESK = registerBlock("cherry_desk", createDesk(BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_DESK = registerBlock("bamboo_desk", createDesk(BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_DESK = registerBlock("crimson_desk", createDesk(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_DESK = registerBlock("warped_desk", createDesk(BlockSoundGroup.NETHER_WOOD));

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
    public static final Block OAK_BEAM = registerBlock("oak_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_BEAM = registerBlock("spruce_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block BIRCH_BEAM = registerBlock("birch_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_BEAM = registerBlock("jungle_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block ACACIA_BEAM = registerBlock("acacia_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_BEAM = registerBlock("dark_oak_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_BEAM = registerBlock("mangrove_beam", createBeam(BlockSoundGroup.WOOD));
    public static final Block CHERRY_BEAM = registerBlock("cherry_beam", createBeam(BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_BEAM = registerBlock("bamboo_beam", createBeam(BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_BEAM = registerBlock("crimson_beam", createBeam(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_BEAM = registerBlock("warped_beam", createBeam(BlockSoundGroup.NETHER_WOOD));

    // Large Stumps
    public static final Block OAK_LARGE_STUMP = registerBlock("oak_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block SPRUCE_LARGE_STUMP = registerBlock("spruce_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block BIRCH_LARGE_STUMP = registerBlock("birch_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block JUNGLE_LARGE_STUMP = registerBlock("jungle_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block ACACIA_LARGE_STUMP = registerBlock("acacia_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block DARK_OAK_LARGE_STUMP = registerBlock("dark_oak_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block MANGROVE_LARGE_STUMP = registerBlock("mangrove_large_stump", createLargeStump(BlockSoundGroup.WOOD));
    public static final Block CHERRY_LARGE_STUMP = registerBlock("cherry_large_stump", createLargeStump(BlockSoundGroup.CHERRY_WOOD));
    public static final Block BAMBOO_LARGE_STUMP = registerBlock("bamboo_large_stump", createLargeStump(BlockSoundGroup.BAMBOO_WOOD));
    public static final Block CRIMSON_LARGE_STUMP = registerBlock("crimson_large_stump", createLargeStump(BlockSoundGroup.NETHER_WOOD));
    public static final Block WARPED_LARGE_STUMP = registerBlock("warped_large_stump", createLargeStump(BlockSoundGroup.NETHER_WOOD));

    public static final Block TELESCOPE = registerBlock("telescope", new TelescopeBlock(AbstractBlock.Settings.create()
            .breakInstantly()
            .mapColor(DyeColor.ORANGE)
            .sounds(BlockSoundGroup.COPPER)));
    public static final Block SADDLE_BLOCK = registerBlock("saddle_block", new SaddleBlock(AbstractBlock.Settings.create()
            .breakInstantly()
            .mapColor(DyeColor.ORANGE)));

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

