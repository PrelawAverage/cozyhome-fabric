package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.custom.water_holding_blocks.bathtub.BathTubBlockEntity;
import net.luckystudio.cozyhome.block.custom.water_holding_blocks.sink.SinkBlockEntity;
import net.luckystudio.cozyhome.block.custom.chimneys.ChimneyBlockEntity;
import net.luckystudio.cozyhome.block.custom.counters.StorageCounterBlockEntity;
import net.luckystudio.cozyhome.block.custom.chairs.ChairBlockEntity;
import net.luckystudio.cozyhome.block.custom.clocks.grandfather_clock.GrandfatherClockBlockEntity;
import net.luckystudio.cozyhome.block.custom.clocks.wall_clock.WallClockBlockEntity;
import net.luckystudio.cozyhome.block.custom.couches.CouchBlockEntity;
import net.luckystudio.cozyhome.block.custom.drawers.DrawerBlockEntity;
import net.luckystudio.cozyhome.block.custom.lamps.LampBlockEntity;
import net.luckystudio.cozyhome.block.custom.sofas.SofaBlockEntity;
import net.luckystudio.cozyhome.block.custom.telescope.TelescopeBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {

    public static final BlockEntityType<StorageCounterBlockEntity> STORAGE_COUNTER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "storage_counter_block_entity"),
        BlockEntityType.Builder.create(StorageCounterBlockEntity::new,
                ModBlocks.OAK_STORAGE_COUNTER,
                ModBlocks.SPRUCE_STORAGE_COUNTER,
                ModBlocks.BIRCH_STORAGE_COUNTER,
                ModBlocks.JUNGLE_STORAGE_COUNTER,
                ModBlocks.ACACIA_STORAGE_COUNTER,
                ModBlocks.DARK_OAK_STORAGE_COUNTER,
                ModBlocks.MANGROVE_STORAGE_COUNTER,
                ModBlocks.CHERRY_STORAGE_COUNTER,
                ModBlocks.BAMBOO_STORAGE_COUNTER,
                ModBlocks.CRIMSON_STORAGE_COUNTER,
                ModBlocks.WARPED_STORAGE_COUNTER
        ).build());

    public static final BlockEntityType<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "drawer_block_entity"),
            BlockEntityType.Builder.create(DrawerBlockEntity::new,
                    ModBlocks.OAK_DRAWER,
                    ModBlocks.SPRUCE_DRAWER,
                    ModBlocks.BIRCH_DRAWER,
                    ModBlocks.JUNGLE_DRAWER,
                    ModBlocks.ACACIA_DRAWER,
                    ModBlocks.DARK_OAK_DRAWER,
                    ModBlocks.MANGROVE_DRAWER,
                    ModBlocks.CHERRY_DRAWER,
                    ModBlocks.BAMBOO_DRAWER,
                    ModBlocks.CRIMSON_DRAWER,
                    ModBlocks.WARPED_DRAWER
            ).build());

    public static final BlockEntityType<ChairBlockEntity> CHAIR_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "chair_block_entity"),
            BlockEntityType.Builder.create(ChairBlockEntity::new,
                    ModBlocks.OAK_CHAIR,
                    ModBlocks.SPRUCE_CHAIR,
                    ModBlocks.BIRCH_CHAIR,
                    ModBlocks.JUNGLE_CHAIR,
                    ModBlocks.ACACIA_CHAIR,
                    ModBlocks.DARK_OAK_CHAIR,
                    ModBlocks.MANGROVE_CHAIR,
                    ModBlocks.CHERRY_CHAIR,
                    ModBlocks.BAMBOO_CHAIR,
                    ModBlocks.CRIMSON_CHAIR,
                    ModBlocks.WARPED_CHAIR,
                    ModBlocks.IRON_CHAIR,
                    ModBlocks.GLASS_CHAIR,
                    ModBlocks.UNDEAD_CHAIR,
                    ModBlocks.OMINOUS_CHAIR
            ).build());

    public static final BlockEntityType<LampBlockEntity> LAMP_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "lamp_block_entity"),
            BlockEntityType.Builder.create(LampBlockEntity::new,
                    ModBlocks.OAK_LAMP,
                    ModBlocks.SPRUCE_LAMP,
                    ModBlocks.BIRCH_LAMP,
                    ModBlocks.JUNGLE_LAMP,
                    ModBlocks.ACACIA_LAMP,
                    ModBlocks.DARK_OAK_LAMP,
                    ModBlocks.MANGROVE_LAMP,
                    ModBlocks.CHERRY_LAMP,
                    ModBlocks.BAMBOO_LAMP,
                    ModBlocks.CRIMSON_LAMP,
                    ModBlocks.WARPED_LAMP,
                    ModBlocks.IRON_LAMP,
                    ModBlocks.GLASS_LAMP,
                    ModBlocks.UNDEAD_LAMP,
                    ModBlocks.OMINOUS_LAMP
            ).build());

    public static final BlockEntityType<SofaBlockEntity> SOFA_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "sofa_block_entity"),
            BlockEntityType.Builder.create(SofaBlockEntity::new,
                    ModBlocks.OAK_SOFA,
                    ModBlocks.SPRUCE_SOFA,
                    ModBlocks.BIRCH_SOFA,
                    ModBlocks.JUNGLE_SOFA,
                    ModBlocks.ACACIA_SOFA,
                    ModBlocks.DARK_OAK_SOFA,
                    ModBlocks.MANGROVE_SOFA,
                    ModBlocks.CHERRY_SOFA,
                    ModBlocks.BAMBOO_SOFA,
                    ModBlocks.CRIMSON_SOFA,
                    ModBlocks.WARPED_SOFA
            ).build());

    public static final BlockEntityType<CouchBlockEntity> COUCH_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "couch_block_entity"),
            BlockEntityType.Builder.create(CouchBlockEntity::new,
                    ModBlocks.OAK_COUCH,
                    ModBlocks.SPRUCE_COUCH,
                    ModBlocks.BIRCH_COUCH,
                    ModBlocks.JUNGLE_COUCH,
                    ModBlocks.ACACIA_COUCH,
                    ModBlocks.DARK_OAK_COUCH,
                    ModBlocks.MANGROVE_COUCH,
                    ModBlocks.CHERRY_COUCH,
                    ModBlocks.BAMBOO_COUCH,
                    ModBlocks.CRIMSON_COUCH,
                    ModBlocks.WARPED_COUCH
            ).build());

    public static final BlockEntityType<WallClockBlockEntity> WALL_CLOCK_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "clock_block_entity"),
            BlockEntityType.Builder.create(WallClockBlockEntity::new,
                    ModBlocks.OAK_WALL_CLOCK,
                    ModBlocks.SPRUCE_WALL_CLOCK,
                    ModBlocks.BIRCH_WALL_CLOCK,
                    ModBlocks.JUNGLE_WALL_CLOCK,
                    ModBlocks.ACACIA_WALL_CLOCK,
                    ModBlocks.DARK_OAK_WALL_CLOCK,
                    ModBlocks.MANGROVE_WALL_CLOCK,
                    ModBlocks.CHERRY_WALL_CLOCK,
                    ModBlocks.BAMBOO_WALL_CLOCK,
                    ModBlocks.CRIMSON_WALL_CLOCK,
                    ModBlocks.WARPED_WALL_CLOCK,
                    ModBlocks.IRON_WALL_CLOCK,
                    ModBlocks.GLASS_WALL_CLOCK,
                    ModBlocks.UNDEAD_WALL_CLOCK,
                    ModBlocks.OMINOUS_WALL_CLOCK
            ).build());

    public static final BlockEntityType<GrandfatherClockBlockEntity> GRANDFATHER_CLOCK_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "grandfather_clock_block_entity"),
            BlockEntityType.Builder.create(GrandfatherClockBlockEntity::new,
                    ModBlocks.OAK_GRANDFATHER_CLOCK,
                    ModBlocks.SPRUCE_GRANDFATHER_CLOCK,
                    ModBlocks.BIRCH_GRANDFATHER_CLOCK,
                    ModBlocks.JUNGLE_GRANDFATHER_CLOCK,
                    ModBlocks.ACACIA_GRANDFATHER_CLOCK,
                    ModBlocks.DARK_OAK_GRANDFATHER_CLOCK,
                    ModBlocks.MANGROVE_GRANDFATHER_CLOCK,
                    ModBlocks.CHERRY_GRANDFATHER_CLOCK,
                    ModBlocks.BAMBOO_GRANDFATHER_CLOCK,
                    ModBlocks.CRIMSON_GRANDFATHER_CLOCK,
                    ModBlocks.WARPED_GRANDFATHER_CLOCK,
                    ModBlocks.IRON_GRANDFATHER_CLOCK,
                    ModBlocks.GLASS_GRANDFATHER_CLOCK,
                    ModBlocks.UNDEAD_GRANDFATHER_CLOCK,
                    ModBlocks.OMINOUS_GRANDFATHER_CLOCK
            ).build());

    public static final BlockEntityType<ChimneyBlockEntity> CHIMNEY_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "chimney_block_entity"),
            BlockEntityType.Builder.create(ChimneyBlockEntity::new,
                    ModBlocks.STONE_BRICK_CHIMNEY,
                    ModBlocks.MOSSY_STONE_BRICK_CHIMNEY,
                    ModBlocks.GRANITE_CHIMNEY,
                    ModBlocks.DIORITE_CHIMNEY,
                    ModBlocks.ANDESITE_CHIMNEY,
                    ModBlocks.DEEPSLATE_CHIMNEY,
                    ModBlocks.CALCITE_CHIMNEY,
                    ModBlocks.TUFF_CHIMNEY,
                    ModBlocks.BRICK_CHIMNEY,
                    ModBlocks.MUD_CHIMNEY,
                    ModBlocks.SANDSTONE_CHIMNEY,
                    ModBlocks.RED_SANDSTONE_CHIMNEY,
                    ModBlocks.PRISMARINE_CHIMNEY,
                    ModBlocks.NETHER_BRICK_CHIMNEY,
                    ModBlocks.RED_NETHER_BRICK_CHIMNEY,
                    ModBlocks.BLACKSTONE_CHIMNEY,
                    ModBlocks.ENDSTONE_CHIMNEY,
                    ModBlocks.PURPUR_CHIMNEY,
                    ModBlocks.IRON_CHIMNEY,
                    ModBlocks.GOLD_CHIMNEY
            ).build());

    public static final BlockEntityType<BathTubBlockEntity> BATHTUB_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "bathtub_block_entity"),
            BlockEntityType.Builder.create(BathTubBlockEntity::new,
                    ModBlocks.STONE_BRICK_BATHTUB,
                    ModBlocks.MOSSY_STONE_BRICK_BATHTUB,
                    ModBlocks.GRANITE_BATHTUB,
                    ModBlocks.DIORITE_BATHTUB,
                    ModBlocks.ANDESITE_BATHTUB,
                    ModBlocks.DEEPSLATE_BATHTUB,
                    ModBlocks.CALCITE_BATHTUB,
                    ModBlocks.TUFF_BATHTUB,
                    ModBlocks.BRICK_BATHTUB,
                    ModBlocks.MUD_BATHTUB,
                    ModBlocks.SANDSTONE_BATHTUB,
                    ModBlocks.RED_SANDSTONE_BATHTUB,
                    ModBlocks.PRISMARINE_BATHTUB,
                    ModBlocks.NETHER_BRICK_BATHTUB,
                    ModBlocks.RED_NETHER_BRICK_BATHTUB,
                    ModBlocks.BLACKSTONE_BATHTUB,
                    ModBlocks.ENDSTONE_BATHTUB,
                    ModBlocks.PURPUR_BATHTUB,
                    ModBlocks.IRON_BATHTUB,
                    ModBlocks.GOLD_BATHTUB
            ).build());

    public static final BlockEntityType<SinkBlockEntity> SINK_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "sink_block_entity"),
            BlockEntityType.Builder.create(SinkBlockEntity::new,
                    ModBlocks.STONE_BRICK_SINK,
                    ModBlocks.MOSSY_STONE_BRICK_SINK,
                    ModBlocks.GRANITE_SINK,
                    ModBlocks.DIORITE_SINK,
                    ModBlocks.ANDESITE_SINK,
                    ModBlocks.DEEPSLATE_SINK,
                    ModBlocks.CALCITE_SINK,
                    ModBlocks.TUFF_SINK,
                    ModBlocks.BRICK_SINK,
                    ModBlocks.MUD_SINK,
                    ModBlocks.SANDSTONE_SINK,
                    ModBlocks.RED_SANDSTONE_SINK,
                    ModBlocks.PRISMARINE_SINK,
                    ModBlocks.NETHER_BRICK_SINK,
                    ModBlocks.RED_NETHER_BRICK_SINK,
                    ModBlocks.BLACKSTONE_SINK,
                    ModBlocks.ENDSTONE_SINK,
                    ModBlocks.PURPUR_SINK,
                    ModBlocks.IRON_SINK,
                    ModBlocks.GOLD_SINK,

                    ModBlocks.OAK_SINK_COUNTER,
                    ModBlocks.SPRUCE_SINK_COUNTER,
                    ModBlocks.BIRCH_SINK_COUNTER,
                    ModBlocks.JUNGLE_SINK_COUNTER,
                    ModBlocks.ACACIA_SINK_COUNTER,
                    ModBlocks.DARK_OAK_SINK_COUNTER,
                    ModBlocks.MANGROVE_SINK_COUNTER,
                    ModBlocks.CHERRY_SINK_COUNTER,
                    ModBlocks.BAMBOO_SINK_COUNTER,
                    ModBlocks.CRIMSON_SINK_COUNTER,
                    ModBlocks.WARPED_SINK_COUNTER
            ).build());

    public static final BlockEntityType<TelescopeBlockEntity> TELESCOPE_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "telescope_block_entity"),
            BlockEntityType.Builder.create(TelescopeBlockEntity::new, ModBlocks.TELESCOPE).build());

    public static void registerBlockEntities() {
        CozyHome.LOGGER.info("Registering block entities for " + CozyHome.MOD_ID);
    }
}
