package net.luckystudio.cozyhome.block.custom;

import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.custom.fountains.FountainSpoutBlock;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FallingLiquidBlock extends Block {
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;
    public static final BooleanProperty HAS_UNDER = ModProperties.HAS_UNDER;
    public static final VoxelShape BASE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 0, 0, 0));
    public FallingLiquidBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(CONTAINS, ContainsBlock.NONE)
                .with(HAS_UNDER, false));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONTAINS, HAS_UNDER);
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.setBlockState(pos, state
                .with(CONTAINS, determineContains(world, pos))
                .with(HAS_UNDER, hasUnder(world, pos)));
        world.scheduleBlockTick(pos, this, 10);
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        world.scheduleBlockTick(pos, this, 10);
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if (!canStay(state, world, pos)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        } else if (!ModBlockUtilities.isEntityObstructing(world, pos) && ModBlockUtilities.canPlaceBelow(world, pos)) {
            world.setBlockState(pos.down(), ModBlocks.FALLING_LIQUID.getDefaultState());
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(CONTAINS, determineContains((World) world, pos))
                .with(HAS_UNDER, hasUnder(world, pos));
    }

    private static boolean canStay(BlockState state, World world, BlockPos pos) {
        BlockPos posAbove = pos.up();
        BlockState blockStateAbove = world.getBlockState(posAbove);
        return (blockStateAbove.getBlock() instanceof FountainSpoutBlock || blockStateAbove.getBlock() instanceof FallingLiquidBlock) && blockStateAbove.get(CONTAINS) == state.get(CONTAINS);
    }

    private ContainsBlock determineContains(World world, BlockPos pos) {
        BlockPos posAbove = pos.up();
        if (world.getBlockState(posAbove).contains(CONTAINS)) {
            ContainsBlock containsAbove = world.getBlockState(posAbove).get(CONTAINS);
            if (containsAbove == ContainsBlock.LAVA) return ContainsBlock.LAVA;
            if (containsAbove == ContainsBlock.WATER) return ContainsBlock.WATER;
        }
        return ContainsBlock.NONE;
    }

    private boolean hasUnder(WorldAccess world, BlockPos pos) {
        BlockPos posBelow = pos.down();
        BlockState blockStateBelow = world.getBlockState(posBelow);
        return blockStateBelow.isSideSolidFullSquare(world, pos, Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Check if the block can see the sky and spawn smoke particles if block contains lava
        if (state.get(HAS_UNDER)) {
            if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                world.addParticle(ParticleTypes.SMOKE,
                        false,
                        pos.getX() + 0.5,
                        pos.getY() + 0.1,
                        pos.getZ() + 0.5,
                        0,
                        0,
                        0);
                return;
            }
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
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
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        float aboveEntity = ((float) entity.getY()) + entity.getHeight();
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                entity.damage(world.getDamageSources().lava(), 4.0F);
                entity.setOnFireFor(3);
                world.addParticle(ParticleTypes.SMOKE,
                        false,
                        pos.getX() + 0.5,
                        aboveEntity,
                        pos.getZ() + 0.5,
                        0,
                        0,
                        0);
            }
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
                if (entity.isOnFire()) entity.extinguishWithSound();
                world.addParticle(ParticleTypes.SPLASH,
                        false,
                        pos.getX() + 0.5,
                        aboveEntity,
                        pos.getZ() + 0.5,
                        0,
                        0,
                        0);
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
