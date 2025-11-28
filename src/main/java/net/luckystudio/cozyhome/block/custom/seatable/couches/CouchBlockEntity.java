package net.luckystudio.cozyhome.block.custom.seatable.couches;

import net.luckystudio.cozyhome.block.custom.seatable.SeatWithCushionBlockEntity;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CouchBlockEntity extends SeatWithCushionBlockEntity {

    public CouchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.COUCH_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
    }

    @Override
    public @Nullable Object getRenderData() {
        return (this.getComponents().contains(DataComponentTypes.DYED_COLOR)) ? this.getComponents().get(DataComponentTypes.DYED_COLOR).rgb() : -17170434;
    }
}
