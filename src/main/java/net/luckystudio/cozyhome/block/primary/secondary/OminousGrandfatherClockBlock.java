package net.luckystudio.cozyhome.block.primary.secondary;

import net.luckystudio.cozyhome.block.primary.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;

public class OminousGrandfatherClockBlock extends GrandfatherClockBlock {
    public static final BooleanProperty DETECTED_PLAYER = ModProperties.DETECTED_PLAYER;
    public static final BooleanProperty OMINOUS = Properties.OMINOUS;

    public OminousGrandfatherClockBlock(GrandfatherClockType grandfatherClockType, Settings settings) {
        super(grandfatherClockType, settings);
        this.stateManager.getDefaultState()
                .with(DETECTED_PLAYER, false)
                .with(OMINOUS, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DETECTED_PLAYER, OMINOUS);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(DETECTED_PLAYER, false)
                .with(OMINOUS, false);
    }
}
