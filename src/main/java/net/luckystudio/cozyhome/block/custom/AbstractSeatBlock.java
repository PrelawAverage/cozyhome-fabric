package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.custom.chairs.ChairBlockEntity;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSeatBlock extends BlockWithEntity implements SeatBlock {
    private static final VoxelShape BASE_SHAPE = VoxelShapes.cuboid(2,0,2,14,10,14);
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final int MAX_ROTATION_INDEX = RotationPropertyHelper.getMax();
    protected static final int MAX_ROTATIONS = MAX_ROTATION_INDEX + 1;

    public AbstractSeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(TRIGGERED, false).with(WATERLOGGED, false));
    }

    @Override
    protected abstract MapCodec<? extends BlockWithEntity> getCodec();

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChairBlockEntity(pos,state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED, WATERLOGGED);
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
        return super.getPlacementState(ctx)
                .with(WATERLOGGED, water)
                .with(TRIGGERED, false);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return SeatBlock.sitDown(state, world, pos, player);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.5f;
    }
}
