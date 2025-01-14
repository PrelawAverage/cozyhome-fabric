package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.entity.ChairBlockEntity;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
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
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ItemActionResult.SUCCESS;
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            System.out.println("Block is a seat block");
            if (!state.get(Properties.TRIGGERED)) {
                System.out.println("Block is not triggered");
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
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.5f;
    }
}
