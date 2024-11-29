package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.entity.ItemRackBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolRackBlock extends BlockWithEntity implements Waterloggable {
    public static final MapCodec<ToolRackBlock> CODEC = createCodec(ToolRackBlock::new);

    private static final Formatting CAPTION = Formatting.GRAY;
    private static final Formatting ENTRIES = Formatting.YELLOW;

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 4, 14, 14, 12, 16),
            Block.createCuboidShape(10, 7, 11, 12, 9, 14),
            Block.createCuboidShape(4, 7, 11, 6, 9, 14));
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 4, 2, 2, 12, 14),
            Block.createCuboidShape(2, 7, 10, 5, 9, 12),
            Block.createCuboidShape(2, 7, 4, 5, 9, 6));
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 4, 0, 14, 12, 2),
            Block.createCuboidShape(4, 7, 2, 6, 9, 5),
            Block.createCuboidShape(10, 7, 2, 12, 9, 5));
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(14, 4, 2, 16, 12, 14),
            Block.createCuboidShape(11, 7, 4, 14, 9, 6),
            Block.createCuboidShape(11, 7, 10, 14, 9, 12));

    public static final VoxelShape NORTH_SHIELD_SHAPE = VoxelShapes.union(NORTH_SHAPE, Block.createCuboidShape(2, -3, 10, 14, 19, 12));
    public static final VoxelShape EAST_SHIELD_SHAPE = VoxelShapes.union(EAST_SHAPE, Block.createCuboidShape(4, -3, 2, 6, 19, 14));
    public static final VoxelShape SOUTH_SHIELD_SHAPE = VoxelShapes.union(SOUTH_SHAPE, Block.createCuboidShape(2, -3, 4, 14, 19, 6));
    public static final VoxelShape WEST_SHIELD_SHAPE = VoxelShapes.union(WEST_SHAPE, Block.createCuboidShape(10, -3, 2, 12, 19, 14));


    public ToolRackBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ItemRackBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> null;
        };
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ItemRackBlockEntity itemRackBlockEntity) {
            if (itemRackBlockEntity.getStack().getItem() instanceof ShieldItem) {
                return switch (state.get(FACING)) {
                    case NORTH -> NORTH_SHIELD_SHAPE;
                    case SOUTH -> SOUTH_SHIELD_SHAPE;
                    case EAST -> EAST_SHIELD_SHAPE;
                    case WEST -> WEST_SHIELD_SHAPE;
                    default -> null;
                };
            }
        }
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        super.onProjectileHit(world, state, hit, projectile);
        if (!world.isClient) {
            BlockPos pos = hit.getBlockPos();
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ItemRackBlockEntity itemRackBlockEntity) {
                if (itemRackBlockEntity.getStack().getItem() instanceof ShieldItem) world.playSound(null, pos, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(FACING);
        BlockPos attachedPos = pos.offset(facing.getOpposite());
        return Block.sideCoversSmallSquare(world, attachedPos, facing);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!ctx.canReplaceExisting()) {
            BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));
            if (blockState.isOf(this) && blockState.get(FACING) == ctx.getSide()) {
                return null;
            }
        }
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.with(FACING, direction.getOpposite());
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }
        return null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        return !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if the block at the given position has an ItemRackBlockEntity associated with it.
        if (world.getBlockEntity(pos) instanceof ItemRackBlockEntity itemRackBlockEntity) {
            // Get the item stack that is currently stored in the block
            ItemStack rackItemStack = itemRackBlockEntity.getStack();

            // Check if the item in hand is a valid tool or weapon.
            if (isToolOrWeapon(stack)) {
                // If the stack is not empty, and the rack is either empty or can accept the item (same type and enough space),
                // proceed to insert the item into the block.
                if (!stack.isEmpty() && (rackItemStack.isEmpty() ||
                        ItemStack.areItemsAndComponentsEqual(rackItemStack, stack) && rackItemStack.getCount() < rackItemStack.getMaxCount())) {

                    // Increment the player's use stat for the item in their hand.
                    player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

                    // Split the stack unless the player is in creative mode (in which case the item won't be removed).
                    ItemStack itemStack2 = stack.splitUnlessCreative(1, player);

                    float f;

                    // If the block was empty, store the item directly.
                    if (itemRackBlockEntity.isEmpty()) {
                        itemRackBlockEntity.setStack(itemStack2);
                        f = (float)itemStack2.getCount() / (float)itemStack2.getMaxCount();
                    } else {
                        // Otherwise, increment the item count in the block's stored stack.
                        rackItemStack.increment(1);
                        f = (float)rackItemStack.getCount() / (float)rackItemStack.getMaxCount();
                    }

                    // Play a sound to indicate that an item has been added to the block.
                    world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_INSERT, SoundCategory.BLOCKS, 1.0F, 0.7F + 0.5F * f);

                    // Mark the block entity as dirty, indicating it has changed.
                    itemRackBlockEntity.markDirty();

                    // Notify the world that the block state has changed and trigger the block update.
                    world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                    // Emit a game event to notify of the block's state change
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                    // Return a successful result to stop further interaction processing.
                    return ItemActionResult.SUCCESS;
                }
            } else if (!itemRackBlockEntity.isEmpty()) {
                // Get the item stack currently in the block
                ItemStack storedStack = itemRackBlockEntity.getStack();

                // Try to give the player the item from the block
                ItemStack itemToGive = storedStack.copy();  // Create a copy of the stored item

                // If the player can hold the item (inventory space check)
                if (player.getInventory().insertStack(itemToGive)) {
                    // Remove the item from the block (decrement the stack)
                    storedStack.decrement(1);  // Decrease the count of the item in the block

                    // If the block is now empty, clear the item rack
                    if (storedStack.isEmpty()) {
                        itemRackBlockEntity.setStack(ItemStack.EMPTY);
                    }

                    // Play sound for item extraction
                    world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    // Mark the block entity as dirty to save the changes
                    itemRackBlockEntity.markDirty();

                    // Notify the world about the block's state change
                    world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                    // Emit a game event to notify of the block's state change
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                    // Return a success result
                    return ItemActionResult.SUCCESS;
                } else {
                    // If the player cannot hold the item (inventory full)
                    return ItemActionResult.FAIL;
                }
            }
            // If no valid interaction was made, let the game handle default block interactions.
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            // If the block at the given position doesn't have a block entity (ItemRackBlockEntity), skip default interaction.
            return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
    }


    private boolean isToolOrWeapon(ItemStack stack) {
        return stack.getItem() instanceof ToolItem ||
                stack.getItem() instanceof SwordItem ||
                stack.getItem() instanceof RangedWeaponItem ||
                stack.getItem() instanceof TridentItem ||
                stack.getItem() instanceof MaceItem ||
                stack.getItem() instanceof ShieldItem ||
                stack.getItem() instanceof FishingRodItem ||
                stack.getItem() instanceof SpyglassItem ||
                stack.getItem() instanceof GoatHornItem;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.block.can_hold").formatted(CAPTION));
        tooltip.add(ScreenTexts.space().append(Text.translatable("tooltip.cozyhome.weapons_and_tools").formatted(ENTRIES)));
    }

    // Causes the contents of the block to drop when block is broken.
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
