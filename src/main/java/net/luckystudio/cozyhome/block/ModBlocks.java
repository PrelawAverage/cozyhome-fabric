package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.chimney.ChimneyBlock;
import net.luckystudio.cozyhome.block.custom.drawer.DeskBlock;
import net.luckystudio.cozyhome.block.custom.drawer.DrawerBlock;
import net.luckystudio.cozyhome.block.custom.couch.CouchBlock;
import net.luckystudio.cozyhome.block.custom.horizontal_connecting_blocks.LargeStump;
import net.luckystudio.cozyhome.block.custom.horizontal_connecting_blocks.ShelfTableBlock;
import net.luckystudio.cozyhome.block.custom.horizontal_connecting_blocks.TableBlock;
import net.luckystudio.cozyhome.block.custom.sofa.SofaBlock;
import net.luckystudio.cozyhome.block.custom.chair.ChairBlock;
import net.luckystudio.cozyhome.block.custom.counters.CounterBlock;
import net.luckystudio.cozyhome.block.custom.counters.SinkCounterBlock;
import net.luckystudio.cozyhome.block.custom.counters.StorageCounterBlock;
import net.luckystudio.cozyhome.block.custom.clocks.grandfather_clock.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.custom.clocks.wall_clock.WallClockBlock;
import net.luckystudio.cozyhome.block.custom.telescope.TelescopeBlock;
import net.luckystudio.cozyhome.block.custom.water_blocks.FallingLiquidBlock;
import net.luckystudio.cozyhome.block.custom.water_blocks.FountainBlock;
import net.luckystudio.cozyhome.block.custom.water_blocks.FountainSproutBlock;
import net.luckystudio.cozyhome.block.custom.lamps.*;
import net.luckystudio.cozyhome.block.custom.wall_mirror.WallMirrorBlock;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.custom.SinkBlock;
import net.luckystudio.cozyhome.item.custom.DyedBlockItem;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.createLightLevelFromLitBlockState;

public class ModBlocks {

    private static Block createCounterBlock(Block block, Boolean requiresTool, Boolean burnable) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(block);
        if (requiresTool) settings.requiresTool();
        if (burnable) settings.burnable();
        return new CounterBlock(settings);
    }

    private static Block createStorageCounterBlock(Block block, Boolean requiresTool, Boolean burnable) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(block);
        if (requiresTool) settings.requiresTool();
        if (burnable) settings.burnable();
        return new StorageCounterBlock(settings);
    }

    private static Block createChair(ChairBlock.ChairType chairType, float hardness, float resistance, BlockSoundGroup soundGroup, Boolean requiresTool, Boolean burnable) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create();
        if (requiresTool) settings.requiresTool();
        if (burnable) settings.burnable();
        settings.hardness(hardness).resistance(resistance).sounds(soundGroup).dynamicBounds();
        return new ChairBlock(chairType, settings);
    }

    private static Block createTable(Block block) {
        return new TableBlock(AbstractBlock.Settings.copy(block).dynamicBounds());
    }

    private static Block createShelfTable(Block block) {
        return new ShelfTableBlock(AbstractBlock.Settings.copy(block).dynamicBounds());
    }

    private static Block createWallClock(WallClockBlock.ClockType clockType, Block block) {
        return new WallClockBlock(clockType, AbstractBlock.Settings.copy(block)
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

    private static Block createGenericLamp() {
        return new GenericLampBlock(AbstractBlock.Settings.create()
                .luminance(createLightLevelFromLitBlockState(10))
                .emissiveLighting((state, world, pos) -> state.get(Properties.LIT))
                .breakInstantly()
                .dynamicBounds()
                .sounds(BlockSoundGroup.LANTERN));
    }

    private static Block createSofa(SofaBlock.SofaType sofaType, Block block) {
        return new SofaBlock(sofaType, AbstractBlock.Settings.copy(block).dynamicBounds());
    }

    private static Block createCouch(Block block) {
        return new CouchBlock(AbstractBlock.Settings.copy(block).dynamicBounds());
    }

    private static Block createDesk(Block block) {
        return new DeskBlock(AbstractBlock.Settings.copy(block));
    }

    private static Block createDrawer(Block block) {
        return new DrawerBlock(block.getDefaultState(), AbstractBlock.Settings.copy(block));
    }

    private static Block createSink(Block block) {
        return new SinkBlock(AbstractBlock.Settings.copy(block).nonOpaque().requiresTool());
    }

    private static Block createFountain(float hardness, float resistance, BlockSoundGroup soundGroup) {
        return new FountainBlock(
                AbstractBlock.Settings.create()
                        .ticksRandomly()
                        .luminance(ModBlockUtilities.createLightLevelFromContainsBlockState(15))
                        .solid()
                        .requiresTool()
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

    private static Block createChimney(float hardness, float resistance, BlockSoundGroup soundGroup) {
        return new ChimneyBlock(
                AbstractBlock.Settings.create()
                        .solid()
                        .requiresTool()
                        .hardness(hardness)
                        .strength(resistance)
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    private static Block createLargeStump(BlockSoundGroup soundGroup) {
        return new LargeStump(
                AbstractBlock.Settings.create()
                        .solid()
                        .requiresTool()
                        .hardness(2)
                        .strength(2)
                        .sounds(soundGroup)
                        .dynamicBounds());
    }

    // Counters
    public static final Block OAK_COUNTER = registerBlock("oak_counter", createCounterBlock(Blocks.OAK_PLANKS, false, true));
    public static final Block SPRUCE_COUNTER = registerBlock("spruce_counter", createCounterBlock(Blocks.SPRUCE_PLANKS, false, true));
    public static final Block BIRCH_COUNTER = registerBlock("birch_counter", createCounterBlock(Blocks.BIRCH_PLANKS, false, true));
    public static final Block JUNGLE_COUNTER = registerBlock("jungle_counter", createCounterBlock(Blocks.JUNGLE_PLANKS, false, true));
    public static final Block ACACIA_COUNTER = registerBlock("acacia_counter", createCounterBlock(Blocks.ACACIA_PLANKS, false, true));
    public static final Block DARK_OAK_COUNTER = registerBlock("dark_oak_counter", createCounterBlock(Blocks.DARK_OAK_PLANKS, false, true));
    public static final Block MANGROVE_COUNTER = registerBlock("mangrove_counter", createCounterBlock(Blocks.MANGROVE_PLANKS, false, true));
    public static final Block CHERRY_COUNTER = registerBlock("cherry_counter", createCounterBlock(Blocks.CHERRY_PLANKS, false, true));
    public static final Block BAMBOO_COUNTER = registerBlock("bamboo_counter", createCounterBlock(Blocks.BAMBOO_PLANKS, false, true));
    public static final Block CRIMSON_COUNTER = registerBlock("crimson_counter", createCounterBlock(Blocks.CRIMSON_PLANKS, false, false));
    public static final Block WARPED_COUNTER = registerBlock("warped_counter", createCounterBlock(Blocks.WARPED_PLANKS, false, false));

    // Storage Counters
    public static final Block OAK_STORAGE_COUNTER = registerBlock("oak_storage_counter", createStorageCounterBlock(Blocks.OAK_PLANKS, false, true));
    public static final Block SPRUCE_STORAGE_COUNTER = registerBlock("spruce_storage_counter", createStorageCounterBlock(Blocks.SPRUCE_PLANKS, false, true));
    public static final Block BIRCH_STORAGE_COUNTER = registerBlock("birch_storage_counter", createStorageCounterBlock(Blocks.BIRCH_PLANKS, false, true));
    public static final Block JUNGLE_STORAGE_COUNTER = registerBlock("jungle_storage_counter", createStorageCounterBlock(Blocks.JUNGLE_PLANKS, false, true));
    public static final Block ACACIA_STORAGE_COUNTER = registerBlock("acacia_storage_counter", createStorageCounterBlock(Blocks.ACACIA_PLANKS, false, true));
    public static final Block DARK_OAK_STORAGE_COUNTER = registerBlock("dark_oak_storage_counter", createStorageCounterBlock(Blocks.DARK_OAK_PLANKS, false, true));
    public static final Block MANGROVE_STORAGE_COUNTER = registerBlock("mangrove_storage_counter", createStorageCounterBlock(Blocks.MANGROVE_PLANKS, false, true));
    public static final Block CHERRY_STORAGE_COUNTER = registerBlock("cherry_storage_counter", createStorageCounterBlock(Blocks.CHERRY_PLANKS, false, true));
    public static final Block BAMBOO_STORAGE_COUNTER = registerBlock("bamboo_storage_counter", createStorageCounterBlock(Blocks.BAMBOO_PLANKS, false, true));
    public static final Block CRIMSON_STORAGE_COUNTER = registerBlock("crimson_storage_counter", createStorageCounterBlock(Blocks.CRIMSON_PLANKS, false, false));
    public static final Block WARPED_STORAGE_COUNTER = registerBlock("warped_storage_counter", createStorageCounterBlock(Blocks.WARPED_PLANKS, false, false));

    // Sink Counters
    public static final Block OAK_SINK_COUNTER = registerBlock("oak_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block SPRUCE_SINK_COUNTER = registerBlock("spruce_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block BIRCH_SINK_COUNTER = registerBlock("birch_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block JUNGLE_SINK_COUNTER = registerBlock("jungle_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block ACACIA_SINK_COUNTER = registerBlock("acacia_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block DARK_OAK_SINK_COUNTER = registerBlock("dark_oak_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block MANGROVE_SINK_COUNTER = registerBlock("mangrove_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block CHERRY_SINK_COUNTER = registerBlock("cherry_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block BAMBOO_SINK_COUNTER = registerBlock("bamboo_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block CRIMSON_SINK_COUNTER = registerBlock("crimson_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block WARPED_SINK_COUNTER = registerBlock("warped_sink_counter",
            new SinkCounterBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));

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
    public static final Block IRON_TABLE = registerBlock("iron_table", createShelfTable(Blocks.IRON_BLOCK));
    public static final Block GLASS_TABLE = registerBlock("glass_table", createShelfTable(Blocks.IRON_BLOCK));
    public static final Block UNDEAD_TABLE = registerBlock("undead_table", new TableBlock(
            AbstractBlock.Settings.create()
                    .hardness(5)
                    .resistance(5)
                    .sounds(BlockSoundGroup.VAULT)
                    .dynamicBounds()
    ));

    public static final Block OMINOUS_TABLE = registerBlock("ominous_table", new TableBlock(
            AbstractBlock.Settings.create()
                    .hardness(5)
                    .resistance(5)
                    .sounds(BlockSoundGroup.TRIAL_SPAWNER)
                    .dynamicBounds()
    ));

    // Chairs
    public static final Block OAK_CHAIR = registerBlock("oak_chair", createChair(ChairBlock.Type.OAK, 2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block SPRUCE_CHAIR = registerBlock("spruce_chair", createChair(ChairBlock.Type.SPRUCE,  2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block BIRCH_CHAIR = registerBlock("birch_chair", createChair(ChairBlock.Type.BIRCH,  2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block JUNGLE_CHAIR = registerBlock("jungle_chair", createChair(ChairBlock.Type.JUNGLE,  2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block ACACIA_CHAIR = registerBlock("acacia_chair", createChair(ChairBlock.Type.ACACIA,  2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block DARK_OAK_CHAIR = registerBlock("dark_oak_chair", createChair(ChairBlock.Type.DARK_OAK,  2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block MANGROVE_CHAIR = registerBlock("mangrove_chair", createChair(ChairBlock.Type.MANGROVE,  2, 3, BlockSoundGroup.WOOD, false, true));
    public static final Block CHERRY_CHAIR = registerBlock("cherry_chair", createChair(ChairBlock.Type.CHERRY,  2, 3, BlockSoundGroup.CHERRY_WOOD, false, true));
    public static final Block BAMBOO_CHAIR = registerBlock("bamboo_chair", createChair(ChairBlock.Type.BAMBOO,  2, 3, BlockSoundGroup.BAMBOO_WOOD, false, true));
    public static final Block CRIMSON_CHAIR = registerBlock("crimson_chair", createChair(ChairBlock.Type.CRIMSON,  2, 3, BlockSoundGroup.NETHER_WOOD, false, false));
    public static final Block WARPED_CHAIR = registerBlock("warped_chair", createChair(ChairBlock.Type.WARPED,  2, 3, BlockSoundGroup.NETHER_WOOD, false, false));
    public static final Block IRON_CHAIR = registerBlock("iron_chair", createChair(ChairBlock.Type.IRON,  2, 3, BlockSoundGroup.METAL, true, false));
    public static final Block GLASS_CHAIR = registerBlock("glass_chair", createChair(ChairBlock.Type.GLASS,  2, 3, BlockSoundGroup.GLASS, true, false));
    public static final Block UNDEAD_CHAIR = registerBlock("undead_chair", createChair(ChairBlock.Type.UNDEAD,  2, 3, BlockSoundGroup.VAULT, true, false));
    public static final Block OMINOUS_CHAIR = registerBlock("ominous_chair", createChair(ChairBlock.Type.OMINOUS, 2, 3, BlockSoundGroup.TRIAL_SPAWNER, true, false));

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
    public static final Block OMINOUS_WALL_CLOCK = registerBlock("ominous_wall_clock", new WallClockBlock(WallClockBlock.Type.OMINOUS,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.TRIAL_SPAWNER)));

    // Grandfather Clocks
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
    public static final Block OMINOUS_GRANDFATHER_CLOCK = registerBlock("ominous_grandfather_clock", createGrandfatherClock(GrandfatherClockBlock.Type.OMINOUS, BlockSoundGroup.TRIAL_SPAWNER));

    // Lamps
    public static final Block OAK_LAMP = registerDyedBlock("oak_lamp", createGenericLamp());
    public static final Block SPRUCE_LAMP = registerDyedBlock("spruce_lamp", new SpruceLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block BIRCH_LAMP = registerDyedBlock("birch_lamp", createGenericLamp());
    public static final Block JUNGLE_LAMP = registerDyedBlock("jungle_lamp", new JungleLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block ACACIA_LAMP = registerDyedBlock("acacia_lamp", createGenericLamp());
    public static final Block DARK_OAK_LAMP = registerDyedBlock("dark_oak_lamp", new DarkOakLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block MANGROVE_LAMP = registerDyedBlock("mangrove_lamp", new MangroveLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block CHERRY_LAMP = registerDyedBlock("cherry_lamp", createGenericLamp());
    public static final Block BAMBOO_LAMP = registerDyedBlock("bamboo_lamp", new BambooLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block CRIMSON_LAMP = registerBlock("crimson_lamp", new CrimsonLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block WARPED_LAMP = registerBlock("warped_lamp", new WarpedLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block IRON_LAMP = registerDyedBlock("iron_lamp", new IronLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block GLASS_LAMP = registerDyedBlock("glass_lamp", new GlassLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block UNDEAD_LAMP = registerDyedBlock("undead_lamp", new UndeadLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));
    public static final Block OMINOUS_LAMP = registerBlock("ominous_lamp", new OminousLampBlock(AbstractBlock.Settings.copy(ModBlocks.OAK_LAMP)));

    // Sofas
    public static final Block OAK_SOFA = registerDyedBlock("oak_sofa", createSofa(SofaBlock.Type.OAK, Blocks.OAK_PLANKS));
    public static final Block SPRUCE_SOFA = registerDyedBlock("spruce_sofa", createSofa(SofaBlock.Type.SPRUCE, Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_SOFA = registerDyedBlock("birch_sofa", createSofa(SofaBlock.Type.BIRCH, Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_SOFA = registerDyedBlock("jungle_sofa", createSofa(SofaBlock.Type.JUNGLE, Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_SOFA = registerDyedBlock("acacia_sofa", createSofa(SofaBlock.Type.ACACIA, Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_SOFA = registerDyedBlock("dark_oak_sofa", createSofa(SofaBlock.Type.DARK_OAK, Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_SOFA = registerDyedBlock("mangrove_sofa", createSofa(SofaBlock.Type.MANGROVE, Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_SOFA = registerDyedBlock("cherry_sofa", createSofa(SofaBlock.Type.CHERRY, Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_SOFA = registerDyedBlock("bamboo_sofa", createSofa(SofaBlock.Type.BAMBOO, Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_SOFA = registerDyedBlock("crimson_sofa", createSofa(SofaBlock.Type.CRIMSON, Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_SOFA = registerDyedBlock("warped_sofa", createSofa(SofaBlock.Type.WARPED, Blocks.WARPED_PLANKS));

    // Couches
    public static final Block OAK_COUCH = registerDyedBlock("oak_couch", createCouch(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_COUCH = registerDyedBlock("spruce_couch", createCouch(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_COUCH = registerDyedBlock("birch_couch", createCouch(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_COUCH = registerDyedBlock("jungle_couch", createCouch(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_COUCH = registerDyedBlock("acacia_couch", createCouch(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_COUCH = registerDyedBlock("dark_oak_couch", createCouch(Blocks.DARK_OAK_PLANKS));
    public static final Block MANGROVE_COUCH = registerDyedBlock("mangrove_couch", createCouch(Blocks.MANGROVE_PLANKS));
    public static final Block CHERRY_COUCH = registerDyedBlock("cherry_couch", createCouch(Blocks.CHERRY_PLANKS));
    public static final Block BAMBOO_COUCH = registerDyedBlock("bamboo_couch", createCouch(Blocks.BAMBOO_PLANKS));
    public static final Block CRIMSON_COUCH = registerDyedBlock("crimson_couch", createCouch(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_COUCH = registerDyedBlock("warped_couch", createCouch(Blocks.WARPED_PLANKS));

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

    // Sinks
    public static final Block STONE_BRICK_SINK = registerBlock("stone_brick_sink", createSink(Blocks.STONE_BRICKS));
    public static final Block MOSSY_STONE_BRICK_SINK = registerBlock("mossy_stone_brick_sink", createSink(Blocks.MOSSY_STONE_BRICKS));
    public static final Block GRANITE_SINK = registerBlock("granite_sink", createSink(Blocks.GRANITE));
    public static final Block DIORITE_SINK = registerBlock("diorite_sink", createSink(Blocks.DIORITE));
    public static final Block ANDESITE_SINK = registerBlock("andesite_sink", createSink(Blocks.ANDESITE));
    public static final Block DEEPSLATE_SINK = registerBlock("deepslate_sink", createSink(Blocks.DEEPSLATE_BRICKS));
    public static final Block CALCITE_SINK = registerBlock("calcite_sink", createSink(Blocks.CALCITE));
    public static final Block TUFF_SINK = registerBlock("tuff_sink", createSink(Blocks.TUFF));
    public static final Block BRICK_SINK = registerBlock("brick_sink", createSink(Blocks.BRICKS));
    public static final Block MUD_SINK = registerBlock("mud_sink", createSink(Blocks.MUD_BRICKS));
    public static final Block SANDSTONE_SINK = registerBlock("sandstone_sink", createSink(Blocks.SANDSTONE));
    public static final Block RED_SANDSTONE_SINK = registerBlock("red_sandstone_sink", createSink(Blocks.RED_SANDSTONE));
    public static final Block PRISMARINE_SINK = registerBlock("prismarine_sink", createSink(Blocks.PRISMARINE));
    public static final Block NETHER_BRICK_SINK = registerBlock("nether_brick_sink", createSink(Blocks.NETHER_BRICKS));
    public static final Block RED_NETHER_BRICK_SINK = registerBlock("red_nether_brick_sink", createSink(Blocks.RED_NETHER_BRICKS));
    public static final Block BLACKSTONE_SINK = registerBlock("blackstone_sink", createSink(Blocks.BLACKSTONE));
    public static final Block ENDSTONE_SINK = registerBlock("endstone_sink", createSink(Blocks.END_STONE));
    public static final Block PURPUR_SINK = registerBlock("purpur_sink", createSink(Blocks.PURPUR_BLOCK));
    public static final Block IRON_SINK = registerBlock("iron_sink", createSink(Blocks.IRON_BLOCK));

    // Fountains
    public static final Block STONE_BRICK_FOUNTAIN = registerBlock("stone_brick_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block MOSSY_STONE_BRICK_FOUNTAIN = registerBlock("mossy_stone_brick_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block GRANITE_FOUNTAIN = registerBlock("granite_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DIORITE_FOUNTAIN = registerBlock("diorite_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block ANDESITE_FOUNTAIN = registerBlock("andesite_fountain", createFountain(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DEEPSLATE_FOUNTAIN = registerBlock("deepslate_fountain", createFountain(3,6, BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block CALCITE_FOUNTAIN = registerBlock("calcite_fountain", createFountain(0.75f,0.75f, BlockSoundGroup.CALCITE));
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
    public static final Block CALCITE_FOUNTAIN_SPROUT = registerBlock("calcite_fountain_sprout", createFountainSprout(0.75f,0.75f, BlockSoundGroup.CALCITE));
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

    // CHIMNEYS
    public static final Block STONE_BRICK_CHIMNEY = registerBlock("stone_brick_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));
    public static final Block MOSSY_STONE_BRICK_CHIMNEY = registerBlock("mossy_stone_brick_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));
    public static final Block GRANITE_CHIMNEY = registerBlock("granite_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DIORITE_CHIMNEY = registerBlock("diorite_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));
    public static final Block ANDESITE_CHIMNEY = registerBlock("andesite_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));
    public static final Block DEEPSLATE_CHIMNEY = registerBlock("deepslate_chimney", createChimney(3,6, BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block CALCITE_CHIMNEY = registerBlock("calcite_chimney", createChimney(0.75f,0.75f, BlockSoundGroup.CALCITE));
    public static final Block TUFF_CHIMNEY = registerBlock("tuff_chimney", createChimney(1.5f,6, BlockSoundGroup.POLISHED_TUFF));
    public static final Block BRICK_CHIMNEY = registerBlock("brick_chimney", createChimney(2,6, BlockSoundGroup.STONE));
    public static final Block MUD_CHIMNEY = registerBlock("mud_chimney", createChimney(1.5f,3, BlockSoundGroup.MUD_BRICKS));
    public static final Block SANDSTONE_CHIMNEY = registerBlock("sandstone_chimney", createChimney(2,6, BlockSoundGroup.STONE));
    public static final Block RED_SANDSTONE_CHIMNEY = registerBlock("red_sandstone_chimney", createChimney(2,6, BlockSoundGroup.STONE));
    public static final Block PRISMARINE_CHIMNEY = registerBlock("prismarine_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));
    public static final Block NETHER_BRICK_CHIMNEY = registerBlock("nether_brick_chimney", createChimney(2,6, BlockSoundGroup.NETHER_BRICKS));
    public static final Block RED_NETHER_BRICK_CHIMNEY = registerBlock("red_nether_brick_chimney", createChimney(2,6, BlockSoundGroup.NETHER_BRICKS));
    public static final Block BLACKSTONE_CHIMNEY = registerBlock("blackstone_chimney", createChimney(1.5f,6, BlockSoundGroup.GILDED_BLACKSTONE));
    public static final Block ENDSTONE_CHIMNEY = registerBlock("endstone_chimney", createChimney(3,9, BlockSoundGroup.STONE));
    public static final Block PURPUR_CHIMNEY = registerBlock("purpur_chimney", createChimney(1.5f,6, BlockSoundGroup.STONE));

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
        Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    // Register Block Method
    private static Block registerDyedBlock(String name, Block block) {
        registerDyedBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CozyHome.MOD_ID, name), block);
    }

    // Helper Method for Register Block Method (Handles rarity dynamically)
    private static void registerDyedBlockItems(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name),
                new DyedBlockItem(block, new Item.Settings()));
    }

    // Registering Blocks
    public static void registerModBlocks(){
        CozyHome.LOGGER.info("Registering ModBlocks for " + CozyHome.MOD_ID);
    }
}

