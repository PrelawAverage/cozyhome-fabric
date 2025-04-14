package net.luckystudio.cozyhome.block.custom.drawers;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.AdvancedHorizontalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class DeskBlock extends Block implements Waterloggable, ConnectingBlock {
    public static final MapCodec<DeskBlock> CODEC = createCodec(DeskBlock::new);

    public static final EnumProperty<AdvancedHorizontalLinearConnectionBlock> HORIZONTAL_CONNECTION = ModProperties.ADVANCED_HORIZONTAL_CONNECTION;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final VoxelShape DESK_TOP = Block.createCuboidShape(0, 12, 0, 16, 16, 16);
    public static final VoxelShape DESK_BODY = Block.createCuboidShape(1, 4, 1, 15, 12, 15);
    public static final VoxelShape NORTH_WEST_LEG_PIECE = Block.createCuboidShape(1, 0, 1, 4, 4, 4);
    public static final VoxelShape SOUTH_EAST_LEG_PIECE = Block.createCuboidShape(12, 0, 12, 15, 4, 15);
    public static final VoxelShape SOUTH_WEST_LEG_PIECE = Block.createCuboidShape(1, 0, 12, 4, 4, 15);
    public static final VoxelShape NORTH_EAST_LEG_PIECE = Block.createCuboidShape(12, 0, 1, 15, 4, 4);

    public DeskBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HORIZONTAL_CONNECTION, AdvancedHorizontalLinearConnectionBlock.SINGLE)
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
        AdvancedHorizontalLinearConnectionBlock horz = state.get(HORIZONTAL_CONNECTION);
        VoxelShape shape = VoxelShapes.union(DESK_TOP, DESK_BODY);

        // Add the inner cutout based on the direction
        shape = VoxelShapes.combineAndSimplify(shape, Block.createCuboidShape(
                x1(direction, horz),
                        0,
                        z1(direction, horz),
                        x2(direction, horz),
                        12,
                        z2(direction, horz)),
                BooleanBiFunction.ONLY_FIRST);

        // Adding legs and returning the shape.
        return switch (horz) {
            case SINGLE -> VoxelShapes.union(shape, NORTH_EAST_LEG_PIECE, NORTH_WEST_LEG_PIECE, SOUTH_EAST_LEG_PIECE, SOUTH_WEST_LEG_PIECE);
            case LEFT, LEFT_DIFF, LEFT_DIFF_LEFT -> switch (direction) {
                case NORTH -> VoxelShapes.union(shape, NORTH_WEST_LEG_PIECE, SOUTH_WEST_LEG_PIECE);
                case SOUTH -> VoxelShapes.union(shape, NORTH_EAST_LEG_PIECE, SOUTH_EAST_LEG_PIECE);
                case WEST -> VoxelShapes.union(shape, SOUTH_EAST_LEG_PIECE, SOUTH_WEST_LEG_PIECE);
                default -> VoxelShapes.union(shape, NORTH_EAST_LEG_PIECE, NORTH_WEST_LEG_PIECE);
            };
            case RIGHT, RIGHT_DIFF, RIGHT_DIFF_RIGHT -> switch (direction) {
                case NORTH -> VoxelShapes.union(shape, NORTH_EAST_LEG_PIECE, SOUTH_EAST_LEG_PIECE);
                case SOUTH -> VoxelShapes.union(shape, NORTH_WEST_LEG_PIECE, SOUTH_WEST_LEG_PIECE);
                case WEST -> VoxelShapes.union(shape, NORTH_WEST_LEG_PIECE, NORTH_EAST_LEG_PIECE);
                default -> VoxelShapes.union(shape, SOUTH_WEST_LEG_PIECE, SOUTH_EAST_LEG_PIECE);
            };
            case MIDDLE, MIDDLE_DIFF -> shape;
        };
    }

    private static int x1(Direction direction, AdvancedHorizontalLinearConnectionBlock horz) {
        return switch (direction) {
            case NORTH -> switch (horz) {
                case RIGHT, RIGHT_DIFF, RIGHT_DIFF_RIGHT, MIDDLE, MIDDLE_DIFF -> 0;
                default -> 4;
            };
            case SOUTH -> switch (horz) {
                case LEFT, LEFT_DIFF, LEFT_DIFF_LEFT, MIDDLE, MIDDLE_DIFF -> 0;
                default -> 4;
            };
            case WEST -> 4;
            default -> 0;
        };
    }

    private static int z1(Direction direction, AdvancedHorizontalLinearConnectionBlock horz) {
        return switch (direction) {
            case EAST -> switch (horz) {
                case RIGHT, RIGHT_DIFF, RIGHT_DIFF_RIGHT, MIDDLE, MIDDLE_DIFF -> 0;
                default -> 4;
            };
            case WEST -> switch (horz) {
                case LEFT, LEFT_DIFF, LEFT_DIFF_LEFT, MIDDLE, MIDDLE_DIFF -> 0;
                default -> 4;
            };
            case NORTH -> 4;
            default -> 0;
        };
    }

    private static int x2(Direction direction, AdvancedHorizontalLinearConnectionBlock horz) {
        return switch (direction) {
            case NORTH -> switch (horz) {
                case LEFT, LEFT_DIFF, LEFT_DIFF_LEFT, MIDDLE, MIDDLE_DIFF -> 16;
                default -> 12;
            };
            case SOUTH -> switch (horz) {
                case RIGHT, RIGHT_DIFF, RIGHT_DIFF_RIGHT, MIDDLE, MIDDLE_DIFF -> 16;
                default -> 12;
            };
            case EAST -> 12;
            default -> 16;
        };
    }

    private static int z2(Direction direction, AdvancedHorizontalLinearConnectionBlock horz) {
        return switch (direction) {
            case EAST -> switch (horz) {
                case LEFT, LEFT_DIFF, LEFT_DIFF_LEFT, MIDDLE, MIDDLE_DIFF -> 16;
                default -> 12;
            };
            case WEST -> switch (horz) {
                case RIGHT, RIGHT_DIFF, RIGHT_DIFF_RIGHT, MIDDLE, MIDDLE_DIFF -> 16;
                default -> 12;
            };
            case SOUTH -> 12;
            default -> 16;
        };
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        BlockState defaultState = this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing()) // Face the player by default
                .with(WATERLOGGED, bl);
        return defaultState;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return state.with(HORIZONTAL_CONNECTION, AdvancedHorizontalLinearConnectionBlock.updateAdvancedHorizontalConnections(state, world, pos));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() instanceof DrawerBlock || targetState.getBlock() instanceof DeskBlock;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}