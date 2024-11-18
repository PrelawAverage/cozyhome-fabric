package net.luckystudio.cozyhome.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.luckystudio.cozyhome.CozyHome;

@Environment(EnvType.CLIENT)
public class ModBlockEntityRendererFactories {



    // Registering ModBlockEntityRendererFactories
    public static void registerModBlockEntityRendererFactories(){
        CozyHome.LOGGER.info("Registering ModBlockEntityModels for " + CozyHome.MOD_ID);
    }
}

