package net.luckystudio.cozyhome.block.entity;

import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GrandfatherClockBlockEntity extends BlockEntity {
    public float lastHourHandAngle = 0.0f;
    public float currentHourHandAngle = 0.0f;
    public float lastMinuteHandAngle = 0.0f;
    public float currentMinuteHandAngle = 0.0f;
    public float lastPendulumAngle = 0.0f;
    public float currentPendulumAngle = 0.0f;
    private int ticks = 0;

    public GrandfatherClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.GRANDFATHER_CLOCK_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
    }

    public static void tick(World world, BlockPos pos, BlockState state, GrandfatherClockBlockEntity blockEntity) {
        blockEntity.ticks++;

        // Get the in-game time
        long worldTime = blockEntity.getWorld().getTimeOfDay() % 24000;

        // Check if it's midnight and play sound
        if (worldTime == 18000) { // Ensure this only triggers once per second
            world.playSoundAtBlockCenter(pos, ModSoundEvents.GRANDFATHER_CLOCK_MIDNIGHT, SoundCategory.BLOCKS, 0.5f, 1.0f, true);
            if (blockEntity.getCachedState().getBlock() == ModBlocks.OMINOUS_GRANDFATHER_CLOCK) {
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_VAULT_ACTIVATE, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
                world.setBlockState(pos, state.with(ModProperties.DETECTED_PLAYER, true));
            }
        }

        // Check if it's midnight and play sound
        if (worldTime == 18360) { // Ensure this only triggers once per second
            if (blockEntity.getCachedState().getBlock() == ModBlocks.OMINOUS_GRANDFATHER_CLOCK) {
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_VAULT_DEACTIVATE, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
                world.setBlockState(pos, state.with(ModProperties.DETECTED_PLAYER, false));
            }
        }

        // Hour hand calculation (12-hour format)
        float hour = (worldTime / 1000.0f) % 12; // 1,000 game ticks per hour, mod 12 for 12-hour clock
        blockEntity.lastHourHandAngle = blockEntity.currentHourHandAngle;
        blockEntity.currentHourHandAngle = (hour * 30.0f + 180.0f) % 360.0f;

        // Minute hand calculation
        float minute = (worldTime % 1000) / 16.6667f; // Convert to 60-minute format (1,000 ticks per hour)
        blockEntity.lastMinuteHandAngle = blockEntity.currentMinuteHandAngle;
        blockEntity.currentMinuteHandAngle = (minute * 6.0f) % 360.0f; // Offset by 180° to point up at midnight

        // Pendulum swing calculation (phase offset for zero angle at sound time)
        float swingSpeed = 2.0f * (float) Math.PI / 40.0f; // Half speed: one full oscillation in 40 ticks
        float phaseOffset = (float) Math.PI / 2.0f; // Offset so pendulum is at 0° at start
        blockEntity.lastPendulumAngle = blockEntity.currentPendulumAngle;
        blockEntity.currentPendulumAngle = (float) Math.sin((blockEntity.ticks * swingSpeed) - phaseOffset) * 7.5f; // ±7.5°

        // Check for center position of pendulum (zero crossing)
        if ((blockEntity.lastPendulumAngle > 0 && blockEntity.currentPendulumAngle <= 0) ||
                (blockEntity.lastPendulumAngle < 0 && blockEntity.currentPendulumAngle >= 0)) {
            // The pendulum has passed through the center, so play the sound
            world.playSoundAtBlockCenter(pos, ModSoundEvents.GRANDFATHER_CLOCK_TICK, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
    }

    @Override
    protected void readComponents(ComponentsAccess components) {
        super.readComponents(components);
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
    }

    // This Syncs the Client and Server
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

    @Override
    public void removeFromCopiedStackNbt(NbtCompound nbt) {
    }
}
