package net.luckystudio.cozyhome.block.custom.drawers;

import net.luckystudio.cozyhome.CozyHomeClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DrawerScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public DrawerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9)); // Change size to 9
    }

    public DrawerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(CozyHomeClient.DRAWER_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9); // Ensure inventory size matches
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        int left_padding = 8;
        int vertical_offset = 36; // Offset to move the custom inventory down by 36 pixels

        // Our inventory: Single row (9 slots)
        for (int l = 0; l < 9; ++l) {
            int x = left_padding + l * 18;
            int y = 17 + vertical_offset; // Add the offset here
            this.addSlot(new Slot(inventory, l, x, y));
        }

        // The player inventory
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The player Hotbar
        for (int m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
