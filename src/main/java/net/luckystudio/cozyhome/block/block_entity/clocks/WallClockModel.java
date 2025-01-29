package net.luckystudio.cozyhome.block.block_entity.clocks;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class WallClockModel extends Model {
	private final ModelPart hour_hand;
	private final ModelPart minute_hand;
	private final ModelPart bb_main;
	public WallClockModel(ModelPart root) {
        super(RenderLayer::getEntityCutout);
        this.hour_hand = root.getChild("hour_hand");
		this.minute_hand = root.getChild("minute_hand");
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData hour_hand = modelPartData.addChild("hour_hand", ModelPartBuilder.create().uv(28, 3).cuboid(-0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 7.16F));

		ModelPartData minute_hand = modelPartData.addChild("minute_hand", ModelPartBuilder.create().uv(26, 2).cuboid(-0.5F, -3.5F, -0.17F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 7.0F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -14.0F, 7.0F, 12.0F, 12.0F, 1.0F, new Dilation(0.0F))
		.uv(26, 0).cuboid(-0.5F, -8.5F, 6.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 13).cuboid(-4.0F, -12.0F, 7.4F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 21).cuboid(-3.5F, -11.5F, 7.3F, 7.0F, 7.0F, 0.0F, new Dilation(0.0F))
		.uv(15, 14).cuboid(-4.0F, -4.0F, 7.0F, 8.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 14).cuboid(4.0F, -12.0F, 7.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 14).cuboid(-4.0F, -12.0F, 7.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-6.0F, -16.0F, 7.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 30).cuboid(-6.0F, -2.0F, 7.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(24, 16).cuboid(-8.0F, -16.0F, 7.0F, 2.0F, 16.0F, 0.0F, new Dilation(0.0F))
		.uv(28, 16).mirrored().cuboid(6.0F, -16.0F, 7.0F, 2.0F, 16.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData frame_top_r1 = bb_main.addChild("frame_top_r1", ModelPartBuilder.create().uv(15, 13).cuboid(-4.0F, 0.0F, -0.5F, 8.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -12.0F, 7.5F, 0.0F, 0.0F, -3.1416F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		hour_hand.render(matrices, vertices, light, overlay);
		minute_hand.render(matrices, vertices, light, overlay);
		bb_main.render(matrices, vertices, light, overlay);
	}

	public void setAngles(float hourHandTurnAmount, float minuteHandTurnAmount) {
		this.minute_hand.roll = minuteHandTurnAmount;
		this.hour_hand.roll = hourHandTurnAmount;
	}
}