package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.CozyHome;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static net.luckystudio.cozyhome.block.util.ModProperties.updateAdvancedHorizontalConnections;

public class DeskBlock extends Block implements Waterloggable, ConnectingBlock {
    public static final MapCodec<DeskBlock> CODEC = createCodec(DeskBlock::new);

    public static final EnumProperty<AdvancedHorizontalLinearConnectionBlock> HORIZONTAL_CONNECTION = ModProperties.ADVANCED_HORIZONTAL_CONNECTION;
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
        return state.with(HORIZONTAL_CONNECTION, updateAdvancedHorizontalConnections(state, world, pos));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() instanceof DrawerBlock || targetState.getBlock() instanceof DeskBlock;
    }
}