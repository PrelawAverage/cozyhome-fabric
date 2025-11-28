package net.luckystudio.cozyhome.block.custom.drawers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.AdvancedHorizontalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class DrawerBlock extends BlockWithEntity implements Waterloggable, ConnectingBlock {
    public static final MapCodec<DrawerBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState), createSettingsCodec())
                    .apply(instance, DrawerBlock::new)
    );

    public static final EnumProperty<AdvancedHorizontalLinearConnectionBlock> HORIZONTAL_CONNECTION = ModProperties.ADVANCED_HORIZONTAL_CONNECTION;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty OPEN = Properties.OPEN;

    // Setting the pieces of the block
    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(0, 12, 0, 16, 16, 16);
    public static final VoxelShape NORTH_WEST_LEG_PIECE = Block.createCuboidShape(1, 0, 1, 4, 4, 4);
    public static final VoxelShape SOUTH_EAST_LEG_PIECE = Block.createCuboidShape(12, 0, 12, 15, 4, 15);
    public static final VoxelShape SOUTH_WEST_LEG_PIECE = Block.createCuboidShape(1, 0, 12, 4, 4, 15);
    public static final VoxelShape NORTH_EAST_LEG_PIECE = Block.createCuboidShape(12, 0, 1, 15, 4, 4);

    public static final VoxelShape NORTH_RIGHT_EXTENSION_PIECE = Block.createCuboidShape(15, 4, 1, 16, 12, 4);
    public static final VoxelShape EAST_RIGHT_EXTENSION_PIECE = Block.createCuboidShape(12, 4, 15, 15, 12, 16);
    public static final VoxelShape SOUTH_RIGHT_EXTENSION_PIECE = Block.createCuboidShape(0, 4, 12, 1, 12, 15);
    public static final VoxelShape WEST_RIGHT_EXTENSION_PIECE = Block.createCuboidShape(1, 4, 0, 4, 12, 1);

    public static final VoxelShape NORTH_LEFT_EXTENSION_PIECE = Block.createCuboidShape(0, 4, 1, 1, 12, 4);
    public static final VoxelShape EAST_LEFT_EXTENSION_PIECE = Block.createCuboidShape(12, 4, 0, 15, 12, 1);
    public static final VoxelShape SOUTH_LEFT_EXTENSION_PIECE = Block.createCuboidShape(15, 4, 12, 16, 12, 15);
    public static final VoxelShape WEST_LEFT_EXTENSION_PIECE = Block.createCuboidShape(1, 4, 15, 4, 12, 16);

    public static final VoxelShape SINGLE_SHAPE = VoxelShapes.union(TOP_PIECE, NORTH_WEST_LEG_PIECE, NORTH_EAST_LEG_PIECE, SOUTH_WEST_LEG_PIECE, SOUTH_EAST_LEG_PIECE, Block.createCuboidShape(1, 4, 1, 15, 12, 15));

    public static final VoxelShape NORTH_LEFT_SHAPE = VoxelShapes.union(TOP_PIECE, NORTH_WEST_LEG_PIECE, SOUTH_WEST_LEG_PIECE, Block.createCuboidShape(1, 4, 1, 16, 12, 15));
    public static final VoxelShape EAST_LEFT_SHAPE = VoxelShapes.union(TOP_PIECE, NORTH_EAST_LEG_PIECE, NORTH_WEST_LEG_PIECE, Block.createCuboidShape(1, 4, 1, 15, 12, 16));
    public static final VoxelShape SOUTH_LEFT_SHAPE = VoxelShapes.union(TOP_PIECE, SOUTH_EAST_LEG_PIECE, NORTH_EAST_LEG_PIECE, Block.createCuboidShape(0, 4, 1, 15, 12, 15));
    public static final VoxelShape WEST_LEFT_SHAPE = VoxelShapes.union(TOP_PIECE, SOUTH_WEST_LEG_PIECE, SOUTH_EAST_LEG_PIECE, Block.createCuboidShape(1, 4, 0, 15, 12, 15));

    public static final VoxelShape NORTH_LEFT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, NORTH_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape EAST_LEFT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, EAST_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape SOUTH_LEFT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, SOUTH_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape WEST_LEFT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, WEST_RIGHT_EXTENSION_PIECE);

    public static final VoxelShape NORTH_LEFT_DIFF_LEFT_SHAPE = VoxelShapes.union(NORTH_LEFT_SHAPE, NORTH_LEFT_EXTENSION_PIECE);
    public static final VoxelShape EAST_LEFT_DIFF_LEFT_SHAPE = VoxelShapes.union(EAST_LEFT_SHAPE, EAST_LEFT_EXTENSION_PIECE);
    public static final VoxelShape SOUTH_LEFT_DIFF_LEFT_SHAPE = VoxelShapes.union(SOUTH_LEFT_SHAPE, SOUTH_LEFT_EXTENSION_PIECE);
    public static final VoxelShape WEST_LEFT_DIFF_LEFT_SHAPE = VoxelShapes.union(WEST_LEFT_SHAPE, WEST_LEFT_EXTENSION_PIECE);

    public static final VoxelShape NORTH_MIDDLE_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(0, 4, 1, 16, 12, 15));
    public static final VoxelShape EAST_MIDDLE_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(1, 4, 0, 15, 12, 16));
    public static final VoxelShape SOUTH_MIDDLE_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(0, 4, 1, 16, 12, 15));
    public static final VoxelShape WEST_MIDDLE_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(1, 4, 0, 15, 12, 16));

    public static final VoxelShape NORTH_MIDDLE_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, NORTH_LEFT_EXTENSION_PIECE, NORTH_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape EAST_MIDDLE_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, EAST_LEFT_EXTENSION_PIECE, EAST_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape SOUTH_MIDDLE_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, SOUTH_LEFT_EXTENSION_PIECE, SOUTH_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape WEST_MIDDLE_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, WEST_LEFT_EXTENSION_PIECE, WEST_RIGHT_EXTENSION_PIECE);

    public static final VoxelShape NORTH_RIGHT_SHAPE = VoxelShapes.union(TOP_PIECE, SOUTH_EAST_LEG_PIECE, NORTH_EAST_LEG_PIECE, Block.createCuboidShape(0, 4, 1, 15, 12, 15));
    public static final VoxelShape EAST_RIGHT_SHAPE = VoxelShapes.union(TOP_PIECE, SOUTH_WEST_LEG_PIECE, SOUTH_EAST_LEG_PIECE, Block.createCuboidShape(1, 4, 0, 15, 12, 15));
    public static final VoxelShape SOUTH_RIGHT_SHAPE = VoxelShapes.union(TOP_PIECE, NORTH_WEST_LEG_PIECE, SOUTH_WEST_LEG_PIECE, Block.createCuboidShape(1, 4, 1, 16, 12, 15));
    public static final VoxelShape WEST_RIGHT_SHAPE = VoxelShapes.union(TOP_PIECE, NORTH_EAST_LEG_PIECE, NORTH_WEST_LEG_PIECE, Block.createCuboidShape(1, 4, 1, 15, 12, 16));

    public static final VoxelShape NORTH_RIGHT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, NORTH_LEFT_EXTENSION_PIECE);
    public static final VoxelShape EAST_RIGHT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, EAST_LEFT_EXTENSION_PIECE);
    public static final VoxelShape SOUTH_RIGHT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, SOUTH_LEFT_EXTENSION_PIECE);
    public static final VoxelShape WEST_RIGHT_DIFF_SHAPE = VoxelShapes.union(SINGLE_SHAPE, WEST_LEFT_EXTENSION_PIECE);

    public static final VoxelShape NORTH_RIGHT_DIFF_RIGHT_SHAPE = VoxelShapes.union(NORTH_LEFT_SHAPE, NORTH_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape EAST_RIGHT_DIFF_RIGHT_SHAPE = VoxelShapes.union(EAST_LEFT_SHAPE, EAST_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape SOUTH_RIGHT_DIFF_RIGHT_SHAPE = VoxelShapes.union(SOUTH_LEFT_SHAPE, SOUTH_RIGHT_EXTENSION_PIECE);
    public static final VoxelShape WEST_RIGHT_DIFF_RIGHT_SHAPE = VoxelShapes.union(WEST_LEFT_SHAPE, WEST_RIGHT_EXTENSION_PIECE);

    protected final BlockState baseBlockState;

    public DrawerBlock(BlockState baseBlockState, Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager
                .getDefaultState()
                .with(HORIZONTAL_CONNECTION, AdvancedHorizontalLinearConnectionBlock.SINGLE)
                .with(WATERLOGGED, Boolean.FALSE)
                .with(FACING, Direction.NORTH)
                .with(OPEN, Boolean.FALSE));
        this.baseBlockState = baseBlockState;
    }

    @Override
    public MapCodec<? extends DrawerBlock> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DrawerBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_CONNECTION, WATERLOGGED, FACING, OPEN);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        AdvancedHorizontalLinearConnectionBlock connectionBlock = state.get(HORIZONTAL_CONNECTION);
        Direction direction = state.get(FACING);
        return switch (direction) {
            case NORTH -> switch (connectionBlock) {
                case LEFT -> NORTH_LEFT_SHAPE;
                case LEFT_DIFF -> NORTH_LEFT_DIFF_SHAPE;
                case LEFT_DIFF_LEFT -> NORTH_LEFT_DIFF_LEFT_SHAPE;
                case MIDDLE -> NORTH_MIDDLE_SHAPE;
                case MIDDLE_DIFF -> NORTH_MIDDLE_DIFF_SHAPE;
                case RIGHT -> NORTH_RIGHT_SHAPE;
                case RIGHT_DIFF -> NORTH_RIGHT_DIFF_SHAPE;
                case RIGHT_DIFF_RIGHT -> NORTH_RIGHT_DIFF_RIGHT_SHAPE;
                default -> SINGLE_SHAPE;
            };
            case EAST -> switch (connectionBlock) {
                case LEFT -> EAST_LEFT_SHAPE;
                case LEFT_DIFF -> EAST_LEFT_DIFF_SHAPE;
                case LEFT_DIFF_LEFT -> EAST_LEFT_DIFF_LEFT_SHAPE;
                case MIDDLE -> EAST_MIDDLE_SHAPE;
                case MIDDLE_DIFF -> EAST_MIDDLE_DIFF_SHAPE;
                case RIGHT -> EAST_RIGHT_SHAPE;
                case RIGHT_DIFF -> EAST_RIGHT_DIFF_SHAPE;
                case RIGHT_DIFF_RIGHT -> EAST_RIGHT_DIFF_RIGHT_SHAPE;
                default -> SINGLE_SHAPE;
            };
            case SOUTH -> switch (connectionBlock) {
                case LEFT -> SOUTH_LEFT_SHAPE;
                case LEFT_DIFF -> SOUTH_LEFT_DIFF_SHAPE;
                case LEFT_DIFF_LEFT -> SOUTH_LEFT_DIFF_LEFT_SHAPE;
                case MIDDLE -> SOUTH_MIDDLE_SHAPE;
                case MIDDLE_DIFF -> SOUTH_MIDDLE_DIFF_SHAPE;
                case RIGHT -> SOUTH_RIGHT_SHAPE;
                case RIGHT_DIFF -> SOUTH_RIGHT_DIFF_SHAPE;
                case RIGHT_DIFF_RIGHT -> SOUTH_RIGHT_DIFF_RIGHT_SHAPE;
                default -> SINGLE_SHAPE;
            };
            case WEST -> switch (connectionBlock) {
                case LEFT -> WEST_LEFT_SHAPE;
                case LEFT_DIFF -> WEST_LEFT_DIFF_SHAPE;
                case LEFT_DIFF_LEFT -> WEST_LEFT_DIFF_LEFT_SHAPE;
                case MIDDLE -> WEST_MIDDLE_SHAPE;
                case MIDDLE_DIFF -> WEST_MIDDLE_DIFF_SHAPE;
                case RIGHT -> WEST_RIGHT_SHAPE;
                case RIGHT_DIFF -> WEST_RIGHT_DIFF_SHAPE;
                case RIGHT_DIFF_RIGHT -> WEST_RIGHT_DIFF_RIGHT_SHAPE;
                default -> SINGLE_SHAPE;
            };
            default -> SINGLE_SHAPE;
        };
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
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
        return state.with(HORIZONTAL_CONNECTION, AdvancedHorizontalLinearConnectionBlock.setAdvancedHorizontalConnections(state, world, pos));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DrawerBlockEntity) {
            player.openHandledScreen((DrawerBlockEntity)blockEntity);
            player.incrementStat(Stats.OPEN_BARREL);
            PiglinBrain.onGuardedBlockInteracted(player, true);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() == newState.getBlock()) return;

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DrawerBlockEntity boxBlockEntity) {
            ItemScatterer.spawn(world, pos, boxBlockEntity);
            // update comparators
            world.updateComparators(pos,this);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DrawerBlockEntity) {
            ((DrawerBlockEntity)blockEntity).tick();
        }
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
