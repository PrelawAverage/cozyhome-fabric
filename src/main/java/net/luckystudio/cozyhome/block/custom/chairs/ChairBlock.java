package net.luckystudio.cozyhome.block.custom.chairs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.luckystudio.cozyhome.block.custom.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.interfaces.TuckableBlock;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.item.custom.CushionItem;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChairBlock extends AbstractSeatBlock implements TuckableBlock, Waterloggable {
    public static final MapCodec<ChairBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ChairBlock.ChairType.CODEC.fieldOf("kind").forGetter(ChairBlock::getChairType),
                    createSettingsCodec() // Ensure this exists and works as expected
            ).apply(instance, ChairBlock::new)
    );
    public static final BooleanProperty TUCKED = ModProperties.TUCKED;
    public static final IntProperty ROTATION = Properties.ROTATION;

    private static final VoxelShape BASE_SHAPE = ChairBlock.createCuboidShape(2,0,2,14,10,14);
    public static final VoxelShape TUCKED_SOUTH = VoxelShapes.union(
            Block.createCuboidShape(2, 0, -8, 14, 10, 4),
            Block.createCuboidShape(2, 10, 2, 14, 24, 4));
    public static final VoxelShape TUCKED_WEST = VoxelShapes.union(
            Block.createCuboidShape(12, 0, 2, 24, 10, 14),
            Block.createCuboidShape(12, 10, 2, 14, 24, 14));
    public static final VoxelShape TUCKED_NORTH = VoxelShapes.union(
            Block.createCuboidShape(2, 0, 12, 14, 10, 24),
            Block.createCuboidShape(2, 10, 12, 14, 24, 14));
    public static final VoxelShape TUCKED_EAST = VoxelShapes.union(
            Block.createCuboidShape(-8, 0, 2, 4, 10, 14),
            Block.createCuboidShape(2, 10, 2, 4, 24, 14));
    private final ChairType type;

    public ChairBlock(ChairType chairType, Settings settings) {
        super(settings);
        this.getDefaultState()
                .with(TUCKED, false)
                .with(ROTATION, 0);
        this.type = chairType;
    }


    @Override
    protected MapCodec<? extends AbstractSeatBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(TUCKED, ROTATION);
    }

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(ROTATION)) {
            case 0:
                if (state.get(TUCKED)) return TUCKED_SOUTH;
            case 4:
                if (state.get(TUCKED)) return TUCKED_WEST;
            case 8:
                if (state.get(TUCKED)) return TUCKED_NORTH;
            case 12:
                if (state.get(TUCKED)) return TUCKED_EAST;
            case null, default:
                return BASE_SHAPE;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean isSneaking = Objects.requireNonNull(ctx.getPlayer()).isSneaking();
        int rotationOffset = isSneaking ? 180 : 0;
        return Objects.requireNonNull(super.getPlacementState(ctx))
                .with(TUCKED, false)
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + rotationOffset));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ItemActionResult.SUCCESS;
        // Check if the block at the given position has an ItemRackBlockEntity associated with it.
        if (world.getBlockEntity(pos) instanceof ChairBlockEntity chairBlockEntity) {
            // Get the item stack that is currently stored in the block
            ItemStack storedItem = chairBlockEntity.getStack();
            // Check if the item in hand is a valid tool or weapon.
            if (stack.getItem() instanceof CushionItem && !stack.isEmpty() && (storedItem.isEmpty())) {
                // If the stack is not empty, and the rack is either empty or can accept the item (same type and enough space),
                // proceed to insert the item into the block.

                // Increment the player's use stat for the item in their hand.
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

                // Split the stack unless the player is in creative mode (in which case the item won't be removed).
                ItemStack itemStack2 = stack.splitUnlessCreative(1, player);

                // If the block was empty, store the item directly.
                if (chairBlockEntity.isEmpty()) {
                    chairBlockEntity.setStack(itemStack2);
                }

                if (chairBlockEntity.getStack() == ModItems.HAY_CUSHION.getDefaultStack()) {
                    world.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                } else {
                    world.playSound(player, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                // Mark the block entity as dirty, indicating it has changed.
                chairBlockEntity.markDirty();

                // Notify the world that the block state has changed and trigger the block update.
                world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                // Emit a game event to notify of the block's state change
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                // Return a successful result to stop further interaction processing.
                return ItemActionResult.SUCCESS;

            } else if (!chairBlockEntity.isEmpty() && stack.getItem() == Items.SHEARS) {
                // Get the item stack currently in the block
                ItemStack storedStack = chairBlockEntity.getStack();

                // Try to give the player the item from the block
                ItemStack itemToGive = storedStack.copy();  // Create a copy of the stored item

                // If the player can hold the item (inventory space check)
                if (player.getInventory().insertStack(itemToGive)) {
                    // Remove the item from the block (decrement the stack)
                    storedStack.decrement(1);  // Decrease the count of the item in the block

                    // If the block is now empty, clear the item rack
                    if (storedStack.isEmpty()) {
                        chairBlockEntity.setStack(ItemStack.EMPTY);
                    }

                    world.playSound(player, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    // Mark the block entity as dirty to save the changes
                    chairBlockEntity.markDirty();

                    // Notify the world about the block's state change
                    world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                    // Emit a game event to notify of the block's state change
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                    // Return a success result
                    return ItemActionResult.SUCCESS;
                }
            } else if (player.isSneaking()) {
                // Call tuckable logic or fallback to super
                TuckableBlock.toggleTuck(state, world, pos, player);
                return ItemActionResult.SUCCESS;
            } else {
                return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
            }
        }
        // If the block at the given position doesn't have a block entity (ItemRackBlockEntity), skip default interaction.
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        if (player.isSneaking() || state.get(TUCKED)) {
            // Call tuckable logic or fallback to super
            TuckableBlock.toggleTuck(state, world, pos, player);
            return ActionResult.SUCCESS;
        }
         return super.onUse(state, world, pos, player, hit);
    }

    public enum Type implements ChairType {
        OAK("oak"),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        DARK_OAK("dark_oak"),
        MANGROVE("mangrove"),
        CHERRY("cherry"),
        BAMBOO("bamboo"),
        CRIMSON("crimson"),
        WARPED("warped"),
        PRINCESS("princess"),
        IRON("iron"),
        GLASS("iron"),
        UNDEAD("undead"),
        OMINOUS("ominous");

        private final String id;

        Type(final String id) {
            this.id = id;
            TYPES.put(id, this);
        }

        @Override
        public String asString() {
            return this.id;
        }
    }

    public ChairType getChairType() {
        return this.type;
    }

    public interface ChairType extends StringIdentifiable {
        Map<String, ChairType> TYPES = new Object2ObjectArrayMap<>();
        Codec<ChairType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_hand_while_sneaking").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.can_tuck_into_certain_blocks")));
        tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_cushion").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.adds_cushion")));
    }

    // Causes the contents of the block to drop when block is broken.
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.65f;
    }

    @Override
    public float getSeatRotation(BlockState state, World world, BlockPos pos) {
        return ModProperties.setSeatRotationFromRotation(state);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(ROTATION, rotation.rotate(state.get(ROTATION), MAX_ROTATIONS));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(ROTATION, mirror.mirror(state.get(ROTATION), MAX_ROTATIONS));
    }
}
