package net.luckystudio.cozyhome.block.custom;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.HorizontalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class FireplaceBlock extends Block implements ConnectingBlock {
    public static final EnumProperty<HorizontalLinearConnectionBlock> HORIZONTAL_LINEAR_CONNECTION = ModProperties.HORIZONTAL_CONNECTION;
    public static final EnumProperty<VerticalLinearConnectionBlock> VERTICAL_LINEAR_CONNECTION = ModProperties.VERTICAL_CONNECTION;
    public static final DirectionProperty FACING = Properties.FACING;

    public FireplaceBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HORIZONTAL_LINEAR_CONNECTION, HorizontalLinearConnectionBlock.SINGLE)
                .with(VERTICAL_LINEAR_CONNECTION, VerticalLinearConnectionBlock.SINGLE)
                .with(FACING, Direction.NORTH)
        );
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() == this;
    }
}
