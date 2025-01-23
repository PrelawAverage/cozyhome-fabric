package net.luckystudio.cozyhome.block.custom.counters;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.interfaces.SinkBehavior;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class AbstractSinkBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_3;
    public static final IntProperty NEXT_LEVEL = ModProperties.NEXT_LEVEL;
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final VoxelShape COUNTER_TOP = Block.createCuboidShape(0, 12, 0, 16, 16, 16);

    public AbstractSinkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(TRIGGERED, false)
                .with(LEVEL, 0)
                .with(NEXT_LEVEL, 0)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED, LEVEL, NEXT_LEVEL, FACING);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int level = state.get(LEVEL);
        int nextLevel = state.get(NEXT_LEVEL);
        if (level < 3) {
            if (nextLevel == 20) {
                world.setBlockState(pos, state
                        .with(LEVEL, level + 1)
                        .with(NEXT_LEVEL, 0), 3);
            } else {
                world.setBlockState(pos, state.with(NEXT_LEVEL, nextLevel + 1), 3);
            }
            world.spawnParticles(ParticleTypes.FALLING_DRIPSTONE_WATER,
                    pos.getX() + 0.5, pos.getY() + 1.1875, pos.getZ() + 0.5, // Position (center of block)
                    1, // Number of particles
                    0.0, 0.0, 0.0, // Offset for randomness
                    0.0 // Speed/velocity
            );
            world.spawnParticles(ParticleTypes.SPLASH,
                    pos.getX() + 0.5, pos.getY() + getWaterLevel(state), pos.getZ() + 0.5, // Position (center of block)
                    1, // Number of particles
                    0.0, 0.0, 0.0, // Offset for randomness
                    0.0 // Speed/velocity
            );
            world.scheduleBlockTick(pos, this, 1);
        } else {
            world.setBlockState(pos, state
                    .with(TRIGGERED, false)
                    .with(NEXT_LEVEL, 0), 3);
        }
    }

    private static float getWaterLevel(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 1 -> 0.438f;
            case 2 -> 0.688f;
            case 3 -> 0.938f;
            default -> 0.125f;
        };
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        SinkBehavior sinkBehavior = SinkBehavior.BASE.map().get(stack.getItem());
        return sinkBehavior.interact(state, world, pos, player, hand, stack);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        if (hasWaterToPull(state, world, pos)) {
            this.togglePower(state, world, pos, player);
            world.scheduleBlockTick(pos, this, 1);
            return ActionResult.SUCCESS;
        }
        player.sendMessage(Text.translatable("message.cozyhome.needs_water"), true);
        return ActionResult.CONSUME;
    }

    private static boolean hasWaterToPull(BlockState state, World world, BlockPos pos) {
        return isWaterLogged(world, pos.down()) || isWaterLogged(world, pos.offset(state.get(FACING)));
    }

    private static boolean isWaterLogged(World world, BlockPos pos) {
        return world.getFluidState(pos).getFluid().matchesType(Fluids.WATER);
    }

    private void togglePower(BlockState state, World world, BlockPos pos, @Nullable PlayerEntity player) {
        state = state.cycle(TRIGGERED);
        world.setBlockState(pos, state, 3);
        this.updateNeighbors(world, pos);
        playClickSound(player, world, pos, state);
        world.emitGameEvent(player, state.get(TRIGGERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }

    protected static void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        float f = state.get(TRIGGERED) ? 1.0F : 0.8F;
        world.playSound(player, pos, ModSoundEvents.SINK_TOGGLE, SoundCategory.BLOCKS, 0.3F, f);
    }

    private void updateNeighbors(World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(TRIGGERED, false);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
}
