package net.luckystudio.cozyhome.block.abstracts;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlockEntities;
import net.luckystudio.cozyhome.block.entity.DyeableBlockEntity;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.LinearConnectionBlock;
import net.luckystudio.cozyhome.sound.ModSounds;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbstractDyeableBlock extends BlockWithEntity {

    public AbstractDyeableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState());
    }

    @Override
    protected MapCodec<? extends AbstractDyeableBlock> getCodec() {
        return createCodec(AbstractDyeableBlock::new);
    }

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

//    @Override
//    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        Item itemStack = state.getBlock().asItem();
//        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.OAK_LAMP.asItem()).copyComponentsToNewStack(itemStack,1));
//        world.spawnEntity(itemEntity);
//        return super.onBreak(world, pos, state, player);
//    }
}
