package net.luckystudio.cozyhome.block.util.blocks;

import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SeatBlock extends Block {
    public SeatBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;

        SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
        seat.setPosition(pos.getX() + 0.5f, pos.getY() + 0.1f, pos.getZ() + 0.5f);

        BlockState seatBlock = world.getBlockState(pos);
        Direction facing = seatBlock.get(TuckableBlock.FACING);

        float rotationOffset = facing == Direction.NORTH ? 180f: facing == Direction.SOUTH ? 0f:
                facing == Direction.EAST ? 270f: facing == Direction.WEST ? 90f: 0;
        seat.setYaw(rotationOffset);

        world.spawnEntity(seat);

        player.startRiding(seat);

        return ActionResult.SUCCESS;
    }
}
