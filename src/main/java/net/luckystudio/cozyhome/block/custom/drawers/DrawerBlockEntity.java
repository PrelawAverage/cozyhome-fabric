package net.luckystudio.cozyhome.block.custom.drawers;

import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.interfaces.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class DrawerBlockEntity extends LootableContainerBlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private final ViewerCountManager stateManager = new ViewerCountManager() {
        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            DrawerBlockEntity.this.playSound(state, SoundEvents.BLOCK_BARREL_OPEN);
            DrawerBlockEntity.this.setOpen(state, true);
        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            DrawerBlockEntity.this.playSound(state, SoundEvents.BLOCK_BARREL_CLOSE);
            DrawerBlockEntity.this.setOpen(state, false);
        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {

        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (player.currentScreenHandler instanceof DrawerScreenHandler) {
                Inventory inventory = ((DrawerScreenHandler)player.currentScreenHandler).getInventory();
                return inventory == DrawerBlockEntity.this;
            } else {
                return false;
            }
        }
    };

    public DrawerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.DRAWER_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if (!this.writeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory, registryLookup);
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.readLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory, registryLookup);
        }
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.cozyhome.drawer");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new DrawerScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int size() {
        return 9;
    }

    @Override
    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    public void tick() {
        if (!this.removed) {
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    void setOpen(BlockState state, boolean open) {
        assert this.world != null;
        this.world.setBlockState(this.getPos(), state.with(DrawerBlock.OPEN, open), DrawerBlock.NOTIFY_ALL);
    }

    void playSound(BlockState state, SoundEvent soundEvent) {
        Vec3i vec3i = state.get(DrawerBlock.FACING).getVector();
        double d = (double)this.pos.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double e = (double)this.pos.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double f = (double)this.pos.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        assert this.world != null;
        this.world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
    }
}
