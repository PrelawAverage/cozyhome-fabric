package net.luckystudio.cozyhome.block.test;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class ChairEntityModel extends ChairBlockEntityModel {
    private final ModelPart root;
    protected final ModelPart head;

    public ChairEntityModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild(EntityModelPartNames.HEAD);
    }

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.NONE);
        return modelData;
    }

    public static TexturedModelData getHeadTexturedModelData() {
        ModelData modelData = getModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.getChild(EntityModelPartNames.HEAD)
                .addChild(
                        EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.NONE
                );
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getSkullTexturedModelData() {
        ModelData modelData = getModelData();
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setHeadRotation(float yaw, float pitch) {
        this.head.yaw = yaw * (float) (Math.PI / 180.0);
        this.head.pitch = pitch * (float) (Math.PI / 180.0);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.root.render(matrices, vertices, light, overlay, color);
    }
}
