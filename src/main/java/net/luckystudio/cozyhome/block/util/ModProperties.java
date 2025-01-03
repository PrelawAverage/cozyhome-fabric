package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.enums.*;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ModProperties {

    public static final EnumProperty<AdvancedHorizontalLinearConnectionBlock> ADVANCED_HORIZONTAL_CONNECTION = EnumProperty.of("advanced_horizontal_connection", AdvancedHorizontalLinearConnectionBlock.class);
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

    public static void sitDown(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            // Creates a new entity
            SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
            // Sets it's location
            seat.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);

            seat.setYaw(seatBlock.getSeatRotation(state, world, pos));
            seat.setAngles(seatBlock.getSeatRotation(state, world, pos), 0);

            world.spawnEntity(seat);

            player.startRiding(seat);
        }
    }

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

    public static VerticalLinearConnectionBlock setVerticalConnection(BlockState state, WorldAccess world, BlockPos pos) {
        if (state.getBlock() instanceof ConnectingBlock connectingBlock) {
            boolean isMatchingBlockAbove = connectingBlock.isMatchingBlock(world.getBlockState(pos.up()));
            boolean isMatchingBlockBelow = connectingBlock.isMatchingBlock(world.getBlockState(pos.down()));

            if (isMatchingBlockAbove && isMatchingBlockBelow) return VerticalLinearConnectionBlock.MIDDLE;
            if (isMatchingBlockAbove) return VerticalLinearConnectionBlock.TAIL;
            if (isMatchingBlockBelow) return VerticalLinearConnectionBlock.HEAD;
        }
        return VerticalLinearConnectionBlock.SINGLE;
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

    public static HorizontalLinearConnectionBlock setHorizontalConnection(BlockState state, WorldAccess world, BlockPos pos) {
        Direction facing = state.get(HorizontalFacingBlock.FACING);

        Direction left = facing.rotateYClockwise();
        Direction right = facing.rotateYCounterclockwise();

        BlockState stateLeft = world.getBlockState(pos.offset(left));
        BlockState stateRight = world.getBlockState(pos.offset(right));

        if (state.getBlock() instanceof ConnectingBlock connectingBlock) {
            if (connectingBlock.isMatchingBlock(stateLeft) && connectingBlock.isMatchingBlock(stateRight)) {
                return HorizontalLinearConnectionBlock.MIDDLE;
            } else if (connectingBlock.isMatchingBlock(stateLeft)) {
                return HorizontalLinearConnectionBlock.LEFT;
            } else if (connectingBlock.isMatchingBlock(stateRight)) {
                return HorizontalLinearConnectionBlock.RIGHT;
            }
        }
        return HorizontalLinearConnectionBlock.SINGLE;
    }

    public static AdvancedHorizontalLinearConnectionBlock updateAdvancedHorizontalConnections(BlockState state, WorldAccess world, BlockPos pos) {
        Direction facing = state.get(HorizontalFacingBlock.FACING);
        // Determine left and right directions based on the block's facing
        Direction left = facing.rotateYClockwise();
        Direction right = facing.rotateYCounterclockwise();

        BlockState stateLeft = world.getBlockState(pos.offset(left));
        BlockState stateRight = world.getBlockState(pos.offset(right));

        if (state.getBlock() instanceof ConnectingBlock connectingBlock) {
            // Check existing connection validity
            if (isLeft(state)) {
                if (connectingBlock.isMatchingBlock(stateLeft) && isMiddle(stateLeft)) {
                    return state.get(ModProperties.ADVANCED_HORIZONTAL_CONNECTION);
                }
            } else if (isMiddle(state)) {
                if (connectingBlock.isMatchingBlock(stateLeft) && connectingBlock.isMatchingBlock(stateRight)) {
                    if (isLeft(stateLeft) && isRight(stateRight)) {
                        return state.get(ModProperties.ADVANCED_HORIZONTAL_CONNECTION);
                    }
                }
            } else if (isRight(state)) {
                if (connectingBlock.isMatchingBlock(stateRight) && isMiddle(stateRight)) {
                    return state.get(ModProperties.ADVANCED_HORIZONTAL_CONNECTION);
                }
            }
        }
        // Determine new connection
        return setConnections(state, world, pos, left, right);
    }

    private static AdvancedHorizontalLinearConnectionBlock setConnections(BlockState state, WorldAccess world, BlockPos pos, Direction left, Direction right) {
        boolean canConnectLeft = canConnect(state, world, pos, left);
        boolean canConnectRight = canConnect(state, world, pos, right);
        BlockState stateLeft = world.getBlockState(pos.offset(left));
        BlockState stateRight = world.getBlockState(pos.offset(right));
        if (canConnectLeft && canConnectRight) {
            if (isMiddle(stateLeft)) {
                return stateLeft.isOf(state.getBlock()) ? AdvancedHorizontalLinearConnectionBlock.LEFT : AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF;
            } else if (isMiddle(stateRight)) {
                return stateRight.isOf(state.getBlock()) ? AdvancedHorizontalLinearConnectionBlock.RIGHT : AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF;
            } else if (stateLeft.isOf(state.getBlock()) && stateRight.isOf(state.getBlock())) {
                return AdvancedHorizontalLinearConnectionBlock.MIDDLE;
            } else if (stateLeft.isOf(state.getBlock())) {
                return AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT;
            } else if (stateRight.isOf(state.getBlock())) {
                return AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT;
            } else {
                return AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF;
            }
        } else if (canConnectLeft) {
            return stateLeft.isOf(state.getBlock()) ? AdvancedHorizontalLinearConnectionBlock.LEFT : AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF;
        } else if (canConnectRight) {
            return stateRight.isOf(state.getBlock()) ? AdvancedHorizontalLinearConnectionBlock.RIGHT : AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF;
        }
        return AdvancedHorizontalLinearConnectionBlock.SINGLE;
    }

    private static boolean canConnect(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
        BlockState neighborState = world.getBlockState(pos.offset(direction));
        BlockState neighborState2 = world.getBlockState(pos.offset(direction,2));
        // Test if the block next to it is already connected to a block
        if (state.getBlock() instanceof ConnectingBlock connectingBlock) {
            if (connectingBlock.isMatchingBlock(neighborState2) && neighborState2.get(HorizontalFacingBlock.FACING) == state.get(HorizontalFacingBlock.FACING) && isMiddle(neighborState2)) return false;
            return connectingBlock.isMatchingBlock(neighborState) && neighborState.get(HorizontalFacingBlock.FACING) == state.get(HorizontalFacingBlock.FACING);
        }
        return false;
    }

    private static boolean isLeft(BlockState targetState) {
        AdvancedHorizontalLinearConnectionBlock connectionBlock = targetState.get(ModProperties.ADVANCED_HORIZONTAL_CONNECTION);
        return connectionBlock == AdvancedHorizontalLinearConnectionBlock.LEFT || connectionBlock == AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF;
    }

    private static boolean isRight(BlockState targetState) {
        AdvancedHorizontalLinearConnectionBlock connectionBlock = targetState.get(ModProperties.ADVANCED_HORIZONTAL_CONNECTION);
        return connectionBlock == AdvancedHorizontalLinearConnectionBlock.RIGHT || connectionBlock == AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF;
    }

    private static boolean isMiddle(BlockState targetState) {
        AdvancedHorizontalLinearConnectionBlock connectionBlock = targetState.get(ModProperties.ADVANCED_HORIZONTAL_CONNECTION);
        return connectionBlock == AdvancedHorizontalLinearConnectionBlock.MIDDLE ||
                connectionBlock == AdvancedHorizontalLinearConnectionBlock.MIDDLE_DIFF ||
                connectionBlock == AdvancedHorizontalLinearConnectionBlock.LEFT_DIFF_LEFT ||
                connectionBlock == AdvancedHorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT;
    }
}
