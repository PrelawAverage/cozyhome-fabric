package net.luckystudio.cozyhome.item.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.DoubleLongPart;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class BathtubItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0,0,0);

        Block block = Block.getBlockFromItem(stack.getItem());

        // Front part (FOOT)
        BlockState frontState = block.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.FRONT);

        // Back part (HEAD)
        BlockState backState = block.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(ModProperties.DOUBLE_LONG_PART, DoubleLongPart.BACK);

        matrices.translate(0, 0, 0);

        // Render front part
        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                backState, matrices, vertexConsumers, light, overlay
        );

        // Move backward one block length to render front part
        matrices.translate(0, 0, -1);

        // Render back part (at origin)
        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(
                frontState, matrices, vertexConsumers, light, overlay
        );

        matrices.pop();
    }

}
