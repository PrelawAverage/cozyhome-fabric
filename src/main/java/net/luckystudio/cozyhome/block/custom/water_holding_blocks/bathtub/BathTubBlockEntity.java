package net.luckystudio.cozyhome.block.custom.water_holding_blocks.bathtub;

import net.luckystudio.cozyhome.block.custom.water_holding_blocks.AbstractWaterHoldingBlockEntity;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BathTubBlockEntity extends AbstractWaterHoldingBlockEntity {

    public BathTubBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BATHTUB_BLOCK_ENTITY, pos, state);
    }
}
