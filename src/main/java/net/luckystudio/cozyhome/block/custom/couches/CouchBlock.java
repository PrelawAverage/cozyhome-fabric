package net.luckystudio.cozyhome.block.custom.couches;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.custom.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.HorizontalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.item.custom.CushionItem;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.StairShape;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static net.luckystudio.cozyhome.block.util.ModProperties.setStairShapeNoFlip;

public class CouchBlock extends AbstractSeatBlock implements ConnectingBlock {
    public static final MapCodec<CouchBlock> CODEC = createCodec(CouchBlock::new);

    public static final EnumProperty<HorizontalLinearConnectionBlock> CONNECTION = ModProperties.HORIZONTAL_CONNECTION;
    public static final EnumProperty<StairShape> SHAPE = Properties.STAIR_SHAPE;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    private static final VoxelShape BASE_SHAPE = CouchBlock.createCuboidShape(0, 2, 0, 16, 8, 16);

    public CouchBlock(Settings settings) {
        super(settings);
        this.getDefaultState()
                .with(CONNECTION, HorizontalLinearConnectionBlock.SINGLE)
                .with(SHAPE, StairShape.STRAIGHT)
                .with(FACING, Direction.NORTH);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CouchBlockEntity(pos,state);
    }

    @Override
    protected MapCodec<? extends AbstractSeatBlock> getCodec() {
        return CODEC;
    }

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, CONNECTION, SHAPE);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx))
                .with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
                .with(CONNECTION, HorizontalLinearConnectionBlock.setHorizontalConnection(state, world, pos))
                .with(SHAPE, setStairShapeNoFlip(state, world, pos));
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if the block at the given position has an ItemRackBlockEntity associated with it.
        if (world.getBlockEntity(pos) instanceof CouchBlockEntity couchBlockEntity) {
            // Get the item stack that is currently stored in the block
            ItemStack storedItem = couchBlockEntity.getStack();

            if (stack.getItem() instanceof DyeItem dyeItem) {
                final int itemColor = dyeItem.getColor().getEntityColor();
                final int blockColor = ModColorHandler.getBlockColor(couchBlockEntity, -17170434);
                final int newColor = ColorHelper.Argb.averageArgb(blockColor, itemColor);
                if (blockColor == newColor) {
                    player.sendMessage(Text.translatable("message.cozyhome.same_color"), true);
                    return ItemActionResult.SUCCESS;
                }
                ComponentMap components = ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(newColor, false)).build();
                couchBlockEntity.setComponents(components);

                stack.decrementUnlessCreative(1, player);
                couchBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
                return ItemActionResult.SUCCESS;
            }

            // Check if the item in hand is a valid tool or weapon.
            if (stack.getItem() instanceof CushionItem && !stack.isEmpty() && (storedItem.isEmpty())) {
                // If the stack is not empty, and the rack is either empty or can accept the item (same type and enough space),
                // proceed to insert the item into the block.

                // Increment the player's use stat for the item in their hand.
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

                // Split the stack unless the player is in creative mode (in which case the item won't be removed).
                ItemStack itemStack2 = stack.splitUnlessCreative(1, player);

                // If the block was empty, store the item directly.
                if (couchBlockEntity.isEmpty()) {
                    couchBlockEntity.setStack(itemStack2);
                }

                if (couchBlockEntity.getStack() == ModItems.HAY_CUSHION.getDefaultStack()) {
                    world.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                } else {
                    world.playSound(player, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                // Mark the block entity as dirty, indicating it has changed.
                couchBlockEntity.markDirty();

                // Notify the world that the block state has changed and trigger the block update.
                world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                // Emit a game event to notify of the block's state change
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                // Return a successful result to stop further interaction processing.
                return ItemActionResult.SUCCESS;

            } else if (!couchBlockEntity.isEmpty() && stack.getItem() == Items.SHEARS) {
                // Get the item stack currently in the block
                ItemStack storedStack = couchBlockEntity.getStack();

                // Try to give the player the item from the block
                ItemStack itemToGive = storedStack.copy();  // Create a copy of the stored item

                // If the player can hold the item (inventory space check)
                if (player.getInventory().insertStack(itemToGive)) {
                    // Remove the item from the block (decrement the stack)
                    storedStack.decrement(1);  // Decrease the count of the item in the block

                    // If the block is now empty, clear the item rack
                    if (storedStack.isEmpty()) {
                        couchBlockEntity.setStack(ItemStack.EMPTY);
                    }

                    world.playSound(player, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    // Mark the block entity as dirty to save the changes
                    couchBlockEntity.markDirty();

                    // Notify the world about the block's state change
                    world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                    // Emit a game event to notify of the block's state change
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                    // Return a success result
                    return ItemActionResult.SUCCESS;
                }
            } else {
                return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
            }
        }
        return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
    }

    // Causes the contents of the block to drop when block is broken.
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance * 0.5F);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounceEntity(entity);
        }
    }

    private void bounceEntity(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0) {
            double d = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * 0.66F * d, vec3d.z);
        }
    }

    @Override
    public float getSeatRotation(BlockState state, World world, BlockPos pos) {
        return ModProperties.setSeatRotationFromShape(state) + 180;
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.4f;
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() instanceof CouchBlock;
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("tooltip.cozyhome.dyeable").formatted(Formatting.GRAY));
    }
}
