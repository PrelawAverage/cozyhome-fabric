package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.CoveredBlock;
import net.luckystudio.cozyhome.block.util.enums.HasUnderBlock;
import net.luckystudio.cozyhome.block.util.enums.LinearConnectionBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class ModProperties {
    public static final EnumProperty<LinearConnectionBlock> LINEAR_CONNECTION = EnumProperty.of("linear_connection", LinearConnectionBlock.class);
    public static final EnumProperty<CoveredBlock> COVERED = EnumProperty.of("cover", CoveredBlock.class);
    public static final EnumProperty<HasUnderBlock> HAS_UNDER = EnumProperty.of("has_under", HasUnderBlock.class);
    public static final BooleanProperty TUCKED = BooleanProperty.of("tucked");
    public static final IntProperty OMNI_ROTATION = IntProperty.of("omni_rotation", 0, 3);
    public static final IntProperty FILLED_LEVEL_0_3 = IntProperty.of("level", 0, 3);
    public static final IntProperty FILLED_LEVEL_0_5 = IntProperty.of("level", 0, 5);
    public static final BooleanProperty NORTH_EAST = BooleanProperty.of("north_east");
    public static final BooleanProperty NORTH_WEST = BooleanProperty.of("north_west");
    public static final BooleanProperty SOUTH_EAST = BooleanProperty.of("south_east");
    public static final BooleanProperty SOUTH_WEST = BooleanProperty.of("south_west");
    public static final EnumProperty<ContainsBlock> CONTAINS = EnumProperty.of("contains", ContainsBlock.class);

    public static int getOmniRotation(int rotation) {
        if (rotation == 0 || rotation == 4 || rotation == 8 || rotation == 12) return 0;
        if (rotation == 1 || rotation == 5 || rotation == 9 || rotation == 13) return 1;
        if (rotation == 2 || rotation == 6 || rotation == 10 || rotation == 14) return 2;
        if (rotation == 3 || rotation == 7 || rotation == 11 || rotation == 15) return 3;
        return 0;
    }
}
