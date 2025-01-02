package net.luckystudio.cozyhome.block.renderer.blockrenders;

import com.google.common.collect.Maps;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.entity.clocks.GrandfatherClockBlockEntity;
import net.luckystudio.cozyhome.block.custom.clocks.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.renderer.models.GrandfatherClockModel;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.luckystudio.cozyhome.block.util.enums.TripleTallBlock;
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

public class GrandfatherClockBlockEntityRenderer implements BlockEntityRenderer<GrandfatherClockBlockEntity> {
    private final GrandfatherClockModel grandfather_clock;
    private static final Map<GrandfatherClockBlock.GrandfatherClockType, Identifier> grandfather_clock_TEXTURES = Util.make(Maps.newHashMap(), map -> {
        map.put(GrandfatherClockBlock.Type.OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/oak_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.SPRUCE, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/spruce_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.BIRCH, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/birch_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.JUNGLE, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/jungle_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.ACACIA, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/acacia_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.DARK_OAK, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/dark_oak_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.MANGROVE, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/mangrove_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.CHERRY, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/cherry_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.BAMBOO, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/bamboo_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.CRIMSON, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/crimson_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.WARPED, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/warped_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.PRINCESS, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/princess_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.IRON, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/iron_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.GLASS, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/glass_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.UNDEAD, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/undead_grandfather_clock.png"));
        map.put(GrandfatherClockBlock.Type.OMINOUS, Identifier.of(CozyHome.MOD_ID,"textures/block/grandfather_clock/ominous_grandfather_clock_inactive.png"));
    });

    // How far does this block render.
    @Override
    public int getRenderDistance() {
        return 64;
    }

    public GrandfatherClockBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        // Use a custom model layer (make sure to register it in the client mod initializer)
        this.grandfather_clock = new GrandfatherClockModel(ctx.getLayerModelPart(ModEntityModelLayers.GRANDFATHER_CLOCK));
    }

    @Override
    public void render(GrandfatherClockBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = entity.getCachedState();

        if (blockState.get(ModProperties.TRIPLE_TALL_BLOCK) == TripleTallBlock.TOP) {
            matrices.push();
            matrices.translate(0.5, -0.5, 0.5);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ModProperties.setSeatRotationFromRotation(entity.getCachedState())));

            GrandfatherClockBlock.GrandfatherClockType clockType = ((GrandfatherClockBlock) blockState.getBlock()).getGrandfatherClockType();

            // Interpolate angles for smooth rendering
            float interpolatedHourAngle = MathHelper.lerp(tickDelta, entity.lastHourHandAngle, entity.currentHourHandAngle);
            float interpolatedMinuteAngle = MathHelper.lerp(tickDelta, entity.lastMinuteHandAngle, entity.currentMinuteHandAngle);
            float interpolatedPendulumAngle = MathHelper.lerp(tickDelta, entity.lastPendulumAngle, entity.currentPendulumAngle);

            // Set angles in the model
            this.grandfather_clock.setAngles(
                    interpolatedHourAngle * ((float) Math.PI / 180.0f),  // Hour hand (radians)
                    interpolatedMinuteAngle * ((float) Math.PI / 180.0f), // Minute hand (radians)
                    interpolatedPendulumAngle * ((float) Math.PI / 180.0f) // Pendulum swing (radians)
            );

            // Render the clock
            RenderLayer clockRenderLayer = getGrandfatherClockRenderLayer(clockType, blockState);
            VertexConsumer clockVertexConsumer = vertexConsumers.getBuffer(clockRenderLayer);
            grandfather_clock.render(matrices, clockVertexConsumer, light, overlay);
            matrices.pop();
        }
    }




    public static RenderLayer getGrandfatherClockRenderLayer(GrandfatherClockBlock.GrandfatherClockType type, BlockState blockState) {
        Identifier identifier;

        if (type == GrandfatherClockBlock.Type.OMINOUS) {
            // If the grandfather_clock type is TRIAL and a player is detected
            if (blockState.get(ModProperties.OMINOUS) == OminousBlock.OMINOUS) {
                identifier = Identifier.of(CozyHome.MOD_ID, "textures/block/grandfather_clock/ominous_grandfather_clock_active_ominous.png");
            } else if (blockState.get(ModProperties.OMINOUS) == OminousBlock.ACTIVE) {
                identifier = Identifier.of(CozyHome.MOD_ID, "textures/block/grandfather_clock/ominous_grandfather_clock_active.png");
            } else {
                identifier = Identifier.of(CozyHome.MOD_ID, "textures/block/grandfather_clock/ominous_grandfather_clock_inactive.png");
            }
        } else {
            // If the grandfather_clock type is not TRIAL, get the identifier from the texture map
            identifier = grandfather_clock_TEXTURES.get(type);
        }
        return RenderLayer.getEntityCutoutNoCullZOffset(identifier);
    }
}
