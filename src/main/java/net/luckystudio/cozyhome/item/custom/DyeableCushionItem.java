package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.components.ModDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.List;

public class DyeableCushionItem extends CushionItem {
    public int color;
    public DyeableCushionItem(Settings settings) {
        super(settings);
        this.color = 0xFFFFFF;
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        stack.set(ModDataComponents.COLOR, color);
        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.block.dyeable").withColor(color));
    }
}
