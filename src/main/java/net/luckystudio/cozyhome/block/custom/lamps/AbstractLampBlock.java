package net.luckystudio.cozyhome.block.custom.lamps;

import net.luckystudio.cozyhome.block.entity.CouchBlockEntity;
import net.luckystudio.cozyhome.block.entity.LampBlockEntity;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractLampBlock extends BlockWithEntity implements ConnectingBlock {
    public static final EnumProperty<VerticalLinearConnectionBlock> CONNECTION = ModProperties.VERTICAL_CONNECTION;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(4, 4, 4, 12, 14, 12);
    public static final VoxelShape BOTTOM_PIECE = Block.createCuboidShape(4, 0, 4, 12, 2, 12);

    public static final VoxelShape SINGLE_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 14, 12);
    public static final VoxelShape TOP_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(6, 0, 6, 10, 4, 10));
    public static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(6, 0, 6, 10, 16, 10);
    public static final VoxelShape BOTTOM_SHAPE = VoxelShapes.union(BOTTOM_PIECE, Block.createCuboidShape(6, 2, 6, 10, 16, 10));

    public AbstractLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(CONNECTION, VerticalLinearConnectionBlock.SINGLE)
                .with(LIT, Boolean.valueOf(false)));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LampBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONNECTION, LIT);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(CONNECTION)) {
            case HEAD -> TOP_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            case TAIL -> BOTTOM_SHAPE;
            default -> SINGLE_SHAPE;
        };
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ItemActionResult.SUCCESS;
        if (world.getBlockEntity(pos) instanceof CouchBlockEntity couchBlockEntity) {
            if (state.get(CONNECTION) == VerticalLinearConnectionBlock.HEAD) {
                if (stack.getItem() instanceof DyeItem dyeItem) {
                    final int itemColor = dyeItem.getColor().getEntityColor();
                    final int blockColor = ModColorHandler.getBlockColor(couchBlockEntity, -17170434);
                    final int newColor = ColorHelper.Argb.averageArgb(blockColor, itemColor);
                    if (blockColor == newColor) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                    ComponentMap components = ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(newColor, false)).build();
                    couchBlockEntity.setComponents(components);
                    stack.decrementUnlessCreative(1, player);
                    couchBlockEntity.markDirty();
                    world.updateListeners(pos, state, state, 0);
                    return ItemActionResult.SUCCESS;
                } else if (stack.getItem() instanceof FlintAndSteelItem) {
                    state = state.with(LIT, true);
                    world.setBlockState(pos, state, Block.NOTIFY_ALL);
                    world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 0.3F, 1);
                    return ItemActionResult.CONSUME;
                } else if (state.get(LIT)) {
                    state = state.with(LIT, false);
                    world.setBlockState(pos, state, Block.NOTIFY_ALL);
                    world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.3F, 1);
                    return ItemActionResult.CONSUME;
                }
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(CONNECTION, ModProperties.setVerticalConnection(state, world, pos));
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() == this;
    }
}
