package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlockEntities;
import net.luckystudio.cozyhome.block.entity.DyeableBlockEntity;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractDyeableBlock extends BlockWithEntity {

    public AbstractDyeableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState());
    }

    @Override
    protected abstract MapCodec<? extends AbstractDyeableBlock> getCodec();

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBlockEntity(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        NbtComponent component = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);
        // Our tool tip
        if (component != null) {
            if (component.contains("color")) {
                tooltip.add(Text.translatable("tooltip.cozyhome.block.dyed").withColor(ModColorHandler.getItemColor(stack)));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.cozyhome.block.dyeable"));
        }
        // Always add this because we also want other tooltips to render too like item group and other modded tooltips
        super.appendTooltip(stack, context, tooltip, options);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        ItemStack itemStack = super.getPickStack(world, pos, state);
        world.getBlockEntity(pos, ModBlockEntities.COLOR_LAMP_BLOCK_ENTITY).ifPresent(blockEntity -> blockEntity.setStackNbt(itemStack, world.getRegistryManager()));
        return itemStack;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Simple Dyeing of the Block functionality.
        if (stack.getItem() instanceof DyeItem dyeItem) {
            if (world.getBlockEntity(pos) instanceof DyeableBlockEntity dyeableBlockEntity) {
                final int newColor = dyeItem.getColor().getEntityColor();
                final int originalColor = dyeableBlockEntity.color;
                dyeableBlockEntity.color = ColorHelper.Argb.averageArgb(newColor, originalColor);
                stack.decrementUnlessCreative(1, player);
                dyeableBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
