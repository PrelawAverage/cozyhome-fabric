package net.luckystudio.cozyhome.block.custom.water_blocks;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.custom.AbstractHorizontalConnectingBlock;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.interfaces.AllSidesConnectingBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class FountainBlock extends AbstractHorizontalConnectingBlock implements AllSidesConnectingBlock {
    public static final MapCodec<FountainBlock> CODEC = createCodec(FountainBlock::new);
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;

    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(0, 10, 0, 16, 16, 16);
    public static final VoxelShape TOP_PIECE_VOID = Block.createCuboidShape(2, 14, 2, 14, 16, 14);
    public static final VoxelShape TOP = VoxelShapes.combine(TOP_PIECE, TOP_PIECE_VOID, BooleanBiFunction.ONLY_FIRST);
    public static final VoxelShape MIDDLE = Block.createCuboidShape(4, 2, 4, 12, 10, 12);
    public static final VoxelShape BASE = Block.createCuboidShape(2, 0, 2, 14, 2, 14);
    public static final VoxelShape SHAPE = VoxelShapes.union(TOP, MIDDLE, BASE);

    public FountainBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(CONTAINS, ContainsBlock.NONE));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONTAINS);
        super.appendProperties(builder);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() == Items.WATER_BUCKET) {
            ItemUsage.exchangeStack(stack, player, Items.BUCKET.getDefaultStack());
            return changeState(state, ContainsBlock.WATER, SoundEvents.ITEM_BUCKET_EMPTY, world, pos, player);
        } else if (stack.getItem() == Items.LAVA_BUCKET) {
            ItemUsage.exchangeStack(stack, player, Items.BUCKET.getDefaultStack());
            return changeState(state, ContainsBlock.LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, world, pos, player);
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private static ItemActionResult changeState(BlockState state, ContainsBlock newContains, SoundEvent soundEvent, World world, BlockPos pos, PlayerEntity player) {
        // Only run if the state actually should change
        if (state.get(CONTAINS) != newContains) {
            state = state.with(ModProperties.CONTAINS, newContains);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1F, 1f);
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    // This spawns particles when the block contains lava using the LavaFluid Classes randomDisplayTick method
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(CONTAINS) == ContainsBlock.LAVA) {
            LavaFluid lavaFluid = (LavaFluid) Fluids.LAVA;
            lavaFluid.randomDisplayTick(world, pos, state.getFluidState(), random);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (state.get(CONTAINS) == ContainsBlock.ICE) {
            ModBlockUtilities.tryMelt(state, world, pos, state.with(CONTAINS, ContainsBlock.WATER));
        } else if (state.get(CONTAINS) == ContainsBlock.WATER) {
            ModBlockUtilities.tryFreezeWater(state, world, pos, state.with(CONTAINS, ContainsBlock.ICE));
        }
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

    // So mobs don't stand on it
    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.block.can_hold").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("block.minecraft.grass_block")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("block.minecraft.water")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("block.minecraft.lava")));
    }

    @Override
    public void onBroken(WorldAccess worldAccess, BlockPos pos, BlockState state) {
        onBlockDestroyed((World)worldAccess, state, pos);
        super.onBroken(worldAccess, pos, state);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        onBlockDestroyed(world, state, pos);
        return super.onBreak(world, pos, state, player);
    }

    private static void onBlockDestroyed(World world, BlockState state, BlockPos pos) {
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
                FluidState fluidState = Fluids.FLOWING_WATER.getDefaultState().with(Properties.LEVEL_1_8, 4);
                world.setBlockState(pos, fluidState.getBlockState());
            }
            if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                FluidState fluidState = Fluids.FLOWING_LAVA.getDefaultState().with(Properties.LEVEL_1_8, 4);
                world.setBlockState(pos, fluidState.getBlockState());
            }
    }

    @Override
    public boolean isMatchingBlock(BlockState state, BlockState targetState) {
        return targetState.getBlock() instanceof FountainBlock &&
                targetState.contains(CONTAINS) && state.contains(CONTAINS) &&
                targetState.get(CONTAINS).equals(state.get(CONTAINS));
    }
}