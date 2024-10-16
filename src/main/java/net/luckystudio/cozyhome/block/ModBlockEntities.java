package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.entity.DyeVatBlockEntity;
import net.luckystudio.cozyhome.block.entity.DyeableBlockEntity;
import net.luckystudio.cozyhome.block.entity.StorageCounterBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<StorageCounterBlockEntity> STORAGE_COUNTER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "storage_counter_be"),
        BlockEntityType.Builder.create(StorageCounterBlockEntity::new, ModBlocks.OAK_STORAGE_COUNTER).build());

    public static final BlockEntityType<DyeableBlockEntity> COLOR_LAMP_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "color_lamp_block"),
            BlockEntityType.Builder.create(DyeableBlockEntity::new, ModBlocks.OAK_LAMP).build());

    public static final BlockEntityType<DyeVatBlockEntity> DYE_VAT_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "dye_vat"),
            BlockEntityType.Builder.create(DyeVatBlockEntity::new, ModBlocks.DYE_VAT).build());

    public static void registerBlockEntities() {
        CozyHome.LOGGER.info("Registering block entities for " + CozyHome.MOD_ID);
    }
}
