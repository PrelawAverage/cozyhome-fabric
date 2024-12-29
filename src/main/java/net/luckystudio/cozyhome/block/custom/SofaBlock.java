package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.luckystudio.cozyhome.block.custom.abstracts.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.entity.SofaBlockEntity;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.item.custom.CushionItem;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DecoratedPotBlockEntity;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SofaBlock extends AbstractSeatBlock {
    public static final MapCodec<SofaBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    SofaType.CODEC.fieldOf("kind").forGetter(SofaBlock::getSofaType),
                    createSettingsCodec() // Ensure this exists and works as expected
            ).apply(instance, SofaBlock::new)
    );
    private static final VoxelShape BASE_SHAPE = SofaBlock.createCuboidShape(0,2,0,16,8,16);
    private final SofaType type;
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;

    public SofaBlock(SofaType sofaType, Settings settings) {
        super(settings);
        this.getDefaultState();
        this.type = sofaType;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SofaBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends AbstractSeatBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
    }

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                return BASE_SHAPE;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if the block at the given position has an ItemRackBlockEntity associated with it.
        if (world.getBlockEntity(pos) instanceof SofaBlockEntity sofaBlockEntity) {
            // Get the item stack that is currently stored in the block
            ItemStack storedItem = sofaBlockEntity.getStack();

            // Check if the item in hand is a valid tool or weapon.
            if (stack.getItem() instanceof CushionItem) {
                // If the stack is not empty, and the rack is either empty or can accept the item (same type and enough space),
                // proceed to insert the item into the block.
                if (!stack.isEmpty() && (storedItem.isEmpty())) {

                    // Increment the player's use stat for the item in their hand.
                    player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

                    // Split the stack unless the player is in creative mode (in which case the item won't be removed).
                    ItemStack itemStack2 = stack.splitUnlessCreative(1, player);

                    // If the block was empty, store the item directly.
                    if (sofaBlockEntity.isEmpty()) {
                        sofaBlockEntity.setStack(itemStack2);
                    }

                    if (sofaBlockEntity.getStack() == ModItems.HAY_CUSHION.getDefaultStack()) {
                        world.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    } else {
                        world.playSound(player, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }

                    // Mark the block entity as dirty, indicating it has changed.
                    sofaBlockEntity.markDirty();

                    // Notify the world that the block state has changed and trigger the block update.
                    world.updateListeners(pos, state, state, Block.NOTIFY_ALL);

                    // Emit a game event to notify of the block's state change
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                    // Return a successful result to stop further interaction processing.
                    return ItemActionResult.SUCCESS;
                }
            } else if (!sofaBlockEntity.isEmpty() && stack.getItem() == Items.SHEARS) {
                // Get the item stack currently in the block
                ItemStack storedStack = sofaBlockEntity.getStack();

                // Try to give the player the item from the block
                ItemStack itemToGive = storedStack.copy();  // Create a copy of the stored item

                // If the player can hold the item (inventory space check)
                if (player.getInventory().insertStack(itemToGive)) {
                    // Remove the item from the block (decrement the stack)
                    storedStack.decrement(1);  // Decrease the count of the item in the block

                    // If the block is now empty, clear the item rack
                    if (storedStack.isEmpty()) {
                        sofaBlockEntity.setStack(ItemStack.EMPTY);
                    }

                    world.playSound(player, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    // Mark the block entity as dirty to save the changes
                    sofaBlockEntity.markDirty();

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
            // If no valid interaction was made, let the game handle default block interactions.
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        // If the block at the given position doesn't have a block entity (ItemRackBlockEntity), skip default interaction.
        return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
    }

    public enum Type implements SofaBlock.SofaType {
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
        WARPED("warped");

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

    public SofaBlock.SofaType getSofaType() {
        return this.type;
    }

    public interface SofaType extends StringIdentifiable {
        Map<String, SofaType> TYPES = new Object2ObjectArrayMap<>();
        Codec<SofaType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.45f;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    // Causes the contents of the block to drop when block is broken.
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockEntity(pos) instanceof SofaBlockEntity sofaBlockEntity
                ? sofaBlockEntity.asStack()
                : super.getPickStack(world, pos, state);
    }
}
