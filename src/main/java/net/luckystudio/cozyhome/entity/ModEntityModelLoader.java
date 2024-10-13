package net.luckystudio.cozyhome.entity;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModels;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloader;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ModEntityModelLoader implements SynchronousResourceReloader {
    private Map<EntityModelLayer, TexturedModelData> modelParts = ImmutableMap.of();

    public ModelPart getModelPart(ModEntityModelLayers layer) {
        TexturedModelData texturedModelData = (TexturedModelData)this.modelParts.get(layer);
        if (texturedModelData == null) {
            throw new IllegalArgumentException("No model for layer " + layer);
        } else {
            return texturedModelData.createModel();
        }
    }

    @Override
    public void reload(ResourceManager manager) {
        this.modelParts = ImmutableMap.copyOf(EntityModels.getModels());
    }
}
