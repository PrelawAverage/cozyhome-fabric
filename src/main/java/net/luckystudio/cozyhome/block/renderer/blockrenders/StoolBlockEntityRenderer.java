package net.luckystudio.cozyhome.block.renderer.blockrenders;

import com.google.common.collect.Maps;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.entity.StoolBlockEntity;
import net.luckystudio.cozyhome.block.custom.StoolBlock;
import net.luckystudio.cozyhome.block.util.ModBlockUtilities;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.RotationAxis;

import java.util.Map;

public class StoolBlockEntityRenderer implements BlockEntityRenderer<StoolBlockEntity> {
    private final ModelPart chair;
    private final ModelPart cushion;
    private static final Map<StoolBlock.StoolType, Identifier> CHAIR_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(StoolBlock.Type.OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/oak_chair.png"));
        map.put(StoolBlock.Type.SPRUCE, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/spruce_chair.png"));
        map.put(StoolBlock.Type.BIRCH, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/birch_chair.png"));
        map.put(StoolBlock.Type.JUNGLE, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/jungle_chair.png"));
        map.put(StoolBlock.Type.ACACIA, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/acacia_chair.png"));
        map.put(StoolBlock.Type.DARK_OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/dark_oak_chair.png"));
        map.put(StoolBlock.Type.MANGROVE, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/mangrove_chair.png"));
        map.put(StoolBlock.Type.CHERRY, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/cherry_chair.png"));
        map.put(StoolBlock.Type.BAMBOO, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/bamboo_chair.png"));
        map.put(StoolBlock.Type.CRIMSON, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/crimson_chair.png"));
        map.put(StoolBlock.Type.WARPED, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/warped_chair.png"));
        map.put(StoolBlock.Type.PRINCESS, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/princess_chair.png"));
        map.put(StoolBlock.Type.IRON, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/iron_chair.png"));
        map.put(StoolBlock.Type.GLASS, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/glass_chair.png"));
        map.put(StoolBlock.Type.UNDEAD, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/undead_chair.png"));
        map.put(StoolBlock.Type.OMINOUS, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/ominous_chair_inactive.png"));
    });

    private static final Map<String, Identifier> CUSHION_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put("generic", Identifier.of(CozyHome.MOD_ID,"textures/block/cushion/cushion.png"));
        map.put("hay", Identifier.of(CozyHome.MOD_ID,"textures/block/cushion/hay_cushion.png"));
        map.put("trader", Identifier.of(CozyHome.MOD_ID,"textures/block/cushion/trader_cushion.png"));
    });

    // How far does this block render.
    @Override
    public int getRenderDistance() {
        return 64;
    }

    public StoolBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Use a custom model layer (make sure to register it in the client mod initializer)
        this.chair = ctx.getLayerModelPart(ModEntityModelLayers.CHAIR);
        this.cushion = ctx.getLayerModelPart(ModEntityModelLayers.CUSHION);
    }

    @Override
    public void render(StoolBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ModBlockUtilities.getRotationAngle(entity)));

        BlockState blockState = entity.getCachedState();
        StoolBlock.StoolType stoolType = ((StoolBlock)blockState.getBlock()).getStoolType();

        RenderLayer chairRenderLayer = getChairRenderLayer(stoolType, blockState);
        VertexConsumer chairVertexConsumer = vertexConsumers.getBuffer(chairRenderLayer);
        chair.render(matrices, chairVertexConsumer, light, overlay);

        if (!entity.cushion_type.isEmpty()) {
            String type = entity.cushion_type;
            RenderLayer cushionRenderLayer = getCushionRenderLayer(type);
            VertexConsumer cushionVertexConsumer = vertexConsumers.getBuffer(cushionRenderLayer);
            int color = entity.color;
            cushion.render(matrices, cushionVertexConsumer, light, overlay, color);
        }

        matrices.pop();
    }

    public static RenderLayer getChairRenderLayer(StoolBlock.StoolType type, BlockState blockState) {
        Identifier identifier;

        if (type == StoolBlock.Type.OMINOUS) {
            // If the chair type is ominous and a player is detected
            if (blockState.get(ModProperties.OMINOUS) == OminousBlock.OMINOUS) {
                identifier = Identifier.of(CozyHome.MOD_ID, "textures/block/chair/ominous_chair_active_ominous.png");
            } else if (blockState.get(ModProperties.OMINOUS) == OminousBlock.ACTIVE) {
                identifier = Identifier.of(CozyHome.MOD_ID, "textures/block/chair/ominous_chair_active.png");
            } else {
                identifier = Identifier.of(CozyHome.MOD_ID, "textures/block/chair/ominous_chair_inactive.png");
            }
        } else {
            // If the chair type is not ominous, get the identifier from the texture map
            identifier = CHAIR_TEXTURES.get(type);
        }
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }

    public static RenderLayer getCushionRenderLayer(String type) {
        Identifier identifier = CUSHION_TEXTURES.get(type);
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }
}
