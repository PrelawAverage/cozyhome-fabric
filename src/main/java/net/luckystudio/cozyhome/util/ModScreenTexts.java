package net.luckystudio.cozyhome.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModScreenTexts {

    private static final Formatting CAPTION = Formatting.GRAY;
    private static final Formatting ENTRIES = Formatting.YELLOW;

    public static final Text ENTRY = entry();

    public static MutableText entry() {
        return Text.literal(" - ").formatted(ENTRIES);
    }
}
