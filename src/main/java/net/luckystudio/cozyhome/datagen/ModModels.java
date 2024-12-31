package net.luckystudio.cozyhome.datagen;

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;

import java.util.Optional;

public class ModModels {
    public static final Model CHIMNEY_SINGLE = modBlockWithType("template_chimney", ModBlockTypes.CHIMNEY, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.PARTICLE);
    public static final Model CHIMNEY_TOP = modBlockWithTypeAndVariant("template_chimney_top", ModBlockTypes.CHIMNEY, "_top", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.PARTICLE);
    public static final Model CHIMNEY_MIDDLE = modBlockWithTypeAndVariant("template_chimney_middle", ModBlockTypes.CHIMNEY, "_side", TextureKey.SIDE, TextureKey.PARTICLE);
    public static final Model CHIMNEY_INTAKE = modBlockWithTypeAndVariant("template_chimney_intake", ModBlockTypes.CHIMNEY, "_bottom", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.PARTICLE);
    public static final Model TABLE = modBlockWithType("template_table", ModBlockTypes.TABLE, TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model TABLE_CORNER = modBlockWithTypeAndVariant("template_table_corner", ModBlockTypes.TABLE, "_corner", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model TABLE_CORNER_PIECE = modBlockWithTypeAndVariant("template_table_corner_piece", ModBlockTypes.TABLE, "_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model TABLE_DOUBLE = modBlockWithTypeAndVariant("template_table_double", ModBlockTypes.TABLE, "_double", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model TABLE_INNER_CORNER_PIECE = modBlockWithTypeAndVariant("template_table_inner_corner_piece", ModBlockTypes.TABLE, "_inner_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model TABLE_MIDDLE = modBlockWithTypeAndVariant("template_table_middle", ModBlockTypes.TABLE, "_middle", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model TABLE_SIDE = modBlockWithTypeAndVariant("template_table_side", ModBlockTypes.TABLE, "_side", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE = modBlockWithType("template_shelf_table", ModBlockTypes.TABLE, TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE_CORNER = modBlockWithTypeAndVariant("template_shelf_table_corner", ModBlockTypes.TABLE, "_corner", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE_CORNER_PIECE = modBlockWithTypeAndVariant("template_shelf_table_corner_piece", ModBlockTypes.TABLE, "_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE_DOUBLE = modBlockWithTypeAndVariant("template_shelf_table_double", ModBlockTypes.TABLE, "_double", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE_INNER_CORNER_PIECE = modBlockWithTypeAndVariant("template_shelf_table_inner_corner_piece", ModBlockTypes.TABLE, "_inner_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE_MIDDLE = modBlockWithTypeAndVariant("template_shelf_table_middle", ModBlockTypes.TABLE, "_middle", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model SHELF_TABLE_SIDE = modBlockWithTypeAndVariant("template_shelf_table_side", ModBlockTypes.TABLE, "_side", TextureKey.ALL, TextureKey.PARTICLE);

    public static final Model FOUNTAIN = modBlockWithType("template_fountain", ModBlockTypes.FOUNTAIN, TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_CORNER = modBlockWithTypeAndVariant("template_fountain_corner", ModBlockTypes.FOUNTAIN, "_corner", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_CORNER_PIECE = modBlockWithTypeAndVariant("template_fountain_corner_piece", ModBlockTypes.FOUNTAIN, "_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_DOUBLE = modBlockWithTypeAndVariant("template_fountain_double", ModBlockTypes.FOUNTAIN, "_double", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_INNER_CORNER_PIECE = modBlockWithTypeAndVariant("template_fountain_inner_corner_piece", ModBlockTypes.FOUNTAIN, "_inner_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_MIDDLE = modBlockWithTypeAndVariant("template_fountain_middle", ModBlockTypes.FOUNTAIN, "_middle", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_SIDE = modBlockWithTypeAndVariant("template_fountain_side", ModBlockTypes.FOUNTAIN, "_side", TextureKey.ALL, TextureKey.PARTICLE);

    public static final Model INSET_WATER_PANE = modBlockWithTypeAndVariant("template_pane_15", ModBlockTypes.PLANE,"water", TextureKey.UP, TextureKey.PARTICLE);
    public static final Model INSET_LAVA_PANE = modBlockWithTypeAndVariant("template_pane_15", ModBlockTypes.PLANE,"lava", TextureKey.UP, TextureKey.PARTICLE);
    public static final Model INSET_ICE_PANE = modBlockWithTypeAndVariant("template_pane_15", ModBlockTypes.PLANE,"ice", TextureKey.UP, TextureKey.PARTICLE);

    private static Model modBlock(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model modBlockWithVariant(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private static Model modBlockWithType(String parent, ModBlockTypes type, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + type + "/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model modBlockWithTypeAndVariant(String parent, ModBlockTypes type, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + type + "/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
}
