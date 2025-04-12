package net.luckystudio.cozyhome.block.custom.bathtub;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.custom.sinks.AbstractLiquidHoldingBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.DoubleLongPart;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
public class BathTubBlock extends AbstractLiquidHoldingBlock implements Waterloggable, SeatBlock {
    public static final MapCodec<BathTubBlock> CODEC = createCodec(BathTubBlock::new);

    public static final EnumProperty<DoubleLongPart> PART = ModProperties.DOUBLE_LONG_PART;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_2;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    // Total of 8 combinations: 4 directions * 2 parts
    private static final VoxelShape[] SHAPES = new VoxelShape[8];

    public BathTubBlock(Settings settings) {
        super(settings);
        this.generateShapes();
        this.setDefaultState(super.getDefaultState()
                .with(WATERLOGGED, false)
                .with(PART, DoubleLongPart.FRONT)
                .with(FACING, Direction.NORTH)
                .with(LEVEL, 0));
    }

    @Override
    protected MapCodec<BathTubBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(LEVEL, WATERLOGGED, PART, FACING));
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

        if (player.isSneaking()) {
            return toggleSwitch(state, world, pos, player);
        }

        // Handle empty bucket
        if (item == Items.BUCKET) {
            if (level > 0) {
                ItemStack filledBucket = switch (contents) {
                    case WATER -> new ItemStack(Items.WATER_BUCKET);
                    case LAVA  -> new ItemStack(Items.LAVA_BUCKET);
                    default    -> ItemStack.EMPTY;
                };

                if (!filledBucket.isEmpty()) {
                    player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, filledBucket));
                    SoundEvent sound = (contents == ContainsBlock.WATER) ? SoundEvents.ITEM_BUCKET_FILL : SoundEvents.ITEM_BUCKET_FILL_LAVA;
                    world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    removeLiquid(state, world, pos, 1);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
                    return ItemActionResult.SUCCESS;
                }
            }
            return SeatBlock.sitDown(state, world, pos, player);
        }

        // Handle water/lava buckets
        if (!isFull(state)) {
            if (item == Items.WATER_BUCKET || item == Items.LAVA_BUCKET) {
                ContainsBlock newContents = (item == Items.WATER_BUCKET) ? ContainsBlock.WATER : ContainsBlock.LAVA;
                SoundEvent sound = (item == Items.WATER_BUCKET) ? SoundEvents.ITEM_BUCKET_EMPTY : SoundEvents.ITEM_BUCKET_EMPTY_LAVA;

                addLiquid(state, world, pos, 1, newContents);
                world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ItemActionResult.SUCCESS;
            }
        }

        // Default sit-down behavior
        return SeatBlock.sitDown(state, world, pos, player);
    }

    private ItemActionResult toggleSwitch(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (hasLiquidToPull(state, world, pos)) {
            state = state.with(TRIGGERED, true);
            BlockPos otherPos = getOtherPartPos(pos, state);
            BlockState otherState = getOtherPartState(world, pos, state);
            world.setBlockState(pos, state, 3);
            world.setBlockState(otherPos, otherState, 3);
            world.scheduleBlockTick(pos, this, 1);
            world.scheduleBlockTick(otherPos, world.getBlockState(otherPos).getBlock(), 1);
            world.updateNeighborsAlways(pos, this);
            playClickSound(player, world, pos, state);
            world.emitGameEvent(player, state.get(TRIGGERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
            return ItemActionResult.SUCCESS;
        } else {
            player.sendMessage(Text.translatable("message.cozyhome.needs_water"), true);
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
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

    public float getLiquidLevelHeight(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 1 -> 0.5f;
            case 2 -> 0.6875f;
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }

    public List<Direction> sidesToPull(BlockState state) {
        Direction facing = state.get(FACING);
        return List.of(facing);
    }

    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 2;
    }

    public int getLevel(BlockState state) {
        return state.get(LEVEL);
    }

    public void addLiquid(BlockState state, World world, BlockPos pos, int amount, ContainsBlock contains) {
        // Update CONTAINS if needed
        if (state.get(CONTAINS) != contains) {
            state = state.with(CONTAINS, contains).with(LEVEL, 1);
            BlockPos otherPos = getOtherPartPos(pos, state);
            BlockState otherState = getOtherPartState(world, pos, state).with(CONTAINS, contains).with(LEVEL, 0);

            world.setBlockState(pos, state, 3);
            world.setBlockState(otherPos, otherState, 3);
        }

        int level = state.get(LEVEL);
        int newLevel = Math.min(level + amount, 2); // Clamp to max 2

        if (level < newLevel) {
            BlockPos otherPos = getOtherPartPos(pos, state);
            BlockState otherState = getOtherPartState(world, pos, state);

            world.setBlockState(pos, state.with(LEVEL, newLevel), 3);
            world.setBlockState(otherPos, otherState.with(LEVEL, newLevel), 3);
        }
    }

    public void removeLiquid(BlockState state, World world, BlockPos pos, int amount) {
        int level = state.get(LEVEL);
        int newLevel = Math.max(0, level - amount); // ensures level never goes below 0
        ContainsBlock contains = newLevel == 0 ? ContainsBlock.NONE : state.get(CONTAINS);
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
        world.setBlockState(getOtherPartPos(pos, state), getOtherPartState(world, pos, state).with(LEVEL, newLevel).with(CONTAINS, contains), 3);
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

    public static BlockPos getOtherPartPos(BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        return state.get(PART) == DoubleLongPart.FRONT ? pos.offset(facing) : pos.offset(facing.getOpposite());
    }

    public static BlockState getOtherPartState(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        BlockPos otherPartPos = state.get(PART) == DoubleLongPart.FRONT ? pos.offset(facing) : pos.offset(facing.getOpposite());
        return world.getBlockState(otherPartPos);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.pulls_water_from").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.behind")));
    }
}
