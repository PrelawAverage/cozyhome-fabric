package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.block_entity.chair.ChairBlockEntity;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSeatBlock extends BlockWithEntity implements SeatBlock {
    private static final VoxelShape BASE_SHAPE = VoxelShapes.cuboid(2,0,2,14,10,14);

    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;

    public AbstractSeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(TRIGGERED, false));
    }

    @Override
    protected abstract MapCodec<? extends BlockWithEntity> getCodec();

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChairBlockEntity(pos,state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE_SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(TRIGGERED, false);
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
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.5f;
    }
}
