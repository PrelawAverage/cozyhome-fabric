package net.luckystudio.cozyhome.block.util.blocks;

import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
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

        world.spawnEntity(seat);

        player.startRiding(seat);

        return ActionResult.SUCCESS;
    }
}
