package net.luckystudio.cozyhome.block.primary;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.interfaces.SinkBehavior;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public class SinkCounterBlock extends Block {
    private final Biome.Precipitation precipitation;
    protected final SinkBehavior.SinkBehaviorMap behaviorMap;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_3;

    // Settings Block general shape
    public static final VoxelShape COUNTER_TOP = Block.createCuboidShape(0, 12, 0, 16, 16, 16);

    // Setting Block shape based on direction
    public static final VoxelShape NORTH_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 0, 16, 12, 14));
    public static final VoxelShape EAST_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(2, 0, 0, 16, 12, 16));
    public static final VoxelShape SOUTH_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 2, 16, 12, 16));
    public static final VoxelShape WEST_STRAIGHT = VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(0, 0, 0, 14, 12, 16));

    public SinkCounterBlock(Biome.Precipitation precipitation, SinkBehavior.SinkBehaviorMap behaviorMap, Settings settings) {
        super(settings);
        this.precipitation = precipitation;
        this.behaviorMap = behaviorMap;
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(LEVEL, 0)
                .with(FACING, Direction.NORTH));
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);

        if (direction == Direction.NORTH) return NORTH_STRAIGHT;
        if (direction == Direction.SOUTH) return SOUTH_STRAIGHT;
        if (direction == Direction.WEST) return WEST_STRAIGHT;
        if (direction == Direction.EAST) return EAST_STRAIGHT;

        throw new IllegalStateException("Unexpected value: " + FACING);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(LEVEL, 0);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        SinkBehavior sinkBehavior = this.behaviorMap.map().get(stack.getItem());
        return sinkBehavior.interact(state, world, pos, player, hand, stack);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos blockPos = PointedDripstoneBlock.getDripPos(world, pos);
        if (blockPos != null) {
            Fluid fluid = PointedDripstoneBlock.getDripFluid(world, blockPos);
            if (fluid != Fluids.EMPTY && this.canBeFilledByDripstone(fluid)) {
                this.fillFromDripstone(state, world, pos);
            }
        }
    }

    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 3;
    }

    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return fluid == Fluids.WATER && this.precipitation == Biome.Precipitation.RAIN;
    }

    protected double getFluidHeight(BlockState state) {
        return (6.0 + (double) state.get(LEVEL) * 3.0) / 16.0;
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity.isOnFire() && this.isEntityTouchingFluid(state, pos, entity)) {
            entity.extinguish();
            if (entity.canModifyAt(world, pos)) {
                this.onFireCollision(state, world, pos);
            }
        }
    }

    protected boolean isEntityTouchingFluid(BlockState state, BlockPos pos, Entity entity) {
        return entity.getY() < (double)pos.getY() + this.getFluidHeight(state) && entity.getBoundingBox().maxY > (double)pos.getY() + 0.25;
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (canFillWithPrecipitation(world, precipitation) && state.get(LEVEL) != 3 && precipitation == this.precipitation) {
            BlockState blockState = state.cycle(LEVEL);
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < 0.05F;
        } else {
            return precipitation == Biome.Precipitation.SNOW && world.getRandom().nextFloat() < 0.1F;
        }
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(LEVEL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL, FACING);
    }

    protected void fillFromDripstone(BlockState state, World world, BlockPos pos) {
        if (!this.isFull(state)) {
            BlockState blockState = state.with(LEVEL, state.get(LEVEL) + 1);
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
        }
    }

    private void onFireCollision(BlockState state, World world, BlockPos pos) {
        if (this.precipitation == Biome.Precipitation.SNOW) {
            decrementFluidLevel(state, world, pos);
        }
    }

    public static void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
        int i = state.get(LEVEL) - 1;
        BlockState blockState = state.with(LEVEL, i);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
}
