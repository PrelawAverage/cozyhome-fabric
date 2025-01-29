package net.luckystudio.cozyhome.block.block_entity.chair;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.util.math.MatrixStack;

/**
 * This is the model of the chair.
 */
@Environment(EnvType.CLIENT)
public class ChairModel extends Model {
	private final ModelPart chair;
	private final ModelPart back;
	private final ModelPart seat;
	private final ModelPart bb_main;

	public ChairModel(ModelPart root) {
		super(RenderLayer::getEntitySolid);
        this.chair = root.getChild("chair");
		this.back = root.getChild("back");
		this.seat = root.getChild("seat");
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

		ModelPartData south_east_leg_outer_r1 = seat.addChild("south_east_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F))
		.uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, 2.0F, 0.0F, 0.0436F, 0.0F, 0.0436F));

		ModelPartData south_west_leg_outer_r1 = seat.addChild("south_west_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F)).mirrored(false)
		.uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 2.0F, 0.0F, 0.0436F, 0.0F, -0.0436F));

		ModelPartData north_west_leg_outer_r1 = seat.addChild("north_west_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F)).mirrored(false)
		.uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 2.0F, -10.0F, -0.0436F, -0.0019F, -0.0436F));

		ModelPartData north_east_leg_outer_r1 = seat.addChild("north_east_leg_outer_r1", ModelPartBuilder.create().uv(8, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.25F))
		.uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 2.0F, -10.0F, -0.0436F, 0.0F, 0.0436F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData west_cover_r1 = bb_main.addChild("west_cover_r1", ModelPartBuilder.create().uv(20, 20).cuboid(0.0F, 0.0F, -5.0F, 0.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData east_cover_r1 = bb_main.addChild("east_cover_r1", ModelPartBuilder.create().uv(20, 20).cuboid(-10.0F, 0.0F, -5.0F, 0.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		ModelPartData back_cover_r1 = bb_main.addChild("back_cover_r1", ModelPartBuilder.create().uv(40, 30).cuboid(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 5.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData front_cover_r1 = bb_main.addChild("front_cover_r1", ModelPartBuilder.create().uv(0, 30).cuboid(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, -5.0F, -0.0436F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		chair.render(matrices, vertices, light, overlay);
		bb_main.render(matrices, vertices, light, overlay);
	}
}