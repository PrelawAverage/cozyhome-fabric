package net.luckystudio.cozyhome.block.renderer;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.entity.TelescopeBlockEntity;
import net.luckystudio.cozyhome.block.primary.TelescopeBlock;
import net.luckystudio.cozyhome.entity.model.TelescopeModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class TelescopeBlockEntityRenderer implements BlockEntityRenderer<TelescopeBlockEntity> {
    private final ModelPart model;

    public TelescopeBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        // Use a custom model layer (make sure to register it in your client mod initializer)
        this.model = context.getLayerModelPart(TelescopeModel.LAYER_LOCATION);
    }

    @Override
    public void render(TelescopeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();  // Save the current matrix stack
        int i = entity.getZoomLevel();
        // Move the model to the center top of the block
        matrices.translate(0.5, 2.5, 0.5);  // Position the model on top of the block
        // Flip the model upright (rotate 180 degrees around the X axis)
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180)); // Rotate the model 180 degrees around the X-axis
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(getRotationAngle(entity))); // Rotate the model 180 degrees around the X-axis

        // Render the entire model
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(Identifier.of(CozyHome.MOD_ID, "textures/entity/telescope_head.png")));
        model.render(matrices, vertexConsumer, light, overlay);

        matrices.pop();  // Restore the matrix stack
    }


    private float getRotationAngle(TelescopeBlockEntity entity) {
        // Rotate the model based on the block's facing direction
        Direction facing = entity.getCachedState().get(TelescopeBlock.FACING);
        return switch (facing) {
            case NORTH -> 0;  // Rotate to face north
            case SOUTH -> 180;    // No rotation needed for south
            case WEST -> -90;    // Rotate 90 degrees for west
            case EAST -> 90;   // Rotate -90 degrees for east
            default -> 0;       // Default to no rotation
        };
    }
}
