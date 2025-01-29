package net.luckystudio.cozyhome.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Environment(EnvType.CLIENT)
public class MirrorScreen extends Screen {
    private final PlayerEntity player;
    private static boolean face;
    public MirrorScreen(PlayerEntity player, boolean face) {
        super(Text.translatable("cozyhome."));
        this.player = player;
        this.face = face;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        int heightOffset = face ? 0 : 125;
        // Get the center of the screen
        int x = this.width / 2; // Center horizontally
        int y = this.height / 2 + heightOffset; // Center vertically

        // Set the size of the player model (adjustable as needed)
        float percentage = face ? .4f : .8f; // Changing the percentage of the model based on whether its a face of full body mirror
        int size = (int) (Math.min(this.width, this.height) * percentage);  // 20% of the smaller dimension

        // Render the player model at the center of the screen
        renderPlayerModel(context, x, y, size, mouseX, mouseY, this.client.player);
    }

    public void renderPlayerModel(DrawContext context, int x, int y, int size, float mouseX, float mouseY, LivingEntity entity) {
        // Calculate the rotation based on the mouse position relative to the center of the screen
        float centerX = this.width / 2.0F;
        float centerY = this.height / 2.0F;

        // Calculate the mouse's offset from the center
        float i = (float) Math.atan((centerX - mouseX) / 40.0F);
        float j = (float) Math.atan((centerY - mouseY) / 40.0F);

        // Apply rotation transformations
        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf2 = new Quaternionf().rotateX(j * 20.0F * (float) (Math.PI / 180.0));
        quaternionf.mul(quaternionf2);

        // Store original body and head yaw and pitch to reset after rendering
        float k = entity.bodyYaw;
        float l = entity.getYaw();
        float m = entity.getPitch();
        float n = entity.prevHeadYaw;
        float o = entity.headYaw;

        // Update entity rotation based on the mouse position
        entity.bodyYaw = 180.0F + i * 20.0F;
        entity.setYaw(180.0F + i * 40.0F);
        entity.setPitch(-j * 20.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();

        // Apply scale based on the size and entity's scale factor
        float p = entity.getScale();
        Vector3f vector3f = new Vector3f(0.0F, entity.getHeight() / 2.0F, 0.0F);
        float q = (float) size / p;

        // Render the entity at the specified screen position
        drawEntity(context, x, y, q, vector3f, quaternionf, quaternionf2, entity);

        // Reset entity rotation after rendering
        entity.bodyYaw = k;
        entity.setYaw(l);
        entity.setPitch(m);
        entity.prevHeadYaw = n;
        entity.headYaw = o;
    }

    public static void drawEntity(
            DrawContext context, float x, float y, float size, Vector3f vector3f, Quaternionf quaternionf, @Nullable Quaternionf quaternionf2, LivingEntity entity
    ) {
        context.getMatrices().push();
        context.getMatrices().translate((double) x, (double) y, 50.0);
        context.getMatrices().scale(size, size, -size);
        context.getMatrices().translate(vector3f.x, vector3f.y, vector3f.z);
        context.getMatrices().multiply(quaternionf);

        DiffuseLighting.method_34742();

        // Render the entity using the EntityRenderDispatcher
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        if (quaternionf2 != null) {
            entityRenderDispatcher.setRotation(quaternionf2.conjugate(new Quaternionf()).rotateY((float) Math.PI));
        }

        entityRenderDispatcher.setRenderShadows(false);
        RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.getMatrices(), context.getVertexConsumers(), 15728880));
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);

        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }


    // So when the player presses any button the screen closes
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.close();
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        super.close();
        MinecraftClient.getInstance().player.closeHandledScreen();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
