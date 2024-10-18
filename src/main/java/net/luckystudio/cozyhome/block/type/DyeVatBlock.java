package net.luckystudio.cozyhome.block.type;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.luckystudio.cozyhome.block.ModBlockEntities;
import net.luckystudio.cozyhome.block.abstracts.AbstractDyeableBlock;
import net.luckystudio.cozyhome.block.entity.DyeVatBlockEntity;
import net.luckystudio.cozyhome.block.entity.DyeableBlockEntity;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DyeVatBlock extends AbstractDyeableBlock {
    public static final MapCodec<DyeVatBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.blockState), createSettingsCodec())
                    .apply(instance, DyeVatBlock::new));
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty FILLED_LEVEL = ModProperties.FILLED_LEVEL_0_5;
    protected final BlockState blockState;

    public static final VoxelShape BASE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 16, 16));
    public static final VoxelShape CUTOUT = VoxelShapes.union(Block.createCuboidShape(3, 2, 3, 13, 16, 13));
    public static final VoxelShape SHAPE = VoxelShapes.combine(BASE, CUTOUT, BooleanBiFunction.ONLY_FIRST);

    public DyeVatBlock(BlockState state, Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager()
                .getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(TRIGGERED, false)
                .with(FILLED_LEVEL, 0));
        this.blockState = state;
    }

    @Override
    protected MapCodec<? extends DyeVatBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TRIGGERED, FILLED_LEVEL);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(TRIGGERED, false)
                .with(FILLED_LEVEL, 0);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeVatBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    // Needs to handle GUI, detect when dyeable item is clicked on it.
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    /*
    This Method adds the feature where if a DyeItem is dropped inside the Dye Vat it will add the color to the Dye Vat.
    Which allows for automatic Dyeing with hoppers.
    */
    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            Item item = itemEntity.getStack().getItem();
            if (item instanceof DyeItem dyeItem) {
                if (world.getBlockEntity(pos) instanceof DyeVatBlockEntity colorBlockEntity) {
                    final int newColor = dyeItem.getColor().getEntityColor();
                    final int originalColor = colorBlockEntity.color;
                    colorBlockEntity.color = ColorHelper.Argb.averageArgb(newColor, originalColor);
                    itemEntity.remove(Entity.RemovalReason.DISCARDED);
                    colorBlockEntity.markDirty();
                    world.updateListeners(pos, state, state, 0);
                }
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }



    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        return;
    }
}
