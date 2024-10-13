package net.luckystudio.cozyhome.block.type;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.luckystudio.cozyhome.block.entity.StorageCounterBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StorageCounterBlock extends BlockWithEntity implements  BlockEntityProvider{
    public static final MapCodec<StorageCounterBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.blockState), createSettingsCodec())
                    .apply(instance, StorageCounterBlock::new)
    );

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;

    // Settings Block general shape
    public static final VoxelShape COUNTER_TOP = Block.createCuboidShape(0, 12, 0, 16, 16, 16);

    // Setting Block shape based on direction
    public static final VoxelShape NORTH_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 0, 16, 12, 14));
    public static final VoxelShape EAST_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(2, 0, 0, 16, 12, 16));
    public static final VoxelShape SOUTH_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 2, 16, 12, 16));
    public static final VoxelShape WEST_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 0, 14, 12, 16));

    protected final BlockState blockState;

    @Override
    public MapCodec<? extends StorageCounterBlock> getCodec() {
        return CODEC;
    }

    public StorageCounterBlock(BlockState state, Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager()
                .getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(OPEN, Boolean.FALSE));

        this.blockState = state;
    }

//    @Override
//    protected boolean hasComparatorOutput(BlockState state) {
//        return true;
//    }
//
//    @Override
//    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
//        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
//    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StorageCounterBlockEntity) {
                player.openHandledScreen((StorageCounterBlockEntity)blockEntity);
                player.incrementStat(Stats.OPEN_BARREL);
                PiglinBrain.onGuardedBlockInteracted(player, true);
            }
            return ActionResult.CONSUME;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing());
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);

        if (direction == Direction.NORTH) return NORTH_STRAIGHT;
        if (direction == Direction.SOUTH) return SOUTH_STRAIGHT;
        if (direction == Direction.WEST) return WEST_STRAIGHT;
        if (direction == Direction.EAST) return EAST_STRAIGHT;

        throw new IllegalStateException("Unexpected value: " + FACING);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StorageCounterBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape(state);
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

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof StorageCounterBlockEntity) {
            ((StorageCounterBlockEntity)blockEntity).tick();
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }
}
