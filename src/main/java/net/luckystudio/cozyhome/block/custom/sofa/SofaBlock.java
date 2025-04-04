package net.luckystudio.cozyhome.block.custom.sofa;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.luckystudio.cozyhome.block.custom.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.item.custom.CushionItem;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class SofaBlock extends AbstractSeatBlock {
    public static final MapCodec<SofaBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    SofaBlock.SofaType.CODEC.fieldOf("kind").forGetter(SofaBlock::getSofaType),
                    createSettingsCodec() // Ensure this exists and works as expected
            ).apply(instance, SofaBlock::new)
    );

    public static final IntProperty ROTATION = Properties.ROTATION;

    private static final VoxelShape BASE_SHAPE = SofaBlock.createCuboidShape(0, 2, 0, 16, 8, 16);
    private final SofaBlock.SofaType type;
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;

    public SofaBlock(SofaBlock.SofaType SofaType, Settings settings) {
        super(settings);
        this.getDefaultState().with(ROTATION, 0);
        this.type = SofaType;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SofaBlockEntity(pos,state);
    }

    @Override
    protected MapCodec<? extends AbstractSeatBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ROTATION);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean isSneaking = ctx.getPlayer().isSneaking();
        int rotationOffset = isSneaking ? 180 : 0;
        return super.getPlacementState(ctx)
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + rotationOffset));
    }

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE_SHAPE;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if the block at the given position has an ItemRackBlockEntity associated with it.
        if (world.getBlockEntity(pos) instanceof SofaBlockEntity sofaBlockEntity) {
            if (stack.getItem() instanceof DyeItem dyeItem) {
                final int itemColor = dyeItem.getColor().getEntityColor();
                final int blockColor = ModColorHandler.getBlockColor(sofaBlockEntity, -17170434);
                final int newColor = ColorHelper.Argb.averageArgb(blockColor, itemColor);
                if (blockColor == newColor) {
                    player.sendMessage(Text.translatable("message.cozyhome.same_color"), true);
                    return ItemActionResult.SUCCESS;
                }
                ComponentMap components = ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(newColor, false)).build();
                sofaBlockEntity.setComponents(components);

                stack.decrementUnlessCreative(1, player);
                sofaBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
                return ItemActionResult.SUCCESS;
            }

            // Check if the item in hand is a valid tool or weapon.
            if (stack.getItem() instanceof CushionItem) {
                // Get the item stack that is currently stored in the block
                ItemStack storedItem = sofaBlockEntity.getStack();
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

    public SofaBlock.SofaType getSofaType() {
        return this.type;
    }

    public interface SofaType extends StringIdentifiable {
        Map<String, SofaBlock.SofaType> TYPES = new Object2ObjectArrayMap<>();
        Codec<SofaBlock.SofaType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        String type = stack.getOrDefault(ModDataComponents.CUSHION_TYPE, "");
        if (!type.isEmpty()) {
            tooltip.add(ScreenTexts.EMPTY);
            tooltip.add(Text.translatable("tooltip.cozyhome.has").formatted(TITLE_FORMATTING));
            if (type.equals("generic")) {
                tooltip.add(ScreenTexts.space().append(Text.translatable("item.cozyhome.cushion").formatted(TITLE_FORMATTING)));
            }
        }
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
        return ModProperties.setSeatRotationFromRotation(state);
    }
}
