package net.luckystudio.cozyhome.block.renderer.blockrenders;

import net.luckystudio.cozyhome.block.entity.ItemRackBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class ToolRackBlockEntityRenderer implements BlockEntityRenderer<ItemRackBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ToolRackBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ItemRackBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        BlockState blockState = entity.getWorld().getBlockState(entity.getPos());
        ItemStack itemStack = entity.getStack();

        if (itemStack != ItemStack.EMPTY) {
            // Push the current matrix state so that transformations are applied locally to this render
            matrices.push();

            centerItem(blockState, matrices); // Moves the item to the arms of the rack

            setItemPosition(itemStack, matrices);

            // Scale the item to fit, adjust the scale factor as needed
            matrices.scale(1F, 1F, 1F);

            // Render the item at the center of the block
            this.itemRenderer.renderItem(itemStack, ModelTransformationMode.NONE, light, overlay, matrices, vertexConsumers, entity.getWorld(), (int) entity.getPos().asLong());

            // Pop the matrix state to restore previous transformations
            matrices.pop();
        }
    }

    private void centerItem(BlockState state, MatrixStack matrices) {
        if (state.contains(Properties.HORIZONTAL_FACING)) {
            Direction facingDirection = state.get(Properties.HORIZONTAL_FACING);

            // Translate item 3.5 pixels to the side based on the FACING direction
            float translation = 3.5F / 16.0F; // 3.5 pixels in block space

            switch (facingDirection) {
                case NORTH:
                    matrices.translate(0.5f, 0.5F, 0.5f + translation + 0.0625f);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
                    break;
                case EAST:
                    matrices.translate(translation, 0.5F, 0.5f);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
                    break;
                case SOUTH:
                    matrices.translate(0.5f, 0.5F, translation);
                    break;
                case WEST:
                    matrices.translate(0.5f + translation + 0.0625, 0.5F, 0.5f);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(270.0F));
                    break;
                default:
                    // Default case: no translation or a fallback
                    break;
            }
        }
    }

    private void setItemPosition(ItemStack itemStack, MatrixStack matrices) {
        Item item = itemStack.getItem();
        if (item instanceof ShovelItem || item instanceof PickaxeItem || item instanceof AxeItem || item instanceof HoeItem) {
            matrices.translate(-0.04625, -0.0625, 0); // These item textures aren't centered when rotated vertically so we adjust.
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45.0f));
        } else if (item instanceof BowItem) {
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-45.0F));
        } else if (item instanceof CrossbowItem) {
            matrices.translate(0, -0.0625, 0); // Move down to rest hand guard on rack arms
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-45.0F));
        } else if (item instanceof SwordItem) {
            matrices.translate(0, -0.0625, 0); // Move down to rest hand guard on rack arms
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-135.0f));
        } else if (item instanceof MaceItem) {
            matrices.translate(0, 0.0625, 0); // Move down to rest hand guard on rack arms
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45.0F));
        } else if (item instanceof SpyglassItem) {
            matrices.translate(0, 0.125, 0); // Move down to rest hand guard on rack arms
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0f));
        } else if (item instanceof GoatHornItem) {
            matrices.translate(0, .5, 0); // Move down to rest hand guard on rack arms
        } else if (item instanceof ShieldItem) {
            matrices.translate(0.5, 0.5, 0.5); // Move down to rest hand guard on rack arms
        } else if (item instanceof TridentItem) {
            matrices.translate(0.5, .6, 0.5); // Move down to rest hand guard on rack arms
        }
    }
}
