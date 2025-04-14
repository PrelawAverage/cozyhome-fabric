package net.luckystudio.cozyhome.block.custom.bathtub;

import net.luckystudio.cozyhome.block.custom.sinks.AbstractSinkBlock;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AbstractWaterHoldingBlockEntity extends BlockEntity {

    public int timer;

    public AbstractWaterHoldingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.timer = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, AbstractWaterHoldingBlockEntity blockEntity) {
        if (state.getBlock() instanceof WaterHoldingBlock waterHoldingBlock) {
            ContainsBlock pullingLiquid = waterHoldingBlock.getLiquidToPull(state, world, blockPos);

            // Turn off the trigger if the block has no liquid to pull
            if (pullingLiquid == ContainsBlock.NONE) {
                world.setBlockState(blockPos, state.with(Properties.TRIGGERED, false), 3);
                return;
            }

            // Choose the correct particles
            ParticleEffect dripParticle;
            ParticleEffect splashParticle;

            if (pullingLiquid == ContainsBlock.WATER) {
                dripParticle = ParticleTypes.FALLING_DRIPSTONE_WATER;
                splashParticle = ParticleTypes.SPLASH;
            } else {
                dripParticle = ParticleTypes.FALLING_LAVA;
                splashParticle = ParticleTypes.LANDING_LAVA;
            }

            // Particle positions
            double faucetHeight = state.getBlock() instanceof AbstractSinkBlock ? 0.6: 0.4875; // Adjust this value to change the height of the faucet
            Vec3d centerTop = Vec3d.ofCenter(blockPos).add(0.0, faucetHeight, 0.0);
            Vec3d center = Vec3d.ofCenter(blockPos).add(blockPos.getX() + 0.5, blockPos.getY() + waterHoldingBlock.getLiquidLevelHeight(state), blockPos.getZ() + 0.5);

            // Spawn particles
            world.addParticle(dripParticle, centerTop.x, centerTop.y, centerTop.z, 0.0, 0.0, 0.0);
            world.addParticle(splashParticle, center.x, center.y, center.z, 0.0, 0.0, 0.0);

            // Update timer and level
            blockEntity.timer++;

            if (blockEntity.timer >= 20) {
                blockEntity.timer = 0; // Reset timer

                // Increase LEVEL if it's not at max (2)
                if (!waterHoldingBlock.isFull(state)) {
                    waterHoldingBlock.addLiquid(state, world, blockPos, pullingLiquid);
                }
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("timer", this.timer);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.timer = nbt.getInt("timer");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    // This Syncs the Client and Server
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
