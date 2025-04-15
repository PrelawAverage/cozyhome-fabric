package net.luckystudio.cozyhome.block.custom.sofas;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class SofaModel extends Model {
	private final ModelPart dyeable;
	private final ModelPart frame;
	public SofaModel(ModelPart root) {
		super(RenderLayer::getEntityCutout);
		this.dyeable = root.getChild("dyeable");
		this.frame = root.getChild("frame");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData dyeable = modelPartData.addChild("dyeable", ModelPartBuilder.create().uv(48, 0).cuboid(-7.0F, -3.0F, -16.0F, 14.0F, 1.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, 7.0F));

		ModelPartData left_arm_r1 = dyeable.addChild("left_arm_r1", ModelPartBuilder.create().uv(44, 14).cuboid(-3.0F, -5.0F, -9.0F, 6.0F, 6.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, -5.0F, -8.0F, 0.0F, 0.0F, 0.2618F));

		ModelPartData right_arm_r1 = dyeable.addChild("right_arm_r1", ModelPartBuilder.create().uv(44, 14).mirrored().cuboid(-3.0F, -5.0F, -9.0F, 6.0F, 6.0F, 18.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-9.0F, -5.0F, -8.0F, 0.0F, 0.0F, -0.2618F));

		ModelPartData head_r1 = dyeable.addChild("head_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -26.5F, -4.5F, 16.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 16).cuboid(-8.0F, -18.5F, -2.5F, 16.0F, 21.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, -0.5F, -0.1309F, 0.0F, 0.0F));

		ModelPartData frame = modelPartData.addChild("frame", ModelPartBuilder.create().uv(54, 38).mirrored().cuboid(-27.0F, -10.0F, -9.0F, 3.0F, 8.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
				.uv(54, 38).cuboid(-10.0F, -10.0F, -9.0F, 3.0F, 8.0F, 16.0F, new Dilation(0.0F))
				.uv(0, 0).mirrored().cuboid(-24.0F, -2.0F, -7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 0).cuboid(-12.0F, -2.0F, -7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-12.0F, -2.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).mirrored().cuboid(-24.0F, -2.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 43).cuboid(-24.0F, -7.0F, -9.0F, 14.0F, 5.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(17.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = frame.addChild("cube_r1", ModelPartBuilder.create().uv(0, 61).cuboid(-8.0F, 2.5F, -2.5F, 16.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-17.0F, -5.5F, 6.5F, -0.1309F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		dyeable.render(matrices, vertices, light, overlay);
		frame.render(matrices, vertices, light, overlay);
	}
}
