package net.luckystudio.cozyhome.block.entity;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<StorageCounterBlockEntity> STORAGE_COUNTER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "storage_counter_be"),
        BlockEntityType.Builder.create(StorageCounterBlockEntity::new, ModBlocks.OAK_STORAGE_COUNTER).build());

    public static void registerBlockEntities() {
        CozyHome.LOGGER.info("Registering block entities for " + CozyHome.MOD_ID);
    }
}
