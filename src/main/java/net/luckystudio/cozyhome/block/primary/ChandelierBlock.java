package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.primary.secondary.DyeableToggleableLightBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
/**
 The Chandelier is a massive 3x3 block that emits a toggleable light.

 For further details on this block,
 @see DyeableToggleableLightBlock
 */
public class ChandelierBlock extends DyeableToggleableLightBlock implements LandingBlock {
    public static final MapCodec<ChandelierBlock> CODEC = createCodec(ChandelierBlock::new);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public ChandelierBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected MapCodec<? extends DyeableToggleableLightBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        super.appendProperties(builder);
    }

    /**
     Checks if there is space to place down the main block and
     the lower half below it.
     */
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockBelow = pos.down();

        // Check that there are no blocks below and two blocks below
        boolean spaceBelow = world.isAir(pos);
        boolean spaceTwoBlocksBelow = world.isAir(blockBelow);

        return spaceBelow && spaceTwoBlocksBelow;
    }
    /**
     Sets the half state of the block to the correct shape
     depending on whether it is the bottom half or upper half
     */
    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        BlockState blockStateAbove = world.getBlockState(pos.up());
        BlockState blockStateBelow = world.getBlockState(pos.down());
        if (blockStateBelow.getBlock() == state.getBlock()) {
            return state.with(HALF, DoubleBlockHalf.UPPER);
        } else if (blockStateAbove.getBlock() == state.getBlock()) {
            return state.with(HALF, DoubleBlockHalf.LOWER);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        return blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.down()).canReplace(ctx) ? super.getPlacementState(ctx) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockPos blockPos = pos.down();
        world.setBlockState(blockPos, withWaterloggedState(world, blockPos, this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER)), Block.NOTIFY_ALL);
    }

    public static BlockState withWaterloggedState(WorldView world, BlockPos pos, BlockState state) {
        return state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, world.isWater(pos)) : state;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        BlockPos otherHalf = pos;
        if (state.get(HALF) == DoubleBlockHalf.UPPER) otherHalf.down();
        if (state.get(HALF) == DoubleBlockHalf.LOWER) otherHalf.up();
        super.toggleLight(state, world, pos, player);
        super.toggleLight(state, world, otherHalf, player);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            if (player.isCreative()) {
                onBreakInCreative(world, pos, state, player);
            } else {
                dropStacks(state, world, pos, null, player, player.getMainHandStack());
            }
        }

        return super.onBreak(world, pos, state, player);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, Blocks.AIR.getDefaultState(), blockEntity, tool);
    }

    /**
     * Destroys a bottom half of a tall double block (such as a plant or a door)
     * without dropping an item when broken in creative.
     *
     * @see Block#onBreak(World, BlockPos, BlockState, PlayerEntity)
     */
    protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockState2 = blockState.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
            }
        }
    }
}
