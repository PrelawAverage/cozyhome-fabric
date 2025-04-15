package net.luckystudio.cozyhome.block.custom.couches;

import com.google.common.collect.Maps;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.luckystudio.cozyhome.item.ModItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.RotationAxis;

import java.util.Map;

public class CouchBlockEntityRenderer implements BlockEntityRenderer<CouchBlockEntity> {
    private final ModelPart cushion;

    private static final Map<Item, Identifier> CUSHION_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(ModItems.CUSHION, Identifier.of(CozyHome.MOD_ID,"textures/block/cushion/cushion.png"));
        map.put(ModItems.HAY_CUSHION, Identifier.of(CozyHome.MOD_ID,"textures/block/cushion/hay_cushion.png"));
        map.put(ModItems.TRADER_CUSHION, Identifier.of(CozyHome.MOD_ID,"textures/block/cushion/trader_cushion.png"));
    });

    // How far does this block render.
    @Override
    public int getRenderDistance() {
        return 64;
    }

    public CouchBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Use a custom model layer (make sure to register it in the client mod initializer)
        this.cushion = ctx.getLayerModelPart(ModEntityModelLayers.COUCH_CUSHION);
    }

    @Override
    public void render(CouchBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ModProperties.setSeatRotationFromShape(entity.getCachedState()) + 180));

        // Update position based on the `tucked` state of this couch
        if (!entity.isEmpty()) {
            Item item = entity.getStack().getItem();
            int colorItem = DyedColorComponent.getColor(entity.getStack(), -17170434);
            RenderLayer cushionRenderLayer = getCushionRenderLayer(item);
            VertexConsumer cushionVertexConsumer = vertexConsumers.getBuffer(cushionRenderLayer);
            cushion.render(matrices, cushionVertexConsumer, light, overlay, colorItem);
        }

        matrices.pop();
    }

    public static RenderLayer getCushionRenderLayer(Item item) {
        Identifier identifier = CUSHION_TEXTURES.get(item);
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }
}
