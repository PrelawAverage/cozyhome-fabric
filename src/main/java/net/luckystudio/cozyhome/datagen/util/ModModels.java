package net.luckystudio.cozyhome.datagen.util;

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;

import java.util.Optional;

public class ModModels {
    public static final Model COUNTER = modBlockWithType("counter", ModBlockTypes.COUNTER, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model COUNTER_INNER = modBlockWithTypeAndVariant("counter_inner", ModBlockTypes.COUNTER, "_inner", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model COUNTER_OUTER = modBlockWithTypeAndVariant("counter_outer", ModBlockTypes.COUNTER, "_outer", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.PARTICLE);

    public static final Model STORAGE_COUNTER = modBlockWithTypeAndVariant("storage_counter", ModBlockTypes.COUNTER, "_open", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.OPEN, ModTextureKey.CLOSED, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model SINK_COUNTER = modBlockWithType("sink_counter", ModBlockTypes.COUNTER, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, ModTextureKey.EXTRA, ModTextureKey.INNER_SIDE, ModTextureKey.INNER_BOTTOM, TextureKey.PARTICLE);

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

    public static final Model COUCH = modBlockWithType("couch", ModBlockTypes.COUCH, TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model COUCH_LEFT = modBlockWithTypeAndVariant("couch_left", ModBlockTypes.COUCH, "_left", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model COUCH_RIGHT = modBlockWithTypeAndVariant("couch_right", ModBlockTypes.COUCH, "_right", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model COUCH_MIDDLE = modBlockWithTypeAndVariant("couch_middle", ModBlockTypes.COUCH, "_middle", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model COUCH_INNER = modBlockWithTypeAndVariant("couch_inner", ModBlockTypes.COUCH, "_inner", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model COUCH_OUTER = modBlockWithTypeAndVariant("couch_outer", ModBlockTypes.COUCH, "_outer", TextureKey.ALL, TextureKey.PARTICLE);

    public static final Model DRAWER = modBlockWithType("template_drawer", ModBlockTypes.DRAWER, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);
    public static final Model DRAWER_LEFT = modBlockWithTypeAndVariant("template_drawer_left", ModBlockTypes.DRAWER, "_left", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model DRAWER_LEFT_DIFF = modBlockWithTypeAndVariant("template_drawer_left_diff", ModBlockTypes.DRAWER, "_left_diff", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);
    public static final Model DRAWER_LEFT_DIFF_LEFT = modBlockWithTypeAndVariant("template_drawer_left_diff_left", ModBlockTypes.DRAWER, "_left_diff_left", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model DRAWER_MIDDLE = modBlockWithTypeAndVariant("template_drawer_middle", ModBlockTypes.DRAWER, "_middle", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model DRAWER_MIDDLE_DIFF = modBlockWithTypeAndVariant("template_drawer_middle_diff", ModBlockTypes.DRAWER, "_middle_diff", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.FRONT, TextureKey.PARTICLE);
    public static final Model DRAWER_RIGHT = modBlockWithTypeAndVariant("template_drawer_right", ModBlockTypes.DRAWER, "_right", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model DRAWER_RIGHT_DIFF = modBlockWithTypeAndVariant("template_drawer_right_diff", ModBlockTypes.DRAWER, "_right_diff", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);
    public static final Model DRAWER_RIGHT_DIFF_RIGHT = modBlockWithTypeAndVariant("template_drawer_right_diff_right", ModBlockTypes.DRAWER, "_right_diff_right", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);

    public static final Model DESK = modBlockWithType("template_desk", ModBlockTypes.DESK, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);
    public static final Model DESK_LEFT = modBlockWithTypeAndVariant("template_desk_left", ModBlockTypes.DESK, "_left", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model DESK_MIDDLE = modBlockWithTypeAndVariant("template_desk_middle", ModBlockTypes.DESK, "_middle", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model DESK_RIGHT = modBlockWithTypeAndVariant("template_desk_right", ModBlockTypes.DESK, "_right", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.INNER_SIDE, TextureKey.BACK, TextureKey.PARTICLE);

    public static final Model WALL_MIRROR = modBlockWithType("wall_mirror", ModBlockTypes.WALL_MIRROR, TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model WALL_MIRROR_TOP = modBlockWithTypeAndVariant("wall_mirror_top", ModBlockTypes.WALL_MIRROR, "_top", TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model WALL_MIRROR_MIDDLE = modBlockWithTypeAndVariant("wall_mirror_middle", ModBlockTypes.WALL_MIRROR, "_middle", TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);
    public static final Model WALL_MIRROR_BOTTOM = modBlockWithTypeAndVariant("wall_mirror_bottom", ModBlockTypes.WALL_MIRROR, "_bottom", TextureKey.FRONT, TextureKey.BACK, TextureKey.PARTICLE);

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

