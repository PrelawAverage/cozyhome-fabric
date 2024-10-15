package net.luckystudio.cozyhome.block.test;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class GenericChairBlockEntityRenderer implements BlockEntityRenderer<GenericChairBlockEntity> {
    @Override
    public void render(GenericChairBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        // Apply the rotation from the block entity
        matrices.push(); // Save the current state
        matrices.translate(0.5, 0.5, 0.5); // Move to the center of the block
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRotation() * 22.5F)); // Apply rotation
        matrices.translate(-0.5, -0.5, -0.5); // Move back

        // Render the block model
        MinecraftClient.getInstance().getBlockRenderManager().renderBlock(entity.getCachedState(), matrices, vertexConsumers, light, overlay);

        matrices.pop(); // Restore the previous state
    }

    @Override
    public boolean rendersOutsideBoundingBox(GenericChairBlockEntity blockEntity) {
        return true;
    }
}
