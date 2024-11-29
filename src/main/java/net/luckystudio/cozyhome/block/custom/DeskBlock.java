package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.HorizontalLinearConnectionBlock;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class DeskBlock extends Block implements Waterloggable {
    public static final MapCodec<DeskBlock> CODEC = createCodec(DeskBlock::new);

    public static final EnumProperty<HorizontalLinearConnectionBlock> HORIZONTAL_CONNECTION = ModProperties.HORIZONTAL_CONNECTION;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final VoxelShape DESK_TOP_PART = Block.createCuboidShape(0, 12, 0, 16, 16, 16);

    public static final VoxelShape DESK_NORTH_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(1, 4, 4, 4, 12, 12),
            Block.createCuboidShape(12, 4, 4, 15, 12, 12),
            Block.createCuboidShape(4, 4, 1, 12, 12, 4));
    public static final VoxelShape DESK_EAST_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(4, 4, 1, 12, 12, 4),
            Block.createCuboidShape(4, 4, 12, 12, 12, 15),
            Block.createCuboidShape(12, 4, 4, 15, 12, 12));
    public static final VoxelShape DESK_SOUTH_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(12, 4, 4, 15, 12, 12),
            Block.createCuboidShape(1, 4, 4, 4, 12, 12),
            Block.createCuboidShape(4, 4, 12, 12, 12, 15));
    public static final VoxelShape DESK_WEST_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(4, 4, 12, 12, 12, 15),
            Block.createCuboidShape(4, 4, 1, 12, 12, 4),
            Block.createCuboidShape(1, 4, 4, 4, 12, 12));

    public static final VoxelShape DESK_NORTH_LEFT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(1, 4, 4, 4, 12, 12),
            Block.createCuboidShape(4, 4, 1, 16, 12, 4));
    public static final VoxelShape DESK_EAST_LEFT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(4, 4, 1, 12, 12, 4),
            Block.createCuboidShape(12, 4, 4, 15, 12, 16));
    public static final VoxelShape DESK_SOUTH_LEFT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(12, 4, 4, 15, 12, 12),
            Block.createCuboidShape(0, 4, 12, 12, 12, 15));
    public static final VoxelShape DESK_WEST_LEFT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(4, 4, 12, 12, 12, 15),
            Block.createCuboidShape(1, 4, 0, 4, 12, 12));

    public static final VoxelShape DESK_NORTH_RIGHT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(12, 4, 4, 15, 12, 12),
            Block.createCuboidShape(0, 4, 1, 12, 12, 4));
    public static final VoxelShape DESK_EAST_RIGHT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(12, 0, 12, 15, 12, 15),
            Block.createCuboidShape(4, 4, 12, 12, 12, 15),
            Block.createCuboidShape(12, 4, 0, 15, 12, 12));
    public static final VoxelShape DESK_SOUTH_RIGHT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(1, 0, 12, 4, 12, 15),
            Block.createCuboidShape(1, 4, 4, 4, 12, 12),
            Block.createCuboidShape(4, 4, 12, 16, 12, 15));
    public static final VoxelShape DESK_WEST_RIGHT_SHAPE = VoxelShapes.union(DESK_TOP_PART,
            Block.createCuboidShape(12, 0, 1, 15, 12, 4),
            Block.createCuboidShape(1, 0, 1, 4, 12, 4),
            Block.createCuboidShape(4, 4, 1, 12, 12, 4),
            Block.createCuboidShape(1, 4, 4, 4, 12, 16));

    public static final VoxelShape DESK_NORTH_MIDDLE_SHAPE = VoxelShapes.union(DESK_TOP_PART, Block.createCuboidShape(0, 4, 1, 16, 12, 4));
    public static final VoxelShape DESK_EAST_MIDDLE_SHAPE = VoxelShapes.union(DESK_TOP_PART, Block.createCuboidShape(12, 4, 0, 15, 12, 16));
    public static final VoxelShape DESK_SOUTH_MIDDLE_SHAPE = VoxelShapes.union(DESK_TOP_PART, Block.createCuboidShape(0, 4, 12, 16, 12, 15));
    public static final VoxelShape DESK_WEST_MIDDLE_SHAPE = VoxelShapes.union(DESK_TOP_PART, Block.createCuboidShape(1, 4, 0, 4, 12, 16));

    public DeskBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HORIZONTAL_CONNECTION, HorizontalLinearConnectionBlock.SINGLE)
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_CONNECTION, FACING, WATERLOGGED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);
        HorizontalLinearConnectionBlock horz = state.get(HORIZONTAL_CONNECTION);
        return switch (direction) {
            case NORTH -> switch (horz) {
                case LEFT -> DESK_NORTH_LEFT_SHAPE;
                case MIDDLE -> DESK_NORTH_MIDDLE_SHAPE;
                case RIGHT -> DESK_NORTH_RIGHT_SHAPE;
                default -> DESK_NORTH_SHAPE;
            };
            case EAST -> switch (horz) {
                case LEFT -> DESK_EAST_LEFT_SHAPE;
                case MIDDLE -> DESK_EAST_MIDDLE_SHAPE;
                case RIGHT -> DESK_EAST_RIGHT_SHAPE;
                default -> DESK_EAST_SHAPE;
            };
            case SOUTH -> switch (horz) {
                case LEFT -> DESK_SOUTH_LEFT_SHAPE;
                case MIDDLE -> DESK_SOUTH_MIDDLE_SHAPE;
                case RIGHT -> DESK_SOUTH_RIGHT_SHAPE;
                default -> DESK_SOUTH_SHAPE;
            };
            case WEST -> switch (horz) {
                case LEFT -> DESK_WEST_LEFT_SHAPE;
                case MIDDLE -> DESK_WEST_MIDDLE_SHAPE;
                case RIGHT -> DESK_WEST_RIGHT_SHAPE;
                default -> DESK_WEST_SHAPE;
            };
            default -> throw new IllegalStateException("Unexpected value: " + FACING + " from " + CozyHome.MOD_ID);
        };
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {

        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        // Start with the block's default state
        BlockState defaultState = this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing()) // Face the player by default
                .with(WATERLOGGED, bl);

        // Update connections based on neighboring blocks
        return updateConnections(defaultState, ctx.getWorld(), ctx.getBlockPos());
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction.getAxis().isHorizontal()) {
            return updateConnections(state, world, pos);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private BlockState updateConnections(BlockState state, WorldAccess world, BlockPos pos) {
        Direction facing = state.get(FACING);
        // Determine left and right directions based on the block's facing
        Direction left = facing.rotateYClockwise();
        Direction right = facing.rotateYCounterclockwise();

        BlockState stateLeft = world.getBlockState(pos.offset(left));
        BlockState stateRight = world.getBlockState(pos.offset(right));

        if (state.get(HORIZONTAL_CONNECTION) == HorizontalLinearConnectionBlock.LEFT) {
            if (sameBlock(stateLeft) && isMiddle(stateLeft)) {
                    return state;
            }
        } else if (state.get(HORIZONTAL_CONNECTION) == HorizontalLinearConnectionBlock.MIDDLE) {
            if (sameBlock(stateLeft) && sameBlock(stateRight)) {
                if (isLeft(stateLeft) && isRight(stateRight)) {
                    return state;
                }
            }
        } else if (state.get(HORIZONTAL_CONNECTION) == HorizontalLinearConnectionBlock.RIGHT) {
            if (sameBlock(stateRight) && isMiddle(stateRight)) {
                return state;
            }
        }
        return setConnections(state, world, pos, left, right);
    }

    private BlockState setConnections(BlockState state, WorldAccess world, BlockPos pos, Direction left, Direction right) {
        boolean canConnectLeft = canConnect(state, world, pos, left);
        boolean canConnectRight = canConnect(state, world, pos, right);

        BlockState stateLeft = world.getBlockState(pos.offset(left));
        BlockState stateRight = world.getBlockState(pos.offset(right));

        HorizontalLinearConnectionBlock connection = HorizontalLinearConnectionBlock.SINGLE;

        if (canConnectLeft && canConnectRight) {
            if (stateLeft.get(HORIZONTAL_CONNECTION) == HorizontalLinearConnectionBlock.MIDDLE) {
                connection = HorizontalLinearConnectionBlock.LEFT;
            } else if (stateRight.get(HORIZONTAL_CONNECTION) == HorizontalLinearConnectionBlock.MIDDLE) {
                connection = HorizontalLinearConnectionBlock.RIGHT;
            } else {
                connection = HorizontalLinearConnectionBlock.MIDDLE;
            }
        } else if (canConnectLeft) {
            connection = HorizontalLinearConnectionBlock.LEFT;
        } else if (canConnectRight) {
            connection = HorizontalLinearConnectionBlock.RIGHT;
        }
        return state.with(HORIZONTAL_CONNECTION, connection);
    }

    private boolean canConnect(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
        BlockState neighborState = world.getBlockState(pos.offset(direction));
        BlockState neighborState2 = world.getBlockState(pos.offset(direction,2));
        // Test if the block next to it is already connected to a block
        if (sameBlock(neighborState2) && neighborState2.get(FACING) == state.get(FACING) && isMiddle(neighborState2)) return false;
        return sameBlock(neighborState) && neighborState.get(FACING) == state.get(FACING);
    }

    private boolean sameBlock(BlockState state) {
        return state.getBlock() instanceof DrawerBlock || state.getBlock() instanceof DeskBlock;
    }

    private boolean isLeft(BlockState state) {
        HorizontalLinearConnectionBlock connectionBlock = state.get(HORIZONTAL_CONNECTION);
        return connectionBlock == HorizontalLinearConnectionBlock.LEFT || connectionBlock == HorizontalLinearConnectionBlock.LEFT_DIFF;
    }

    private boolean isRight(BlockState state) {
        HorizontalLinearConnectionBlock connectionBlock = state.get(HORIZONTAL_CONNECTION);
        return connectionBlock == HorizontalLinearConnectionBlock.RIGHT || connectionBlock == HorizontalLinearConnectionBlock.RIGHT_DIFF;
    }

    private boolean isMiddle(BlockState state) {
        HorizontalLinearConnectionBlock connectionBlock = state.get(HORIZONTAL_CONNECTION);
        return connectionBlock == HorizontalLinearConnectionBlock.MIDDLE ||
                connectionBlock == HorizontalLinearConnectionBlock.MIDDLE_DIFF ||
                connectionBlock == HorizontalLinearConnectionBlock.LEFT_DIFF_LEFT ||
                connectionBlock == HorizontalLinearConnectionBlock.RIGHT_DIFF_RIGHT;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}