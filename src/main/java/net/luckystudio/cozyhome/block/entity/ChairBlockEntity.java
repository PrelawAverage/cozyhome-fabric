package net.luckystudio.cozyhome.block.entity;

import net.luckystudio.cozyhome.block.ModBlockEntities;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ChairBlockEntity extends BlockEntity {
    public float currentOffset;
    public int color;
    public String cushion_type;

    public ChairBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHAIR_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
        this.color = 0xFFFFFF;
        this.cushion_type = "";
        this.currentOffset = 0;  // Default zoom level
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("color", color);
        nbt.putString("cushion_type", cushion_type);
        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        color = nbt.getInt("color");
        cushion_type = nbt.getString("cushion_type");
    }

    @Override
    protected void readComponents(ComponentsAccess components) {
        super.readComponents(components);
        this.color = components.getOrDefault(ModDataComponents.COLOR, 0xFFFFFF);
        this.cushion_type = components.getOrDefault(ModDataComponents.CUSHION_TYPE, "");
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
        componentMapBuilder.add(ModDataComponents.COLOR, color);
        componentMapBuilder.add(ModDataComponents.CUSHION_TYPE, cushion_type);
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
        nbt.remove("color");
        nbt.remove("cushion_type");
    }
}
