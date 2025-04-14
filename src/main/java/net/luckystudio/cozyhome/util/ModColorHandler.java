package net.luckystudio.cozyhome.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ModColorHandler {
    public static final Map<Integer, Text> COLOR_MAP = new HashMap<>();

    static {
        // Basic colors
        COLOR_MAP.put(-393218, Text.translatable("cozyhome.color.white"));
        COLOR_MAP.put(-6447721, Text.translatable("cozyhome.color.gray"));
        COLOR_MAP.put(-14869215, Text.translatable("cozyhome.color.black"));
        COLOR_MAP.put(-8170446, Text.translatable("cozyhome.color.brown"));
        COLOR_MAP.put(-5231066, Text.translatable("cozyhome.color.red"));
        COLOR_MAP.put(-425955, Text.translatable("cozyhome.color.orange"));
        COLOR_MAP.put(-75715, Text.translatable("cozyhome.color.yellow"));
        COLOR_MAP.put(-8337633, Text.translatable("cozyhome.color.lime"));
        COLOR_MAP.put(-10585066, Text.translatable("cozyhome.color.green"));
        COLOR_MAP.put(-15295332, Text.translatable("cozyhome.color.cyan"));
        COLOR_MAP.put(-12930086, Text.translatable("cozyhome.color.light_blue"));
        COLOR_MAP.put(-12827478, Text.translatable("cozyhome.color.blue"));
        COLOR_MAP.put(-7785800, Text.translatable("cozyhome.color.purple"));
        COLOR_MAP.put(-3715395, Text.translatable("cozyhome.color.magenta"));
        COLOR_MAP.put(-816214, Text.translatable("cozyhome.color.pink"));
    }

    public static Text getColorName(int color) {
        return COLOR_MAP.getOrDefault(color, Text.translatable("cozyhome.custom_colored"));
    }

    public static int getBlockColor(@Nullable BlockEntity entity, int defaultColor) {
        if (entity == null) {
            return defaultColor;
        }
        DyedColorComponent dyedColorComponent = entity.getComponents().get(DataComponentTypes.DYED_COLOR);
        return dyedColorComponent != null ? ColorHelper.Argb.fullAlpha(dyedColorComponent.rgb()) : defaultColor;
    }
}
