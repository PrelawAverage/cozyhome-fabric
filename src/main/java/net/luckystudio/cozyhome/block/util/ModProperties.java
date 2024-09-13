package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.blockstates.LinearConnectionBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public class ModProperties {
    public static final EnumProperty<LinearConnectionBlock> LINEAR_CONNECTION_BLOCK = EnumProperty.of("linear_connection", LinearConnectionBlock.class);
    public static final BooleanProperty TUCKED = BooleanProperty.of("tucked");
}
