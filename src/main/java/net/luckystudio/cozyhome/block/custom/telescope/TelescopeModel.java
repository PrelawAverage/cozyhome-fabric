package net.luckystudio.cozyhome.block.custom.telescope;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TelescopeModel extends Model {
	private final ModelPart holder;
	private final ModelPart head;
	public TelescopeModel(ModelPart root) {
		super(RenderLayer::getEntityCutout);
        this.holder = root.getChild("holder");
		this.head = this.holder.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData holder = modelPartData.addChild("holder", ModelPartBuilder.create().uv(0, 29).cuboid(-3.0F, -2.5F, -1.5F, 6.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(16, 19).cuboid(-1.5F, 0.5F, -1.5F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, 0.0F));

		ModelPartData head = holder.addChild("head", ModelPartBuilder.create().uv(0, 16).cuboid(-1.5F, -5.5F, -10.2F, 3.0F, 3.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.5F, -6.5F, -0.3F, 5.0F, 5.0F, 11.0F, new Dilation(0.0F))
		.uv(21, -10).cuboid(0.0F, -2.5F, -4.8F, 0.0F, 5.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, -0.2F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		this.holder.render(matrices, vertices, light, overlay);
	}

	public void setRotations(float yaw, float pitch) {
		this.holder.yaw = (float) Math.toRadians(yaw);  // Rotate the yaw part
		this.head.pitch = (float) Math.toRadians(pitch);  // Rotate the pitch part
	}
}