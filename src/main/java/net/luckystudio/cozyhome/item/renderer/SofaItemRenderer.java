package net.luckystudio.cozyhome.item.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.luckystudio.cozyhome.block.custom.sofas.SofaBlock;
import net.luckystudio.cozyhome.block.custom.sofas.SofaBlockEntityRenderer;
import net.luckystudio.cozyhome.block.custom.sofas.SofaModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class SofaItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final ModelPart sofa;

    public SofaItemRenderer() {
        this.sofa = SofaModel.getTexturedModelData().createModel();
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        int color = DyedColorComponent.getColor(stack, -17170434);
        matrices.translate(0.5, 1.125, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.scale(.85f, .85f, .85f);
        Block block = Block.getBlockFromItem(stack.getItem());
        BlockState blockState = block.getDefaultState();
        SofaBlock.SofaType sofaType = ((SofaBlock)blockState.getBlock()).getSofaType();

        RenderLayer chairRenderLayer = SofaBlockEntityRenderer.getSofaRenderLayer(sofaType);
        VertexConsumer chairVertexConsumer = vertexConsumers.getBuffer(chairRenderLayer);
        sofa.render(matrices, chairVertexConsumer, light, overlay, color);

        matrices.pop();
    }
}
