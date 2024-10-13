package net.luckystudio.cozyhome.entity;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;

import java.util.Set;

public class ModEntityModelLayers {
    private static final Set<ModEntityModelLayer> LAYERS = Sets.<ModEntityModelLayer>newHashSet();

    public static final ModEntityModelLayer CHAIR = registerMain("chair");
    
    private static ModEntityModelLayer registerMain(String id) {
        return register(id, "main");
    }

    private static ModEntityModelLayer register(String id, String layer) {
        ModEntityModelLayer ModEntityModelLayer = create(id, layer);
        if (!LAYERS.add(ModEntityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + ModEntityModelLayer);
        } else {
            return ModEntityModelLayer;
        }
    }

    private static ModEntityModelLayer create(String id, String layer) {
        return new ModEntityModelLayer(Identifier.ofVanilla(id), layer);
    }
}
