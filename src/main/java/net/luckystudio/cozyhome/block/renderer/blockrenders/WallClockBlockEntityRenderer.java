package net.luckystudio.cozyhome.block.renderer.blockrenders;

import com.google.common.collect.Maps;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.entity.clocks.WallClockBlockEntity;
import net.luckystudio.cozyhome.block.custom.clocks.WallClockBlock;
import net.luckystudio.cozyhome.block.renderer.models.WallClockModel;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.client.ModEntityModelLayers;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import java.util.Map;

public class WallClockBlockEntityRenderer implements BlockEntityRenderer<WallClockBlockEntity> {
    private final WallClockModel wall_clock;
    private static final Map<WallClockBlock.ClockType, Identifier> grandfather_clock_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(WallClockBlock.Type.OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/oak_wall_clock.png"));
        map.put(WallClockBlock.Type.SPRUCE, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/spruce_wall_clock.png"));
        map.put(WallClockBlock.Type.BIRCH, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/birch_wall_clock.png"));
        map.put(WallClockBlock.Type.JUNGLE, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/jungle_wall_clock.png"));
        map.put(WallClockBlock.Type.ACACIA, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/acacia_wall_clock.png"));
        map.put(WallClockBlock.Type.DARK_OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/dark_oak_wall_clock.png"));
        map.put(WallClockBlock.Type.MANGROVE, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/mangrove_wall_clock.png"));
        map.put(WallClockBlock.Type.CHERRY, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/cherry_wall_clock.png"));
        map.put(WallClockBlock.Type.BAMBOO, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/bamboo_wall_clock.png"));
        map.put(WallClockBlock.Type.CRIMSON, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/crimson_wall_clock.png"));
        map.put(WallClockBlock.Type.WARPED, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/warped_wall_clock.png"));
        map.put(WallClockBlock.Type.PRINCESS, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/princess_wall_clock.png"));
        map.put(WallClockBlock.Type.IRON, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/iron_wall_clock.png"));
        map.put(WallClockBlock.Type.GLASS, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/glass_wall_clock.png"));
        map.put(WallClockBlock.Type.UNDEAD, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/undead_wall_clock.png"));
        map.put(WallClockBlock.Type.OMINOUS, Identifier.of(CozyHome.MOD_ID,"textures/block/wall_clock/ominous_wall_clock_inactive.png"));
    });

    // How far does this block render.
    @Override
    public int getRenderDistance() {
        return 64;
    }

    public WallClockBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Use a custom model layer (make sure to register it in the client mod initializer)
        this.wall_clock = new WallClockModel(ctx.getLayerModelPart(ModEntityModelLayers.WALL_CLOCK));
    }

    @Override
    public void render(WallClockBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = entity.getCachedState();
        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ModProperties.setSeatRotationFromFacing(entity.getCachedState())));
        WallClockBlock.ClockType clockType = ((WallClockBlock) blockState.getBlock()).getClockType();

        // Interpolate angles for smooth rendering
        float interpolatedHourAngle = MathHelper.lerp(tickDelta, entity.lastHourHandAngle, entity.currentHourHandAngle);
        float interpolatedMinuteAngle = MathHelper.lerp(tickDelta, entity.lastMinuteHandAngle, entity.currentMinuteHandAngle);

        // Set angles in the model
        this.wall_clock.setAngles(
                interpolatedHourAngle * ((float) Math.PI / 180.0f),  // Hour hand (radians)
                interpolatedMinuteAngle * ((float) Math.PI / 180.0f) // Minute hand (radians)
        );

        // Render the clock
        RenderLayer clockRenderLayer = getClockRenderLayer(clockType);
        VertexConsumer clockVertexConsumer = vertexConsumers.getBuffer(clockRenderLayer);
        wall_clock.render(matrices, clockVertexConsumer, light, overlay);
        matrices.pop();
    }

    public static RenderLayer getClockRenderLayer(WallClockBlock.ClockType type) {
        Identifier identifier = grandfather_clock_TEXTURES.get(type);
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }
}
