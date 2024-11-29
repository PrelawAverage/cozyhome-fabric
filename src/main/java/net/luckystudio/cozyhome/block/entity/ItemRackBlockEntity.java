package net.luckystudio.cozyhome.block.entity;

import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
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

import java.util.List;

/**
 * This block is heavily coded based on the DecoratedPotBlock
 * @see net.minecraft.block.entity.DecoratedPotBlockEntity
 */
public class ItemRackBlockEntity extends BlockEntity implements LootableInventory, SingleStackInventory.SingleStackBlockEntityInventory{
    protected RegistryKey<LootTable> lootTableId;
    protected long lootTableSeed;
    private ItemStack stack;

    public ItemRackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ITEM_RACK_BLOCK_ENTITY, pos, state);
        this.stack = ItemStack.EMPTY;
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

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this); // Sends updated state to client
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup); // Sends block entity state during chunk load
    }


    @Nullable
    @Override
    public RegistryKey<LootTable> getLootTable() {
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
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
        componentMapBuilder.add(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(List.of(this.stack)));
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components) {
        super.readComponents(components);
        this.stack = components.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT).copyFirstStack();
    }

    @Override
    public void removeFromCopiedStackNbt(NbtCompound nbt) {
        super.removeFromCopiedStackNbt(nbt);
        nbt.remove("item");
    }

    @Override
    public ItemStack getStack() {
        this.generateLoot(null);
        return this.stack;
    }

    @Override
    public ItemStack decreaseStack(int count) {
        this.generateLoot(null);
        ItemStack itemStack = this.stack.split(count);
        if (this.stack.isEmpty()) {
            this.stack = ItemStack.EMPTY;  // Ensure the stack is empty if all items are removed
        }
        return itemStack;
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
    public boolean onSyncedBlockEvent(int type, int data) {
        return super.onSyncedBlockEvent(type, data);
    }

    @Override
    public BlockEntity asBlockEntity() {
        return this;
    }

    @Override
    public @Nullable Object getRenderData() { return this.stack; }
}
