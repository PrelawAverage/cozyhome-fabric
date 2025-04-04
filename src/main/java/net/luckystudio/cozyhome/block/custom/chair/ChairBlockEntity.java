package net.luckystudio.cozyhome.block.custom.chair;

import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.inventory.LootableInventory;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ChairBlockEntity extends BlockEntity implements LootableInventory, SingleStackInventory.SingleStackBlockEntityInventory {
    protected RegistryKey<LootTable> lootTableId;
    protected long lootTableSeed;
    private ItemStack stack;
    public float currentOffset;

    public ChairBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CHAIR_BLOCK_ENTITY, pos, state); // Pass the correct BlockEntityType here
        this.stack = ItemStack.EMPTY;
        this.currentOffset = 0;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if (!this.writeLootTable(nbt) && !this.stack.isEmpty()) {
            nbt.put("item", this.stack.encode(registryLookup));
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (!this.readLootTable(nbt)) {
            if (nbt.contains("item", NbtElement.COMPOUND_TYPE)) {
                this.stack = ItemStack.fromNbt(registryLookup, nbt.getCompound("item")).orElse(ItemStack.EMPTY);
            } else {
                this.stack = ItemStack.EMPTY;
            }
        }
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
        nbt.remove("item");
    }

    @Override
    public @Nullable RegistryKey<LootTable> getLootTable() {
        return this.lootTableId;
    }

    @Override
    public void setLootTable(@Nullable RegistryKey<LootTable> lootTable) {
        this.lootTableId = lootTable;
    }

    @Override
    public long getLootTableSeed() {
        return this.lootTableSeed;
    }

    @Override
    public void setLootTableSeed(long lootTableSeed) {
        this.lootTableSeed = lootTableSeed;
    }

    @Override
    public ItemStack getStack() {
        this.generateLoot(null);
        return this.stack;
    }

    @Override
    public void setStack(ItemStack stack) {
        this.generateLoot(null);
        this.stack = stack;
        updateListeners();
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public BlockEntity asBlockEntity() {
        return this;
    }

    @Override
    public int getMaxCountPerStack() {
        return 1;
    }
}
