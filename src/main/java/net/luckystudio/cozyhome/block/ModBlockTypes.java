package net.luckystudio.cozyhome.block;

import com.mojang.serialization.MapCodec;

import net.luckystudio.cozyhome.block.primary.secondary.tertiary.DyeableLampBlock;
import net.luckystudio.cozyhome.block.primary.PlankedWallBlock;
import net.minecraft.block.*;
import net.minecraft.registry.Registry;

public class ModBlockTypes {

    public static MapCodec<? extends Block> registerAndGetDefault(Registry<MapCodec<? extends Block>> registry) {
        Registry.register(registry, "planked_wall", PlankedWallBlock.CODEC);
        return Registry.register(registry, "lamp", DyeableLampBlock.CODEC);
    }
}

