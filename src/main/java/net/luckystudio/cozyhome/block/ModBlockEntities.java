package net.luckystudio.cozyhome.block;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.test.ChairBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
//    public static final BlockEntityType<ChairBlockEntity> CHAIR_BLOCK_ENTITY_BLOCK_ENTITY =
//            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "chair_block_be"),
//                    FabricBlockEntityTypeBuilder.create(ChairBlockEntity::new, ModBlocks.OAK_CHAIR).build());
public static final BlockEntityType<ChairBlockEntity> CHAIR_BLOCK_ENTITY_BLOCK_ENTITY =
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CozyHome.MOD_ID, "chair_block_be"),
                BlockEntityType.Builder.create(ChairBlockEntity::new, ModBlocks.OAK_CHAIR).build());
    public static void registerBlockEntities() {
        CozyHome.LOGGER.info("Registering Block Entities");
    }
}
