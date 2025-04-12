package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.enums.*;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.enums.StairShape;
import net.minecraft.state.property.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class ModProperties {

    public static final EnumProperty<AdvancedHorizontalLinearConnectionBlock> ADVANCED_HORIZONTAL_CONNECTION = EnumProperty.of("advanced_horizontal_connection", AdvancedHorizontalLinearConnectionBlock.class);
    public static final EnumProperty<HorizontalLinearConnectionBlock> HORIZONTAL_CONNECTION = EnumProperty.of("horizontal_connection", HorizontalLinearConnectionBlock.class);
    public static final EnumProperty<VerticalLinearConnectionBlock> VERTICAL_CONNECTION = EnumProperty.of("vertical_connection", VerticalLinearConnectionBlock.class);
    public static final EnumProperty<VerticalWithExtraConnectionBlock> VERTICAL_WITH_EXTRA_CONNECTION = EnumProperty.of("vertical_with_extra_connection", VerticalWithExtraConnectionBlock.class);
    public static final EnumProperty<TripleTallBlock> TRIPLE_TALL_BLOCK = EnumProperty.of("part", TripleTallBlock.class);
    public static final EnumProperty<HasUnderBlock> HAS_UNDER = EnumProperty.of("has_under", HasUnderBlock.class);
    public static final EnumProperty<ContainsBlock> CONTAINS = EnumProperty.of("contains", ContainsBlock.class);

    public static final BooleanProperty NORTH_EAST = BooleanProperty.of("north_east");
    public static final BooleanProperty NORTH_WEST = BooleanProperty.of("north_west");
    public static final BooleanProperty SOUTH_EAST = BooleanProperty.of("south_east");
    public static final BooleanProperty SOUTH_WEST = BooleanProperty.of("south_west");
    public static final BooleanProperty TUCKED = BooleanProperty.of("tucked");

    public static final IntProperty FILLED_LEVEL_0_2 = IntProperty.of("level", 0, 2);
    public static final IntProperty FILLED_LEVEL_0_3 = IntProperty.of("level", 0, 3);
    public static final IntProperty NEXT_LEVEL_TIMER = IntProperty.of("next_level", 0, 20);
    public static final EnumProperty<DoubleLongPart> DOUBLE_LONG_PART = EnumProperty.of("part", DoubleLongPart.class);

    public static float setSeatRotationFromFacing(BlockState state) {
        Direction facing = state.get(HorizontalFacingBlock.FACING);
        return switch (facing) {
            case NORTH -> 180;
            case EAST -> 270;
            case WEST -> 90;
            default -> 0;
        };
    }

    public static float setSeatRotationFromShape(BlockState state) {
        StairShape stairShape = state.get(Properties.STAIR_SHAPE);
        Direction facing = state.get(HorizontalFacingBlock.FACING);
        return switch (stairShape) {
            case INNER_LEFT, OUTER_LEFT -> switch (facing) {
                case NORTH -> 135;
                case EAST -> 225;
                case WEST -> 45;
                default -> 315;
            };
            case INNER_RIGHT, OUTER_RIGHT -> switch (facing) {
                case NORTH -> 225;
                case EAST -> 315;
                case WEST -> 135;
                default -> 45;
            };
            default -> setSeatRotationFromFacing(state);
        };
    }

    public static float setSeatRotationFromRotation(BlockState state) {
        int rotation = state.get(Properties.ROTATION);
        return RotationPropertyHelper.toDegrees(rotation) + 180;
    }

    public static StairShape setStairShapeNoFlip(BlockState state, BlockView world, BlockPos pos) {
        Direction direction = state.get(Properties.HORIZONTAL_FACING);
        BlockState blockState = world.getBlockState(pos.offset(direction));
        if (state.getBlock() instanceof ConnectingBlock connectingBlock)
        {
            if (connectingBlock.isMatchingBlock(blockState)) {
                Direction direction2 = blockState.get(Properties.HORIZONTAL_FACING);
                if (direction2.getAxis() != state.get(Properties.HORIZONTAL_FACING).getAxis()) {
                    if (direction2 == direction.rotateYCounterclockwise()) {
                        return StairShape.OUTER_LEFT;
                    }

                    return StairShape.OUTER_RIGHT;
                }
            }
            BlockState blockState2 = world.getBlockState(pos.offset(direction.getOpposite()));
            if (connectingBlock.isMatchingBlock(blockState2)) {
                Direction direction3 = blockState2.get(Properties.HORIZONTAL_FACING);
                if (direction3.getAxis() != state.get(Properties.HORIZONTAL_FACING).getAxis()) {
                    if (direction3 == direction.rotateYCounterclockwise()) {
                        return StairShape.INNER_LEFT;
                    }
                    return StairShape.INNER_RIGHT;
                }
            }
        }
        return StairShape.STRAIGHT;
    }
}
