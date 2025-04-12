package net.luckystudio.cozyhome.block.custom.sinks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractLiquidHoldingBlock extends Block implements WaterHoldingBlock {

    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final IntProperty NEXT_LEVEL = ModProperties.NEXT_LEVEL_TIMER;

    public static final VoxelShape COUNTER_TOP = Block.createCuboidShape(0, 12, 0, 16, 16, 16);

    public AbstractLiquidHoldingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(CONTAINS, ContainsBlock.NONE)
                .with(TRIGGERED, false)
                .with(NEXT_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(CONTAINS, TRIGGERED, NEXT_LEVEL));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int nextLevel = state.get(NEXT_LEVEL);
        ContainsBlock contains = state.get(CONTAINS);
        if (!isFull(state) && getPullingFluid(state, world, pos) != ContainsBlock.NONE) {
            if (nextLevel == 20) {
                addLiquid(state, world, pos, 1, getPullingFluid(state, world, pos));
            } else {
                world.setBlockState(pos, state.with(NEXT_LEVEL, nextLevel + 1), 3);
            }
            if (contains == ContainsBlock.WATER || contains == ContainsBlock.LAVA) {
                boolean isWater = contains == ContainsBlock.WATER;

                ParticleEffect dripParticle = isWater ? ParticleTypes.FALLING_DRIPSTONE_WATER : ParticleTypes.FALLING_DRIPSTONE_LAVA;
                ParticleEffect splashParticle = isWater ? ParticleTypes.SPLASH : ParticleTypes.LANDING_LAVA;

                Vec3d centerTop = Vec3d.ofCenter(pos).add(0.0, 0.6875, 0.0); // Y + 1.1875 - 0.5 (since ofCenter adds 0.5 already)
                Vec3d levelCenter = new Vec3d(pos.getX() + 0.5, pos.getY() + getLiquidLevelHeight(state), pos.getZ() + 0.5);

                world.spawnParticles(dripParticle, centerTop.x, centerTop.y, centerTop.z, 1, 0.0, 0.0, 0.0, 0.0);
                world.spawnParticles(splashParticle, levelCenter.x, levelCenter.y, levelCenter.z, 1, 0.0, 0.0, 0.0, 0.0);
            }
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
                world.spawnParticles(ParticleTypes.FALLING_DRIPSTONE_WATER,
                        pos.getX() + 0.5, pos.getY() + 1.1875, pos.getZ() + 0.5, // Position (center of block)
                        1, // Number of particles
                        0.0, 0.0, 0.0, // Offset for randomness
                        0.0 // Speed/velocity
                );
                world.spawnParticles(ParticleTypes.SPLASH,
                        pos.getX() + 0.5, pos.getY() + getLiquidLevelHeight(state), pos.getZ() + 0.5, // Position (center of block)
                        1, // Number of particles
                        0.0, 0.0, 0.0, // Offset for randomness
                        0.0 // Speed/velocity
                );
            } else if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                world.spawnParticles(ParticleTypes.FALLING_DRIPSTONE_LAVA,
                        pos.getX() + 0.5, pos.getY() + 1.1875, pos.getZ() + 0.5, // Position (center of block)
                        1, // Number of particles
                        0.0, 0.0, 0.0, // Offset for randomness
                        0.0 // Speed/velocity
                );
                world.spawnParticles(ParticleTypes.LANDING_LAVA,
                        pos.getX() + 0.5, pos.getY() + getLiquidLevelHeight(state), pos.getZ() + 0.5, // Position (center of block)
                        1, // Number of particles
                        0.0, 0.0, 0.0, // Offset for randomness
                        0.0 // Speed/velocity
                );
            }
            world.scheduleBlockTick(pos, this, 1);
        } else {
            world.setBlockState(pos, state
                    .with(TRIGGERED, false)
                    .with(NEXT_LEVEL, 0), 3);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        if (isFull(state)) {
            player.sendMessage(Text.translatable("message.cozyhome.full"), true);
            return ActionResult.SUCCESS;
        } else {
            if (hasLiquidToPull(state, world, pos)) {
                this.togglePower(state, world, pos, player);
                world.scheduleBlockTick(pos, this, 1);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.translatable("message.cozyhome.needs_water"), true);
            }
        }
        return ActionResult.CONSUME;
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
                .with(CONTAINS, ContainsBlock.NONE)
                .with(NEXT_LEVEL, 0)
                .with(TRIGGERED, false);
    }

    @Override
    public abstract float getLiquidLevelHeight(BlockState state);

    private ContainsBlock getPullingFluid(BlockState state, World world, BlockPos pos) {
        for (Direction direction : sidesToPull(state)) {
            BlockPos offset = pos.offset(direction);
            BlockState fluidState = world.getBlockState(offset);
            if (fluidState.getFluidState().isStill()) {
                return fluidState.get(CONTAINS);
            }
        }
        return ContainsBlock.NONE;
    }
}
