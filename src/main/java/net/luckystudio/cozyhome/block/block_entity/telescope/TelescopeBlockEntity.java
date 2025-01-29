package net.luckystudio.cozyhome.block.block_entity.telescope;

import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TelescopeBlockEntity extends BlockEntity {
    private int zoomLevel; // Custom field

    public TelescopeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TELESCOPE_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
        this.zoomLevel = 0;  // Default zoom level
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
}
