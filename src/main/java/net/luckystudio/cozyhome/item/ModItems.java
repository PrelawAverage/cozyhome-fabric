package net.luckystudio.cozyhome.item;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.item.custom.CushionItem;
import net.luckystudio.cozyhome.item.custom.DyeableCushionItem;
import net.luckystudio.cozyhome.item.custom.PaintBrushItem;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BannerPatternTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    // Register Items Here
    public static final Item PAINT_BRUSH = registerItem("paint_brush", new PaintBrushItem(new Item.Settings()));
    public static final Item CUSHION = registerItem("cushion", new DyeableCushionItem(new Item.Settings()));
    public static final Item HAY_CUSHION = registerItem("hay_cushion", new CushionItem(new Item.Settings()));
    public static final Item TRADER_CUSHION = registerItem("trader_cushion", new CushionItem(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item KAUPEN_BOW = registerItem("kaupen_bow",
            new BowItem(new Item.Settings().maxDamage(500)));

    // Helper Method to register items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CozyHome.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CozyHome.LOGGER.info("Registering Mod Items for " + CozyHome.MOD_ID);
    }
}
