package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.enums.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class ModProperties {

    public static final EnumProperty<HorizontalLinearConnectionBlock> HORIZONTAL_CONNECTION = EnumProperty.of("horizontal_connection", HorizontalLinearConnectionBlock.class);
    public static final EnumProperty<VerticalLinearConnectionBlock> VERTICAL_CONNECTION = EnumProperty.of("vertical_connection", VerticalLinearConnectionBlock.class);
    public static final EnumProperty<TripleTallBlock> TRIPLE_TALL_BLOCK = EnumProperty.of("part", TripleTallBlock.class);
    public static final EnumProperty<HasUnderBlock> HAS_UNDER = EnumProperty.of("has_under", HasUnderBlock.class);
    public static final EnumProperty<ContainsBlock> CONTAINS = EnumProperty.of("contains", ContainsBlock.class);
    public static final EnumProperty<OminousBlock> OMINOUS = EnumProperty.of("ominous", OminousBlock.class);

    public static final BooleanProperty NORTH_EAST = BooleanProperty.of("north_east");
    public static final BooleanProperty NORTH_WEST = BooleanProperty.of("north_west");
    public static final BooleanProperty SOUTH_EAST = BooleanProperty.of("south_east");
    public static final BooleanProperty SOUTH_WEST = BooleanProperty.of("south_west");
    public static final BooleanProperty TUCKED = BooleanProperty.of("tucked");

    public static final IntProperty FILLED_LEVEL_0_3 = IntProperty.of("level", 0, 3);
}
