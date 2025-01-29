package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.HasUnderBlock;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class FountainSproutBlock extends WallMountedBlock {
    public static final MapCodec<FountainSproutBlock> CODEC = createCodec(FountainSproutBlock::new);
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;
    public static final EnumProperty<HasUnderBlock> HAS_UNDER = ModProperties.HAS_UNDER;
    private int tickCounter = 0; // Counter to track ticks

    protected static final VoxelShape NORTH_WALL_SHAPE = Block.createCuboidShape(5, 10, 10, 11, 16, 16);
    protected static final VoxelShape SOUTH_WALL_SHAPE = Block.createCuboidShape(5, 10, 0, 11, 16, 6);
    protected static final VoxelShape WEST_WALL_SHAPE = Block.createCuboidShape(10, 10, 5, 16, 16, 11);
    protected static final VoxelShape EAST_WALL_SHAPE = Block.createCuboidShape(0, 10, 5, 6, 16, 11);
    protected static final VoxelShape FLOOR_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 6, 11);
    protected static final VoxelShape CEILING_SHAPE = Block.createCuboidShape(5, 10, 5, 11, 16, 11);

    public FountainSproutBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HAS_UNDER, HasUnderBlock.NONE)
                .with(FACING, Direction.NORTH)
                .with(CONTAINS, ContainsBlock.NONE)
                .with(FACE, BlockFace.WALL));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONTAINS,FACE,FACING, HAS_UNDER);
        super.appendProperties(builder);
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return CODEC;
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACE)) {
            case WALL -> switch (state.get(FACING)) {
                case EAST -> EAST_WALL_SHAPE;
                case WEST -> WEST_WALL_SHAPE;
                case SOUTH -> SOUTH_WALL_SHAPE;
                default -> NORTH_WALL_SHAPE;
            };
            case CEILING -> CEILING_SHAPE;
            default -> FLOOR_SHAPE;
        };
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify); // Call super first
        world.setBlockState(pos, state
                .with(CONTAINS, determineContains(state, world, pos))
                .with(HAS_UNDER, hasUnder(state, world, pos)));
        world.scheduleBlockTick(pos, this, 10); // Schedule the tick
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canStay(world, pos, getDirection(state).getOpposite());
    }

    public static boolean canStay(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction);
        return world.getBlockState(blockPos).getBlock() == Blocks.WATER_CAULDRON ||
                world.getBlockState(blockPos).getBlock() == Blocks.LAVA_CAULDRON ||
                world.getBlockState(blockPos).getBlock() == Blocks.CAULDRON ||
                world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction.getOpposite()) ||
                world.getBlockState(blockPos).getBlock() instanceof FountainBlock;
    }

    private void elongate(World world, BlockPos pos) {
        if (world.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
            world.setBlockState(pos.down(), ModBlocks.FALLING_LIQUID.getDefaultState());
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.scheduleBlockTick(pos, this, 20);
        if (getDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos)) return Blocks.AIR.getDefaultState();
        return state.with(CONTAINS, determineContains(state, world, pos)).with(HAS_UNDER, hasUnder(state, world, pos));
    }

    private ContainsBlock determineContains(BlockState state, WorldAccess world, BlockPos pos) {
        BlockPos targetPos = pos.offset(getDirection(state).getOpposite());
        BlockState targetState = world.getBlockState(targetPos);
        BooleanProperty property = Properties.WATERLOGGED;
        if (isFountainBlock(targetState)) {
            if (targetState.get(CONTAINS) == ContainsBlock.WATER) return ContainsBlock.WATER;
            if (targetState.get(CONTAINS) == ContainsBlock.LAVA) return ContainsBlock.LAVA;
        } else if (targetState.contains(property)) {
            if (targetState.get(property)) return ContainsBlock.WATER;
        } else if (targetState.getBlock() == Blocks.WATER_CAULDRON) {
            return ContainsBlock.WATER;
        } else if (targetState.getBlock() == Blocks.LAVA_CAULDRON) {
            return ContainsBlock.LAVA;
        }
        return ContainsBlock.NONE;
    }

    private boolean isFountainBlock(BlockState targetState) {
        return targetState.getBlock() instanceof FountainBlock;
    }

    private HasUnderBlock hasUnder(BlockState state, WorldAccess world, BlockPos pos) {
        BlockPos posBelow = pos.down();
        BlockState blockStateBelow = world.getBlockState(posBelow);
        if (state.get(FACE) != BlockFace.FLOOR) {
            if (blockStateBelow.isSideSolidFullSquare(world, pos, Direction.UP)) return HasUnderBlock.FLAT;
            if (isLowered(blockStateBelow)) return HasUnderBlock.LOWERED;
            if (isDeep(blockStateBelow)) return HasUnderBlock.DEEP;
        }
        return HasUnderBlock.NONE;
    }

    private static boolean isLowered(BlockState blockStateBelow) {
        return blockStateBelow.getBlock() instanceof LavaCauldronBlock || blockStateBelow.getBlock() instanceof FountainBlock ||
                blockStateBelow.getBlock() == Blocks.FARMLAND ||
                blockStateBelow.getBlock() instanceof FluidBlock;
    }

    private static boolean isDeep(BlockState blockStateBelow) {
        return blockStateBelow.getBlock() instanceof LeveledCauldronBlock || blockStateBelow.getBlock() instanceof CauldronBlock;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Check if the block can see the sky and spawn smoke particles if block contains lava
        if (state.get(HAS_UNDER) != HasUnderBlock.NONE) {
            if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                world.addParticle(ParticleTypes.SMOKE,
                        false,
                        pos.getX() + 0.5,
                        pos.getY() + 0.1,
                        pos.getZ() + 0.5,
                        0,
                        0,
                        0);
            }
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
                world.playSound(null, pos, ModSoundEvents.LIGHT_WATER_FLOW, SoundCategory.AMBIENT, 0.1f, 1);
                world.addParticle(ParticleTypes.CLOUD,
                        false,
                        pos.getX() + 0.5,
                        pos.getY() + 0.1,
                        pos.getZ() + 0.5,
                        0,
                        0,
                        0);
                world.addParticle(ParticleTypes.SPLASH,
                        false,
                        pos.getX() + 0.5,
                        pos.getY() + 0.1,
                        pos.getZ() + 0.5,
                        0,
                        0,
                        0);
            }
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!ModBlockUtilities.isEntityObstructing(world, pos) && ModBlockUtilities.canPlaceBelow(world, pos)) {
            elongate(world, pos);
        }
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
                if (entity.isOnFire()) entity.extinguishWithSound();
            }
            if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                entity.damage(world.getDamageSources().lava(), 4.0F);
                entity.setOnFireFor(3);
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}

