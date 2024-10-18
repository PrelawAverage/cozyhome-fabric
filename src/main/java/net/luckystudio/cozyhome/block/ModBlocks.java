package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.special.MangroveLanternBlock;
import net.luckystudio.cozyhome.block.special.ZaisuSeatBlock;
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
private static Block createColorLampBlock() {
    return new DyeableLampBlock(
            AbstractBlock.Settings.create()
                    .luminance(createLightLevelFromLitBlockState(9))
                    .emissiveLighting(ModBlocks::ifLit)
                    .nonOpaque()
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

    // Stained Windows
    public static final Block AUTUMN_STAINED_WINDOW = registerBlock("autumn_stained_window",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS)));
    public static final Block AUTUMN_STAINED_WINDOW_PANE = registerBlock("autumn_stained_window_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE)));

    // Chairs
    public static final Block OAK_CHAIR = registerBlock("oak_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_CHAIR = registerBlock("spruce_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final Block BIRCH_CHAIR = registerBlock("birch_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block JUNGLE_CHAIR = registerBlock("jungle_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block ACACIA_CHAIR = registerBlock("acacia_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final Block DARK_OAK_CHAIR = registerBlock("dark_oak_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Block MANGROVE_CHAIR = registerBlock("mangrove_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));
    public static final Block CHERRY_CHAIR = registerBlock("cherry_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));
    public static final Block BAMBOO_CHAIR = registerBlock("bamboo_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block CRIMSON_CHAIR = registerBlock("crimson_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_CHAIR = registerBlock("warped_chair",
            new DyeableChairBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));

    // Lamps
    public static final Block OAK_LAMP = registerBlock("oak_lamp", createColorLampBlock());
//    public static final Block MANGROVE_LAMP = registerBlock("mangrove_lamp",
//            createLampBlock(MapColor.DULL_RED));
    public static final Block MANGROVE_LANTERN = registerBlock("mangrove_lantern",
            new MangroveLanternBlock(AbstractBlock.Settings.create()
                    .luminance(createLightLevelFromLitBlockState(9))
                    .nonOpaque()
                    .emissiveLighting(ModBlocks::ifLit)));

    // Sofas
    public static final Block OAK_SOFA = registerBlock("oak_sofa", createSofaBlock(MapColor.WHITE));

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

    public static final Block MANGROVE_ZAISU = registerBlock("mangrove_zaisu", new ZaisuSeatBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));

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

