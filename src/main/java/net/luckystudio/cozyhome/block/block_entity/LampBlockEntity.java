package net.luckystudio.cozyhome.block.block_entity;

import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class LampBlockEntity extends BlockEntity {
    public float currentOffset;

    public LampBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.LAMP_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
        this.currentOffset = 0;
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
        super.removeFromCopiedStackNbt(nbt);
    }

    @Override
    public @Nullable Object getRenderData() {
        return (this.getComponents().contains(DataComponentTypes.DYED_COLOR)) ? this.getComponents().get(DataComponentTypes.DYED_COLOR).rgb() : -17170434;
    }
}
