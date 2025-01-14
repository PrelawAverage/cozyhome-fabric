package net.luckystudio.cozyhome.item.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.luckystudio.cozyhome.block.custom.WallClockBlock;
import net.luckystudio.cozyhome.block.renderer.blockrenders.WallClockBlockEntityRenderer;
import net.luckystudio.cozyhome.block.renderer.models.WallClockModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class WallClockItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final ModelPart wall_clock;

    public WallClockItemRenderer() {
        this.wall_clock = WallClockModel.getTexturedModelData().createModel();
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        Block block = Block.getBlockFromItem(stack.getItem());
        BlockState blockState = block.getDefaultState();
        WallClockBlock.ClockType clockType = ((WallClockBlock)blockState.getBlock()).getClockType();

        RenderLayer clockRenderLayer = WallClockBlockEntityRenderer.getClockRenderLayer(clockType);
        VertexConsumer clockVertexConsumer = vertexConsumers.getBuffer(clockRenderLayer);
        wall_clock.render(matrices, clockVertexConsumer, light, overlay);

        matrices.pop();
    }
}
