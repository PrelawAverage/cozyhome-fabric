package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.*;
import net.luckystudio.cozyhome.datagen.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.block.enums.StairShape;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    // Even though it states "blockstate", these will generate blockstates, models, and item models.
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGenerals(blockStateModelGenerator);
        registerFallingLiquid(blockStateModelGenerator, ModBlocks.FALLING_LIQUID);
        registerCounter(blockStateModelGenerator, ModBlocks.OAK_COUNTER, CozyHome.id("block/break/oak_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.SPRUCE_COUNTER, CozyHome.id("block/break/spruce_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.BIRCH_COUNTER, CozyHome.id("block/break/birch_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.JUNGLE_COUNTER, CozyHome.id("block/break/jungle_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.ACACIA_COUNTER, CozyHome.id("block/break/acacia_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.DARK_OAK_COUNTER, CozyHome.id("block/break/dark_oak_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.MANGROVE_COUNTER, CozyHome.id("block/break/mangrove_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.CHERRY_COUNTER, CozyHome.id("block/break/cherry_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.BAMBOO_COUNTER, CozyHome.id("block/break/bamboo_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.CRIMSON_COUNTER, CozyHome.id("block/break/crimson_furniture"));
        registerCounter(blockStateModelGenerator, ModBlocks.WARPED_COUNTER, CozyHome.id("block/break/warped_furniture"));

        registerStorageCounter(blockStateModelGenerator, ModBlocks.OAK_STORAGE_COUNTER, CozyHome.id("block/break/oak_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.SPRUCE_STORAGE_COUNTER, CozyHome.id("block/break/spruce_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.BIRCH_STORAGE_COUNTER, CozyHome.id("block/break/birch_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.JUNGLE_STORAGE_COUNTER, CozyHome.id("block/break/jungle_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.ACACIA_STORAGE_COUNTER, CozyHome.id("block/break/acacia_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.DARK_OAK_STORAGE_COUNTER, CozyHome.id("block/break/dark_oak_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.MANGROVE_STORAGE_COUNTER, CozyHome.id("block/break/mangrove_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.CHERRY_STORAGE_COUNTER, CozyHome.id("block/break/cherry_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.BAMBOO_STORAGE_COUNTER, CozyHome.id("block/break/bamboo_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.CRIMSON_STORAGE_COUNTER, CozyHome.id("block/break/crimson_furniture"));
        registerStorageCounter(blockStateModelGenerator, ModBlocks.WARPED_STORAGE_COUNTER, CozyHome.id("block/break/warped_furniture"));

        registerSinkCounter(blockStateModelGenerator, ModBlocks.OAK_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/oak_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.SPRUCE_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/spruce_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.BIRCH_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/birch_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.JUNGLE_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/jungle_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.ACACIA_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/acacia_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.DARK_OAK_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/dark_oak_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.MANGROVE_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/mangrove_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.CHERRY_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/cherry_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.BAMBOO_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/bamboo_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.CRIMSON_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/crimson_furniture"));
        registerSinkCounter(blockStateModelGenerator, ModBlocks.WARPED_SINK_COUNTER, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/warped_furniture"));

        registerTable(blockStateModelGenerator, ModBlocks.OAK_TABLE, TableTypes.GENERIC, Identifier.of("block/oak_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.SPRUCE_TABLE, TableTypes.GENERIC, Identifier.of("block/spruce_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.BIRCH_TABLE, TableTypes.GENERIC, Identifier.of("block/birch_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.JUNGLE_TABLE, TableTypes.GENERIC, Identifier.of("block/jungle_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.ACACIA_TABLE, TableTypes.GENERIC, Identifier.of("block/acacia_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.DARK_OAK_TABLE, TableTypes.GENERIC, Identifier.of("block/dark_oak_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.MANGROVE_TABLE, TableTypes.GENERIC, Identifier.of("block/mangrove_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.CHERRY_TABLE, TableTypes.GENERIC, Identifier.of("block/cherry_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.BAMBOO_TABLE, TableTypes.GENERIC, Identifier.of("block/bamboo_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.CRIMSON_TABLE, TableTypes.GENERIC, Identifier.of("block/crimson_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.WARPED_TABLE, TableTypes.GENERIC, Identifier.of("block/warped_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.IRON_TABLE, TableTypes.SHELF, CozyHome.id("block/break/iron_furniture"));
        registerTable(blockStateModelGenerator, ModBlocks.GLASS_TABLE, TableTypes.SHELF, CozyHome.id("block/break/glass_furniture"));
        registerTable(blockStateModelGenerator, ModBlocks.UNDEAD_TABLE, TableTypes.GENERIC, CozyHome.id("block/break/undead_furniture"));
        registerTable(blockStateModelGenerator, ModBlocks.OMINOUS_TABLE, TableTypes.GENERIC, CozyHome.id("block/break/ominous_furniture"));

        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.OAK_CHAIR, Identifier.of("block/oak_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.SPRUCE_CHAIR, Identifier.of("block/spruce_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.BIRCH_CHAIR, Identifier.of("block/birch_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.JUNGLE_CHAIR, Identifier.of("block/jungle_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.ACACIA_CHAIR, Identifier.of("block/acacia_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.DARK_OAK_CHAIR, Identifier.of("block/dark_oak_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.MANGROVE_CHAIR, Identifier.of("block/mangrove_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.CHERRY_CHAIR, Identifier.of("block/cherry_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.BAMBOO_CHAIR, Identifier.of("block/bamboo_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.CRIMSON_CHAIR, Identifier.of("block/crimson_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.WARPED_CHAIR, Identifier.of("block/warped_planks"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.IRON_CHAIR, CozyHome.id("block/break/iron_furniture"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.GLASS_CHAIR, CozyHome.id("block/break/glass_furniture"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.UNDEAD_CHAIR, CozyHome.id("block/break/undead_furniture"), ModItemTemplates.CHAIR.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.OMINOUS_CHAIR, CozyHome.id("block/break/ominous_furniture"), ModItemTemplates.CHAIR.get());

        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.OAK_WALL_CLOCK, Identifier.of("block/oak_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.SPRUCE_WALL_CLOCK, Identifier.of("block/spruce_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.BIRCH_WALL_CLOCK, Identifier.of("block/birch_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.JUNGLE_WALL_CLOCK, Identifier.of("block/jungle_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.ACACIA_WALL_CLOCK, Identifier.of("block/acacia_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.DARK_OAK_WALL_CLOCK, Identifier.of("block/dark_oak_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.MANGROVE_WALL_CLOCK, Identifier.of("block/mangrove_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.CHERRY_WALL_CLOCK, Identifier.of("block/cherry_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.BAMBOO_WALL_CLOCK, Identifier.of("block/bamboo_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.CRIMSON_WALL_CLOCK, Identifier.of("block/crimson_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.WARPED_WALL_CLOCK, Identifier.of("block/warped_planks"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.IRON_WALL_CLOCK, CozyHome.id("block/break/iron_furniture"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.GLASS_WALL_CLOCK, CozyHome.id("block/break/glass_furniture"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.UNDEAD_WALL_CLOCK, CozyHome.id("block/break/undead_furniture"), ModItemTemplates.WALL_CLOCK.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.OMINOUS_WALL_CLOCK, CozyHome.id("block/break/ominous_furniture"), ModItemTemplates.WALL_CLOCK.get());

        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.OAK_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/oak_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.SPRUCE_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/spruce_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.BIRCH_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/birch_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.JUNGLE_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/jungle_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.ACACIA_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/acacia_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.DARK_OAK_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/dark_oak_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.MANGROVE_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/mangrove_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.CHERRY_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/cherry_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.BAMBOO_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/bamboo_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.CRIMSON_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/crimson_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.WARPED_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, Identifier.of("block/warped_planks"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.IRON_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, CozyHome.id("block/break/iron_furniture"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.GLASS_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, CozyHome.id("block/break/glass_furniture"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.UNDEAD_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, CozyHome.id("block/break/undead_furniture"));
        registerBuiltinWithParticleAndGeneratedItemModel(blockStateModelGenerator, ModBlocks.OMINOUS_GRANDFATHER_CLOCK, ModBlockTypes.GRANDFATHER_CLOCK, CozyHome.id("block/break/ominous_furniture"));

        registerLamp(blockStateModelGenerator, ModBlocks.OAK_LAMP, Identifier.of("block/oak_planks"), true, true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.SPRUCE_LAMP, Identifier.of("block/spruce_planks"), false,true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.BIRCH_LAMP, Identifier.of("block/birch_planks"), true, true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.JUNGLE_LAMP, Identifier.of("block/jungle_planks"), false, true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.ACACIA_LAMP, Identifier.of("block/acacia_planks"), true, true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.DARK_OAK_LAMP, Identifier.of("block/dark_oak_planks"), false, true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.MANGROVE_LAMP, Identifier.of("block/mangrove_planks"), false, false, false);
        registerLamp(blockStateModelGenerator, ModBlocks.CHERRY_LAMP, Identifier.of("block/cherry_planks"), true, true, true);
        registerLamp(blockStateModelGenerator, ModBlocks.BAMBOO_LAMP, Identifier.of("block/bamboo_planks"), false, true, false);
        registerLamp(blockStateModelGenerator, ModBlocks.CRIMSON_LAMP, Identifier.of("block/crimson_planks"), false, false, false);
        registerLamp(blockStateModelGenerator, ModBlocks.WARPED_LAMP, Identifier.of("block/warped_planks"), false, false, false);
        registerLamp(blockStateModelGenerator, ModBlocks.IRON_LAMP, CozyHome.id("block/break/iron_furniture"), false, false, false);
        registerLamp(blockStateModelGenerator, ModBlocks.GLASS_LAMP, CozyHome.id("block/break/glass_furniture"), false, false, false);
        registerLamp(blockStateModelGenerator, ModBlocks.UNDEAD_LAMP, CozyHome.id("block/break/undead_furniture"), false, false, false);
        registerLamp(blockStateModelGenerator, ModBlocks.OMINOUS_LAMP, CozyHome.id("block/break/ominous_furniture"), false, false, false);

        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.OAK_SOFA, Identifier.of("block/oak_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.SPRUCE_SOFA, Identifier.of("block/spruce_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.BIRCH_SOFA, Identifier.of("block/birch_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.JUNGLE_SOFA, Identifier.of("block/jungle_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.ACACIA_SOFA, Identifier.of("block/acacia_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.DARK_OAK_SOFA, Identifier.of("block/dark_oak_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.MANGROVE_SOFA, Identifier.of("block/mangrove_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.CHERRY_SOFA, Identifier.of("block/cherry_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.BAMBOO_SOFA, Identifier.of("block/bamboo_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.CRIMSON_SOFA, Identifier.of("block/crimson_planks"), ModItemTemplates.SOFA.get());
        registerBuiltinWithParticleAndParentedItemModel(blockStateModelGenerator, ModBlocks.WARPED_SOFA, Identifier.of("block/warped_planks"), ModItemTemplates.SOFA.get());

        registerCouch(blockStateModelGenerator, ModBlocks.OAK_COUCH, Identifier.of("block/oak_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.SPRUCE_COUCH, Identifier.of("block/spruce_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.BIRCH_COUCH, Identifier.of("block/birch_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.JUNGLE_COUCH, Identifier.of("block/jungle_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.ACACIA_COUCH, Identifier.of("block/acacia_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.DARK_OAK_COUCH, Identifier.of("block/dark_oak_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.MANGROVE_COUCH, Identifier.of("block/mangrove_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.CHERRY_COUCH, Identifier.of("block/cherry_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.BAMBOO_COUCH, Identifier.of("block/bamboo_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.CRIMSON_COUCH, Identifier.of("block/crimson_planks"));
        registerCouch(blockStateModelGenerator, ModBlocks.WARPED_COUCH, Identifier.of("block/warped_planks"));

        registerDesk(blockStateModelGenerator, ModBlocks.OAK_DESK, Identifier.of("block/oak_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.SPRUCE_DESK, Identifier.of("block/spruce_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.BIRCH_DESK, Identifier.of("block/birch_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.JUNGLE_DESK, Identifier.of("block/jungle_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.ACACIA_DESK, Identifier.of("block/acacia_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.DARK_OAK_DESK, Identifier.of("block/dark_oak_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.MANGROVE_DESK, Identifier.of("block/mangrove_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.CHERRY_DESK, Identifier.of("block/cherry_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.BAMBOO_DESK, Identifier.of("block/bamboo_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.CRIMSON_DESK, Identifier.of("block/crimson_planks"));
        registerDesk(blockStateModelGenerator, ModBlocks.WARPED_DESK, Identifier.of("block/warped_planks"));

        registerDrawer(blockStateModelGenerator, ModBlocks.OAK_DRAWER, Identifier.of("block/oak_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.SPRUCE_DRAWER, Identifier.of("block/spruce_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.BIRCH_DRAWER, Identifier.of("block/birch_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.JUNGLE_DRAWER, Identifier.of("block/jungle_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.ACACIA_DRAWER, Identifier.of("block/acacia_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.DARK_OAK_DRAWER, Identifier.of("block/dark_oak_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.MANGROVE_DRAWER, Identifier.of("block/mangrove_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.CHERRY_DRAWER, Identifier.of("block/cherry_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.BAMBOO_DRAWER, Identifier.of("block/bamboo_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.CRIMSON_DRAWER, Identifier.of("block/crimson_planks"));
        registerDrawer(blockStateModelGenerator, ModBlocks.WARPED_DRAWER, Identifier.of("block/warped_planks"));

        registerWallMirror(blockStateModelGenerator, ModBlocks.OAK_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.SPRUCE_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.BIRCH_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.JUNGLE_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.ACACIA_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.DARK_OAK_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.MANGROVE_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.CHERRY_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.BAMBOO_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.CRIMSON_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/nether_mirror"));
        registerWallMirror(blockStateModelGenerator, ModBlocks.WARPED_WALL_MIRROR, Identifier.of(CozyHome.MOD_ID, "block/mirror/nether_mirror"));

        registerSink(blockStateModelGenerator, ModBlocks.STONE_BRICK_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/stone_bricks"));
        registerSink(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/mossy_stone_bricks"));
        registerSink(blockStateModelGenerator, ModBlocks.GRANITE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/granite"));
        registerSink(blockStateModelGenerator, ModBlocks.DIORITE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/diorite"));
        registerSink(blockStateModelGenerator, ModBlocks.ANDESITE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/andesite"));
        registerSink(blockStateModelGenerator, ModBlocks.DEEPSLATE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/deepslate"));
        registerSink(blockStateModelGenerator, ModBlocks.CALCITE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/calcite"));
        registerSink(blockStateModelGenerator, ModBlocks.TUFF_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/tuff"));
        registerSink(blockStateModelGenerator, ModBlocks.BRICK_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/bricks"));
        registerSink(blockStateModelGenerator, ModBlocks.MUD_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/mud"));
        registerSink(blockStateModelGenerator, ModBlocks.SANDSTONE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/sandstone"));
        registerSink(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/red_sandstone"));
        registerSink(blockStateModelGenerator, ModBlocks.PRISMARINE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/prismarine_bricks"));
        registerSink(blockStateModelGenerator, ModBlocks.NETHER_BRICK_SINK, CozyHome.id("block/faucet/netherite_faucet"), Identifier.ofVanilla("block/nether_bricks"));
        registerSink(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_SINK, CozyHome.id("block/faucet/netherite_faucet"), Identifier.ofVanilla("block/red_nether_bricks"));
        registerSink(blockStateModelGenerator, ModBlocks.BLACKSTONE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/blackstone"));
        registerSink(blockStateModelGenerator, ModBlocks.ENDSTONE_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/end_stone"));
        registerSink(blockStateModelGenerator, ModBlocks.PURPUR_SINK, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/purpur_block"));
        registerSink(blockStateModelGenerator, ModBlocks.IRON_SINK, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/iron_furniture"));
        registerSink(blockStateModelGenerator, ModBlocks.GOLD_SINK, CozyHome.id("block/faucet/gold_faucet"), CozyHome.id("block/break/gold_furniture"));

        registerBathtub(blockStateModelGenerator, ModBlocks.STONE_BRICK_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/stone_bricks"));
        registerBathtub(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/mossy_stone_bricks"));
        registerBathtub(blockStateModelGenerator, ModBlocks.GRANITE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/granite"));
        registerBathtub(blockStateModelGenerator, ModBlocks.DIORITE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/diorite"));
        registerBathtub(blockStateModelGenerator, ModBlocks.ANDESITE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/andesite"));
        registerBathtub(blockStateModelGenerator, ModBlocks.DEEPSLATE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/deepslate"));
        registerBathtub(blockStateModelGenerator, ModBlocks.CALCITE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/calcite"));
        registerBathtub(blockStateModelGenerator, ModBlocks.TUFF_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/tuff"));
        registerBathtub(blockStateModelGenerator, ModBlocks.BRICK_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/bricks"));
        registerBathtub(blockStateModelGenerator, ModBlocks.MUD_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/mud"));
        registerBathtub(blockStateModelGenerator, ModBlocks.SANDSTONE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/sandstone"));
        registerBathtub(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/red_sandstone"));
        registerBathtub(blockStateModelGenerator, ModBlocks.PRISMARINE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/prismarine_bricks"));
        registerBathtub(blockStateModelGenerator, ModBlocks.NETHER_BRICK_BATHTUB, CozyHome.id("block/faucet/netherite_faucet"), Identifier.ofVanilla("block/nether_bricks"));
        registerBathtub(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_BATHTUB, CozyHome.id("block/faucet/netherite_faucet"), Identifier.ofVanilla("block/red_nether_bricks"));
        registerBathtub(blockStateModelGenerator, ModBlocks.BLACKSTONE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/blackstone"));
        registerBathtub(blockStateModelGenerator, ModBlocks.ENDSTONE_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/end_stone"));
        registerBathtub(blockStateModelGenerator, ModBlocks.PURPUR_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), Identifier.ofVanilla("block/purpur_block"));
        registerBathtub(blockStateModelGenerator, ModBlocks.IRON_BATHTUB, CozyHome.id("block/faucet/iron_faucet"), CozyHome.id("block/break/iron_furniture"));
        registerBathtub(blockStateModelGenerator, ModBlocks.GOLD_BATHTUB, CozyHome.id("block/faucet/gold_faucet"), CozyHome.id("block/break/gold_furniture"));

        registerLargeStump(blockStateModelGenerator, ModBlocks.OAK_LARGE_STUMP, Identifier.of("oak_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.SPRUCE_LARGE_STUMP, Identifier.of("spruce_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.BIRCH_LARGE_STUMP, Identifier.of("birch_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.JUNGLE_LARGE_STUMP, Identifier.of("jungle_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.ACACIA_LARGE_STUMP, Identifier.of("acacia_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.DARK_OAK_LARGE_STUMP, Identifier.of("dark_oak_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.MANGROVE_LARGE_STUMP, Identifier.of("mangrove_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.CHERRY_LARGE_STUMP, Identifier.of("cherry_log"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.BAMBOO_LARGE_STUMP, Identifier.of("bamboo_block"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.CRIMSON_LARGE_STUMP, Identifier.of("crimson_stem"));
        registerLargeStump(blockStateModelGenerator, ModBlocks.WARPED_LARGE_STUMP, Identifier.of("warped_stem"));

        registerFountain(blockStateModelGenerator, ModBlocks.STONE_BRICK_FOUNTAIN, Identifier.of("block/stone_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN, Identifier.of("block/mossy_stone_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.GRANITE_FOUNTAIN, Identifier.of("block/granite"));
        registerFountain(blockStateModelGenerator, ModBlocks.DIORITE_FOUNTAIN, Identifier.of("block/diorite"));
        registerFountain(blockStateModelGenerator, ModBlocks.ANDESITE_FOUNTAIN, Identifier.of("block/andesite"));
        registerFountain(blockStateModelGenerator, ModBlocks.DEEPSLATE_FOUNTAIN, Identifier.of("block/deepslate"));
        registerFountain(blockStateModelGenerator, ModBlocks.CALCITE_FOUNTAIN, Identifier.of("block/calcite"));
        registerFountain(blockStateModelGenerator, ModBlocks.TUFF_FOUNTAIN, Identifier.of("block/tuff"));
        registerFountain(blockStateModelGenerator, ModBlocks.BRICK_FOUNTAIN, Identifier.of("block/bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.MUD_FOUNTAIN, Identifier.of("block/mud"));
        registerFountain(blockStateModelGenerator, ModBlocks.SANDSTONE_FOUNTAIN, Identifier.of("block/sandstone"));
        registerFountain(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_FOUNTAIN, Identifier.of("block/red_sandstone"));
        registerFountain(blockStateModelGenerator, ModBlocks.PRISMARINE_FOUNTAIN, Identifier.of("block/prismarine_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.NETHER_BRICK_FOUNTAIN, Identifier.of("block/nether_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_FOUNTAIN, Identifier.of("block/red_nether_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.BLACKSTONE_FOUNTAIN, Identifier.of("block/blackstone"));
        registerFountain(blockStateModelGenerator, ModBlocks.ENDSTONE_FOUNTAIN, Identifier.of("block/end_stone"));
        registerFountain(blockStateModelGenerator, ModBlocks.PURPUR_FOUNTAIN, Identifier.of("block/purpur_block"));

        registerFountainSprout(blockStateModelGenerator, ModBlocks.STONE_BRICK_FOUNTAIN_SPROUT, Identifier.of("block/stone_bricks"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN_SPROUT, Identifier.of("block/mossy_stone_bricks"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.GRANITE_FOUNTAIN_SPROUT, Identifier.of("block/granite"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.DIORITE_FOUNTAIN_SPROUT, Identifier.of("block/diorite"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.ANDESITE_FOUNTAIN_SPROUT, Identifier.of("block/andesite"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.DEEPSLATE_FOUNTAIN_SPROUT, Identifier.of("block/deepslate"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.CALCITE_FOUNTAIN_SPROUT, Identifier.of("block/calcite"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.TUFF_FOUNTAIN_SPROUT, Identifier.of("block/tuff"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.BRICK_FOUNTAIN_SPROUT, Identifier.of("block/bricks"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.MUD_FOUNTAIN_SPROUT, Identifier.of("block/mud"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.SANDSTONE_FOUNTAIN_SPROUT, Identifier.of("block/sandstone"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_FOUNTAIN_SPROUT, Identifier.of("block/red_sandstone"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.PRISMARINE_FOUNTAIN_SPROUT, Identifier.of("block/prismarine_bricks"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.NETHER_BRICK_FOUNTAIN_SPROUT, Identifier.of("block/nether_bricks"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_FOUNTAIN_SPROUT, Identifier.of("block/red_nether_bricks"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.BLACKSTONE_FOUNTAIN_SPROUT, Identifier.of("block/blackstone"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.ENDSTONE_FOUNTAIN_SPROUT, Identifier.of("block/end_stone"));
        registerFountainSprout(blockStateModelGenerator, ModBlocks.PURPUR_FOUNTAIN_SPROUT, Identifier.of("block/purpur_block"));

        registerChimney(blockStateModelGenerator, ModBlocks.STONE_BRICK_CHIMNEY, Identifier.ofVanilla("block/stone_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_CHIMNEY, Identifier.ofVanilla("block/mossy_stone_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.GRANITE_CHIMNEY, Identifier.ofVanilla("block/polished_granite"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.DIORITE_CHIMNEY, Identifier.ofVanilla("block/polished_diorite"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.ANDESITE_CHIMNEY, Identifier.ofVanilla("block/polished_andesite"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.DEEPSLATE_CHIMNEY, Identifier.ofVanilla("block/chiseled_deepslate"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.CALCITE_CHIMNEY, Identifier.ofVanilla("block/calcite"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.TUFF_CHIMNEY, Identifier.ofVanilla("block/chiseled_tuff"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.BRICK_CHIMNEY, Identifier.ofVanilla("block/bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.MUD_CHIMNEY, Identifier.ofVanilla("block/bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.SANDSTONE_CHIMNEY, Identifier.ofVanilla("block/chiseled_sandstone"),  ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_CHIMNEY, Identifier.ofVanilla("block/chiseled_red_sandstone"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.PRISMARINE_CHIMNEY, Identifier.ofVanilla("block/prismarine_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.NETHER_BRICK_CHIMNEY, Identifier.ofVanilla("block/nether_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_CHIMNEY, Identifier.ofVanilla("block/red_nether_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.BLACKSTONE_CHIMNEY, Identifier.ofVanilla("block/polished_blackstone_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.ENDSTONE_CHIMNEY, Identifier.ofVanilla("block/end_stone_bricks"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.PURPUR_CHIMNEY, Identifier.ofVanilla("block/purpur_block"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.IRON_CHIMNEY, CozyHome.id("block/break/iron_furniture"), ChimneyIntake.IRON);
        registerChimney(blockStateModelGenerator, ModBlocks.GOLD_CHIMNEY, CozyHome.id("block/break/gold_furniture"), ChimneyIntake.IRON);
    }

    // I have no idea what this does, but it's required.
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModBlocks.STONE_BRICK_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.MOSSY_STONE_BRICK_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.GRANITE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.DIORITE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.ANDESITE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.DEEPSLATE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.CALCITE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.TUFF_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.BRICK_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.MUD_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.SANDSTONE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.RED_SANDSTONE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.PRISMARINE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.NETHER_BRICK_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.RED_NETHER_BRICK_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.BLACKSTONE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.ENDSTONE_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.PURPUR_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.IRON_BATHTUB.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.GOLD_BATHTUB.asItem(), Models.GENERATED);
    }

    /**
     * Many blocks share models in them, but we can't register the shared models in the blockstate generator because it will cause a duplicate model error.
     * So we create a separate method to generate them here and call it at the beginning, for all to use.
     * In order to use these models, we just simply call the Cozyhome.id(block).
     */
    public final void registerGenerals(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier waterModelId = ModModels.WATER_15.upload(
                CozyHome.id("block/water_15"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/water_still")), blockStateModelGenerator.modelCollector);
        Identifier lavaModelId = ModModels.LAVA_15.upload(
                CozyHome.id("block/lava_15"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/lava_still")), blockStateModelGenerator.modelCollector);

        Identifier streamWaterDrippingModelId = ModModels.STREAM_WATER_DRIPPING.upload(
                CozyHome.id("block/stream_water_dripping"), new TextureMap().put(TextureKey.ALL, Identifier.ofVanilla("block/water_flow")), blockStateModelGenerator.modelCollector);
        Identifier streamLavaDrippingModelId = ModModels.STREAM_LAVA_DRIPPING.upload(
                CozyHome.id("block/stream_lava_dripping"), new TextureMap().put(TextureKey.ALL, Identifier.ofVanilla("block/lava_flow")), blockStateModelGenerator.modelCollector);
        Identifier streamWaterFallingModelId = ModModels.STREAM_WATER_FALLING.upload(
                CozyHome.id("block/stream_water_falling"), new TextureMap().put(TextureKey.ALL, Identifier.ofVanilla("block/water_flow")), blockStateModelGenerator.modelCollector);
        Identifier streamLavaFallingModelId = ModModels.STREAM_LAVA_FALLING.upload(
                CozyHome.id("block/stream_lava_falling"), new TextureMap().put(TextureKey.ALL, Identifier.ofVanilla("block/lava_flow")), blockStateModelGenerator.modelCollector);
        Identifier streamWaterLandingModelId = ModModels.STREAM_WATER_LANDING.upload(
                CozyHome.id("block/stream_water_landing"), new TextureMap().put(TextureKey.ALL, Identifier.ofVanilla("block/water_flow")), blockStateModelGenerator.modelCollector);
        Identifier streamLavaLandingModelId = ModModels.STREAM_LAVA_LANDING.upload(
                CozyHome.id("block/stream_lava_landing"), new TextureMap().put(TextureKey.ALL, Identifier.ofVanilla("block/lava_flow")), blockStateModelGenerator.modelCollector);

        Identifier insetWater15ModelId = ModModels.INSET_WATER_FLAT_15.upload(
                CozyHome.id("block/inset_water_15"), new TextureMap().put(TextureKey.UP, CozyHome.id("block/liquid/inset_water_still")), blockStateModelGenerator.modelCollector);
        Identifier insetWater13ModelId = ModModels.INSET_WATER_FLAT_13.upload(
                CozyHome.id("block/inset_water_13"), new TextureMap().put(TextureKey.UP, CozyHome.id("block/liquid/inset_water_still")), blockStateModelGenerator.modelCollector);
        Identifier insetWater11ModelId = ModModels.INSET_WATER_FLAT_11.upload(
                CozyHome.id("block/inset_water_11"), new TextureMap().put(TextureKey.UP, CozyHome.id("block/liquid/inset_water_still")), blockStateModelGenerator.modelCollector);
        Identifier insetLava15ModelId = ModModels.INSET_LAVA_FLAT_15.upload(
                CozyHome.id("block/inset_lava_15"), new TextureMap().put(TextureKey.UP, CozyHome.id("block/liquid/inset_lava_still")), blockStateModelGenerator.modelCollector);
        Identifier insetLava13ModelId = ModModels.INSET_LAVA_FLAT_13.upload(
                CozyHome.id("block/inset_lava_13"), new TextureMap().put(TextureKey.UP, CozyHome.id("block/liquid/inset_lava_still")), blockStateModelGenerator.modelCollector);
        Identifier insetLava11ModelId = ModModels.INSET_LAVA_FLAT_11.upload(
                CozyHome.id("block/inset_lava_11"), new TextureMap().put(TextureKey.UP, CozyHome.id("block/liquid/inset_lava_still")), blockStateModelGenerator.modelCollector);

        Identifier bathtubWater1ModelId = ModModels.BATHTUB_WATER_1.upload(CozyHome.id("block/bathtub/bathtub_water_1"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/water_still")), blockStateModelGenerator.modelCollector);
        Identifier bathtubWater2ModelId = ModModels.BATHTUB_WATER_2.upload(CozyHome.id("block/bathtub/bathtub_water_2"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/water_still")), blockStateModelGenerator.modelCollector);
        Identifier bathtubLava1ModelId = ModModels.BATHTUB_LAVA_1.upload(CozyHome.id("block/bathtub/bathtub_lava_1"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/lava_still")), blockStateModelGenerator.modelCollector);
        Identifier bathtubLava2ModelId = ModModels.BATHTUB_LAVA_2.upload(CozyHome.id("block/bathtub/bathtub_lava_2"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/lava_still")), blockStateModelGenerator.modelCollector);
    }

    public final void registerFallingLiquid(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_falling")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_falling")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(ModProperties.HAS_UNDER, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_landing")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(ModProperties.HAS_UNDER, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_landing")))
        );
    }

    public final void registerBuiltinWithParticleAndParentedItemModel(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier particleSource, Identifier modelPath) {
        blockStateModelGenerator.registerBuiltinWithParticle(block, particleSource);
        blockStateModelGenerator.registerParentedItemModel(block, modelPath);
    }

    public final void registerBuiltinWithParticleAndGeneratedItemModel(BlockStateModelGenerator blockStateModelGenerator, Block block, ModBlockTypes modBlockTypes, Identifier particleSource) {
        blockStateModelGenerator.registerBuiltinWithParticle(block, particleSource);
        Item item = block.asItem();
        Identifier textureId = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/" + modBlockTypes + "/" + Registries.BLOCK.getId(block).getPath() + "_item");
        Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(textureId), blockStateModelGenerator.modelCollector);
    }

    public final void registerCounter(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        String counterPath = "block/counter/" + Registries.BLOCK.getId(block).getPath();
        TextureMap counter = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_front"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_front"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap counterInner = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_inner_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_inner_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_front"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_back"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap counterOuter = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_outer_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_outer_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), counterPath + "_outer_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier counterModelId = ModModels.COUNTER.upload(block, counter, blockStateModelGenerator.modelCollector);
        Identifier counterInnerModelId = ModModels.COUNTER_INNER.upload(block, counterInner, blockStateModelGenerator.modelCollector);
        Identifier counterOuterModelId = ModModels.COUNTER_OUTER.upload(block, counterOuter, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.STAIR_SHAPE)
                        .register(Direction.NORTH, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                        .register(Direction.SOUTH, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterModelId))
                        .register(Direction.SOUTH, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId))
                        .register(Direction.SOUTH, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId))
                        .register(Direction.SOUTH, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                        .register(Direction.WEST, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                        .register(Direction.EAST, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterInnerModelId))
                        .register(Direction.EAST, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, counterOuterModelId))
                ));
    }

    public final void registerStorageCounter(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        Identifier top = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_storage", "") + "_top");
        Identifier side = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_storage", "") + "_side");
        Identifier bottom = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_storage", "") + "_bottom");
        Identifier open = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_storage", "") + "_open");
        Identifier closed = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_storage", "") + "_closed");
        Identifier back = Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_storage", "") + "_front");

        TextureMap storageCounter = new TextureMap()
                .put(TextureKey.TOP, top)
                .put(TextureKey.SIDE, side)
                .put(TextureKey.BOTTOM, bottom)
                .put(TextureKey.FRONT, closed)
                .put(TextureKey.BACK, back)
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap storageCounterOpen = new TextureMap()
                .put(TextureKey.TOP, top)
                .put(TextureKey.SIDE, side)
                .put(TextureKey.BOTTOM, bottom)
                .put(ModTextureKey.OPEN, open)
                .put(ModTextureKey.CLOSED, closed)
                .put(TextureKey.BACK, back)
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier storageCounterModelId = ModModels.COUNTER.upload(block, storageCounter, blockStateModelGenerator.modelCollector);
        Identifier storageCounterOpenModelId = ModModels.STORAGE_COUNTER.upload(block, storageCounterOpen, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.OPEN)
                        .register(Direction.NORTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.EAST,false,  BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.SOUTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterModelId))
                        .register(Direction.WEST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                        .register(Direction.NORTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterOpenModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.EAST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterOpenModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.SOUTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterOpenModelId))
                        .register(Direction.WEST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, storageCounterOpenModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                ));
    }

    public final void registerSinkCounter(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier faucetTexture, Identifier breakParticle) {
        TextureMap baseTexture = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_sink", "") + "_sink_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_sink", "") + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_sink", "") + "_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_sink", "") + "_front"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/counter/" + Registries.BLOCK.getId(block).getPath().replace("_sink", "") + "_back"))
                .put(ModTextureKey.INNER_SIDE, CozyHome.id("block/counter/sink_inner_side"))
                .put(ModTextureKey.INNER_BOTTOM, CozyHome.id("block/counter/sink_inner_bottom"))
                .put(ModTextureKey.EXTRA, faucetTexture)
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier sinkCounterModelID = ModModels.SINK_COUNTER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        Identifier sinkCounterOnModelID = ModModels.SINK_COUNTER_ON.upload(block, baseTexture, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                        // Sink
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(Properties.TRIGGERED, false),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterModelID)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(Properties.TRIGGERED, false),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterModelID))
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(Properties.TRIGGERED, false),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterModelID)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(Properties.TRIGGERED, false),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterModelID)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                        // Sink On
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(Properties.TRIGGERED, true),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterOnModelID)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(Properties.TRIGGERED, true),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterOnModelID))
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(Properties.TRIGGERED, true),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterOnModelID)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(Properties.TRIGGERED, true),
                                BlockStateVariant.create().put(VariantSettings.MODEL, sinkCounterOnModelID)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                        // Water
                        .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 1).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                                BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_11")))
                        .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 2).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                                BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_13")))
                        .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 3).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                                BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_15")))

                        // Lava
                        .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 1).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                                BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_11")))
                        .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 2).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                                BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_13")))
                        .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 3).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                                BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_15")))
                );
    }

    public final void registerTable(BlockStateModelGenerator blockStateModelGenerator, Block block, TableTypes tableTypes, Identifier breakParticle) {
        // Define texture maps
        String blockNamespace = Registries.BLOCK.getId(block).getNamespace();
        String blockPath = Registries.BLOCK.getId(block).getPath();
        String baseTexturePath = "block/table/" + blockPath;

        TextureMap baseTexture = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier tableModelID;
        Identifier tableCornerModelID;
        Identifier tableCornerPieceModelID;
        Identifier tableDoubleModelID;
        Identifier tableInnerCornerPieceModelID;
        Identifier tableMiddleModelID;
        Identifier tableSideModelID;

        if (tableTypes == TableTypes.SHELF) {
            // Upload models for various table states
            tableModelID = ModModels.SHELF_TABLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerModelID = ModModels.SHELF_TABLE_CORNER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerPieceModelID = ModModels.SHELF_TABLE_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableDoubleModelID = ModModels.SHELF_TABLE_DOUBLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableInnerCornerPieceModelID = ModModels.SHELF_TABLE_INNER_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableMiddleModelID = ModModels.SHELF_TABLE_MIDDLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableSideModelID = ModModels.SHELF_TABLE_SIDE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        } else {
            // Upload models for various table states
            tableModelID = ModModels.TABLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerModelID = ModModels.TABLE_CORNER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerPieceModelID = ModModels.TABLE_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableDoubleModelID = ModModels.TABLE_DOUBLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableInnerCornerPieceModelID = ModModels.TABLE_INNER_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableMiddleModelID = ModModels.TABLE_MIDDLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableSideModelID = ModModels.TABLE_SIDE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        }

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableMiddleModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableMiddleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.SOUTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID))
                .with(When.create().set(ModProperties.SOUTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.NORTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.NORTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.EAST, true).set(ModProperties.SOUTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.WEST, true).set(ModProperties.SOUTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.WEST, true).set(ModProperties.NORTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(ModProperties.NORTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        );
    }

    public final void registerLamp(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle, boolean useGenericLampModel, boolean useGenericMiddleModel, boolean useGenericBaseModel) {
        TextureMap lamp = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/lamp/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap lampOn = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/lamp/" + Registries.BLOCK.getId(block).getPath() + "_on"))
                .put(TextureKey.PARTICLE, breakParticle);

        Identifier lampModelId;
        Identifier lampOnModelId;

        Identifier lampTopModelId;
        Identifier lampTopOnModelId;

        Identifier lampMiddleModelId;
        Identifier lampMiddleOnModelId;

        Identifier lampBaseModelId;
        Identifier lampBaseOnModelId;

        if (useGenericLampModel) {
            lampModelId = ModModels.GENERIC_LAMP.upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampOnModelId = ModModels.GENERIC_LAMP_ON.upload(block, lampOn, blockStateModelGenerator.modelCollector);
            lampTopModelId = ModModels.GENERIC_LAMP_HEAD.upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampTopOnModelId = ModModels.GENERIC_LAMP_HEAD_ON.upload(block, lampOn, blockStateModelGenerator.modelCollector);
        } else {
            lampModelId = ModModels.modBlockWithType(String.valueOf(Registries.BLOCK.getId(block).getPath()), ModBlockTypes.LAMP, TextureKey.ALL, TextureKey.PARTICLE).upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampOnModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath(), ModBlockTypes.LAMP, "_on", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lampOn, blockStateModelGenerator.modelCollector);
            lampTopModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath() + "_head", ModBlockTypes.LAMP, "_head", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampTopOnModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath() + "_head", ModBlockTypes.LAMP, "_head_on", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lampOn, blockStateModelGenerator.modelCollector);
        }

        if (useGenericMiddleModel) {
            lampMiddleModelId = ModModels.GENERIC_LAMP_MIDDLE.upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampMiddleOnModelId = ModModels.GENERIC_LAMP_MIDDLE_ON.upload(block, lampOn, blockStateModelGenerator.modelCollector);
        } else {
            lampMiddleModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath() + "_middle", ModBlockTypes.LAMP, "_middle", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampMiddleOnModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath() + "_middle", ModBlockTypes.LAMP, "_middle_on", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lampOn, blockStateModelGenerator.modelCollector);
        }

        if (useGenericBaseModel) {
            lampBaseModelId = ModModels.GENERIC_LAMP_BASE.upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampBaseOnModelId = ModModels.GENERIC_LAMP_BASE_ON.upload(block, lampOn, blockStateModelGenerator.modelCollector);
        } else {
            lampBaseModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath() + "_base", ModBlockTypes.LAMP, "_base", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lamp, blockStateModelGenerator.modelCollector);
            lampBaseOnModelId = ModModels.modBlockWithTypeAndVariant(Registries.BLOCK.getId(block).getPath() + "_base", ModBlockTypes.LAMP, "_base_on", TextureKey.ALL, TextureKey.PARTICLE).upload(block, lampOn, blockStateModelGenerator.modelCollector);
        }

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(ModProperties.VERTICAL_CONNECTION, Properties.HORIZONTAL_FACING, Properties.LIT)
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.NORTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampModelId))
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.NORTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampOnModelId))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.NORTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopModelId))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.NORTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopOnModelId))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.NORTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleModelId))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.NORTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleOnModelId))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.NORTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseModelId))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.NORTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseOnModelId))

                        // EAST
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.EAST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.EAST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.EAST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.EAST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.EAST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.EAST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.EAST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.EAST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                        // SOUTH
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.SOUTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.SOUTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.SOUTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.SOUTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.SOUTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.SOUTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.SOUTH, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.SOUTH, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                        // WEST
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.WEST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.SINGLE, Direction.WEST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.WEST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.HEAD, Direction.WEST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampTopOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.WEST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.MIDDLE, Direction.WEST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampMiddleOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.WEST, false, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(VerticalLinearConnectionBlock.TAIL, Direction.WEST, true, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, lampBaseOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                ));

    }

    public final void registerFountain(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        // Define texture maps
        String blockNamespace = Registries.BLOCK.getId(block).getNamespace();
        String blockPath = Registries.BLOCK.getId(block).getPath();
        String baseTexturePath = "block/fountain/" + blockPath;

        TextureMap baseTexture = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, breakParticle);

        Identifier fountainModelID;
        Identifier fountainCornerModelID;
        Identifier fountainCornerPieceModelID;
        Identifier fountainDoubleModelID;
        Identifier fountainInnerCornerPieceModelID;
        Identifier fountainMiddleModelID;
        Identifier fountainSideModelID;

        // Upload models for various fountain states
        fountainModelID = ModModels.FOUNTAIN.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        fountainCornerModelID = ModModels.FOUNTAIN_CORNER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        fountainCornerPieceModelID = ModModels.FOUNTAIN_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        fountainDoubleModelID = ModModels.FOUNTAIN_DOUBLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        fountainInnerCornerPieceModelID = ModModels.FOUNTAIN_INNER_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        fountainMiddleModelID = ModModels.FOUNTAIN_MIDDLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        fountainSideModelID = ModModels.FOUNTAIN_SIDE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainMiddleModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainMiddleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.SOUTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID))
                .with(When.create().set(ModProperties.SOUTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.NORTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.NORTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.EAST, true).set(ModProperties.SOUTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.WEST, true).set(ModProperties.SOUTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.WEST, true).set(ModProperties.NORTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(ModProperties.NORTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))

                // Dripping Liquid
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/water_15")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/lava_15")))
        );
    }

    public final void registerFountainSprout(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        String blockNamespace = Registries.BLOCK.getId(block).getNamespace();
        String blockPath = Registries.BLOCK.getId(block).getPath();
        String baseTexturePath = "block/fountain_sprout/" + blockPath;

        TextureMap baseTextureMap = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, breakParticle);

        Identifier baseModelId = ModModels.FOUNTAIN_SPROUT.upload(block, baseTextureMap, blockStateModelGenerator.modelCollector);
        Identifier wallModelId = ModModels.FOUNTAIN_SPROUT_WALL.upload(block, baseTextureMap, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)

                // Floor and Ceilings
                .with(When.create().set(Properties.BLOCK_FACE, BlockFace.FLOOR),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseModelId))
                .with(When.create().set(Properties.BLOCK_FACE, BlockFace.CEILING),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseModelId)
                                .put(VariantSettings.X, VariantSettings.Rotation.R180))

                // Walls
                .with(When.create().set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.NORTH),
                        BlockStateVariant.create().put(VariantSettings.MODEL, wallModelId))
                .with(When.create().set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.EAST),
                        BlockStateVariant.create().put(VariantSettings.MODEL, wallModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.SOUTH),
                        BlockStateVariant.create().put(VariantSettings.MODEL, wallModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.WEST),
                        BlockStateVariant.create().put(VariantSettings.MODEL, wallModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                // Dripping Liquid
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.NORTH),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_dripping")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.EAST),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_dripping"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.SOUTH),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_dripping"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.WEST),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_dripping"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.NORTH),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_dripping")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.EAST),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_dripping"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.SOUTH),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_dripping"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(Properties.BLOCK_FACE, BlockFace.WALL).set(Properties.HORIZONTAL_FACING, Direction.WEST),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_dripping"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                // Falling Liquid
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(Properties.BLOCK_FACE, BlockFace.CEILING),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_falling")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(Properties.BLOCK_FACE, BlockFace.CEILING),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_falling")))

                // Landed Liquid
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER).set(ModProperties.HAS_UNDER, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_water_landing")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA).set(ModProperties.HAS_UNDER, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/stream_lava_landing")))
        );
    }

    public final void registerChimney(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier baseBlockTexture, ChimneyIntake intakeType) {
        TextureMap chimney_single = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(ModTextureKey.EXTRA, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_intake_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + intakeType.asString()))
                .put(TextureKey.PARTICLE, baseBlockTexture);
        TextureMap chimney_top = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.PARTICLE, baseBlockTexture);
        TextureMap chimney_middle = new TextureMap()
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_middle"))
                .put(TextureKey.PARTICLE, baseBlockTexture);
        TextureMap chimney_solid = new TextureMap()
                .put(TextureKey.END, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, baseBlockTexture)
                .put(TextureKey.PARTICLE, baseBlockTexture);
        TextureMap chimney_intake = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_intake_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + intakeType.asString()))
                .put(TextureKey.PARTICLE, baseBlockTexture);
        Identifier singleModelId = ModModels.CHIMNEY_SINGLE.upload(block, chimney_single, blockStateModelGenerator.modelCollector);
        Identifier headModelId = ModModels.CHIMNEY_TOP.upload(block, chimney_top, blockStateModelGenerator.modelCollector);
        Identifier middleModelId = ModModels.CHIMNEY_MIDDLE.upload(block, chimney_middle, blockStateModelGenerator.modelCollector);
        Identifier solidModelId = Models.CUBE_COLUMN.upload(block, "_solid", chimney_solid, blockStateModelGenerator.modelCollector);
        Identifier tailModelId = ModModels.CHIMNEY_INTAKE.upload(block, chimney_intake, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(ModProperties.VERTICAL_WITH_EXTRA_CONNECTION)
                        .register(VerticalWithExtraConnectionBlock.HEAD, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, headModelId))
                        .register(VerticalWithExtraConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, middleModelId))
                        .register(VerticalWithExtraConnectionBlock.EXTENDED, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, solidModelId))
                        .register(VerticalWithExtraConnectionBlock.TAIL, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, tailModelId))
                        .register(VerticalWithExtraConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, singleModelId))
                )
        );
    }

    public enum ChimneyIntake implements StringIdentifiable {
        IRON("chimney_intake_iron"),
        GOLD("chimney_intake_gold");

        private final String type;

        ChimneyIntake(final String type) {
            this.type = type;
        }

        @Override
        public String asString() {
            return this.type;
        }
    }

    public final void registerDesk(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        TextureMap desk = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_bottom"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_side_inner"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap desk_left = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_top_side"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_bottom_side"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_side_inner"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap desk_middle = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_top_middle"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_bottom_middle"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_back_middle"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap desk_right = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_top_side"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_bottom_side"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_side_inner"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath().replace("desk", "drawer") + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier deskModelId = ModModels.DESK.upload(block, desk, blockStateModelGenerator.modelCollector);
        Identifier deskLeftModelId = ModModels.DESK_LEFT.upload(block, desk_left, blockStateModelGenerator.modelCollector);
        Identifier deskMiddleModelId = ModModels.DESK_MIDDLE.upload(block, desk_middle, blockStateModelGenerator.modelCollector);
        Identifier deskRightModelId = ModModels.DESK_RIGHT.upload(block, desk_right, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, ModProperties.ADVANCED_HORIZONTAL_CONNECTION)
                        // North
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                        // Repeat for SOUTH
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId))

                        // Repeat for EAST
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                        // Repeat for WEST
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, deskMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                )
        );
    }

    public final void registerDrawer(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        TextureMap drawer = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_left = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_side"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom_side"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front_side"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_left_diff = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_side"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_left_diff_left = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_middle"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom_side"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front_side"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_middle = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_middle"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom_middle"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front_middle"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_middle"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_middle_diff = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_middle"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_right = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_side"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom_side"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front_side_right"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_right_diff = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_side"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap drawer_right_diff_right = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_top_middle"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_bottom_side"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_side_inner"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_front_side_right"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/drawer/" + Registries.BLOCK.getId(block).getPath() + "_back_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier drawerModelId = ModModels.DRAWER.upload(block, drawer, blockStateModelGenerator.modelCollector);
        Identifier drawerLeftModelId = ModModels.DRAWER_LEFT.upload(block, drawer_left, blockStateModelGenerator.modelCollector);
        Identifier drawerLeftDiffModelId = ModModels.DRAWER_LEFT_DIFF.upload(block, drawer_left_diff, blockStateModelGenerator.modelCollector);
        Identifier drawerLeftDiffLeftModelId = ModModels.DRAWER_LEFT_DIFF_LEFT.upload(block, drawer_left_diff_left, blockStateModelGenerator.modelCollector);
        Identifier drawerMiddleModelId = ModModels.DRAWER_MIDDLE.upload(block, drawer_middle, blockStateModelGenerator.modelCollector);
        Identifier drawerMiddleDiffModelId = ModModels.DRAWER_MIDDLE_DIFF.upload(block, drawer_middle_diff, blockStateModelGenerator.modelCollector);
        Identifier drawerRightModelId = ModModels.DRAWER_RIGHT.upload(block, drawer_right, blockStateModelGenerator.modelCollector);
        Identifier drawerRightDiffModelId = ModModels.DRAWER_RIGHT_DIFF.upload(block, drawer_right_diff, blockStateModelGenerator.modelCollector);
        Identifier drawerRightDiffRightModelId = ModModels.DRAWER_RIGHT_DIFF_RIGHT.upload(block, drawer_right_diff_right, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, ModProperties.ADVANCED_HORIZONTAL_CONNECTION)
                        // North
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                        // Repeat for SOUTH
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffLeftModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffRightModelId))
                        .register(Direction.SOUTH, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleDiffModelId))

                        // Repeat for EAST
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                        // Repeat for WEST
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerLeftDiffLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerRightDiffRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, drawerMiddleDiffModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                )
        );
    }

    public final void registerWallMirror(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier mirrorTexture) {
        TextureMap wall_mirror = new TextureMap()
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_frame"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_board"))
                .put(TextureKey.PARTICLE, mirrorTexture);
        TextureMap wall_mirror_top = new TextureMap()
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_frame_top"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_board_top"))
                .put(TextureKey.PARTICLE, mirrorTexture);
        TextureMap wall_mirror_middle = new TextureMap()
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_frame_middle"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_board_middle"))
                .put(TextureKey.PARTICLE, mirrorTexture);
        TextureMap wall_mirror_bottom = new TextureMap()
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_frame_bottom"))
                .put(TextureKey.BACK, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/shared/" + Registries.BLOCK.getId(block).getPath().replace("_wall_mirror", "") + "_board_bottom"))
                .put(TextureKey.PARTICLE, mirrorTexture);
        Identifier wallMirrorModelId = ModModels.WALL_MIRROR.upload(block, wall_mirror, blockStateModelGenerator.modelCollector);
        Identifier wallMirrorTopModelId = ModModels.WALL_MIRROR_TOP.upload(block, wall_mirror_top, blockStateModelGenerator.modelCollector);
        Identifier wallMirrorMiddleModelId = ModModels.WALL_MIRROR_MIDDLE.upload(block, wall_mirror_middle, blockStateModelGenerator.modelCollector);
        Identifier wallMirrorBottomModelId = ModModels.WALL_MIRROR_BOTTOM.upload(block, wall_mirror_bottom, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, ModProperties.VERTICAL_CONNECTION)
                        .register(Direction.NORTH, VerticalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorModelId))
                        .register(Direction.NORTH, VerticalLinearConnectionBlock.HEAD, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorTopModelId))
                        .register(Direction.NORTH, VerticalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorMiddleModelId))
                        .register(Direction.NORTH, VerticalLinearConnectionBlock.TAIL, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorBottomModelId))

                        // Repeat for SOUTH
                        .register(Direction.SOUTH, VerticalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.SOUTH, VerticalLinearConnectionBlock.HEAD, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorTopModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.SOUTH, VerticalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.SOUTH, VerticalLinearConnectionBlock.TAIL, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorBottomModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                        // Repeat for EAST
                        .register(Direction.EAST, VerticalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.EAST, VerticalLinearConnectionBlock.HEAD, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorTopModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.EAST, VerticalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.EAST, VerticalLinearConnectionBlock.TAIL, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorBottomModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                        // Repeat for WEST
                        .register(Direction.WEST, VerticalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.WEST, VerticalLinearConnectionBlock.HEAD, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorTopModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.WEST, VerticalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.WEST, VerticalLinearConnectionBlock.TAIL, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, wallMirrorBottomModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                ));
    }

    public final void registerSink(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier faucetTexture, Identifier breakParticle) {
        TextureMap baseTextureMap = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/sink/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/sink/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/sink/" + Registries.BLOCK.getId(block).getPath() + "_bottom"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/sink/" + Registries.BLOCK.getId(block).getPath() + "_inner_side"))
                .put(ModTextureKey.EXTRA, faucetTexture)
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier baseModelId = ModModels.SINK.upload(block, baseTextureMap, blockStateModelGenerator.modelCollector);
        Identifier baseOnModelId = ModModels.SINK_ON.upload(block, baseTextureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                // Sink
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseModelId))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                // Sink On
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseOnModelId))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, baseOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                // Water
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 1).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_11")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 2).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_13")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 3).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_15")))

                // Lava
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 1).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_11")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 2).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_13")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_3, 3).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_15")))
        );
    }

    public final void registerBathtub(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier faucetTexture, Identifier breakParticle) {
        String bathtubPath = "block/bathtub/" + Registries.BLOCK.getId(block).getPath();
        TextureMap frontTextureMap = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_front"))
                .put(ModTextureKey.INNER_FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_inner_front"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_inner_side"))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap backTextureMap = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_bottom"))
                .put(TextureKey.FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_front"))
                .put(ModTextureKey.INNER_FRONT, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_inner_front"))
                .put(ModTextureKey.INNER_SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), bathtubPath + "_inner_side"))
                .put(ModTextureKey.EXTRA, faucetTexture)
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier frontModelId = ModModels.BATHTUB_FRONT.upload(block, frontTextureMap, blockStateModelGenerator.modelCollector);
        Identifier backModelId = ModModels.BATHTUB_BACK.upload(block, backTextureMap, blockStateModelGenerator.modelCollector);
        Identifier backOnModelId = ModModels.BATHTUB_BACK_ON.upload(block, backTextureMap, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)

                // Front Part, shown regardless of on/off state, just rotates
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT),
                        BlockStateVariant.create().put(VariantSettings.MODEL, frontModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT),
                        BlockStateVariant.create().put(VariantSettings.MODEL, frontModelId))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT),
                        BlockStateVariant.create().put(VariantSettings.MODEL, frontModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT),
                        BlockStateVariant.create().put(VariantSettings.MODEL, frontModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                // Back when On
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backModelId))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                // Back when Off
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backOnModelId))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK).set(Properties.TRIGGERED, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, backOnModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                // Water North
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_1"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_2"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_1")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_2")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_1"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_2"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_1"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_water_2"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))

                // Lava
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_1"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_2"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_1")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.SOUTH).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_2")))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_1"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.EAST).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_2"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 1).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_1"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.FILLED_LEVEL_0_2, 2).set(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT).set(Properties.HORIZONTAL_FACING, Direction.WEST).set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/bathtub/bathtub_lava_2"))
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
        );
    }

    public final void registerLargeStump(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier baseBlock) {
        // Define texture maps
        String blockNamespace = baseBlock.getNamespace();
        String blockPath = baseBlock.getPath();
        String baseTexturePath = "block/" + blockPath;

        TextureMap baseTexture = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(blockNamespace, baseTexturePath + "_top"))
                .put(TextureKey.SIDE, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, Identifier.of(blockNamespace, baseTexturePath));

        TextureMap cornerTexture = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/large_stump/" + Registries.BLOCK.getId(block).getPath() + "_corner_top"))
                .put(TextureKey.SIDE, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, Identifier.of(blockNamespace, baseTexturePath));

        TextureMap doubleTexture = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/large_stump/" + Registries.BLOCK.getId(block).getPath() + "_double_top"))
                .put(TextureKey.SIDE, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, Identifier.of(blockNamespace, baseTexturePath));

        TextureMap innerCornerPieceTexture = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/large_stump/" + Registries.BLOCK.getId(block).getPath() + "_corner_inner_top"))
                .put(TextureKey.SIDE, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, Identifier.of(blockNamespace, baseTexturePath));

        TextureMap middleTexture = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/large_stump/" + Registries.BLOCK.getId(block).getPath() + "_middle_top"))
                .put(TextureKey.SIDE, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, Identifier.of(blockNamespace, baseTexturePath));

        // Upload models for various stump states
        Identifier stumpModelID = ModModels.LARGE_STUMP.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        Identifier stumpCornerModelID = ModModels.LARGE_STUMP_CORNER.upload(block, cornerTexture, blockStateModelGenerator.modelCollector);
        Identifier stumpCornerPieceModelID = ModModels.LARGE_STUMP_CORNER_PIECE.upload(block, cornerTexture, blockStateModelGenerator.modelCollector);
        Identifier stumpDoubleModelID = ModModels.LARGE_STUMP_DOUBLE.upload(block, doubleTexture, blockStateModelGenerator.modelCollector);
        Identifier stumpInnerCornerPieceModelID = ModModels.LARGE_STUMP_INNER_CORNER_PIECE.upload(block, innerCornerPieceTexture, blockStateModelGenerator.modelCollector);
        Identifier stumpMiddleModelID = ModModels.LARGE_STUMP_MIDDLE.upload(block, middleTexture, blockStateModelGenerator.modelCollector);
        Identifier stumpSideModelID = ModModels.LARGE_STUMP_SIDE.upload(block, middleTexture, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpDoubleModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpMiddleModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpMiddleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.SOUTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpInnerCornerPieceModelID))
                .with(When.create().set(ModProperties.SOUTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.NORTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.NORTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.EAST, true).set(ModProperties.SOUTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerPieceModelID))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.WEST, true).set(ModProperties.SOUTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.WEST, true).set(ModProperties.NORTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(ModProperties.NORTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpSideModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, stumpSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        );
    }

    public final void registerCouch(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        TextureMap couch = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/couch/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap couchLeft = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/couch/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap couchRight = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/couch/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap couchMiddle = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/couch/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap couchInner = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/couch/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        TextureMap couchOuter = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/couch/" + Registries.BLOCK.getId(block).getPath()))
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier couchModelId = ModModels.COUCH.upload(block, couch, blockStateModelGenerator.modelCollector);
        Identifier couchLeftModelId = ModModels.COUCH_LEFT.upload(block, couchLeft, blockStateModelGenerator.modelCollector);
        Identifier couchRightModelId = ModModels.COUCH_RIGHT.upload(block, couchRight, blockStateModelGenerator.modelCollector);
        Identifier couchMiddleModelId = ModModels.COUCH_MIDDLE.upload(block, couchMiddle, blockStateModelGenerator.modelCollector);
        Identifier couchInnerModelId = ModModels.COUCH_INNER.upload(block, couchInner, blockStateModelGenerator.modelCollector);
        Identifier couchOuterModelId = ModModels.COUCH_OUTER.upload(block, couchOuter, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, ModProperties.HORIZONTAL_CONNECTION, Properties.STAIR_SHAPE)
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.LEFT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.NORTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))

                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.LEFT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchLeftModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.MIDDLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchMiddleModelId))
                        .register(Direction.SOUTH, HorizontalLinearConnectionBlock.RIGHT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchRightModelId))

                        .register(Direction.WEST, HorizontalLinearConnectionBlock.SINGLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.LEFT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.RIGHT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.WEST, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180))

                        .register(Direction.EAST, HorizontalLinearConnectionBlock.SINGLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.SINGLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.SINGLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.LEFT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchLeftModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.LEFT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.LEFT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchMiddleModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.MIDDLE, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.RIGHT, StairShape.STRAIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchRightModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.RIGHT, StairShape.INNER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchInnerModelId))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_LEFT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.EAST, HorizontalLinearConnectionBlock.RIGHT, StairShape.OUTER_RIGHT, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, couchOuterModelId))
                ));
    }
}