package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.List;

public class DyeableCushionItem extends CushionItem {
    public DyeableCushionItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        return stack;
    }

    @Override
    public ComponentMap getComponents() {
        return super.getComponents();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.block.dyeable"));
    }

    @Override
    public Text getName(ItemStack stack) {
        int color = DyedColorComponent.getColor(stack, -393218); // Using 'this' as the BlockEntity
        String colorName = ModColorHandler.getColorName(color);
        return Text.literal(colorName + " ").append(super.getName(stack));
    }
}
