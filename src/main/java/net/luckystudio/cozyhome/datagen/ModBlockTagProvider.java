package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                // COUNTERS
                .add(ModBlocks.OAK_COUNTER)
                .add(ModBlocks.SPRUCE_COUNTER)
                .add(ModBlocks.BIRCH_COUNTER)
                .add(ModBlocks.JUNGLE_COUNTER)
                .add(ModBlocks.ACACIA_COUNTER)
                .add(ModBlocks.DARK_OAK_COUNTER)
                .add(ModBlocks.MANGROVE_COUNTER)
                .add(ModBlocks.CHERRY_COUNTER)
                .add(ModBlocks.BAMBOO_COUNTER)
                .add(ModBlocks.CRIMSON_COUNTER)
                .add(ModBlocks.WARPED_COUNTER)

                // STORAGE COUNTERS
                .add(ModBlocks.OAK_STORAGE_COUNTER)
                .add(ModBlocks.SPRUCE_STORAGE_COUNTER)
                .add(ModBlocks.BIRCH_STORAGE_COUNTER)
                .add(ModBlocks.JUNGLE_STORAGE_COUNTER)
                .add(ModBlocks.ACACIA_STORAGE_COUNTER)
                .add(ModBlocks.DARK_OAK_STORAGE_COUNTER)
                .add(ModBlocks.MANGROVE_STORAGE_COUNTER)
                .add(ModBlocks.CHERRY_STORAGE_COUNTER)
                .add(ModBlocks.BAMBOO_STORAGE_COUNTER)
                .add(ModBlocks.CRIMSON_STORAGE_COUNTER)
                .add(ModBlocks.WARPED_STORAGE_COUNTER)

                // SINK COUNTERS
                .add(ModBlocks.OAK_SINK_COUNTER)
                .add(ModBlocks.SPRUCE_SINK_COUNTER)
                .add(ModBlocks.BIRCH_SINK_COUNTER)
                .add(ModBlocks.JUNGLE_SINK_COUNTER)
                .add(ModBlocks.ACACIA_SINK_COUNTER)
                .add(ModBlocks.DARK_OAK_SINK_COUNTER)
                .add(ModBlocks.MANGROVE_SINK_COUNTER)
                .add(ModBlocks.CHERRY_SINK_COUNTER)
                .add(ModBlocks.BAMBOO_SINK_COUNTER)
                .add(ModBlocks.CRIMSON_SINK_COUNTER)
                .add(ModBlocks.WARPED_SINK_COUNTER)

                // TABLES
                .add(ModBlocks.OAK_TABLE)
                .add(ModBlocks.SPRUCE_TABLE)
                .add(ModBlocks.BIRCH_TABLE)
                .add(ModBlocks.JUNGLE_TABLE)
                .add(ModBlocks.ACACIA_TABLE)
                .add(ModBlocks.DARK_OAK_TABLE)
                .add(ModBlocks.MANGROVE_TABLE)
                .add(ModBlocks.CHERRY_TABLE)
                .add(ModBlocks.BAMBOO_TABLE)
                .add(ModBlocks.CRIMSON_TABLE)
                .add(ModBlocks.WARPED_TABLE)

                // CHAIRS
                .add(ModBlocks.OAK_CHAIR)
                .add(ModBlocks.SPRUCE_CHAIR)
                .add(ModBlocks.BIRCH_CHAIR)
                .add(ModBlocks.JUNGLE_CHAIR)
                .add(ModBlocks.ACACIA_CHAIR)
                .add(ModBlocks.DARK_OAK_CHAIR)
                .add(ModBlocks.MANGROVE_CHAIR)
                .add(ModBlocks.CHERRY_CHAIR)
                .add(ModBlocks.BAMBOO_CHAIR)
                .add(ModBlocks.CRIMSON_CHAIR)
                .add(ModBlocks.WARPED_CHAIR)

                // WALL CLOCKS
                .add(ModBlocks.OAK_WALL_CLOCK)
                .add(ModBlocks.SPRUCE_WALL_CLOCK)
                .add(ModBlocks.BIRCH_WALL_CLOCK)
                .add(ModBlocks.JUNGLE_WALL_CLOCK)
                .add(ModBlocks.ACACIA_WALL_CLOCK)
                .add(ModBlocks.DARK_OAK_WALL_CLOCK)
                .add(ModBlocks.MANGROVE_WALL_CLOCK)
                .add(ModBlocks.CHERRY_WALL_CLOCK)
                .add(ModBlocks.BAMBOO_WALL_CLOCK)
                .add(ModBlocks.CRIMSON_WALL_CLOCK)
                .add(ModBlocks.WARPED_WALL_CLOCK)

                // GRANDFATHER CLOCKS
                .add(ModBlocks.OAK_GRANDFATHER_CLOCK)
                .add(ModBlocks.SPRUCE_GRANDFATHER_CLOCK)
                .add(ModBlocks.BIRCH_GRANDFATHER_CLOCK)
                .add(ModBlocks.JUNGLE_GRANDFATHER_CLOCK)
                .add(ModBlocks.ACACIA_GRANDFATHER_CLOCK)
                .add(ModBlocks.DARK_OAK_GRANDFATHER_CLOCK)
                .add(ModBlocks.MANGROVE_GRANDFATHER_CLOCK)
                .add(ModBlocks.CHERRY_GRANDFATHER_CLOCK)
                .add(ModBlocks.BAMBOO_GRANDFATHER_CLOCK)
                .add(ModBlocks.CRIMSON_GRANDFATHER_CLOCK)
                .add(ModBlocks.WARPED_GRANDFATHER_CLOCK)

                // SOFA
                .add(ModBlocks.OAK_SOFA)
                .add(ModBlocks.SPRUCE_SOFA)
                .add(ModBlocks.BIRCH_SOFA)
                .add(ModBlocks.JUNGLE_SOFA)
                .add(ModBlocks.ACACIA_SOFA)
                .add(ModBlocks.DARK_OAK_SOFA)
                .add(ModBlocks.MANGROVE_SOFA)
                .add(ModBlocks.CHERRY_SOFA)
                .add(ModBlocks.BAMBOO_SOFA)
                .add(ModBlocks.CRIMSON_SOFA)
                .add(ModBlocks.WARPED_SOFA)

                // COUCH
                .add(ModBlocks.OAK_COUCH)
                .add(ModBlocks.SPRUCE_COUCH)
                .add(ModBlocks.BIRCH_COUCH)
                .add(ModBlocks.JUNGLE_COUCH)
                .add(ModBlocks.ACACIA_COUCH)
                .add(ModBlocks.DARK_OAK_COUCH)
                .add(ModBlocks.MANGROVE_COUCH)
                .add(ModBlocks.CHERRY_COUCH)
                .add(ModBlocks.BAMBOO_COUCH)
                .add(ModBlocks.CRIMSON_COUCH)
                .add(ModBlocks.WARPED_COUCH)

                // DESKS
                .add(ModBlocks.OAK_DESK)
                .add(ModBlocks.SPRUCE_DESK)
                .add(ModBlocks.BIRCH_DESK)
                .add(ModBlocks.JUNGLE_DESK)
                .add(ModBlocks.ACACIA_DESK)
                .add(ModBlocks.DARK_OAK_DESK)
                .add(ModBlocks.MANGROVE_DESK)
                .add(ModBlocks.CHERRY_DESK)
                .add(ModBlocks.BAMBOO_DESK)
                .add(ModBlocks.CRIMSON_DESK)
                .add(ModBlocks.WARPED_DESK)

                // WALL MIRRORS
                .add(ModBlocks.OAK_WALL_MIRROR)
                .add(ModBlocks.SPRUCE_WALL_MIRROR)
                .add(ModBlocks.BIRCH_WALL_MIRROR)
                .add(ModBlocks.JUNGLE_WALL_MIRROR)
                .add(ModBlocks.ACACIA_WALL_MIRROR)
                .add(ModBlocks.DARK_OAK_WALL_MIRROR)
                .add(ModBlocks.MANGROVE_WALL_MIRROR)
                .add(ModBlocks.CHERRY_WALL_MIRROR)
                .add(ModBlocks.BAMBOO_WALL_MIRROR)
                .add(ModBlocks.CRIMSON_WALL_MIRROR)
                .add(ModBlocks.WARPED_WALL_MIRROR)

                // DRAWERS
                .add(ModBlocks.OAK_DRAWER)
                .add(ModBlocks.SPRUCE_DRAWER)
                .add(ModBlocks.BIRCH_DRAWER)
                .add(ModBlocks.JUNGLE_DRAWER)
                .add(ModBlocks.ACACIA_DRAWER)
                .add(ModBlocks.DARK_OAK_DRAWER)
                .add(ModBlocks.MANGROVE_DRAWER)
                .add(ModBlocks.CHERRY_DRAWER)
                .add(ModBlocks.BAMBOO_DRAWER)
                .add(ModBlocks.CRIMSON_DRAWER)
                .add(ModBlocks.WARPED_DRAWER)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                // COUNTERS
                .add(ModBlocks.OAK_COUNTER)
                .add(ModBlocks.SPRUCE_COUNTER)
                .add(ModBlocks.BIRCH_COUNTER)
                .add(ModBlocks.JUNGLE_COUNTER)
                .add(ModBlocks.ACACIA_COUNTER)
                .add(ModBlocks.DARK_OAK_COUNTER)
                .add(ModBlocks.MANGROVE_COUNTER)
                .add(ModBlocks.CHERRY_COUNTER)
                .add(ModBlocks.BAMBOO_COUNTER)
                .add(ModBlocks.CRIMSON_COUNTER)
                .add(ModBlocks.WARPED_COUNTER)

                // STORAGE COUNTERS
                .add(ModBlocks.OAK_STORAGE_COUNTER)
                .add(ModBlocks.SPRUCE_STORAGE_COUNTER)
                .add(ModBlocks.BIRCH_STORAGE_COUNTER)
                .add(ModBlocks.JUNGLE_STORAGE_COUNTER)
                .add(ModBlocks.ACACIA_STORAGE_COUNTER)
                .add(ModBlocks.DARK_OAK_STORAGE_COUNTER)
                .add(ModBlocks.MANGROVE_STORAGE_COUNTER)
                .add(ModBlocks.CHERRY_STORAGE_COUNTER)
                .add(ModBlocks.BAMBOO_STORAGE_COUNTER)
                .add(ModBlocks.CRIMSON_STORAGE_COUNTER)
                .add(ModBlocks.WARPED_STORAGE_COUNTER)

                // SINK COUNTERS
                .add(ModBlocks.OAK_SINK_COUNTER)
                .add(ModBlocks.SPRUCE_SINK_COUNTER)
                .add(ModBlocks.BIRCH_SINK_COUNTER)
                .add(ModBlocks.JUNGLE_SINK_COUNTER)
                .add(ModBlocks.ACACIA_SINK_COUNTER)
                .add(ModBlocks.DARK_OAK_SINK_COUNTER)
                .add(ModBlocks.MANGROVE_SINK_COUNTER)
                .add(ModBlocks.CHERRY_SINK_COUNTER)
                .add(ModBlocks.BAMBOO_SINK_COUNTER)
                .add(ModBlocks.CRIMSON_SINK_COUNTER)
                .add(ModBlocks.WARPED_SINK_COUNTER)

                // TABLES
                .add(ModBlocks.IRON_TABLE)
                .add(ModBlocks.GLASS_TABLE)
                .add(ModBlocks.UNDEAD_TABLE)
                .add(ModBlocks.OMINOUS_TABLE)

                // WALL CLOCKS
                .add(ModBlocks.IRON_WALL_CLOCK)
                .add(ModBlocks.GLASS_WALL_CLOCK)
                .add(ModBlocks.UNDEAD_WALL_CLOCK)
                .add(ModBlocks.OMINOUS_WALL_CLOCK)

                // GRANDFATHER CLOCKS
                .add(ModBlocks.IRON_GRANDFATHER_CLOCK)
                .add(ModBlocks.GLASS_GRANDFATHER_CLOCK)
                .add(ModBlocks.UNDEAD_GRANDFATHER_CLOCK)
                .add(ModBlocks.OMINOUS_GRANDFATHER_CLOCK)

                // CHIMNEY
                .add(ModBlocks.STONE_BRICK_CHIMNEY)
                .add(ModBlocks.MOSSY_STONE_BRICK_CHIMNEY)
                .add(ModBlocks.GRANITE_CHIMNEY)
                .add(ModBlocks.DIORITE_CHIMNEY)
                .add(ModBlocks.ANDESITE_CHIMNEY)
                .add(ModBlocks.DEEPSLATE_CHIMNEY)
                .add(ModBlocks.TUFF_CHIMNEY)
                .add(ModBlocks.BRICK_CHIMNEY)
                .add(ModBlocks.MUD_CHIMNEY)
                .add(ModBlocks.SANDSTONE_CHIMNEY)
                .add(ModBlocks.RED_SANDSTONE_CHIMNEY)
                .add(ModBlocks.PRISMARINE_CHIMNEY)
                .add(ModBlocks.NETHER_BRICK_CHIMNEY)
                .add(ModBlocks.RED_NETHER_BRICK_CHIMNEY)
                .add(ModBlocks.BLACKSTONE_CHIMNEY)
                .add(ModBlocks.ENDSTONE_CHIMNEY)
                .add(ModBlocks.PURPUR_CHIMNEY)

                // FOUNTAIN
                .add(ModBlocks.STONE_BRICK_FOUNTAIN)
                .add(ModBlocks.MOSSY_STONE_BRICK_FOUNTAIN)
                .add(ModBlocks.GRANITE_FOUNTAIN)
                .add(ModBlocks.DIORITE_FOUNTAIN)
                .add(ModBlocks.ANDESITE_FOUNTAIN)
                .add(ModBlocks.DEEPSLATE_FOUNTAIN)
                .add(ModBlocks.TUFF_FOUNTAIN)
                .add(ModBlocks.BRICK_FOUNTAIN)
                .add(ModBlocks.MUD_FOUNTAIN)
                .add(ModBlocks.SANDSTONE_FOUNTAIN)
                .add(ModBlocks.RED_SANDSTONE_FOUNTAIN)
                .add(ModBlocks.PRISMARINE_FOUNTAIN)
                .add(ModBlocks.NETHER_BRICK_FOUNTAIN)
                .add(ModBlocks.RED_NETHER_BRICK_FOUNTAIN)
                .add(ModBlocks.BLACKSTONE_FOUNTAIN)
                .add(ModBlocks.ENDSTONE_FOUNTAIN)
                .add(ModBlocks.PURPUR_FOUNTAIN)
        ;
    }
}
