package net.luckystudio.cozyhome.components;

import com.mojang.serialization.Codec;
import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final ComponentType<Float> YAW = register("yaw", builder ->
            builder.codec(Codec.FLOAT));
    public static final ComponentType<Float> PITCH = register("pitch", builder ->
            builder.codec(Codec.FLOAT));

    public static <T> ComponentType<T> register(String path, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CozyHome.MOD_ID, path), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerModDataComponents() {
        CozyHome.LOGGER.info("Registering Mod Data Components for " + CozyHome.MOD_ID);
    }
}

