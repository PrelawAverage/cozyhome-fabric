package net.luckystudio.cozyhome.block.custom.abstracts;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.entity.ChairBlockEntity;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSeatBlock extends BlockWithEntity implements SeatBlock, Waterloggable {
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape BASE_SHAPE = VoxelShapes.cuboid(2,0,2,14,10,14);

    public AbstractSeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(ROTATION, 0)
                .with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected abstract MapCodec<? extends BlockWithEntity> getCodec();

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChairBlockEntity(pos,state);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, WATERLOGGED);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE_SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean water = fluidState.getFluid() == Fluids.WATER;
        boolean isSneaking = ctx.getPlayer().isSneaking();
        int rotationOffset = isSneaking ? 180 : 0;
        return super.getPlacementState(ctx)
                .with(WATERLOGGED, water)
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + rotationOffset));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ItemActionResult.SUCCESS;
        return SeatBlock.sitDown(state, world, pos, player);
    }

    @Override
    public float setRiderRotation(Entity entity) {
        return entity.getYaw();
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.5f;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
