package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.entity.util.ModEntityUtils;
import net.luckystudio.cozyhome.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FountainBlock extends AbstractHorizontalConnectingBlock {
    public static final MapCodec<FountainBlock> CODEC = createCodec(FountainBlock::new);
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;

    public static final BooleanProperty NORTH_EAST = ModProperties.NORTH_EAST;
    public static final BooleanProperty NORTH_WEST = ModProperties.NORTH_WEST;
    public static final BooleanProperty SOUTH_EAST = ModProperties.SOUTH_EAST;
    public static final BooleanProperty SOUTH_WEST = ModProperties.SOUTH_WEST;

    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(0, 10, 0, 16, 16, 16);
    public static final VoxelShape TOP_PIECE_VOID = Block.createCuboidShape(2, 14, 2, 14, 16, 14);
    public static final VoxelShape TOP = VoxelShapes.combine(TOP_PIECE, TOP_PIECE_VOID, BooleanBiFunction.ONLY_FIRST);
    public static final VoxelShape MIDDLE = Block.createCuboidShape(4, 2, 4, 12, 10, 12);
    public static final VoxelShape BASE = Block.createCuboidShape(2, 0, 2, 14, 2, 14);
    public static final VoxelShape SHAPE = VoxelShapes.union(TOP, MIDDLE, BASE);

    public FountainBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(CONTAINS, ContainsBlock.NONE)
                .with(NORTH_EAST, false)
                .with(NORTH_WEST, false)
                .with(SOUTH_EAST, false)
                .with(SOUTH_WEST, false)
        );
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONTAINS, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
        super.appendProperties(builder);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            if (stack.getItem() == Blocks.GRASS_BLOCK.asItem()) {
                return changeState(state, ContainsBlock.GRASS, SoundEvents.BLOCK_GRASS_PLACE, world, pos, player);
            }
            if (stack.getItem() == Items.WATER_BUCKET) {
                ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET));
                return changeState(state, ContainsBlock.WATER, SoundEvents.ITEM_BUCKET_EMPTY, world, pos, player);
            }
        if (stack.getItem() == Items.POWDER_SNOW_BUCKET) {
            return changeState(state, ContainsBlock.POWDER_SNOW, SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW, world, pos, player);
        }
            if (stack.getItem() == Items.LAVA_BUCKET) {
                return changeState(state, ContainsBlock.LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, world, pos, player);
            }
            if (stack.getItem() == Items.BUCKET) {
                if (state.get(CONTAINS) == ContainsBlock.WATER) {
                    return changeState(state, ContainsBlock.NONE, SoundEvents.ITEM_BUCKET_FILL, world, pos, player);
                } else if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                    return changeState(state, ContainsBlock.NONE, SoundEvents.ITEM_BUCKET_FILL_LAVA, world, pos, player);
                }
            }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private static ItemActionResult changeState(BlockState state, ContainsBlock newContains, SoundEvent soundEvent, World world, BlockPos pos, PlayerEntity player) {
        // Only run if the state actually should change
        if (state.get(CONTAINS) != newContains) {
            if (state.get(CONTAINS) == ContainsBlock.GRASS) {
                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.GRASS_BLOCK));
                dropStack(world, pos.up(), new ItemStack(Items.GRASS_BLOCK, 1));
            } else if (state.get(CONTAINS) == ContainsBlock.LAVA) {

            }
            state = state.with(ModProperties.CONTAINS, newContains);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1F, 1f);
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        BlockPos pos = ctx.getBlockPos();
        WorldAccess world = ctx.getWorld();

        return state
                .with(NORTH, checkDirectionalNeighbor(state, Direction.NORTH, world, pos))
                .with(EAST, checkDirectionalNeighbor(state, Direction.EAST, world, pos))
                .with(SOUTH, checkDirectionalNeighbor(state, Direction.SOUTH, world, pos))
                .with(WEST, checkDirectionalNeighbor(state, Direction.WEST, world, pos));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state
                .with(NORTH, checkDirectionalNeighbor(state, Direction.NORTH, world, pos))
                .with(EAST, checkDirectionalNeighbor(state, Direction.EAST, world, pos))
                .with(SOUTH, checkDirectionalNeighbor(state, Direction.SOUTH, world, pos))
                .with(WEST, checkDirectionalNeighbor(state, Direction.WEST, world, pos))
                .with(NORTH_EAST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.EAST, world, pos))
                .with(NORTH_WEST, checkDiagonalNeighbor(state, Direction.NORTH, Direction.WEST, world, pos))
                .with(SOUTH_EAST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.EAST, world, pos))
                .with(SOUTH_WEST, checkDiagonalNeighbor(state, Direction.SOUTH, Direction.WEST, world, pos));
    }

    private boolean checkDirectionalNeighbor(BlockState state, Direction direction, WorldAccess world, BlockPos pos) {
        BlockPos targetPos = pos.offset(direction);
        return isMatchingFountainBlock(state, world.getBlockState(targetPos));
    }

    private boolean checkDiagonalNeighbor(BlockState state, Direction direction1, Direction direction2, WorldAccess world, BlockPos pos) {
        // Ensure both adjacent directions (e.g., NORTH and EAST) are set to true in the state
        BooleanProperty property1 = getDirectionalProperty(direction1);
        BooleanProperty property2 = getDirectionalProperty(direction2);

        if (!state.get(property1) || !state.get(property2)) return false;

        // Check the diagonal position offset by direction1 and direction2
        BlockPos targetPos = pos.offset(direction1).offset(direction2);
        return isMatchingFountainBlock(state, world.getBlockState(targetPos));
    }

    private BooleanProperty getDirectionalProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction for diagonal neighbor check");
        };
    }

    private boolean isMatchingFountainBlock(BlockState state, BlockState targetState) {
        return targetState.getBlock() instanceof FountainBlock &&
                targetState.contains(CONTAINS) && state.contains(CONTAINS) &&
                targetState.get(CONTAINS).equals(state.get(CONTAINS));
    }

    // This spawns particles when the block contains lava using the LavaFluid Classes randomDisplayTick method
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(CONTAINS) == ContainsBlock.LAVA) {
            LavaFluid lavaFluid = (LavaFluid) Fluids.LAVA;
            lavaFluid.randomDisplayTick(world, pos, state.getFluidState(), random);
        }
    }

    // Handles entity effects based on the block’s fill state (LAVA, POWDER_SNOW, WATER)
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        ContainsBlock fillState = state.get(CONTAINS);

        // If block contains lava, apply burn damage and set the entity on fire
        if (fillState == ContainsBlock.LAVA && entity instanceof LivingEntity) {
            entity.damage(world.getDamageSources().hotFloor(), 4.0F);
            entity.setOnFireFor(3);
        }

        // If block contains powder snow, slow movement and create particle effects
        else if (fillState == ContainsBlock.POWDER_SNOW) {
            if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
                entity.slowMovement(state, new Vec3d(0.9, 1.5, 0.9));

                if (world.isClient) {
                    Random random = world.getRandom();
                    boolean moved = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                    if (moved && random.nextBoolean()) {
                        world.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), pos.getY() + 1, entity.getZ(),
                                MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083, 0.05,
                                MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083);
                    }
                }
            }
            entity.setInPowderSnow(true);

            if (!world.isClient) {
                entity.setOnFire(false);
            }
        }

        // Call the superclass method after handling custom effects
        super.onSteppedOn(world, pos, state, entity);
    }

    // So mobs don't stand on it
    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.cozyhome.block.fountain"));
        tooltip.add(Text.translatable("block.minecraft.grass_block").withColor(11184810));
        tooltip.add(Text.translatable("block.minecraft.water").withColor(11184810));
        super.appendTooltip(stack, context, tooltip, options);
    }

    @Override
    public void onBroken(WorldAccess worldAccess, BlockPos pos, BlockState state) {
        onBlockDestroyed((World)worldAccess, state, pos);
        super.onBroken(worldAccess, pos, state);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        onBlockDestroyed(world, state, pos);
        return super.onBreak(world, pos, state, player);
    }

    private static void onBlockDestroyed(World world, BlockState state, BlockPos pos) {
            if (state.get(CONTAINS) == ContainsBlock.WATER) {
                FluidState fluidState = Fluids.FLOWING_WATER.getDefaultState().with(Properties.LEVEL_1_8, 4);
                world.setBlockState(pos, fluidState.getBlockState());
            }
            if (state.get(CONTAINS) == ContainsBlock.LAVA) {
                FluidState fluidState = Fluids.FLOWING_LAVA.getDefaultState().with(Properties.LEVEL_1_8, 4);
                world.setBlockState(pos, fluidState.getBlockState());
            }
    }
}