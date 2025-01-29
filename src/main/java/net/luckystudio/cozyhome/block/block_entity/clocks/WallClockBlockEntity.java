package net.luckystudio.cozyhome.block.block_entity.clocks;

import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.interfaces.ClockBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WallClockBlockEntity extends BlockEntity implements ClockBlock {
    public float lastHourHandAngle = 0.0f;
    public float currentHourHandAngle = 0.0f;
    public float lastMinuteHandAngle = 0.0f;
    public float currentMinuteHandAngle = 0.0f;

    private int ticks = 0;

    public WallClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, WallClockBlockEntity blockEntity) {
        blockEntity.incrementTicks();
        ClockFunctionalityHandler.handleHandRotations(world, pos, state, blockEntity);
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

    @Override
    public int getTicks() {
        return ticks;
    }

    @Override
    public void incrementTicks() {
        ticks++;
    }

    @Override
    public float getCurrentHourHandAngle() {
        return currentHourHandAngle;
    }

    @Override
    public void setCurrentHourHandAngle(float angle) {
        currentHourHandAngle = angle;
    }

    @Override
    public float getLastHourHandAngle() {
        return lastHourHandAngle;
    }

    @Override
    public void setLastHourHandAngle(float angle) {
        lastHourHandAngle = angle;
    }

    @Override
    public float getCurrentMinuteHandAngle() {
        return currentMinuteHandAngle;
    }

    @Override
    public void setCurrentMinuteHandAngle(float angle) {
        currentMinuteHandAngle = angle;
    }

    @Override
    public float getLastMinuteHandAngle() {
        return lastMinuteHandAngle;
    }

    @Override
    public void setLastMinuteHandAngle(float angle) {
        lastMinuteHandAngle = angle;
    }

    @Override
    public float getCurrentPendulumAngle() {
        return 0;
    }

    @Override
    public void setCurrentPendulumAngle(float angle) {

    }

    @Override
    public float getLastPendulumAngle() {
        return 0;
    }

    @Override
    public void setLastPendulumAngle(float angle) {

    }

    @Override
    public World getWorld() {
        return super.getWorld();
    }
}