package net.luckystudio.cozyhome.item.custom;

import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CushionItem extends Item {
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    public CushionItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.applies_to").formatted(TITLE_FORMATTING));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("blocks.cozyhome.chairs")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("blocks.cozyhome.stools")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("blocks.cozyhome.couches")));
    }
}
