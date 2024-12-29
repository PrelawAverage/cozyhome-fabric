package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.Block;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class DyedBlockItem extends BlockItem {

    public DyedBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        int color = DyedColorComponent.getColor(stack, -393218); // Using 'this' as the BlockEntity
        String colorName = ModColorHandler.getColorName(color);
        return Text.literal(colorName + " ").append(super.getName(stack));
    }
}
