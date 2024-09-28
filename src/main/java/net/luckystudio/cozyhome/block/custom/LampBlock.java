package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.blockstates.LinearConnectionBlock;
import net.luckystudio.cozyhome.sound.ModSounds;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
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

import java.util.Objects;

public class LampBlock extends Block {
    public static final MapCodec<LampBlock> CODEC = createCodec(LampBlock::new);
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final EnumProperty<LinearConnectionBlock> STACKABLE_BLOCK = ModProperties.LINEAR_CONNECTION_BLOCK;
    protected static final VoxelShape SINGLE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 14.0, 13.0);
    protected static final VoxelShape POLE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    protected static final VoxelShape BASE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 2.0, 12.0),
            POLE);
    protected static final VoxelShape HEAD = VoxelShapes.union(
            Block.createCuboidShape(3.0, 4.0, 3.0, 13.0, 14.0, 13.0),
            Block.createCuboidShape(6,0,6,10,4,10));
    public static final IntProperty OMNI_ROTATION = ModProperties.OMNI_ROTATION;
    public LampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(LIT, Boolean.FALSE)
                .with(OMNI_ROTATION, 0)
                .with(STACKABLE_BLOCK, LinearConnectionBlock.SINGLE));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(STACKABLE_BLOCK)) {
            case SINGLE -> SINGLE;
            case HEAD -> HEAD;
            case MIDDLE -> POLE;
            case TAIL -> BASE;
        };
    }

    @Override
    public float getHardness() {
        return 0.1f;
    }

    @Override
    public float getBlastResistance() {
        return 0.1f;
    }

    @Override
    public MapCodec<LampBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, OMNI_ROTATION, STACKABLE_BLOCK);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int rotation = ModProperties.getOmniRotation(RotationPropertyHelper.fromYaw(ctx.getPlayerYaw()));
        return this.getDefaultState()
                .with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()))
                .with(OMNI_ROTATION, rotation)
                .with(STACKABLE_BLOCK, LinearConnectionBlock.SINGLE);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        LinearConnectionBlock type = state.get(STACKABLE_BLOCK);
        boolean isLightEmittingBlock = type == LinearConnectionBlock.HEAD || type == LinearConnectionBlock.SINGLE;
        if (world.isClient) {
            if (canStackInstead(isLightEmittingBlock, stack, state, hit)) {
                return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
            } else if (isLightEmittingBlock) {
                {
                    return ItemActionResult.SUCCESS;
                }
            }
        } else {
            if (canStackInstead(isLightEmittingBlock, stack, state, hit)) {
                return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
            } else if (isLightEmittingBlock) {
                 {
                    this.toggleLight(state, world, pos, null);
                    return ItemActionResult.SUCCESS;
                 }
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private boolean canStackInstead(boolean isLightEmittingBlock, ItemStack stack,  BlockState state, BlockHitResult hit) {
        return isLightEmittingBlock && stack.isOf(state.getBlock().asItem()) && hit.getSide() == Direction.UP;
    }

    private boolean isToggleLight(boolean isLightEmittingBlock, ItemStack stack,  BlockState state, BlockHitResult hit) {
        return isLightEmittingBlock && !stack.isOf(state.getBlock().asItem()) && hit.getSide() == Direction.UP;
    }

    public void toggleLight(BlockState state, World world, BlockPos pos, @Nullable PlayerEntity player) {
        state = state.cycle(LIT);
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        playClickSound(player, world, pos, state);
        world.emitGameEvent(player, state.get(LIT) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
    }

    protected static void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        // Just alters the pitch when the lamp is being turned on and off.
        float f = state.get(LIT) ? 1F : 0.9F;
        world.playSound(player, pos, ModSounds.LAMP_TOGGLE, SoundCategory.BLOCKS, 1F, f);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        // Getting the states of the block above and below our block
        BlockState getBlockAbove = world.getBlockState(pos.up());
        BlockState getBlockBelow = world.getBlockState(pos.down());
        // Getting Vertical Connection Types based on the states of the block above and below.
        LinearConnectionBlock linearConnectionBlockType = getLinearConnectionBlockType(state, getBlockAbove, getBlockBelow);
        // Checking if the block is on before we make changes
        boolean wasOn = state.get(LIT);
        // Creating the new state to update the block to.
        BlockState updatedState =
                state
                        .with(STACKABLE_BLOCK, linearConnectionBlockType)
                        .with(LIT, didShapeChange(state, linearConnectionBlockType, wasOn))
                        .with(OMNI_ROTATION, adjustRotation(state, getBlockBelow));
        // Updating the block to the new state
        world.setBlockState(pos, updatedState, 3);
    }

    private boolean didShapeChange(BlockState state, LinearConnectionBlock linearConnectionBlockType, boolean wasOn) {
        return wasOn && (state.get(STACKABLE_BLOCK) != linearConnectionBlockType);
    }

    private Integer adjustRotation(BlockState state, BlockState blockbelow) {
        if (blockbelow.getBlock() == state.getBlock()) {
            return blockbelow.get(OMNI_ROTATION);
        } else {
            return state.get(OMNI_ROTATION);
        }
    }

    private LinearConnectionBlock getLinearConnectionBlockType(BlockState state, BlockState getBlockAbove, BlockState getBlockBelow) {
        boolean above = getBlockAbove.getBlock() == state.getBlock();
        boolean below = getBlockBelow.getBlock() == state.getBlock();

        if (above && below) return LinearConnectionBlock.MIDDLE;
        if (above) return LinearConnectionBlock.TAIL;
        if (below) return LinearConnectionBlock.HEAD;
        return LinearConnectionBlock.SINGLE;
    }
}
