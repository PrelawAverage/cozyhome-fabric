package net.luckystudio.cozyhome.client;

import com.google.common.collect.Sets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Set;

@Environment(EnvType.CLIENT)
public class ModEntityModelLayers {

    private static final String MAIN = "main";
    private static final Set<EntityModelLayer> LAYERS = Sets.<EntityModelLayer>newHashSet();

    public static final EntityModelLayer SEAT = registerMain("seat");
    public static final EntityModelLayer TELESCOPE = registerMain("telescope");
    public static final EntityModelLayer CHAIR = registerMain("chair");
    public static final EntityModelLayer CUSHION = registerMain("cushion");
    public static final EntityModelLayer GRANDFATHER_CLOCK = registerMain("grandfather_clock");
    public static final EntityModelLayer WALL_CLOCK = registerMain("wall_clock");

    private static EntityModelLayer registerMain(String id) {
        return register(id, MAIN);
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(Identifier.of(CozyHome.MOD_ID, id), layer);
    }

    // Registering EntityModelLayers
    public static void registerEntityModelLayers(){
        CozyHome.LOGGER.info("Registering ModBlockEntityModels for " + CozyHome.MOD_ID);
    }
}
