package net.luckystudio.cozyhome.block.custom.chair;

import com.google.common.collect.Maps;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.item.ModItems;
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
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.RotationAxis;

import java.util.Map;

public class ChairBlockEntityRenderer implements BlockEntityRenderer<ChairBlockEntity> {
    private final ModelPart chair;
    private final ModelPart cushion;
    private static final Map<ChairBlock.ChairType, Identifier> CHAIR_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(ChairBlock.Type.OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/oak_chair.png"));
        map.put(ChairBlock.Type.SPRUCE, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/spruce_chair.png"));
        map.put(ChairBlock.Type.BIRCH, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/birch_chair.png"));
        map.put(ChairBlock.Type.JUNGLE, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/jungle_chair.png"));
        map.put(ChairBlock.Type.ACACIA, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/acacia_chair.png"));
        map.put(ChairBlock.Type.DARK_OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/dark_oak_chair.png"));
        map.put(ChairBlock.Type.MANGROVE, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/mangrove_chair.png"));
        map.put(ChairBlock.Type.CHERRY, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/cherry_chair.png"));
        map.put(ChairBlock.Type.BAMBOO, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/bamboo_chair.png"));
        map.put(ChairBlock.Type.CRIMSON, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/crimson_chair.png"));
        map.put(ChairBlock.Type.WARPED, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/warped_chair.png"));
        map.put(ChairBlock.Type.PRINCESS, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/princess_chair.png"));
        map.put(ChairBlock.Type.IRON, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/iron_chair.png"));
        map.put(ChairBlock.Type.GLASS, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/glass_chair.png"));
        map.put(ChairBlock.Type.UNDEAD, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/undead_chair.png"));
        map.put(ChairBlock.Type.OMINOUS, Identifier.of(CozyHome.MOD_ID,"textures/block/chair/ominous_chair_inactive.png"));
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

    public ChairBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Use a custom model layer (make sure to register it in the client mod initializer)
        this.chair = ctx.getLayerModelPart(ModEntityModelLayers.CHAIR);
        this.cushion = ctx.getLayerModelPart(ModEntityModelLayers.CUSHION);
    }

    @Override
    public void render(ChairBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        // Update position based on the `tucked` state of this chair
        handleSlide(entity, tickDelta);
        if (canTuck(entity)) {
            getLocationForTuck(entity, matrices);
        } else {
            matrices.translate(0.5, 1.5, 0.5);
        }
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ModProperties.setSeatRotationFromRotation(entity.getCachedState())));

        BlockState blockState = entity.getCachedState();
        ChairBlock.ChairType chairType = ((ChairBlock)blockState.getBlock()).getChairType();

        RenderLayer chairRenderLayer = getChairRenderLayer(chairType, blockState);
        VertexConsumer chairVertexConsumer = vertexConsumers.getBuffer(chairRenderLayer);
        chair.render(matrices, chairVertexConsumer, light, overlay);

        if (!entity.isEmpty()) {
            Item item = entity.getStack().getItem();
            int color = DyedColorComponent.getColor(entity.getStack(), -17170434);
            RenderLayer cushionRenderLayer = getCushionRenderLayer(item);
            VertexConsumer cushionVertexConsumer = vertexConsumers.getBuffer(cushionRenderLayer);
            cushion.render(matrices, cushionVertexConsumer, light, overlay, color);
        }

        matrices.pop();
    }

    private boolean canTuck(ChairBlockEntity entity) {
        int rot = entity.getCachedState().get(ChairBlock.ROTATION);
        return rot == 0 || rot == 4 || rot == 8 || rot == 12;
    }

    private void handleSlide(ChairBlockEntity entity, float tickDelta) {
        // Update position based on the `tucked` state of this chair
        if (entity.getCachedState().get(ChairBlock.TUCKED)) {
            // Smoothly transition to the tucked offset
            entity.currentOffset = Math.min(entity.currentOffset + 0.1f * tickDelta, 0.625f);
        } else {
            // Smoothly transition back to the original position
            entity.currentOffset = Math.max(entity.currentOffset - 0.1f * tickDelta, 0.0f);
        }
    }

    private void getLocationForTuck(ChairBlockEntity entity, MatrixStack matrices) {
        if (entity.getCachedState().get(ChairBlock.ROTATION) == 0) {
            matrices.translate(0.5, 1.5, 0.5 - entity.currentOffset); // Apply offset here
        }
        if (entity.getCachedState().get(ChairBlock.ROTATION) == 4) {
            matrices.translate(0.5 + entity.currentOffset, 1.5, 0.5); // Apply offset here
        }
        if (entity.getCachedState().get(ChairBlock.ROTATION) == 8) {
            matrices.translate(0.5, 1.5, 0.5 + entity.currentOffset); // Apply offset here
        }
        if (entity.getCachedState().get(ChairBlock.ROTATION) == 12) {
            matrices.translate(0.5 - entity.currentOffset, 1.5, 0.5); // Apply offset here
        }
    }

    public static RenderLayer getChairRenderLayer(ChairBlock.ChairType type, BlockState blockState) {
        Identifier identifier;

        if (type == ChairBlock.Type.OMINOUS) {
            // If the chair type is ominous and a player is detected
            if (blockState.get(Properties.TRIGGERED)) {
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

    public static RenderLayer getCushionRenderLayer(Item item) {
        Identifier identifier = CUSHION_TEXTURES.get(item);
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }
}
