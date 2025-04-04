package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
    public Text getName(ItemStack stack) {
        int color = DyedColorComponent.getColor(stack, -393218); // Using 'this' as the BlockEntity
        String colorName = ModColorHandler.getColorName(color);
        return Text.literal(colorName + " ").append(super.getName(stack));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.cozyhome.dyeable").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
