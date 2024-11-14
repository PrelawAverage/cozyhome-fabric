package net.luckystudio.cozyhome.entity.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class CushionModel extends EntityModel<Entity> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of(CozyHome.MOD_ID, "cushion_model"), "main");
	private final ModelPart bb_main;
	public CushionModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -11.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(-12, 11).cuboid(-6.0F, -10.01F, -6.0F, 12.0F, 0.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData front_r1 = bb_main.addChild("front_r1", ModelPartBuilder.create().uv(0, 23).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -10.0F, -6.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData east_r1 = bb_main.addChild("east_r1", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, 0.0F, -6.0F, 0.0F, 10.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -10.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		ModelPartData west_r1 = bb_main.addChild("west_r1", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, 0.0F, -6.0F, 0.0F, 10.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -10.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData south_r1 = bb_main.addChild("south_r1", ModelPartBuilder.create().uv(0, 43).cuboid(-6.0F, 0.0F, 0.0F, 12.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -10.0F, 6.0F, 0.0436F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		bb_main.render(matrices, vertices, light, overlay);
	}
}