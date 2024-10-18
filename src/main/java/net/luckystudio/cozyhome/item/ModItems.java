package net.luckystudio.cozyhome.item;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.item.custom.PaintBrushItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Register Items Here
    public static final Item PAINT_BRUSH =registerItem("paint_brush", new PaintBrushItem(new Item.Settings()));

    // Helper Method to register items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CozyHome.LOGGER.info("Registering Mod Items for " + CozyHome.MOD_ID);
    }
}
