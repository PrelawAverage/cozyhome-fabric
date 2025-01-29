package net.luckystudio.cozyhome.block.block_entity.sofa;

import com.google.common.collect.Maps;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.SofaBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.util.ModColorHandler;
import net.minecraft.block.BlockState;
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

public class SofaBlockEntityRenderer implements BlockEntityRenderer<SofaBlockEntity> {
    private final ModelPart sofa;
    private final ModelPart cushion;
    private static final Map<SofaBlock.SofaType, Identifier> SOFA_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(SofaBlock.Type.OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/oak_sofa.png"));
        map.put(SofaBlock.Type.SPRUCE, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/spruce_sofa.png"));
        map.put(SofaBlock.Type.BIRCH, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/birch_sofa.png"));
        map.put(SofaBlock.Type.JUNGLE, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/jungle_sofa.png"));
        map.put(SofaBlock.Type.ACACIA, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/acacia_sofa.png"));
        map.put(SofaBlock.Type.DARK_OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/dark_oak_sofa.png"));
        map.put(SofaBlock.Type.MANGROVE, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/mangrove_sofa.png"));
        map.put(SofaBlock.Type.CHERRY, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/cherry_sofa.png"));
        map.put(SofaBlock.Type.BAMBOO, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/bamboo_sofa.png"));
        map.put(SofaBlock.Type.CRIMSON, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/crimson_sofa.png"));
        map.put(SofaBlock.Type.WARPED, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/warped_sofa.png"));
        map.put(SofaBlock.Type.PRINCESS, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/princess_sofa.png"));
        map.put(SofaBlock.Type.IRON, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/iron_sofa.png"));
        map.put(SofaBlock.Type.GLASS, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/glass_sofa.png"));
        map.put(SofaBlock.Type.UNDEAD, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/undead_sofa.png"));
        map.put(SofaBlock.Type.OMINOUS, Identifier.of(CozyHome.MOD_ID,"textures/block/sofa/ominous_sofa_inactive.png"));
    });

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

    public SofaBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Use a custom model layer (make sure to register it in the client mod initializer)
        this.sofa = ctx.getLayerModelPart(ModEntityModelLayers.SOFA);
        this.cushion = ctx.getLayerModelPart(ModEntityModelLayers.SOFA_CUSHION);
    }

    @Override
    public void render(SofaBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        // Update position based on the `tucked` state of this sofa

        int color = ModColorHandler.getBlockColor(entity, -17170434);

        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ModProperties.setSeatRotationFromRotation(entity.getCachedState())));

        BlockState blockState = entity.getCachedState();
        SofaBlock.SofaType sofaType = ((SofaBlock)blockState.getBlock()).getSofaType();

        // Render the frame (uncolored part)
        RenderLayer sofaRenderLayer = getSofaRenderLayer(sofaType);
        VertexConsumer frameVertexConsumer = vertexConsumers.getBuffer(sofaRenderLayer);
        this.sofa.getChild("frame").render(matrices, frameVertexConsumer, light, overlay);

        // Render the dyeable part with a default color if no color is set
        VertexConsumer dyeableVertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(SOFA_TEXTURES.get(sofaType)));
        this.sofa.getChild("dyeable").render(matrices, dyeableVertexConsumer, light, overlay, color);

        if (!entity.isEmpty()) {
            Item item = entity.getStack().getItem();
            int colorItem = DyedColorComponent.getColor(entity.getStack(), -17170434);
            RenderLayer cushionRenderLayer = getCushionRenderLayer(item);
            VertexConsumer cushionVertexConsumer = vertexConsumers.getBuffer(cushionRenderLayer);
            cushion.render(matrices, cushionVertexConsumer, light, overlay, colorItem);
        }

        matrices.pop();
    }

    public static RenderLayer getSofaRenderLayer(SofaBlock.SofaType type) {
        Identifier identifier = SOFA_TEXTURES.get(type);
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }

    public static RenderLayer getCushionRenderLayer(Item item) {
        Identifier identifier = CUSHION_TEXTURES.get(item);
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }
}
