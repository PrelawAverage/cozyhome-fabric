package net.luckystudio.cozyhome.block.test;

import net.luckystudio.cozyhome.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ChairBlockEntity extends BlockEntity {

    public ChairBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHAIR_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
    }
}
