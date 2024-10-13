package net.luckystudio.cozyhome.block.test;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public abstract class ChairBlockEntityModel extends Model {
    public ChairBlockEntityModel() {
        super(RenderLayer::getEntityCutout);
    }

    public abstract void setHeadRotation(float yaw, float pitch);
}
