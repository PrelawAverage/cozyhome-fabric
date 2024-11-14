package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.entity.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
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

    public static final BlockEntityType<DyeableBlockEntity> DYEABLE_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "dyeable_block_entity"),
            BlockEntityType.Builder.create(DyeableBlockEntity::new,
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
                    ModBlocks.WARPED_LAMP
            ).build());

    public static final BlockEntityType<DyeVatBlockEntity> CLOCK_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "clock_block_entity"),
            BlockEntityType.Builder.create(DyeVatBlockEntity::new,
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
                    ModBlocks.WARPED_WALL_CLOCK
            ).build());

    public static final BlockEntityType<DyeVatBlockEntity> DYE_VAT_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "dye_vat_block_entity"),
            BlockEntityType.Builder.create(DyeVatBlockEntity::new, ModBlocks.DYE_VAT).build());

    public static final BlockEntityType<TelescopeBlockEntity> TELESCOPE_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "telescope_block_entity"),
            BlockEntityType.Builder.create(TelescopeBlockEntity::new, ModBlocks.TELESCOPE).build());

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
                    ModBlocks.PRINCESS_CHAIR,
                    ModBlocks.UNDEAD_CHAIR,
                    ModBlocks.TRIAL_CHAIR
            ).build());

    public static void registerBlockEntities() {
        CozyHome.LOGGER.info("Registering block entities for " + CozyHome.MOD_ID);
    }
}
