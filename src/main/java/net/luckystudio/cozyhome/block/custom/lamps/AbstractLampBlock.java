package net.luckystudio.cozyhome.block.custom.lamps;

import net.luckystudio.cozyhome.block.block_entity.LampBlockEntity;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractLampBlock extends BlockWithEntity implements ConnectingBlock {
    public static final EnumProperty<VerticalLinearConnectionBlock> CONNECTION = ModProperties.VERTICAL_CONNECTION;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public AbstractLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(CONNECTION, VerticalLinearConnectionBlock.SINGLE)
                .with(FACING, Direction.NORTH)
                .with(LIT, Boolean.FALSE));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LampBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONNECTION, FACING, LIT);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (world.getBlockState(pos.down()).getBlock() == this) {
            if (world.getBlockState(pos.down(2)).getBlock() == this) {
                return world.getBlockState(pos.down(2)).get(CONNECTION) != VerticalLinearConnectionBlock.MIDDLE;
            } else {
                return true;
            }
        } else {
            return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
        }
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction;
        if (canPlaceAt(ctx.getWorld().getBlockState(ctx.getBlockPos()), ctx.getWorld(), ctx.getBlockPos())) {
            if (ctx.getWorld().getBlockState(ctx.getBlockPos().down()).getBlock() == this) {
                direction = ctx.getWorld().getBlockState(ctx.getBlockPos().down()).get(FACING);
                return this.getDefaultState()
                        .with(CONNECTION, VerticalLinearConnectionBlock.HEAD)
                        .with(FACING, direction)
                        .with(LIT, ctx.getWorld().getBlockState(ctx.getBlockPos().down()).get(LIT));
            }
        }
        return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof LampBlockEntity lampBlockEntity) {
            VerticalLinearConnectionBlock connection = state.get(CONNECTION);
                if (stack.getItem() instanceof DyeItem dyeItem) {
                    final int itemColor = dyeItem.getColor().getEntityColor();
                    final int blockColor = ModColorHandler.getBlockColor(lampBlockEntity, -17170434);
                    final int newColor = ColorHelper.Argb.averageArgb(blockColor, itemColor);
                    if (blockColor == newColor) {
                        player.sendMessage(Text.translatable("message.cozyhome.same_color"), true);
                        return ItemActionResult.SUCCESS;
                    }
                    ComponentMap components = ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(newColor, false)).build();
                    lampBlockEntity.setComponents(components);
                    stack.decrementUnlessCreative(1, player);
                    lampBlockEntity.markDirty();
                    world.updateListeners(pos, state, state, 0);
                } else if (stack.getItem() == this.asItem() && hit.getSide() == Direction.UP) {
                    return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                } else {
                    state = state.cycle(LIT);
                    float f = state.get(LIT) ? 1.0F : 0.8F;
                    if (connection == VerticalLinearConnectionBlock.HEAD) {
                        world.setBlockState(pos.down(), state, Block.NOTIFY_ALL);
                        if (world.getBlockState(pos.down(2)).getBlock() == this) {
                            world.setBlockState(pos.down(2), state, Block.NOTIFY_ALL);
                        }
                    } else if (connection == VerticalLinearConnectionBlock.MIDDLE) {
                        world.setBlockState(pos.up(), state, Block.NOTIFY_ALL);
                        world.setBlockState(pos.down(), state, Block.NOTIFY_ALL);
                    } else if (connection == VerticalLinearConnectionBlock.TAIL) {
                        world.setBlockState(pos.up(), state, Block.NOTIFY_ALL);
                        if (world.getBlockState(pos.up(2)).getBlock() == this) {
                            world.setBlockState(pos.up(2), state, Block.NOTIFY_ALL);
                        }
                    }
                    world.setBlockState(pos, state, Block.NOTIFY_ALL);
                    world.playSound(player, pos, ModSoundEvents.LAMP_TOGGLE, SoundCategory.BLOCKS, 0.3F, f);
                }
                return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return canPlaceAt(state, world, pos) ? state.with(CONNECTION, ModProperties.setVerticalConnection(state, world, pos)) : Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean isMatchingBlock(BlockState targetState) {
        return targetState.getBlock() == this;
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
        tooltip.add(Text.translatable("tooltip.cozyhome.dyeable").formatted(Formatting.GRAY).append(""));
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_hand").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.toggle_light")));
        tooltip.add(Text.translatable("tooltip.cozyhome.interact_with_dye").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.sets_block_color")));
    }
}
