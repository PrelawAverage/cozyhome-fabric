package net.luckystudio.cozyhome.block.custom.seatable.sofas;

import net.luckystudio.cozyhome.block.custom.seatable.SeatWithCushionBlockEntity;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class SofaBlockEntity extends SeatWithCushionBlockEntity {

    public SofaBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.SOFA_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
    }
}
