package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.components.ModDataComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CushionItem extends Item {
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    public int color;
    public CushionItem(Settings settings) {
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
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.applies_to").formatted(TITLE_FORMATTING));
        tooltip.add(ScreenTexts.space().append(Text.translatable("block.cozyhome.chairs").formatted(TITLE_FORMATTING)));
    }
}
