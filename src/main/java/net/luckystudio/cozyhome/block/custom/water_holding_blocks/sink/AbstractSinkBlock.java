package net.luckystudio.cozyhome.block.custom.water_holding_blocks.sink;

import net.fabricmc.loader.api.FabricLoader;
import net.luckystudio.cozyhome.block.custom.water_holding_blocks.AbstractWaterHoldingBlockEntity;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

        // --- 0. Check if the block has water and the item is a soup ---
        if (WaterHoldingBlock.trySoup(item, world, pos, player, hand, contents)) {
            return ItemActionResult.SUCCESS;
        }

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
            SoundEvent soundEvent = newContents == ContainsBlock.WATER ? SoundEvents.ITEM_BUCKET_EMPTY : SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state.with(LEVEL, 3).with(CONTAINS, newContents), 3);
            world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            return ItemActionResult.SUCCESS;
        }

        // --- 3. Using a water bottle to fill the block ---
        if (item == Items.POTION && ((contents == ContainsBlock.WATER && level < 3) || contents == ContainsBlock.NONE)) {
            PotionContentsComponent potionContentsComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContentsComponent != null && potionContentsComponent.matches(Potions.WATER)) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.with(LEVEL, level + 1).with(CONTAINS, ContainsBlock.WATER), 3);
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                return ItemActionResult.SUCCESS;
            } else {
                return WaterHoldingBlock.toggleSwitch(state, world, pos, player);
            }
        }

        // --- 4. Filling a bottle from the block ---
        if (item == Items.GLASS_BOTTLE && contents == ContainsBlock.WATER && level > 0) {
            int newLevel = level - 1;
            ContainsBlock newContents = newLevel == 0 ? ContainsBlock.NONE : contents;
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionContentsComponent.createStack(Items.POTION, Potions.WATER)));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, state.with(LEVEL, level - 1).with(CONTAINS, newContents), 3);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            return ItemActionResult.SUCCESS;
        }
        return WaterHoldingBlock.toggleSwitch(state, world, pos, player);
    }

    // CARLOS IS GAY

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntityTypes.SINK_BLOCK_ENTITY, SinkBlockEntity::tick);
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
    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 3;
    }

    @Override
    public void addLiquid(BlockState state, World world, BlockPos pos, BlockState pullState, Direction pullDirection) {
        int level = state.get(LEVEL);
        int newLevel = Math.min(3, level + 1); // ensures level never goes above 2
        ContainsBlock contains;
        if (pullState.getFluidState().isIn(FluidTags.WATER) || pullState.contains(Properties.WATERLOGGED) && pullState.get(Properties.WATERLOGGED)) {
            contains = ContainsBlock.WATER;
            world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
            return;
        }
        if (pullState.getFluidState().isIn(FluidTags.LAVA)) {
            world.setBlockState(pos.offset(pullDirection), Blocks.AIR.getDefaultState(), 3);
            contains = ContainsBlock.LAVA;
            world.setBlockState(pos, state.with(LEVEL, 3).with(CONTAINS, contains), 3);
            return;
        }
        if (pullState.getBlock() == Blocks.WATER_CAULDRON) {
            LeveledCauldronBlock.decrementFluidLevel(pullState, world, pos.offset(pullDirection));
            contains = ContainsBlock.WATER;
            world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
            return;
        }
        if (pullState.getBlock() == Blocks.LAVA_CAULDRON) {
            world.setBlockState(pos.offset(pullDirection), Blocks.CAULDRON.getDefaultState(), 3);
            contains = ContainsBlock.LAVA;
            world.setBlockState(pos, state.with(LEVEL, 3).with(CONTAINS, contains), 3);
        }
    }

    @Override
    public void removeLiquid(BlockState state, World world, BlockPos pos) {
        int level = state.get(LEVEL);
        int newLevel = Math.max(0, level - 1); // ensures level never goes below 0
        ContainsBlock contains = newLevel == 0 ? ContainsBlock.NONE : state.get(CONTAINS);
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
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

    // Handles entity effects based on the block’s fill state (LAVA, POWDER_SNOW, WATER)
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        ContainsBlock fillState = state.get(CONTAINS);

        // If block contains lava, apply burn damage and set the entity on fire
        if (fillState == ContainsBlock.LAVA && entity instanceof LivingEntity) {
            entity.damage(world.getDamageSources().hotFloor(), 4.0F);
            entity.setOnFireFor(3);
        }

        // Call the superclass method after handling custom effects
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (world.getBlockEntity(pos) instanceof AbstractWaterHoldingBlockEntity blockEntity) {
            if (world.isClient && blockEntity.soupTime > 0) {
                MinecraftClient client = MinecraftClient.getInstance();
                ParticleManager particleManager = client.particleManager;

                // The particle ID (e.g. "supplementaries:suds") — must be registered with a factory!
                Identifier id = Identifier.of("supplementaries", "suds");
                ParticleType<?> type = Registries.PARTICLE_TYPE.get(id);

                if (type != null) {
                    double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.3;
                    double y = pos.getY() + getLiquidLevelHeight(state);
                    double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.3;

                    particleManager.addParticle((ParticleEffect) type, x, y, z, 0.0, 0.01, 0.0);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_hand_while_sneaking").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.toggle_switch")));
        if (FabricLoader.getInstance().isModLoaded("supplementaries")) {
            tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_soup").formatted(Formatting.GRAY));
            tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.add_bubbles")));
        }
    }
}
