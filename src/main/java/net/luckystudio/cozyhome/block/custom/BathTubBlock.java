package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.DoubleLongPart;
import net.luckystudio.cozyhome.block.util.interfaces.BathtubBehavior;
import net.luckystudio.cozyhome.block.util.interfaces.LeveledWaterHoldingBlock;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

// Copied from BedBlock
public class BathTubBlock extends HorizontalFacingBlock implements LeveledWaterHoldingBlock, Waterloggable, SeatBlock {

    public static final MapCodec<BathTubBlock> CODEC = createCodec(BathTubBlock::new);
    public static final EnumProperty<DoubleLongPart> PART = ModProperties.DOUBLE_LONG_PART;
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_6;
    public static final IntProperty NEXT_LEVEL = ModProperties.NEXT_LEVEL_TIMER;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;

    public BathTubBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(WATERLOGGED, false)
                .with(PART, DoubleLongPart.FRONT)
                .with(TRIGGERED, false)
                .with(LEVEL, 0)
                .with(NEXT_LEVEL, 0));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape topShape = BathTubBlock.createCuboidShape(0,10,0,16,12,16);
        Direction facing = state.get(FACING);
        boolean isNorth = facing == Direction.NORTH;
        boolean isSouth = facing == Direction.SOUTH;
        boolean isEast = facing == Direction.EAST;
        boolean isWest = facing == Direction.WEST;
        boolean front = state.get(PART) == DoubleLongPart.FRONT;
        VoxelShape baseShape = VoxelShapes.union(
                topShape,
                Block.createCuboidShape(
                        (isWest && front || isEast && !front ? 0 : 1),
                        1,
                        (isNorth && front || isSouth && !front ? 0 : 1),
                        (isEast && front || isWest && !front ? 16 : 15),
                        10,
                        (isSouth && front || isNorth && !front ? 16 : 15)
                )
        );

        // Create Hole Based on Direction
        baseShape = VoxelShapes.combine(baseShape, Block.createCuboidShape(isWest && front || isEast && !front ? 0 : 2,2, isNorth && front || isSouth && !front ? 0 : 2, isEast && front || isWest && !front ? 16 : 14,12, isSouth && front || isNorth && !front ? 16 : 14), BooleanBiFunction.ONLY_FIRST);

        return baseShape;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(FACING, PART, WATERLOGGED, TRIGGERED, LEVEL, NEXT_LEVEL));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (direction == getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))) {
            return neighborState.isOf(this) && neighborState.get(PART) != state.get(PART)
                    ? state
                    : Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    private static Direction getDirectionTowardsOtherPart(DoubleLongPart part, Direction direction) {
        return part == DoubleLongPart.FRONT ? direction : direction.getOpposite();
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(PART) == DoubleLongPart.BACK) {
            int level = state.get(LEVEL);
            int nextLevel = state.get(NEXT_LEVEL);
            BlockState blockStateBehind = world.getBlockState(pos.offset(state.get(FACING).getOpposite()));
            if (level < 6) {
                if (nextLevel == 20) {
                    world.setBlockState(pos, state
                            .with(LEVEL, level + 1)
                            .with(NEXT_LEVEL, 0), 3);
                    world.setBlockState(pos, blockStateBehind
                            .with(LEVEL, level + 1)
                            .with(NEXT_LEVEL, 0), 3);
                } else {
                    world.setBlockState(pos, state.with(NEXT_LEVEL, nextLevel + 1), 3);
                    world.setBlockState(pos, blockStateBehind.with(NEXT_LEVEL, nextLevel + 1), 3);
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
    }

    public static void decreaseLevel(int amount, BlockState state, World world, BlockPos pos) {
        int level = state.get(LEVEL);
        BlockState blockStateBehind = world.getBlockState(pos.offset(state.get(FACING).getOpposite()));
        if (level > 0) {
            world.setBlockState(pos, state.with(LEVEL, level - amount), 3);
            world.setBlockState(pos, blockStateBehind.with(LEVEL, level - amount), 3);
        }
    }

    public static void increaseLevel(int amount, BlockState state, World world, BlockPos pos) {
        int level = state.get(LEVEL);
        BlockState blockStateBehind = world.getBlockState(pos.offset(state.get(FACING).getOpposite()));
        if (level < 3) {
            world.setBlockState(pos, state.with(LEVEL, level + amount), 3);
            world.setBlockState(pos, blockStateBehind.with(LEVEL, level + amount), 3);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BathtubBehavior bathtubBehavior = BathtubBehavior.BASE.map().get(stack.getItem());
        return stack.getItem() == Items.AIR ? sit(state, world, pos, player) : bathtubBehavior.interact(state, world, pos, player, hand, stack);
    }

    private ItemActionResult sit(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            if (!state.get(Properties.TRIGGERED)) {
                world.setBlockState(pos, state.with(Properties.TRIGGERED, true));
                // Creates a new entity
                SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
                // Sets it's location
                seat.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);

                seat.setYaw(seatBlock.getSeatRotation(state, world, pos));
                seat.setAngles(seatBlock.getSeatRotation(state, world, pos), 0);

                world.spawnEntity(seat);

                player.startRiding(seat);
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            if (!state.get(Properties.TRIGGERED)) {
                world.setBlockState(pos, state.with(Properties.TRIGGERED, true));
                // Creates a new entity
                SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
                // Sets it's location
                seat.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);

                seat.setYaw(seatBlock.getSeatRotation(state, world, pos));
                seat.setAngles(seatBlock.getSeatRotation(state, world, pos), 0);

                world.spawnEntity(seat);

                player.startRiding(seat);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            DoubleLongPart tubPart = state.get(PART);
            if (tubPart == DoubleLongPart.FRONT) {
                BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(tubPart, state.get(FACING)));
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isOf(this) && blockState.get(PART) == DoubleLongPart.BACK) {
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                    world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
                }
            }
        }

        return super.onBreak(world, pos, state, player);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getHorizontalPlayerFacing();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos blockPos2 = blockPos.offset(direction);
        World world = ctx.getWorld();
        return world.getBlockState(blockPos2).canReplace(ctx) && world.getWorldBorder().contains(blockPos2) ? this.getDefaultState().with(FACING, direction) : null;
    }

    @Override
    public float getWaterLevel(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 0 -> 0.3125f;
            case 1 -> 0.375f;
            case 2 -> 0.4375f;
            case 3 -> 0.5f;
            case 4 -> 0.5625f;
            case 5 -> 0.625f;
            case 6 -> 0.6875f;
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }

    @Override
    public boolean hasWaterToPull(BlockState state, World world, BlockPos pos) {
        return state.get(PART) == DoubleLongPart.BACK && hasWaterBehind(state, world, pos);
    }

    private boolean hasWaterBehind(BlockState state, World world, BlockPos pos) {
        BlockPos blockPosBehind = pos.offset(state.get(FACING).getOpposite());
        BlockState blockBehind = world.getBlockState(blockPosBehind);
        return isWaterLogged(world, blockPosBehind) || blockBehind.getBlock() == Blocks.WATER;
    }

    public static boolean isWaterLogged(World world, BlockPos pos) {
        return world.getFluidState(pos).getFluid().matchesType(Fluids.WATER);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            BlockPos backPos = pos.offset(state.get(FACING));
            // Check if the offset position contains water
            boolean isWater = world.getFluidState(backPos).isEqualAndStill(Fluids.WATER);
            // Set the blockstate at the back position with PART = BACK and WATERLOGGED if needed
            BlockState backState = state
                    .with(PART, DoubleLongPart.BACK)
                    .with(WATERLOGGED, isWater);
            world.setBlockState(backPos, backState, Block.NOTIFY_ALL);
            // Notify neighbors
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public float getSeatRotation(BlockState state, World world, BlockPos pos) {
        return ModProperties.setSeatRotationFromFacing(state) + (state.get(PART) == DoubleLongPart.FRONT ? 180 : 0);
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.2f;
    }
}
