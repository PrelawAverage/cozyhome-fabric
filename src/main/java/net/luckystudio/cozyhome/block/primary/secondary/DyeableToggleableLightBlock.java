package net.luckystudio.cozyhome.block.primary.secondary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.primary.AbstractDyeableBlock;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class DyeableToggleableLightBlock extends AbstractDyeableBlock {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public DyeableToggleableLightBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(LIT, Boolean.FALSE));
    }

    @Override
    protected MapCodec<? extends DyeableToggleableLightBlock> getCodec() {
        return createCodec(DyeableToggleableLightBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(LIT, false);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public void toggleLight(BlockState state, World world, BlockPos pos, @Nullable PlayerEntity player) {
        state = state.cycle(LIT);
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        this.updateNeighbors(world, pos);
        playClickSound(player, world, pos, state);
        world.emitGameEvent(player, state.get(LIT) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }

    private void updateNeighbors(World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
    }

    protected static void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        // Just alters the pitch when the lamp is being turned on and off.
        float f = state.get(LIT) ? 1F : 0.9F;
        world.playSound(player, pos, ModSoundEvents.LAMP_TOGGLE, SoundCategory.BLOCKS, 1F, f);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            ItemStack itemStack = super.getPickStack(world, pos, state);
            dropStack((World)world, pos, itemStack);
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.isCreative()) {
            ItemStack itemStack = super.getPickStack(world, pos, state);
            dropStack(world, pos, itemStack);
        }
        return super.onBreak(world, pos, state, player);
    }
}
