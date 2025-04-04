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

public class OminousChairModel extends EntityModel<Entity> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of(CozyHome.MOD_ID, "ominous_chair_model"), "main");
	private final ModelPart chair;
	private final ModelPart back;
	private final ModelPart seat;
	private final ModelPart spike;
	private final ModelPart spike2;
	private final ModelPart spike3;
	private final ModelPart bb_main;
	public OminousChairModel(ModelPart root) {
		this.chair = root.getChild("chair");
		this.back = root.getChild("back");
		this.seat = root.getChild("seat");
		this.spike = root.getChild("spike");
		this.spike2 = root.getChild("spike2");
		this.spike3 = root.getChild("spike3");
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData chair = modelPartData.addChild("chair", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData back = chair.addChild("back", ModelPartBuilder.create().uv(28, 0).cuboid(-6.0F, -16.0F, 1.0F, 12.0F, 16.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(4.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 0).cuboid(4.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.25F))
		.uv(0, 0).mirrored().cuboid(-6.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(8, 0).mirrored().cuboid(-6.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.of(0.0F, -10.0F, 4.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData seat = chair.addChild("seat", ModelPartBuilder.create().uv(16, 16).cuboid(-6.0F, 0.0F, -11.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -10.0F, 5.0F));

		ModelPartData west_cover_r1 = seat.addChild("west_cover_r1", ModelPartBuilder.create().uv(20, 20).cuboid(0.0F, 0.0F, -5.0F, 0.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 2.0F, -5.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData east_cover_r1 = seat.addChild("east_cover_r1", ModelPartBuilder.create().uv(20, 20).cuboid(-10.0F, 0.0F, -5.0F, 0.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 2.0F, -5.0F, 0.0F, 0.0F, 0.0436F));

		ModelPartData back_cover_r1 = seat.addChild("back_cover_r1", ModelPartBuilder.create().uv(40, 30).cuboid(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData front_cover_r1 = seat.addChild("front_cover_r1", ModelPartBuilder.create().uv(0, 30).cuboid(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -10.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData south_east_leg_outer_r1 = seat.addChild("south_east_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F))
		.uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, 2.0F, 0.0F, 0.0436F, 0.0F, 0.0436F));

		ModelPartData south_west_leg_outer_r1 = seat.addChild("south_west_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F)).mirrored(false)
		.uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 2.0F, 0.0F, 0.0436F, 0.0F, -0.0436F));

		ModelPartData north_west_leg_outer_r1 = seat.addChild("north_west_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F)).mirrored(false)
		.uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 2.0F, -10.0F, -0.0436F, -0.0019F, -0.0436F));

		ModelPartData north_east_leg_outer_r1 = seat.addChild("north_east_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F))
		.uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 2.0F, -10.0F, -0.0436F, 0.0F, 0.0436F));

		ModelPartData spike = modelPartData.addChild("spike", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 13.0F, 2.0F));

		ModelPartData cube_r1 = spike.addChild("cube_r1", ModelPartBuilder.create().uv(16, 2).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		ModelPartData cube_r2 = spike.addChild("cube_r2", ModelPartBuilder.create().uv(16, 2).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		ModelPartData spike2 = modelPartData.addChild("spike2", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 13.0F, 1.0F));

		ModelPartData cube_r3 = spike2.addChild("cube_r3", ModelPartBuilder.create().uv(16, 2).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		ModelPartData cube_r4 = spike2.addChild("cube_r4", ModelPartBuilder.create().uv(16, 2).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		ModelPartData spike3 = modelPartData.addChild("spike3", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 13.0F, -1.0F));

		ModelPartData cube_r5 = spike3.addChild("cube_r5", ModelPartBuilder.create().uv(16, 2).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		ModelPartData cube_r6 = spike3.addChild("cube_r6", ModelPartBuilder.create().uv(16, 2).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 38).cuboid(-6.0F, -14.0F, -3.0F, 12.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		chair.render(matrices, vertices, light, overlay);
		spike.render(matrices, vertices, light, overlay);
		spike2.render(matrices, vertices, light, overlay);
		spike3.render(matrices, vertices, light, overlay);
		bb_main.render(matrices, vertices, light, overlay);
	}
}