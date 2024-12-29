package net.luckystudio.cozyhome.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.util.math.ColorHelper;

import java.util.HashMap;
import java.util.Map;

public class ModColorHandler {
    public static final Map<String, Integer> COLOR_MAP = new HashMap<>();

    static {
        // Basic colors
        COLOR_MAP.put("White", -393218);
        COLOR_MAP.put("Light Gray", -6447721);
        COLOR_MAP.put("Gray", -6447721);
        COLOR_MAP.put("Black", -14869215);
        COLOR_MAP.put("Brown", -8170446);
        COLOR_MAP.put("Red", -5231066);
        COLOR_MAP.put("Orange", -425955);
        COLOR_MAP.put("Yellow", -75715);
        COLOR_MAP.put("Lime", -8337633);
        COLOR_MAP.put("Green", -10585066);
        COLOR_MAP.put("Cyan", -15295332);
        COLOR_MAP.put("Light Blue", -12930086);
        COLOR_MAP.put("Blue", -12827478);
        COLOR_MAP.put("Purple", -7785800);
        COLOR_MAP.put("Magenta", -3715395);
        COLOR_MAP.put("Pink", -816214);
    }

    public static String getColorName(int color) {
        for (Map.Entry<String, Integer> entry : COLOR_MAP.entrySet()) {
            if (entry.getValue() == color) {
                return entry.getKey();
            }
        }
        return "Custom Dyed";
    }

    public static int getBlockColor(BlockEntity entity, int defaultColor) {
        DyedColorComponent dyedColorComponent = entity.getComponents().get(DataComponentTypes.DYED_COLOR);
        return dyedColorComponent != null ? ColorHelper.Argb.fullAlpha(dyedColorComponent.rgb()) : defaultColor;
    }
}
