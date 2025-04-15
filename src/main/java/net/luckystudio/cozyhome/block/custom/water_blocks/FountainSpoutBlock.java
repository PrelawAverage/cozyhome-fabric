package net.luckystudio.cozyhome.block.custom.water_blocks;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.List;

public class FountainSpoutBlock extends WallMountedBlock {
    public static final MapCodec<FountainSpoutBlock> CODEC = createCodec(FountainSpoutBlock::new);
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;
    public static final BooleanProperty HAS_UNDER = ModProperties.HAS_UNDER;

    protected static final VoxelShape NORTH_WALL_SHAPE = Block.createCuboidShape(5, 10, 10, 11, 16, 16);
    protected static final VoxelShape SOUTH_WALL_SHAPE = Block.createCuboidShape(5, 10, 0, 11, 16, 6);
    protected static final VoxelShape WEST_WALL_SHAPE = Block.createCuboidShape(10, 10, 5, 16, 16, 11);
    protected static final VoxelShape EAST_WALL_SHAPE = Block.createCuboidShape(0, 10, 5, 6, 16, 11);
    protected static final VoxelShape FLOOR_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 6, 11);
    protected static final VoxelShape CEILING_SHAPE = Block.createCuboidShape(5, 10, 5, 11, 16, 11);

    public FountainSpoutBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HAS_UNDER, false)
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

    private boolean hasUnder(BlockState state, WorldAccess world, BlockPos pos) {
        BlockPos posBelow = pos.down();
        BlockState blockStateBelow = world.getBlockState(posBelow);
        return state.get(FACE) != BlockFace.FLOOR && (blockStateBelow.isSideSolidFullSquare(world, pos, Direction.UP) || blockStateBelow.getBlock() instanceof FountainBlock);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        ContainsBlock contents = state.get(CONTAINS);
        boolean isFloor = state.get(FACE) == BlockFace.FLOOR;
        boolean hasUnder = state.get(HAS_UNDER);
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.1;
        double z = pos.getZ() + 0.5;

        if (isFloor) {
            switch (contents) {
                case LAVA -> world.addParticle(ParticleTypes.LAVA, false, x, y, z, 0, 0, 0);
                case WATER -> {
                    world.addParticle(ParticleTypes.CLOUD, false, x, y, z, 0, 0.1, 0);
                    world.addParticle(ParticleTypes.SPLASH, false, x, pos.getY() + 2.1, z, 0, 0.1, 0);
                }
            }
        } else if (hasUnder) {
            switch (contents) {
                case LAVA -> world.addParticle(ParticleTypes.SMOKE, false, x, y, z, 0, 0, 0);
                case WATER -> {
                    world.playSound(null, pos, ModSoundEvents.LIGHT_WATER_FLOW, SoundCategory.AMBIENT, 0.1f, 1);
                    world.addParticle(ParticleTypes.CLOUD, false, x, y, z, 0, 0, 0);
                    world.addParticle(ParticleTypes.SPLASH, false, x, y, z, 0, 0, 0);
                }
            }
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos posBehind = pos.offset(getDirection(state).getOpposite());
        BlockState stateBehind = world.getBlockState(posBehind);

        BlockPos posBelow = pos.down();
        BlockState stateBelow = world.getBlockState(posBelow);

        ContainsBlock contains = state.get(CONTAINS);

        // Attempt to transfer liquid
        if (contains != ContainsBlock.NONE && canPullLiquid(stateBehind) && canPourLiquidBelow(stateBelow)) {

            // Pour into block below
            if (stateBelow.getBlock() instanceof FountainBlock) {
                world.setBlockState(posBelow, stateBelow.with(CONTAINS, contains), 3);
            } else if (stateBelow.isOf(Blocks.CAULDRON)) {
                BlockState filledCauldron = contains == ContainsBlock.WATER
                        ? Blocks.WATER_CAULDRON.getDefaultState().with(Properties.LEVEL_3, 3)
                        : Blocks.LAVA_CAULDRON.getDefaultState();
                world.setBlockState(posBelow, filledCauldron, 3);
            }

            // Empty source behind
            if (stateBehind.getBlock() instanceof FountainBlock) {
                world.setBlockState(posBehind, stateBehind.with(CONTAINS, ContainsBlock.NONE), 3);
            } else if (stateBehind.isOf(Blocks.WATER_CAULDRON) || stateBehind.isOf(Blocks.LAVA_CAULDRON)) {
                world.setBlockState(posBehind, Blocks.CAULDRON.getDefaultState(), 3);
            }

        } else if (!ModBlockUtilities.isEntityObstructing(world, pos)
                && ModBlockUtilities.canPlaceBelow(world, pos)
                && contains != ContainsBlock.NONE) {
            elongate(world, pos);
        }
    }

    private boolean canPullLiquid(BlockState state) {
        Block block = state.getBlock();
        return block instanceof FountainBlock
                || (block == Blocks.WATER_CAULDRON && state.get(Properties.LEVEL_3) == 3)
                || block == Blocks.LAVA_CAULDRON;
    }

    private boolean canPourLiquidBelow(BlockState state) {
        Block block = state.getBlock();
        return (block instanceof FountainBlock && state.get(CONTAINS) == ContainsBlock.NONE)
                || block == Blocks.CAULDRON;
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

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(Text.translatable("tooltip.cozyhome.pours_liquid_from_liquid_holding_blocks_into_others").formatted(Formatting.GRAY));
    }
}

