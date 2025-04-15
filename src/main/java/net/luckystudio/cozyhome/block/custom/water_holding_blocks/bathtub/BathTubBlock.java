package net.luckystudio.cozyhome.block.custom.water_holding_blocks.bathtub;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.DoubleLongPart;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// Copied from BedBlock
public class BathTubBlock extends BlockWithEntity implements Waterloggable, SeatBlock, WaterHoldingBlock {
    public static final MapCodec<BathTubBlock> CODEC = createCodec(BathTubBlock::new);

    // Boolean properties
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Direction properties
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    // Enum properties
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;
    public static final EnumProperty<DoubleLongPart> PART = ModProperties.DOUBLE_LONG_PART;

    // Integer properties
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_2;

    // Total of 8 combinations: 4 directions * 2 parts
    private static final VoxelShape[] SHAPES = new VoxelShape[8];

    public BathTubBlock(Settings settings) {
        super(settings);
        this.generateShapes();
        this.setDefaultState(super.getDefaultState()
                .with(TRIGGERED, false)
                .with(WATERLOGGED, false)
                .with(FACING, Direction.NORTH)
                .with(CONTAINS, ContainsBlock.NONE)
                .with(PART, DoubleLongPart.FRONT)
                .with(LEVEL, 0));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BathTubBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<BathTubBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(TRIGGERED, WATERLOGGED, FACING, CONTAINS, PART, LEVEL));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[getShapeIndex(state.get(FACING), state.get(PART))];
    }

    private static int getShapeIndex(Direction facing, DoubleLongPart part) {
        return facing.getHorizontal() + (part == DoubleLongPart.BACK ? 4 : 0);
    }

    private void generateShapes() {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            for (DoubleLongPart part : DoubleLongPart.values()) {
                SHAPES[getShapeIndex(direction, part)] = createShape(direction, part);
            }
        }
    }

    private VoxelShape createShape(Direction facing, DoubleLongPart part) {
        boolean front = part == DoubleLongPart.FRONT;

        VoxelShape topShape = Block.createCuboidShape(0, 10, 0, 16, 12, 16);

        int minX = 1, maxX = 15, minZ = 1, maxZ = 15;
        switch (facing) {
            case WEST -> {
                if (front) minX = 0;
                else maxX = 16;
            }
            case EAST -> {
                if (front) maxX = 16;
                else minX = 0;
            }
            case NORTH -> {
                if (front) minZ = 0;
                else maxZ = 16;
            }
            case SOUTH -> {
                if (front) maxZ = 16;
                else minZ = 0;
            }
        }

        VoxelShape baseShape = VoxelShapes.union(
                topShape,
                Block.createCuboidShape(minX, 1, minZ, maxX, 10, maxZ)
        );

        int holeMinX = 2, holeMaxX = 14, holeMinZ = 2, holeMaxZ = 14;
        switch (facing) {
            case WEST -> {
                if (front) holeMinX = 0;
                else holeMaxX = 16;
            }
            case EAST -> {
                if (front) holeMaxX = 16;
                else holeMinX = 0;
            }
            case NORTH -> {
                if (front) holeMinZ = 0;
                else holeMaxZ = 16;
            }
            case SOUTH -> {
                if (front) holeMaxZ = 16;
                else holeMinZ = 0;
            }
        }

        VoxelShape holeShape = Block.createCuboidShape(holeMinX, 2, holeMinZ, holeMaxX, 12, holeMaxZ);
        return VoxelShapes.combine(baseShape, holeShape, BooleanBiFunction.ONLY_FIRST);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (direction == getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))) {
            return neighborState.isOf(this) && neighborState.get(PART) != state.get(PART)
                    ? state
                    : Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    private static Direction getDirectionTowardsOtherPart(DoubleLongPart part, Direction direction) {
        return part == DoubleLongPart.FRONT ? direction : direction.getOpposite();
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = stack.getItem();
        ContainsBlock contents = state.get(CONTAINS);
        int level = state.get(LEVEL);

        if (player.shouldCancelInteraction()) {
            return toggleSwitch(state, world, pos, player);
        }

        // --- 0. Check if the block has water and the item is a soup ---
        if (WaterHoldingBlock.trySoup(item, world, pos, player, hand, contents)) {
            return ItemActionResult.SUCCESS;
        }

        // --- 1. Filling a bucket from a full block ---
        if (item == Items.BUCKET && level >= 1) {
            ItemStack filledBucket = contents == ContainsBlock.WATER ? new ItemStack(Items.WATER_BUCKET) : new ItemStack(Items.LAVA_BUCKET);
            SoundEvent soundEvent = contents == ContainsBlock.WATER ? SoundEvents.ITEM_BUCKET_FILL : SoundEvents.ITEM_BUCKET_FILL_LAVA;
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, filledBucket));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (level - 1 == 0) {
                world.setBlockState(pos, state.with(LEVEL, 0).with(CONTAINS, ContainsBlock.NONE), 3);
                world.setBlockState(getOtherPartPos(state, pos), getOtherPartState(state, world, pos).with(LEVEL, 0).with(CONTAINS, ContainsBlock.NONE), 3);
            } else {
                world.setBlockState(pos, state.with(LEVEL, level - 1), 3);
                world.setBlockState(getOtherPartPos(state, pos), getOtherPartState(state, world, pos).with(LEVEL, level - 1), 3);
            }
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            return ItemActionResult.SUCCESS;
        }

        // --- 2. Pouring water/lava bucket into the block ---
        if ((item == Items.WATER_BUCKET || item == Items.LAVA_BUCKET) && level < 2) {
            ContainsBlock newContents = item == Items.WATER_BUCKET ? ContainsBlock.WATER : ContainsBlock.LAVA;

            // Prevent mixing fluids
            if (contents != ContainsBlock.NONE && contents != newContents) {
                return SeatBlock.sitDown(state, world, pos, player);
            }

            SoundEvent soundEvent = newContents == ContainsBlock.WATER ? SoundEvents.ITEM_BUCKET_EMPTY : SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state.with(LEVEL, level + 1).with(CONTAINS, newContents), 3);
            world.setBlockState(getOtherPartPos(state, pos), getOtherPartState(state, world, pos).with(LEVEL, level + 1).with(CONTAINS, newContents), 3);
            world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            return ItemActionResult.SUCCESS;
        }
        return SeatBlock.sitDown(state, world, pos, player);
    }

    public ItemActionResult toggleSwitch(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (player.isSneaking()) {
            BlockState stateToRun = state.get(PART) == DoubleLongPart.BACK ? state : getOtherPartState(state, world, pos);
            BlockPos posToRun = state.get(PART) == DoubleLongPart.BACK ? pos : getOtherPartPos(state, pos);
            if (pullingDirection(stateToRun, world, posToRun) != null) {
                world.setBlockState(posToRun, stateToRun.cycle(TRIGGERED), Block.NOTIFY_ALL);
            } else {
                player.sendMessage(Text.translatable("message.cozyhome.needs_liquid"), true);
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return state.get(PART) == DoubleLongPart.BACK && state.get(TRIGGERED) ? validateTicker(type, ModBlockEntityTypes.BATHTUB_BLOCK_ENTITY, BathTubBlockEntity::tick) : null;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (state.get(CONTAINS) == ContainsBlock.WATER && state.get(LEVEL) > 0) {
            if (world.getBlockState(pos.down()).getBlock() == Blocks.MAGMA_BLOCK) {
                float randomOffset = (float) (Math.random() * 0.5 - 0.25);
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5 + randomOffset, pos.getY() + getLiquidLevelHeight(state), pos.getZ() + 0.5 + randomOffset, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5 + randomOffset, pos.getY() + getLiquidLevelHeight(state), pos.getZ() + 0.5 + randomOffset, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            DoubleLongPart tubPart = state.get(PART);
            if (tubPart == DoubleLongPart.FRONT) {
                BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(tubPart, state.get(FACING)));
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isOf(this) && blockState.get(PART) == DoubleLongPart.BACK) {
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                    world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
                }
            }
        }
        return super.onBreak(world, pos, state, player);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getHorizontalPlayerFacing();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos blockPos2 = blockPos.offset(direction);
        World world = ctx.getWorld();
        return world.getBlockState(blockPos2).canReplace(ctx) && world.getWorldBorder().contains(blockPos2) ? this.getDefaultState().with(FACING, direction) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            BlockPos backPos = pos.offset(state.get(FACING));
            // Check if the offset position contains water
            boolean isWater = world.getFluidState(backPos).isEqualAndStill(Fluids.WATER);
            // Set the blockstate at the back position with PART = BACK and WATERLOGGED if needed
            BlockState backState = state
                    .with(PART, DoubleLongPart.BACK)
                    .with(WATERLOGGED, isWater);
            world.setBlockState(backPos, backState, Block.NOTIFY_ALL);
            // Notify neighbors
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public float getSeatRotation(BlockState state, World world, BlockPos pos) {
        return ModProperties.setSeatRotationFromFacing(state) + (state.get(PART) == DoubleLongPart.FRONT ? 0 : 180);
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.2f;
    }

    public static BlockPos getOtherPartPos(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);
        return state.get(PART) == DoubleLongPart.FRONT ? pos.offset(facing) : pos.offset(facing.getOpposite());
    }

    public static BlockState getOtherPartState(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);
        BlockPos otherPartPos = state.get(PART) == DoubleLongPart.FRONT ? pos.offset(facing) : pos.offset(facing.getOpposite());
        return world.getBlockState(otherPartPos);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_hand_while_sneaking").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.toggle_switch")));
        tooltip.add(Text.translatable("tooltip.cozyhome.pulls_water_from").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.behind")));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public float getLiquidLevelHeight(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 0 -> 0.125f;
            case 1 -> 0.5f;
            case 2 -> 0.6875f;
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }

    @Override
    public List<Direction> getDirectionsToPull(BlockState state) {
        Direction behind = state.get(FACING);
        return List.of(behind);
    }

    @Override
    public Direction pullingDirection(BlockState state, World world, BlockPos pos) {
        for (Direction direction : getDirectionsToPull(state)) {
            BlockPos offsetPos = pos.offset(direction);
            BlockState offsetState = world.getBlockState(offsetPos);
            if (offsetState.getFluidState().isIn(FluidTags.WATER) || offsetState.getFluidState().isIn(FluidTags.LAVA) || offsetState.getBlock() == Blocks.WATER_CAULDRON || offsetState.getBlock() == Blocks.LAVA_CAULDRON) {
                return direction;
            }
        }
        return null;
    }

    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 2;
    }

    @Override
    public void addLiquid(BlockState state, World world, BlockPos pos, BlockState pullState, Direction pullDirection) {
        int level = state.get(LEVEL);
        int newLevel = Math.min(2, level + 1); // ensures level never goes above 2

        ContainsBlock contains = ContainsBlock.NONE;

        // Add  1 level of water without removing water from the block
        if (pullState.getFluidState().isIn(FluidTags.WATER) || pullState.contains(Properties.WATERLOGGED) && pullState.get(Properties.WATERLOGGED)) {
            contains = ContainsBlock.WATER;
        }

        // Add 1 level of lava while removing lava from the block
        if (pullState.getFluidState().isIn(FluidTags.LAVA)) {
            world.setBlockState(pos.offset(pullDirection), Blocks.AIR.getDefaultState(), 3);
            contains = ContainsBlock.LAVA;
        }

        // Adding 1 water to the block while removing water from the block
        if (pullState.getBlock() == Blocks.WATER_CAULDRON) {
            world.setBlockState(pos.offset(pullDirection), Blocks.CAULDRON.getDefaultState(), 3);
            contains = ContainsBlock.WATER;
        }

        // Adding 1 lava to the block while removing lava from the block
        if (pullState.getBlock() == Blocks.LAVA_CAULDRON) {
            world.setBlockState(pos.offset(pullDirection), Blocks.CAULDRON.getDefaultState(), 3);
            contains = ContainsBlock.LAVA;
        }
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
        world.setBlockState(getOtherPartPos(state, pos), getOtherPartState(state, world, pos).with(LEVEL, newLevel).with(CONTAINS, contains), 3);
    }

    public void removeLiquid(BlockState state, World world, BlockPos pos) {
        int level = state.get(LEVEL);
        int newLevel = Math.max(0, level - 1); // ensures level never goes below 0
        ContainsBlock contains = newLevel == 0 ? ContainsBlock.NONE : state.get(CONTAINS);
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
        world.setBlockState(getOtherPartPos(state, pos), getOtherPartState(state, world, pos).with(LEVEL, newLevel).with(CONTAINS, contains), 3);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, getOtherPartPos(state, pos), GameEvent.Emitter.of(getOtherPartState(state, world, pos)));
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (!world.isClient && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
                if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                    entity.slowMovement(state, new Vec3d(0.8F, 0.75, 0.8F));
                    entity.damage(world.getDamageSources().lava(), 3.0F);
                    entity.setOnFireFor(2.0F);
                } else if (state.get(CONTAINS) == ContainsBlock.WATER && entity.isOnFire()) {
                    entity.extinguish();
                    if (entity.canModifyAt(world, pos)) {
                        removeLiquid(state, world, pos);
                    }
                }
            }
        }
    }
}
