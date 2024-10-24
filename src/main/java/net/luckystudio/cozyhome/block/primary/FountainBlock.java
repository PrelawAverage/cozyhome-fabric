package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class FountainBlock extends AbstractHorizontalConnectingBlock {
    public static final MapCodec<FountainBlock> CODEC = createCodec(FountainBlock::new);
    public static final IntProperty MOISTURE = Properties.MOISTURE;
    public static final EnumProperty<ContainsBlock> CONTAINS = ModProperties.CONTAINS;

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
        );
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONTAINS);
        super.appendProperties(builder);
    }

    @Override
    protected MapCodec<? extends AbstractHorizontalConnectingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(CONTAINS) == ContainsBlock.NONE) {
            if (stack.getItem() == Blocks.GRASS_BLOCK.asItem()) {
                state = state.with(ModProperties.CONTAINS, ContainsBlock.GRASS);
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
                world.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1F, 1f);
                world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
                return ItemActionResult.SUCCESS;
            } else if (stack.getItem() == Items.WATER_BUCKET) {
                state = state.with(ModProperties.CONTAINS, ContainsBlock.WATER);
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
                world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1F, 1f);
                world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
                return ItemActionResult.SUCCESS;
            }
        } else if (state.get(CONTAINS) == ContainsBlock.GRASS) {
            ItemStack item = new ItemStack(Items.GRASS_BLOCK, 1);
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 1.1,
                    pos.getZ() + 0.5,
                    item);
            state = state.with(ModProperties.CONTAINS, ContainsBlock.NONE);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            world.spawnEntity(itemEntity);
            return ItemActionResult.SUCCESS;
        } else if (state.get(CONTAINS) == ContainsBlock.WATER) {
            if (stack.getItem() == Items.BUCKET) {
                state = state.with(ModProperties.CONTAINS, ContainsBlock.NONE);
                world.playSound(player, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1F, 1f);
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        System.out.println(ModProperties.CONTAINS);
        return this.getDefaultState()
                .with(NORTH, checkDirection(ctx.getWorld().getBlockState(ctx.getBlockPos()), Direction.NORTH, ctx.getWorld(), ctx.getBlockPos()))
                .with(EAST, checkDirection(ctx.getWorld().getBlockState(ctx.getBlockPos()), Direction.EAST, ctx.getWorld(), ctx.getBlockPos()))
                .with(SOUTH, checkDirection(ctx.getWorld().getBlockState(ctx.getBlockPos()), Direction.SOUTH, ctx.getWorld(), ctx.getBlockPos()))
                .with(WEST, checkDirection(ctx.getWorld().getBlockState(ctx.getBlockPos()), Direction.WEST, ctx.getWorld(), ctx.getBlockPos()));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state
                .with(NORTH, checkDirection(state, Direction.NORTH, world, pos))
                .with(EAST, checkDirection(state, Direction.EAST, world, pos))
                .with(SOUTH, checkDirection(state, Direction.SOUTH, world, pos))
                .with(WEST, checkDirection(state, Direction.WEST, world, pos));
    }

    private boolean checkDirection(BlockState state, Direction direction, WorldAccess world, BlockPos pos) {
        // Get the BlockState of the block in the given direction
        BlockPos targetPos = pos.offset(direction);
        BlockState targetState = world.getBlockState(targetPos);  // Cache the block state
        // Check if the block is an instance of AbstractHorizontalConnectingBlock
        if (targetState.getBlock() instanceof AbstractHorizontalConnectingBlock) {
            // Check if the block state contains the 'CONTAINS' property
            if (targetState.contains(CONTAINS) && state.contains(CONTAINS)) {
                // Compare the 'CONTAINS' property of both blocks
                return targetState.get(CONTAINS) == state.get(CONTAINS);
            }
        }
        return false;
    }
}
