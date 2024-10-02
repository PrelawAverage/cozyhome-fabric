package net.luckystudio.cozyhome.block.util.blocks;

import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class SeatBlock extends Block {
    public SeatBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.PASS;

        sitDown(world, pos, player);

        return ActionResult.SUCCESS;
    }

    private static void sitDown(World world, BlockPos pos, PlayerEntity player) {
        // Creates a new entity
        SeatEntity seat = new SeatEntity(ModEntities.SEAT_ENTITY, world);
        // Sets it's location
        seat.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);

        BlockState seatBlock = world.getBlockState(pos);
        Direction facing = seatBlock.get(TuckableBlock.FACING);

        float rotationDirection = facing == Direction.NORTH ? 180f: facing == Direction.SOUTH ? 0f:
                facing == Direction.EAST ? 270f: facing == Direction.WEST ? 90f: 0;
        seat.setYaw(rotationDirection);
        seat.setAngles(rotationDirection, 0);

        world.spawnEntity(seat);

        player.startRiding(seat);
    }

    public float setRiderRotation(Entity entity) {
        return entity.getYaw();
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
}
