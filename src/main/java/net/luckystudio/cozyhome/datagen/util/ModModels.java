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

    public static final Model SINK_COUNTER = modBlockWithType("sink_counter", ModBlockTypes.COUNTER, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, ModTextureKey.INNER_SIDE, ModTextureKey.INNER_BOTTOM, ModTextureKey.EXTRA, TextureKey.PARTICLE);
    public static final Model SINK_COUNTER_ON = modBlockWithTypeAndVariant("sink_counter_on", ModBlockTypes.COUNTER, "_on", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.BACK, ModTextureKey.INNER_SIDE, ModTextureKey.INNER_BOTTOM, ModTextureKey.EXTRA, TextureKey.PARTICLE);

    public static final Model CHIMNEY_SINGLE = modBlockWithType("template_chimney", ModBlockTypes.CHIMNEY, TextureKey.TOP, TextureKey.SIDE, ModTextureKey.EXTRA, TextureKey.BOTTOM, TextureKey.PARTICLE);
    public static final Model CHIMNEY_TOP = modBlockWithTypeAndVariant("template_chimney_top", ModBlockTypes.CHIMNEY, "_top", TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);
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

    public static final Model GENERIC_LAMP = modBlockWithType("generic_lamp", ModBlockTypes.LAMP,TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_ON = modBlockWithTypeAndVariant("generic_lamp", ModBlockTypes.LAMP, "_on", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_HEAD = modBlockWithTypeAndVariant("generic_lamp_head", ModBlockTypes.LAMP, "_head", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_HEAD_ON = modBlockWithTypeAndVariant("generic_lamp_head", ModBlockTypes.LAMP, "_head_on", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_MIDDLE = modBlockWithTypeAndVariant("generic_lamp_middle", ModBlockTypes.LAMP,"_middle", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_MIDDLE_ON = modBlockWithTypeAndVariant("generic_lamp_middle", ModBlockTypes.LAMP,"_middle_on", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_BASE = modBlockWithTypeAndVariant("generic_lamp_base", ModBlockTypes.LAMP, "_base", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model GENERIC_LAMP_BASE_ON = modBlockWithTypeAndVariant("generic_lamp_base", ModBlockTypes.LAMP, "_base_on", TextureKey.ALL, TextureKey.PARTICLE);

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

    public static final Model SINK = modBlockWithType("sink", ModBlockTypes.SINK, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.EXTRA, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);
    public static final Model SINK_ON = modBlockWithTypeAndVariant("sink_on", ModBlockTypes.SINK, "_on", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, ModTextureKey.EXTRA, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);

    public static final Model BATHTUB_FRONT = modBlockWithType("bathtub_front", ModBlockTypes.BATHTUB, TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, ModTextureKey.INNER_FRONT, ModTextureKey.INNER_SIDE, TextureKey.PARTICLE);
    public static final Model BATHTUB_BACK = modBlockWithTypeAndVariant("bathtub_back", ModBlockTypes.BATHTUB, "_back", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, ModTextureKey.INNER_FRONT, ModTextureKey.INNER_SIDE, ModTextureKey.EXTRA, TextureKey.PARTICLE);
    public static final Model BATHTUB_BACK_ON = modBlockWithTypeAndVariant("bathtub_back_on", ModBlockTypes.BATHTUB, "_back_on", TextureKey.TOP, TextureKey.SIDE, TextureKey.BOTTOM, TextureKey.FRONT, ModTextureKey.INNER_FRONT, ModTextureKey.INNER_SIDE, ModTextureKey.EXTRA, TextureKey.PARTICLE);
    public static final Model BATHTUB_WATER_6 = modBlockWithTypeAndVariant("bathtub_6", ModBlockTypes.BATHTUB,"bathtub_water_6", TextureKey.UP);
    public static final Model BATHTUB_WATER_5 = modBlockWithTypeAndVariant("bathtub_5", ModBlockTypes.BATHTUB,"bathtub_water_5", TextureKey.UP);
    public static final Model BATHTUB_WATER_4 = modBlockWithTypeAndVariant("bathtub_4", ModBlockTypes.BATHTUB,"bathtub_water_4", TextureKey.UP);
    public static final Model BATHTUB_WATER_3 = modBlockWithTypeAndVariant("bathtub_3", ModBlockTypes.BATHTUB,"bathtub_water_3", TextureKey.UP);
    public static final Model BATHTUB_WATER_2 = modBlockWithTypeAndVariant("bathtub_2", ModBlockTypes.BATHTUB,"bathtub_water_2", TextureKey.UP);
    public static final Model BATHTUB_WATER_1 = modBlockWithTypeAndVariant("bathtub_1", ModBlockTypes.BATHTUB,"bathtub_water_1", TextureKey.UP);
    public static final Model BATHTUB_LAVA_6 = modBlockWithTypeAndVariant("bathtub_6", ModBlockTypes.BATHTUB,"bathtub_lava_6", TextureKey.UP);
    public static final Model BATHTUB_LAVA_5 = modBlockWithTypeAndVariant("bathtub_5", ModBlockTypes.BATHTUB,"bathtub_lava_5", TextureKey.UP);
    public static final Model BATHTUB_LAVA_4 = modBlockWithTypeAndVariant("bathtub_4", ModBlockTypes.BATHTUB,"bathtub_lava_4", TextureKey.UP);
    public static final Model BATHTUB_LAVA_3 = modBlockWithTypeAndVariant("bathtub_3", ModBlockTypes.BATHTUB,"bathtub_lava_3", TextureKey.UP);
    public static final Model BATHTUB_LAVA_2 = modBlockWithTypeAndVariant("bathtub_2", ModBlockTypes.BATHTUB,"bathtub_lava_2", TextureKey.UP);
    public static final Model BATHTUB_LAVA_1 = modBlockWithTypeAndVariant("bathtub_1", ModBlockTypes.BATHTUB,"bathtub_lava_1", TextureKey.UP);

    public static final Model LARGE_STUMP = modBlockWithType("template_large_stump", ModBlockTypes.LARGE_STUMP, TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);
    public static final Model LARGE_STUMP_CORNER = modBlockWithTypeAndVariant("template_large_stump_corner", ModBlockTypes.LARGE_STUMP, "_corner", TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);
    public static final Model LARGE_STUMP_CORNER_PIECE = modBlockWithTypeAndVariant("template_large_stump_corner_piece", ModBlockTypes.LARGE_STUMP, "_corner_piece", TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);
    public static final Model LARGE_STUMP_DOUBLE = modBlockWithTypeAndVariant("template_large_stump_double", ModBlockTypes.LARGE_STUMP, "_double", TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);
    public static final Model LARGE_STUMP_INNER_CORNER_PIECE = modBlockWithTypeAndVariant("template_large_stump_inner_corner_piece", ModBlockTypes.LARGE_STUMP, "_inner_corner_piece", TextureKey.TOP, TextureKey.PARTICLE);
    public static final Model LARGE_STUMP_MIDDLE = modBlockWithTypeAndVariant("template_large_stump_middle", ModBlockTypes.LARGE_STUMP, "_middle", TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);
    public static final Model LARGE_STUMP_SIDE = modBlockWithTypeAndVariant("template_large_stump_side", ModBlockTypes.LARGE_STUMP, "_side", TextureKey.TOP, TextureKey.SIDE, TextureKey.PARTICLE);

    public static final Model FOUNTAIN = modBlockWithType("template_fountain", ModBlockTypes.FOUNTAIN, TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_CORNER = modBlockWithTypeAndVariant("template_fountain_corner", ModBlockTypes.FOUNTAIN, "_corner", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_CORNER_PIECE = modBlockWithTypeAndVariant("template_fountain_corner_piece", ModBlockTypes.FOUNTAIN, "_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_DOUBLE = modBlockWithTypeAndVariant("template_fountain_double", ModBlockTypes.FOUNTAIN, "_double", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_INNER_CORNER_PIECE = modBlockWithTypeAndVariant("template_fountain_inner_corner_piece", ModBlockTypes.FOUNTAIN, "_inner_corner_piece", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_MIDDLE = modBlockWithTypeAndVariant("template_fountain_middle", ModBlockTypes.FOUNTAIN, "_middle", TextureKey.ALL, TextureKey.PARTICLE);
    public static final Model FOUNTAIN_SIDE = modBlockWithTypeAndVariant("template_fountain_side", ModBlockTypes.FOUNTAIN, "_side", TextureKey.ALL, TextureKey.PARTICLE);

    public static final Model WATER_15 = modBlockWithTypeAndVariant("flat_15", ModBlockTypes.FLAT,"water_15", TextureKey.UP);

    public static final Model LAVA_15 = modBlockWithTypeAndVariant("flat_15", ModBlockTypes.FLAT,"lava_15", TextureKey.UP);
    public static final Model ICE_15 = modBlockWithTypeAndVariant("flat_15", ModBlockTypes.FLAT,"ice_15", TextureKey.UP);
    public static final Model INSET_WATER_FLAT_15 = modBlockWithTypeAndVariant("inset_flat_15", ModBlockTypes.FLAT,"inset_water_15", TextureKey.UP);
    public static final Model INSET_WATER_FLAT_13 = modBlockWithTypeAndVariant("inset_flat_13", ModBlockTypes.FLAT,"inset_water_13", TextureKey.UP);
    public static final Model INSET_WATER_FLAT_11 = modBlockWithTypeAndVariant("inset_flat_11", ModBlockTypes.FLAT,"inset_water_11", TextureKey.UP);

    private static Model modBlock(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model modBlockWithVariant(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    public static Model modBlockWithType(String parent, ModBlockTypes type, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + type + "/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    public static Model modBlockWithTypeAndVariant(String parent, ModBlockTypes type, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(CozyHome.id("block/" + type + "/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
}

