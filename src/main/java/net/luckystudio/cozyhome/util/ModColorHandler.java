package net.luckystudio.cozyhome.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;

public class ModColorHandler {
    public static int getItemColor(ItemStack stack) {
        // Retrieve the block entity data component from the item
        NbtComponent component = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);

        if (component != null && component.contains("color")) {  // 3 = int type
            return component.copyNbt().getInt("color");
        }
        return 0xFFFFFF;  // Default color (white) if color is not found
    }
}
