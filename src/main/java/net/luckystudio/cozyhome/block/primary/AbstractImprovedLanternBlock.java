package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class AbstractImprovedLanternBlock extends Block {
    public static final MapCodec<LanternBlock> CODEC = createCodec(LanternBlock::new);
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final IntProperty OMNI_ROTATION = ModProperties.OMNI_ROTATION;

    @Override
    public MapCodec<LanternBlock> getCodec() {
        return CODEC;
    }

    public AbstractImprovedLanternBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HANGING, Boolean.FALSE)
                .with(LIT, false)
                .with(OMNI_ROTATION, 0));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int rotation = ModProperties.getOmniRotation(RotationPropertyHelper.fromYaw(ctx.getPlayerYaw()));
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState()
                        .with(OMNI_ROTATION, rotation)
                        .with(LIT, false)
                        .with(HANGING, direction == Direction.UP);
            }
        }
        return null;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            toggleLight(state, world, pos, player);
        return stack.getItem() instanceof BlockItem && new ItemPlacementContext(player, hand, stack, hit).canPlace()
                ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                : ItemActionResult.SUCCESS;
    }

    public void toggleLight(BlockState state, World world, BlockPos pos, @Nullable PlayerEntity player) {
        state = state.cycle(LIT);
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        playClickSound(player, world, pos, state);
        world.emitGameEvent(player, state.get(LIT) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }


    protected static void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        // Just alters the pitch when the lamp is being turned on and off.
        float f = state.get(LIT) ? 1F : 0.9F;
        world.playSound(player, pos, ModSoundEvents.LAMP_TOGGLE, SoundCategory.BLOCKS, 1F, f);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, LIT, OMNI_ROTATION);
    }
}
