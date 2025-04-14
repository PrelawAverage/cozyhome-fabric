package net.luckystudio.cozyhome.block.custom.sinks;

import net.luckystudio.cozyhome.block.custom.bathtub.SinkBlockEntity;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSinkBlock extends BlockWithEntity implements WaterHoldingBlock {

    // Boolean properties
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;

    // Direction properties
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    // Enum properties
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;

    // Integer properties
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_3;

    public AbstractSinkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(TRIGGERED, false)
                .with(FACING, Direction.NORTH)
                .with(CONTAINS, ContainsBlock.NONE)
                .with(LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(TRIGGERED, FACING, CONTAINS, LEVEL));
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = stack.getItem();
        ContainsBlock contents = state.get(CONTAINS);
        int level = state.get(LEVEL);

        // --- 1. Filling a bucket from a full block ---
        if (item == Items.BUCKET && level == 3 && contents != ContainsBlock.NONE) {
            ItemStack filledBucket = contents == ContainsBlock.WATER ? new ItemStack(Items.WATER_BUCKET) : new ItemStack(Items.LAVA_BUCKET);
            SoundEvent soundEvent = contents == ContainsBlock.WATER ? SoundEvents.ITEM_BUCKET_FILL : SoundEvents.ITEM_BUCKET_FILL_LAVA;
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, filledBucket));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, state.with(LEVEL, 0).with(CONTAINS, ContainsBlock.NONE), 3);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            return ItemActionResult.SUCCESS;
        }

        // --- 2. Pouring water/lava bucket into the block ---
        if ((item == Items.WATER_BUCKET || item == Items.LAVA_BUCKET) && level < 3) {
            ContainsBlock newContents = item == Items.WATER_BUCKET ? ContainsBlock.WATER : ContainsBlock.LAVA;
            SoundEvent soundEvent = contents == ContainsBlock.WATER ? SoundEvents.ITEM_BUCKET_EMPTY : SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state.with(LEVEL, 3).with(CONTAINS, newContents), 3);
            world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            return ItemActionResult.SUCCESS;
        }

        // --- 3. Using a water bottle to fill the block ---
        if (item == Items.POTION && contents == ContainsBlock.WATER && level < 3) {
            PotionContentsComponent potionContentsComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContentsComponent != null && potionContentsComponent.matches(Potions.WATER)) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.with(LEVEL, level + 1), 3);
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                return ItemActionResult.SUCCESS;
            } else {
                return toggleSwitch(state, world, pos, player);
            }
        }

        // --- 4. Filling a bottle from the block ---
        if (item == Items.GLASS_BOTTLE && contents == ContainsBlock.WATER && level > 0) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionContentsComponent.createStack(Items.POTION, Potions.WATER)));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state.with(LEVEL, level - 1), 3);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            return ItemActionResult.SUCCESS;
        }
        return toggleSwitch(state, world, pos, player);
    }


    private ItemActionResult toggleSwitch(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (player.isSneaking()) {
            if (hasLiquidToPull(state, world, pos)) {
                world.setBlockState(pos, state.cycle(TRIGGERED), Block.NOTIFY_ALL);
            } else {
                player.sendMessage(Text.translatable("message.cozyhome.needs_liquid"), true);
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private void togglePower(BlockState state, World world, BlockPos pos, @Nullable PlayerEntity player) {
        state = state.cycle(TRIGGERED);
        world.setBlockState(pos, state, 3);
        this.updateNeighbors(world, pos);
        playClickSound(player, world, pos, state);
        world.emitGameEvent(player, state.get(TRIGGERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }

    protected static void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        float f = state.get(TRIGGERED) ? 1.0F : 0.8F;
        world.playSound(player, pos, ModSoundEvents.SINK_TOGGLE, SoundCategory.BLOCKS, 0.3F, f);
    }

    private void updateNeighbors(World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return state.get(TRIGGERED) ? validateTicker(type, ModBlockEntityTypes.SINK_BLOCK_ENTITY, SinkBlockEntity::tick) : null;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(CONTAINS, ContainsBlock.NONE)
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(TRIGGERED, false);
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
            case 1 -> 0.438f;
            case 2 -> 0.688f;
            case 3 -> 0.938f;
            default -> 0.125f;
        };
    }

    @Override
    public boolean hasLiquidToPull(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);
        BlockPos offsetPos = pos.offset(facing);
        BlockState offsetState = world.getBlockState(offsetPos);
        return offsetState.getFluidState().isIn(FluidTags.LAVA) ||
                offsetState.getFluidState().isIn(FluidTags.WATER) ||
                (offsetState.contains(Properties.WATERLOGGED) && offsetState.get(Properties.WATERLOGGED));
    }

    @Override
    public ContainsBlock getLiquidToPull(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);
        BlockPos offsetPos = pos.offset(facing);
        BlockState offsetState = world.getBlockState(offsetPos);
        FluidState fluid = offsetState.getFluidState();

        if (fluid.isOf(Fluids.WATER) || (offsetState.contains(Properties.WATERLOGGED) && offsetState.get(Properties.WATERLOGGED))) {
            return ContainsBlock.WATER;
        } else if (fluid.isOf(Fluids.LAVA)) {
            return ContainsBlock.LAVA;
        }

        return ContainsBlock.NONE;
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 3;
    }

    @Override
    public void addLiquid(BlockState state, World world, BlockPos pos, ContainsBlock contains) {
        int level = state.get(LEVEL);
        int newLevel = Math.min(3, level + 1); // ensures level never goes above 2
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
    }

    @Override
    public void removeLiquid(BlockState state, World world, BlockPos pos) {
        int level = state.get(LEVEL);
        int newLevel = Math.max(0, level - 1); // ensures level never goes below 0
        ContainsBlock contains = newLevel == 0 ? ContainsBlock.NONE : state.get(CONTAINS);
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
    }
}
