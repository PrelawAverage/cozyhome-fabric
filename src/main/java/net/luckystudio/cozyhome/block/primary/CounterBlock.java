package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.enums.StairShape;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;


public class CounterBlock extends Block {
    public static final MapCodec<CounterBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState), createSettingsCodec())
                    .apply(instance, CounterBlock::new)
    );
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<StairShape> SHAPE = Properties.STAIR_SHAPE;

    // Setting the pieces of the block
    public static final VoxelShape COUNTER_TOP = Block.createCuboidShape(0, 12, 0, 16, 16, 16);
    public static final VoxelShape NORTH_EAST_INNER = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 14, 12, 16));
    public static final VoxelShape NORTH_WEST_INNER = VoxelShapes.union(Block.createCuboidShape(2, 0, 0, 16, 12, 16));
    public static final VoxelShape SOUTH_EAST_INNER = VoxelShapes.union(Block.createCuboidShape(2, 0, 0, 16, 12, 16));
    public static final VoxelShape SOUTH_WEST_INNER = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 14, 12, 16));
    public static final VoxelShape NORTH_EAST_OUTER = VoxelShapes.union(Block.createCuboidShape(2, 0, 0, 16, 12, 14));
    public static final VoxelShape NORTH_WEST_OUTER = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 14, 12, 14));
    public static final VoxelShape SOUTH_EAST_OUTER = VoxelShapes.union(Block.createCuboidShape(2, 0, 2, 16, 12, 16));
    public static final VoxelShape SOUTH_WEST_OUTER = VoxelShapes.union(Block.createCuboidShape(0, 0, 2, 14, 12, 16));

    // Final Shapes
    public static final VoxelShape NORTH_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 0, 16, 12, 14));
    public static final VoxelShape NORTH_INNER_LEFT = VoxelShapes.union(COUNTER_TOP, NORTH_EAST_INNER);
    public static final VoxelShape NORTH_INNER_RIGHT = VoxelShapes.union(COUNTER_TOP, NORTH_WEST_INNER);
    public static final VoxelShape NORTH_OUTER_LEFT = VoxelShapes.union(COUNTER_TOP, NORTH_WEST_OUTER);
    public static final VoxelShape NORTH_OUTER_RIGHT = VoxelShapes.union(COUNTER_TOP, NORTH_EAST_OUTER);
    public static final VoxelShape EAST_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(2, 0, 0, 16, 12, 16));
    public static final VoxelShape EAST_INNER_LEFT = VoxelShapes.union(COUNTER_TOP, NORTH_WEST_INNER);
    public static final VoxelShape EAST_INNER_RIGHT = VoxelShapes.union(COUNTER_TOP, SOUTH_EAST_INNER);
    public static final VoxelShape EAST_OUTER_LEFT = VoxelShapes.union(COUNTER_TOP, NORTH_EAST_OUTER);
    public static final VoxelShape EAST_OUTER_RIGHT = VoxelShapes.union(COUNTER_TOP, SOUTH_EAST_OUTER);
    public static final VoxelShape SOUTH_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 2, 16, 12, 16));
    public static final VoxelShape SOUTH_INNER_LEFT = VoxelShapes.union(COUNTER_TOP, SOUTH_EAST_INNER);
    public static final VoxelShape SOUTH_INNER_RIGHT = VoxelShapes.union(COUNTER_TOP, SOUTH_WEST_INNER);
    public static final VoxelShape SOUTH_OUTER_LEFT = VoxelShapes.union(COUNTER_TOP, SOUTH_EAST_OUTER);
    public static final VoxelShape SOUTH_OUTER_RIGHT = VoxelShapes.union(COUNTER_TOP, SOUTH_WEST_OUTER);
    public static final VoxelShape WEST_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 0, 14, 12, 16));
    public static final VoxelShape WEST_INNER_LEFT = VoxelShapes.union(COUNTER_TOP, SOUTH_WEST_INNER);
    public static final VoxelShape WEST_INNER_RIGHT = VoxelShapes.union(COUNTER_TOP, NORTH_EAST_INNER);
    public static final VoxelShape WEST_OUTER_LEFT = VoxelShapes.union(COUNTER_TOP, SOUTH_WEST_OUTER);
    public static final VoxelShape WEST_OUTER_RIGHT = VoxelShapes.union(COUNTER_TOP, NORTH_WEST_OUTER);
    protected final BlockState baseBlockState;

    public CounterBlock(BlockState baseBlockState, Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager
                .getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(SHAPE, StairShape.STRAIGHT));
        this.baseBlockState = baseBlockState;
    }

    @Override
    public MapCodec<? extends CounterBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return super.getOpacity(state, world, pos);
    }

    @Override
    protected boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return super.getAmbientOcclusionLightLevel(state, world, pos);
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);
        StairShape shape = state.get(SHAPE);
        switch (direction) {
            case NORTH:
                return switch (shape) {
                    case STRAIGHT -> NORTH_STRAIGHT;
                    case INNER_LEFT -> NORTH_INNER_LEFT;
                    case INNER_RIGHT -> NORTH_INNER_RIGHT;
                    case OUTER_LEFT -> NORTH_OUTER_LEFT;
                    case OUTER_RIGHT -> NORTH_OUTER_RIGHT;
                };
            case EAST:
                return switch (shape) {
                    case STRAIGHT -> EAST_STRAIGHT;
                    case INNER_LEFT -> EAST_INNER_LEFT;
                    case INNER_RIGHT -> EAST_INNER_RIGHT;
                    case OUTER_LEFT -> EAST_OUTER_LEFT;
                    case OUTER_RIGHT -> EAST_OUTER_RIGHT;
                };
            case SOUTH:
                return switch (shape) {
                    case STRAIGHT -> SOUTH_STRAIGHT;
                    case INNER_LEFT -> SOUTH_INNER_LEFT;
                    case INNER_RIGHT -> SOUTH_INNER_RIGHT;
                    case OUTER_LEFT -> SOUTH_OUTER_LEFT;
                    case OUTER_RIGHT -> SOUTH_OUTER_RIGHT;
                };
            case WEST:
                return switch (shape) {
                    case STRAIGHT -> WEST_STRAIGHT;
                    case INNER_LEFT -> WEST_INNER_LEFT;
                    case INNER_RIGHT -> WEST_INNER_RIGHT;
                    case OUTER_LEFT -> WEST_OUTER_LEFT;
                    case OUTER_RIGHT -> WEST_OUTER_RIGHT;
                };
            default:
                throw new IllegalStateException("Unexpected value: " + FACING);
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape(state);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape(state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing());
        return blockState.with(SHAPE, getCounterShape(blockState, ctx.getWorld(), blockPos));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        return direction.getAxis().isHorizontal()
                ? state.with(SHAPE, getCounterShape(state, world, pos))
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static StairShape getCounterShape(BlockState state, BlockView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockState blockState = world.getBlockState(pos.offset(direction));
        if (isCounter(blockState)) {
            Direction direction2 = blockState.get(FACING);
            if (direction2.getAxis() != state.get(FACING).getAxis()) {
                if (direction2 == direction.rotateYCounterclockwise()) {
                    return StairShape.OUTER_LEFT;
                }

                return StairShape.OUTER_RIGHT;
            }
        }
        BlockState blockState2 = world.getBlockState(pos.offset(direction.getOpposite()));
        if (isCounter(blockState2)) {
            Direction direction3 = blockState2.get(FACING);
            if (direction3.getAxis() != state.get(FACING).getAxis()) {
                if (direction3 == direction.rotateYCounterclockwise()) {
                    return StairShape.INNER_LEFT;
                }
                return StairShape.INNER_RIGHT;
            }
        }
        return StairShape.STRAIGHT;
    }

    public static boolean isCounter(BlockState state) {
        return state.getBlock() instanceof CounterBlock ||
                state.getBlock() instanceof StorageCounterBlock;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
}
