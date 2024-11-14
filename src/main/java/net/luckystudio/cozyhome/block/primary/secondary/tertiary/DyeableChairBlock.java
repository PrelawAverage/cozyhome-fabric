package net.luckystudio.cozyhome.block.primary.secondary.tertiary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.primary.secondary.DyeableSeatBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.CoveredBlock;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.block.util.interfaces.TuckableBlock;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DyeableChairBlock extends DyeableSeatBlock implements Waterloggable, TuckableBlock {
    public static final MapCodec<DyeableChairBlock> CODEC = createCodec(DyeableChairBlock::new);
    public static final BooleanProperty TUCKED = ModProperties.TUCKED;
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<CoveredBlock> COVER = ModProperties.COVERED;

    public DyeableChairBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(ROTATION, 0)
                .with(TUCKED, false)
                .with(WATERLOGGED, Boolean.FALSE)
                .with(COVER, CoveredBlock.NONE)
        );
    }

    @Override
    protected MapCodec<? extends DyeableChairBlock> getCodec() {
        return CODEC;
    }

    private static final VoxelShape BASE_SHAPE = DyeableChairBlock.createCuboidShape(2,0,2,14,10,14);
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 10, 12, 14, 24, 14),
            BASE_SHAPE);
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 10, 2, 4, 24, 14),
            BASE_SHAPE);
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 10, 2, 14, 24, 4),
            BASE_SHAPE);
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(12, 10, 2, 14, 24, 14),
            BASE_SHAPE);
    public static final VoxelShape TUCKED_NORTH = VoxelShapes.union(
            Block.createCuboidShape(2, 0, -8, 14, 10, 4),
            Block.createCuboidShape(2, 10, 2, 14, 24, 4));
    public static final VoxelShape TUCKED_EAST = VoxelShapes.union(
            Block.createCuboidShape(12, 0, 2, 24, 10, 14),
            Block.createCuboidShape(12, 10, 2, 14, 24, 14));
    public static final VoxelShape TUCKED_SOUTH = VoxelShapes.union(
            Block.createCuboidShape(2, 0, 12, 14, 10, 24),
            Block.createCuboidShape(2, 10, 12, 14, 24, 14));
    public static final VoxelShape TUCKED_WEST = VoxelShapes.union(
            Block.createCuboidShape(-8, 0, 2, 4, 10, 14),
            Block.createCuboidShape(2, 10, 2, 4, 24, 14));

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(ROTATION)) {
            case 0:
                if (state.get(TUCKED)) {
                    return TUCKED_NORTH;
                } else {
                    return NORTH_SHAPE;
                }
            case 4:
                if (state.get(TUCKED)) {
                    return TUCKED_EAST;
                } else {
                    return EAST_SHAPE;
                }
            case 8:
                if (state.get(TUCKED)) {
                    return TUCKED_SOUTH;
                } else {
                    return SOUTH_SHAPE;
                }
            case 12:
                if (state.get(TUCKED)) {
                    return TUCKED_WEST;
                } else {
                    return WEST_SHAPE;
                }
            case null, default:
                return BASE_SHAPE;
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ItemActionResult.SUCCESS;
        TuckableBlock.tryTuck(state, world, pos, player);
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int rotation = RotationPropertyHelper.fromYaw(ctx.getPlayerYaw());
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState()
                        .with(ROTATION, rotation)
                        .with(TUCKED, false)
                        .with(COVER, CoveredBlock.NONE)
                        .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
        }
        return null;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, TUCKED, WATERLOGGED, COVER);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (!world.isClient()) {
            // Define the search radius
            double radius = 5.0;

            // Create an AABB (Axis-Aligned Bounding Box) to search around the block position
            Box searchBox = new Box(pos).expand(radius);

            // Get all entities
            List<SeatEntity> entities = world.getEntitiesByClass(SeatEntity.class, searchBox, entity -> true);

            // Loop through the list and remove each entity
            for (SeatEntity seatEntity : entities) {
                seatEntity.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    public float setRiderRotation(Entity entity) {
        return entity.getYaw();
    }

    public float getSeatHeight(BlockState state) {
        return 0.5f;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {

    }
}
