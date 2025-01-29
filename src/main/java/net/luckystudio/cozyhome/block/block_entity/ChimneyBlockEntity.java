package net.luckystudio.cozyhome.block.block_entity;

import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ChimneyBlockEntity extends BlockEntity {
    public ChimneyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CHIMNEY_BLOCK_ENTITY, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, ChimneyBlockEntity campfire) {
        Random random = world.random;
        if (random.nextFloat() < 0.11F) {
            for (int i = 0; i < random.nextInt(2) + 2; i++) {
                world.addImportantParticle(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        true,
                        (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1),
                        (double)pos.getY() + random.nextDouble() + random.nextDouble(),
                        (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1),
                        0.0,
                        0.07,
                        0.0
                );
            }
        }
    }
}
