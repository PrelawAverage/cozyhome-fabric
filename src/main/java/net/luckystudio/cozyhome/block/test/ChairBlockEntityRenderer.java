package net.luckystudio.cozyhome.block.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.luckystudio.cozyhome.entity.ModEntityModelLayers;
import net.luckystudio.cozyhome.entity.ModEntityModelLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ChairBlockEntityRenderer implements BlockEntityRenderer<ChairBlockEntity> {
    private final Map<ChairBlock.ChairType, ChairBlockEntityModel> MODELS;
    private static final Map<ChairBlock.ChairType, Identifier> TEXTURES = Util.make(Maps.<ChairBlock.ChairType, Identifier>newHashMap(), map -> {
        map.put(ChairBlock.Type.OAK, Identifier.ofVanilla("textures/entity/skeleton/wither_skeleton.png"));
        map.put(ChairBlock.Type.SPRUCE, Identifier.ofVanilla("textures/entity/skeleton/wither_skeleton.png"));
        map.put(ChairBlock.Type.BIRCH, Identifier.ofVanilla("textures/entity/zombie/zombie.png"));
        map.put(ChairBlock.Type.JUNGLE, Identifier.ofVanilla("textures/entity/creeper/creeper.png"));
        map.put(ChairBlock.Type.ACACIA, Identifier.ofVanilla("textures/entity/enderdragon/dragon.png"));
        map.put(ChairBlock.Type.DARK_OAK, Identifier.ofVanilla("textures/entity/piglin/piglin.png"));
        map.put(ChairBlock.Type.MANGROVE, DefaultSkinHelper.getTexture());
    });
    public static Map<ChairBlock.ChairType, ChairBlockEntityModel> getModels(ModEntityModelLoader modelLoader) {
        ImmutableMap.Builder<ChairBlock.ChairType, ChairBlockEntityModel> builder = ImmutableMap.builder();
        builder.put(ChairBlock.Type.OAK, new ChairEntityModel(modelLoader.getModelPart(ModEntityModelLayers.CHAIR)));
        builder.put(ChairBlock.Type.SPRUCE, new ChairEntityModel(modelLoader.getModelPart(EntityModelLayers.WITHER_SKELETON_SKULL)));
        builder.put(ChairBlock.Type.BIRCH, new ChairEntityModel(modelLoader.getModelPart(EntityModelLayers.PLAYER_HEAD)));
        builder.put(ChairBlock.Type.JUNGLE, new ChairEntityModel(modelLoader.getModelPart(EntityModelLayers.ZOMBIE_HEAD)));
        builder.put(ChairBlock.Type.ACACIA, new ChairEntityModel(modelLoader.getModelPart(EntityModelLayers.CREEPER_HEAD)));
        builder.put(ChairBlock.Type.DARK_OAK, new ChairEntityModel(modelLoader.getModelPart(EntityModelLayers.DRAGON_SKULL)));
        builder.put(ChairBlock.Type.MANGROVE, new ChairEntityModel(modelLoader.getModelPart(EntityModelLayers.PIGLIN_HEAD)));
        return builder.build();
    }

    public ChairBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.MODELS = getModels(ctx.getLayerRenderDispatcher());
    }

    public static void renderChair(@Nullable Direction direction, float yaw, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
            ChairBlockEntityModel model,
            RenderLayer renderLayer
    ) {
        matrices.push();
        if (direction == null) {
            matrices.translate(0.5F, 0.0F, 0.5F);
        } else {
            float f = 0.25F;
            matrices.translate(0.5F - (float)direction.getOffsetX() * 0.25F, 0.25F, 0.5F - (float)direction.getOffsetZ() * 0.25F);
        }

        matrices.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);
        model.setHeadRotation(yaw, 0.0F);
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }

    public static RenderLayer getRenderLayer(ChairBlock.ChairType type) {
        Identifier identifier = (Identifier)TEXTURES.get(type);
            return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }

    @Override
    public void render(ChairBlockEntity chairBlockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = chairBlockEntity.getCachedState();
        boolean bl = blockState.getBlock() instanceof WallSkullBlock;
        Direction direction = bl ? blockState.get(WallSkullBlock.FACING) : null;
        int k = bl ? RotationPropertyHelper.fromDirection(direction.getOpposite()) : (Integer)blockState.get(SkullBlock.ROTATION);
        float yaw = RotationPropertyHelper.toDegrees(k);
        ChairBlock.ChairType chairType = ((AbstractChairBlock)blockState.getBlock()).getChairType();
        ChairBlockEntityModel chairBlockEntityModel = (ChairBlockEntityModel)this.MODELS.get(chairType);
        RenderLayer renderLayer = getRenderLayer(chairType);
        renderChair(direction, yaw, matrices, vertexConsumers, light, chairBlockEntityModel, renderLayer);
    }
}
