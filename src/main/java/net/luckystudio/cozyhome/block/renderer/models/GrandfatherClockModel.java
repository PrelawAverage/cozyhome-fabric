package net.luckystudio.cozyhome.block.renderer.models;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class GrandfatherClockModel extends Model {
	private final ModelPart main;
	private final ModelPart hour_hand;
	private final ModelPart minute_hand;
	private final ModelPart pendulum;
	public GrandfatherClockModel(ModelPart root) {
		super(RenderLayer::getEntityCutoutNoCullZOffset);
        this.main = root.getChild("main");
		this.hour_hand = root.getChild("hour_hand");
		this.minute_hand = root.getChild("minute_hand");
		this.pendulum = root.getChild("pendulum");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(56, 95).cuboid(-5.0F, -15.0F, 2.5F, 10.0F, 8.0F, 10.0F, new Dilation(0.0F))
		.uv(56, 73).cuboid(-7.0F, -15.0F, 0.5F, 14.0F, 8.0F, 14.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-7.0F, -7.0F, 0.5F, 14.0F, 14.0F, 14.0F, new Dilation(0.0F))
		.uv(42, 11).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 73).cuboid(-7.0F, 7.0F, 0.5F, 14.0F, 28.0F, 14.0F, new Dilation(0.0F))
		.uv(27, 44).cuboid(-6.0F, 7.0F, 12.5F, 12.0F, 28.0F, 1.0F, new Dilation(0.0F))
		.uv(53, 35).cuboid(5.0F, 7.0F, 2.5F, 1.0F, 28.0F, 10.0F, new Dilation(0.0F))
		.uv(75, 35).cuboid(-6.0F, 7.0F, 2.5F, 1.0F, 28.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 44).cuboid(-6.0F, 7.0F, 1.5F, 12.0F, 28.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-7.0F, 35.0F, 0.5F, 14.0F, 2.0F, 14.0F, new Dilation(0.0F))
		.uv(97, 45).cuboid(-4.0F, 10.0F, 1.5F, 8.0F, 22.0F, 1.0F, new Dilation(0.0F))
		.uv(42, 0).cuboid(-4.5F, -4.5F, 0.4F, 9.0F, 9.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 9).cuboid(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, -7.5F));

		ModelPartData hour_hand = modelPartData.addChild("hour_hand", ModelPartBuilder.create().uv(48, 9).cuboid(-0.5F, -4.5F, 0.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, -7.5F));

		ModelPartData minute_hand = modelPartData.addChild("minute_hand", ModelPartBuilder.create().uv(50, 10).cuboid(-0.5F, -3.5F, 0.25F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, -7.5F));

		ModelPartData pendulum = modelPartData.addChild("pendulum", ModelPartBuilder.create().uv(60, 0).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 20.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		main.render(matrices, vertices, light, overlay);
		hour_hand.render(matrices, vertices, light, overlay);
		minute_hand.render(matrices, vertices, light, overlay);
		pendulum.render(matrices, vertices, light, overlay);
	}

	public void setAngles(float hourHandTurnAmount, float minuteHandTurnAmount, float pendulumSwingAmount) {
		this.hour_hand.roll = hourHandTurnAmount;
		this.minute_hand.roll = minuteHandTurnAmount;
		this.pendulum.roll = pendulumSwingAmount;
	}
}