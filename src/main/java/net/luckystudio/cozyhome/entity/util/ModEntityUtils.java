package net.luckystudio.cozyhome.entity.util;

import net.minecraft.entity.Entity;

public class ModEntityUtils {

    public static boolean isEntityMoving(Entity entity) {
        return entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
    }
}
