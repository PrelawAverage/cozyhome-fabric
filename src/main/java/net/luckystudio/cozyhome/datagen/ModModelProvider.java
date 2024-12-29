package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    // Even though it states "blockstate", these will generate blockstates, models, and item models.
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGenerals(blockStateModelGenerator);

        registerTable(blockStateModelGenerator, ModBlocks.OAK_TABLE, TableTypes.GENERIC, Identifier.of("block/oak_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.SPRUCE_TABLE, TableTypes.GENERIC, Identifier.of("block/spruce_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.BIRCH_TABLE, TableTypes.GENERIC, Identifier.of("block/birch_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.JUNGLE_TABLE, TableTypes.GENERIC, Identifier.of("block/jungle_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.ACACIA_TABLE, TableTypes.GENERIC, Identifier.of("block/acacia_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.DARK_OAK_TABLE, TableTypes.GENERIC, Identifier.of("block/dark_oak_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.MANGROVE_TABLE, TableTypes.GENERIC, Identifier.of("block/mangrove_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.CHERRY_TABLE, TableTypes.GENERIC, Identifier.of("block/cherry_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.BAMBOO_TABLE, TableTypes.GENERIC, Identifier.of("block/bamboo_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.CRIMSON_TABLE, TableTypes.GENERIC, Identifier.of("block/crimson_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.WARPED_TABLE, TableTypes.GENERIC, Identifier.of("block/warped_planks"));
        registerTable(blockStateModelGenerator, ModBlocks.IRON_TABLE, TableTypes.SHELF, CozyHome.id("block/break/iron_furniture"));
        registerTable(blockStateModelGenerator, ModBlocks.GLASS_TABLE, TableTypes.SHELF, CozyHome.id("block/break/glass_furniture"));
        registerTable(blockStateModelGenerator, ModBlocks.UNDEAD_TABLE, TableTypes.GENERIC, CozyHome.id("block/break/undead_furniture"));
        registerTable(blockStateModelGenerator, ModBlocks.OMINOUS_TABLE, TableTypes.GENERIC, CozyHome.id("block/break/ominous_furniture"));

        registerFountain(blockStateModelGenerator, ModBlocks.STONE_BRICK_FOUNTAIN, Identifier.of("block/stone_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN, Identifier.of("block/mossy_stone_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.GRANITE_FOUNTAIN, Identifier.of("block/granite"));
        registerFountain(blockStateModelGenerator, ModBlocks.DIORITE_FOUNTAIN, Identifier.of("block/diorite"));
        registerFountain(blockStateModelGenerator, ModBlocks.ANDESITE_FOUNTAIN, Identifier.of("block/andesite"));
        registerFountain(blockStateModelGenerator, ModBlocks.DEEPSLATE_FOUNTAIN, Identifier.of("block/deepslate"));
        registerFountain(blockStateModelGenerator, ModBlocks.TUFF_FOUNTAIN, Identifier.of("block/tuff"));
        registerFountain(blockStateModelGenerator, ModBlocks.BRICK_FOUNTAIN, Identifier.of("block/bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.MUD_FOUNTAIN, Identifier.of("block/mud"));
        registerFountain(blockStateModelGenerator, ModBlocks.SANDSTONE_FOUNTAIN, Identifier.of("block/sandstone"));
        registerFountain(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_FOUNTAIN, Identifier.of("block/red_sandstone"));
        registerFountain(blockStateModelGenerator, ModBlocks.PRISMARINE_FOUNTAIN, Identifier.of("block/prismarine_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.NETHER_BRICK_FOUNTAIN, Identifier.of("block/nether_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_FOUNTAIN, Identifier.of("block/red_nether_bricks"));
        registerFountain(blockStateModelGenerator, ModBlocks.BLACKSTONE_FOUNTAIN, Identifier.of("block/blackstone"));
        registerFountain(blockStateModelGenerator, ModBlocks.ENDSTONE_FOUNTAIN, Identifier.of("block/end_stone"));
        registerFountain(blockStateModelGenerator, ModBlocks.PURPUR_FOUNTAIN, Identifier.of("block/purpur_block"));

        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.OAK_CHAIR, Identifier.of("block/oak_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.SPRUCE_CHAIR, Identifier.of("block/spruce_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.BIRCH_CHAIR, Identifier.of("block/birch_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.JUNGLE_CHAIR, Identifier.of("block/jungle_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.ACACIA_CHAIR, Identifier.of("block/acacia_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.DARK_OAK_CHAIR, Identifier.of("block/dark_oak_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.MANGROVE_CHAIR, Identifier.of("block/mangrove_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.CHERRY_CHAIR, Identifier.of("block/cherry_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.BAMBOO_CHAIR, Identifier.of("block/bamboo_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.CRIMSON_CHAIR, Identifier.of("block/crimson_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.WARPED_CHAIR, Identifier.of("block/warped_planks"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.IRON_CHAIR, CozyHome.id("block/break/iron_furniture"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.GLASS_CHAIR, CozyHome.id("block/break/glass_furniture"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.UNDEAD_CHAIR, CozyHome.id("block/break/undead_furniture"), CozyHome.id("item/template/template_chair"));
        registerBuiltinWithParticleAndItemModel(blockStateModelGenerator, ModBlocks.OMINOUS_CHAIR, CozyHome.id("block/break/ominous_furniture"), CozyHome.id("item/template/template_chair"));

        registerChimney(blockStateModelGenerator, ModBlocks.STONE_BRICK_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.MOSSY_STONE_BRICK_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.GRANITE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.DIORITE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.ANDESITE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.DEEPSLATE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.TUFF_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.BRICK_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.MUD_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.SANDSTONE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.RED_SANDSTONE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.PRISMARINE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.NETHER_BRICK_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.RED_NETHER_BRICK_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.BLACKSTONE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.ENDSTONE_CHIMNEY);
        registerChimney(blockStateModelGenerator, ModBlocks.PURPUR_CHIMNEY);
    }

    // I have no idea what this does, but it's required.
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }

    /**
     * Many blocks share models in them, but we can't register the shared models in the blockstate generator because it will cause a duplicate model error.
     * So we create a separate method to generate them here and call it at the beginning, for all to use.
     */
    public final void registerGenerals(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier waterModelId = ModModels.INSET_WATER_PANE.upload(
                CozyHome.id("block/inset_water_pane"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/water_still")).put(TextureKey.PARTICLE, Identifier.ofVanilla("block/water_still")), blockStateModelGenerator.modelCollector);
        Identifier lavaModelId = ModModels.INSET_LAVA_PANE.upload(
                CozyHome.id("block/inset_lava_pane"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/lava_still")).put(TextureKey.PARTICLE, Identifier.ofVanilla("block/lava_still")), blockStateModelGenerator.modelCollector);
        Identifier iceModelId = ModModels.INSET_ICE_PANE.upload(
                CozyHome.id("block/inset_ice_pane"), new TextureMap().put(TextureKey.UP, Identifier.ofVanilla("block/ice")).put(TextureKey.PARTICLE, Identifier.ofVanilla("block/ice")), blockStateModelGenerator.modelCollector);
    }

    /**
     * Some blocks are rendered and don't have actual models to them, but they still require blockstate, block, and item files for certain features like break particles. We also give these blocks a custom item model.
     */
    public final void registerBuiltinWithParticleAndItemModel(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier particleSource, Identifier modelPath) {
        blockStateModelGenerator.registerBuiltinWithParticle(block, particleSource);
        blockStateModelGenerator.registerParentedItemModel(block, modelPath);
    }

    /**
     * Registering Tables
     * @param blockStateModelGenerator
     * @param block
     * @param tableTypes
     * @param breakParticle
     */
    public final void registerTable(BlockStateModelGenerator blockStateModelGenerator, Block block, TableTypes tableTypes, Identifier breakParticle) {
        // Define texture maps
        String blockNamespace = Registries.BLOCK.getId(block).getNamespace();
        String blockPath = Registries.BLOCK.getId(block).getPath();
        String baseTexturePath = "block/table/" + blockPath;

        TextureMap baseTexture = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, breakParticle);
        Identifier tableModelID;
        Identifier tableCornerModelID;
        Identifier tableCornerPieceModelID;
        Identifier tableDoubleModelID;
        Identifier tableInnerCornerPieceModelID;
        Identifier tableMiddleModelID;
        Identifier tableSideModelID;

        if (tableTypes == TableTypes.SHELF) {
            // Upload models for various table states
            tableModelID = ModModels.SHELF_TABLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerModelID = ModModels.SHELF_TABLE_CORNER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerPieceModelID = ModModels.SHELF_TABLE_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableDoubleModelID = ModModels.SHELF_TABLE_DOUBLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableInnerCornerPieceModelID = ModModels.SHELF_TABLE_INNER_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableMiddleModelID = ModModels.SHELF_TABLE_MIDDLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableSideModelID = ModModels.SHELF_TABLE_SIDE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        } else {
            // Upload models for various table states
            tableModelID = ModModels.TABLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerModelID = ModModels.TABLE_CORNER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableCornerPieceModelID = ModModels.TABLE_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableDoubleModelID = ModModels.TABLE_DOUBLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableInnerCornerPieceModelID = ModModels.TABLE_INNER_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableMiddleModelID = ModModels.TABLE_MIDDLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            tableSideModelID = ModModels.TABLE_SIDE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
        }

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableMiddleModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableMiddleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.SOUTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID))
                .with(When.create().set(ModProperties.SOUTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.NORTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.NORTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.EAST, true).set(ModProperties.SOUTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.WEST, true).set(ModProperties.SOUTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.WEST, true).set(ModProperties.NORTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(ModProperties.NORTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, tableSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        );
    }

    /**
     * Registering Fountains
     * @param blockStateModelGenerator
     * @param block
     */
    public final void registerFountain(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier breakParticle) {
        // Define texture maps
        String blockNamespace = Registries.BLOCK.getId(block).getNamespace();
        String blockPath = Registries.BLOCK.getId(block).getPath();
        String baseTexturePath = "block/fountain/" + blockPath;

        TextureMap baseTexture = new TextureMap()
                .put(TextureKey.ALL, Identifier.of(blockNamespace, baseTexturePath))
                .put(TextureKey.PARTICLE, breakParticle);

        Identifier fountainModelID;
        Identifier fountainCornerModelID;
        Identifier fountainCornerPieceModelID;
        Identifier fountainDoubleModelID;
        Identifier fountainInnerCornerPieceModelID;
        Identifier fountainMiddleModelID;
        Identifier fountainSideModelID;

            // Upload models for various fountain states
            fountainModelID = ModModels.FOUNTAIN.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            fountainCornerModelID = ModModels.FOUNTAIN_CORNER.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            fountainCornerPieceModelID = ModModels.FOUNTAIN_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            fountainDoubleModelID = ModModels.FOUNTAIN_DOUBLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            fountainInnerCornerPieceModelID = ModModels.FOUNTAIN_INNER_CORNER_PIECE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            fountainMiddleModelID = ModModels.FOUNTAIN_MIDDLE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);
            fountainSideModelID = ModModels.FOUNTAIN_SIDE.upload(block, baseTexture, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainDoubleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainMiddleModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainMiddleModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.SOUTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID))
                .with(When.create().set(ModProperties.SOUTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(ModProperties.NORTH_WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(ModProperties.NORTH_EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainInnerCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.EAST, true).set(ModProperties.SOUTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID))
                .with(When.create().set(Properties.SOUTH, true).set(Properties.WEST, true).set(ModProperties.SOUTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.WEST, true).set(ModProperties.NORTH_WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(ModProperties.NORTH_EAST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerPieceModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainCornerModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, true).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainSideModelID).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.WATER),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_water_pane")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.LAVA),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_lava_pane")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.ICE),
                        BlockStateVariant.create().put(VariantSettings.MODEL, CozyHome.id("block/inset_ice_pane")))
                .with(When.create().set(ModProperties.CONTAINS, ContainsBlock.ICE).set(Properties.NORTH, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, fountainModelID))
        );
    }

    /**
     * Registering Chimneys
     * @param blockStateModelGenerator
     * @param block
     */
    public final void registerChimney (
            BlockStateModelGenerator blockStateModelGenerator, Block block) {
        TextureMap chimney_single = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_single"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_intake_bottom"))
                .put(TextureKey.PARTICLE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"));
        TextureMap chimney_top = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_bottom"))
                .put(TextureKey.PARTICLE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"));
        TextureMap chimney_middle = new TextureMap()
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_middle"))
                .put(TextureKey.PARTICLE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"));
        TextureMap chimney_intake = new TextureMap()
                .put(TextureKey.TOP, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_intake_top"))
                .put(TextureKey.SIDE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_intake_side"))
                .put(TextureKey.BOTTOM, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_intake_bottom"))
                .put(TextureKey.PARTICLE, Identifier.of(Registries.BLOCK.getId(block).getNamespace(), "block/chimney/" + Registries.BLOCK.getId(block).getPath() + "_top"));
        Identifier singleModelId = ModModels.CHIMNEY_SINGLE.upload(block, chimney_single, blockStateModelGenerator.modelCollector);
        Identifier headModelId = ModModels.CHIMNEY_TOP.upload(block, chimney_top, blockStateModelGenerator.modelCollector);
        Identifier middleModelId = ModModels.CHIMNEY_MIDDLE.upload(block, chimney_middle, blockStateModelGenerator.modelCollector);
        Identifier tailModelId = ModModels.CHIMNEY_INTAKE.upload(block, chimney_intake, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(ModProperties.VERTICAL_CONNECTION)
                        .register(VerticalLinearConnectionBlock.HEAD, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, headModelId))
                        .register(VerticalLinearConnectionBlock.MIDDLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, middleModelId))
                        .register(VerticalLinearConnectionBlock.TAIL, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, tailModelId))
                        .register(VerticalLinearConnectionBlock.SINGLE, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, singleModelId))
                )
        );
    }
}
