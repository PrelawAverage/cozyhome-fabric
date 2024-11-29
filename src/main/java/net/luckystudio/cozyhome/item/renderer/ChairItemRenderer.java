package net.luckystudio.cozyhome.item.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.luckystudio.cozyhome.block.custom.chairs.ChairBlock;
import net.luckystudio.cozyhome.block.renderer.blockrenders.ChairBlockEntityRenderer;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.luckystudio.cozyhome.block.renderer.models.ChairModel;
import net.luckystudio.cozyhome.entity.model.CushionModel;
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

public class ChairItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final ModelPart chair;
    private final ModelPart cushion;

    public ChairItemRenderer() {
        this.chair = ChairModel.getTexturedModelData().createModel();
        this.cushion = CushionModel.getTexturedModelData().createModel();
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5, 1.125, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.scale(.85f, .85f, .85f);
        Block block = Block.getBlockFromItem(stack.getItem());
        BlockState blockState = block.getDefaultState();
        ChairBlock.ChairType chairType = ((ChairBlock)blockState.getBlock()).getChairType();

        RenderLayer chairRenderLayer = ChairBlockEntityRenderer.getChairRenderLayer(chairType, blockState);
        VertexConsumer chairVertexConsumer = vertexConsumers.getBuffer(chairRenderLayer);
        chair.render(matrices, chairVertexConsumer, light, overlay);

        if (!stack.getOrDefault(ModDataComponents.CUSHION_TYPE, "").isEmpty()) {
            String type = stack.getOrDefault(ModDataComponents.CUSHION_TYPE, "");
            RenderLayer cushionRenderLayer = ChairBlockEntityRenderer.getCushionRenderLayer(type);
            VertexConsumer cushionVertexConsumer = vertexConsumers.getBuffer(cushionRenderLayer);
            int color = stack.getOrDefault(ModDataComponents.COLOR, 0xFFFFFF);
            cushion.render(matrices, cushionVertexConsumer, light, overlay, color);
        }

        matrices.pop();
    }
}
