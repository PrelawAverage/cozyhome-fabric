package net.luckystudio.cozyhome.block.custom.water_holding_blocks.sink;

import net.luckystudio.cozyhome.block.custom.water_holding_blocks.AbstractWaterHoldingBlockEntity;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class SinkBlockEntity extends AbstractWaterHoldingBlockEntity {
    public SinkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.SINK_BLOCK_ENTITY, pos, state);
    }
}
