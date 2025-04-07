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
    public CushionItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.applied_when_interacted_with").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("blocks.cozyhome.chairs")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("blocks.cozyhome.sofas")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("blocks.cozyhome.couches")));
    }
}
