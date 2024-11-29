package net.luckystudio.cozyhome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;

import java.util.concurrent.CompletableFuture;

public class MobBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MobBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                // Adding Planked Walls
                .add(ModBlocks.OAK_PLANKED_WALL)
                .add(ModBlocks.SPRUCE_PLANKED_WALL)
                .add(ModBlocks.BIRCH_PLANKED_WALL)
                .add(ModBlocks.JUNGLE_PLANKED_WALL)
                .add(ModBlocks.ACACIA_PLANKED_WALL)
                .add(ModBlocks.DARK_OAK_PLANKED_WALL)
                .add(ModBlocks.MANGROVE_PLANKED_WALL)
                .add(ModBlocks.CHERRY_PLANKED_WALL)
                .add(ModBlocks.BAMBOO_PLANKED_WALL)
                .add(ModBlocks.CRIMSON_PLANKED_WALL)
                .add(ModBlocks.WARPED_PLANKED_WALL)

                // Counters
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

                // Storage Counters
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

                // Sink Counters
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

                // Wall Clock
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

                // Grandfather Clock
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

                // Desk
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

                // Drawers
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

                // Beams
                .add(ModBlocks.OAK_BEAM)
                .add(ModBlocks.SPRUCE_BEAM)
                .add(ModBlocks.BIRCH_BEAM)
                .add(ModBlocks.JUNGLE_BEAM)
                .add(ModBlocks.ACACIA_BEAM)
                .add(ModBlocks.DARK_OAK_BEAM)
                .add(ModBlocks.MANGROVE_BEAM)
                .add(ModBlocks.CHERRY_BEAM)
                .add(ModBlocks.BAMBOO_BEAM)
                .add(ModBlocks.CRIMSON_BEAM)
                .add(ModBlocks.WARPED_BEAM)
                .add(ModBlocks.STRIPPED_OAK_BEAM)
                .add(ModBlocks.STRIPPED_SPRUCE_BEAM)
                .add(ModBlocks.STRIPPED_BIRCH_BEAM)
                .add(ModBlocks.STRIPPED_JUNGLE_BEAM)
                .add(ModBlocks.STRIPPED_ACACIA_BEAM)
                .add(ModBlocks.STRIPPED_DARK_OAK_BEAM)
                .add(ModBlocks.STRIPPED_MANGROVE_BEAM)
                .add(ModBlocks.STRIPPED_CHERRY_BEAM)
                .add(ModBlocks.STRIPPED_BAMBOO_BEAM)
                .add(ModBlocks.STRIPPED_CRIMSON_BEAM)
                .add(ModBlocks.STRIPPED_WARPED_BEAM)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                // Counters
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

                // Storage Counters
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

                // Sink Counters
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

                // Wall Clocks
                .add(ModBlocks.IRON_WALL_CLOCK)
                .add(ModBlocks.GLASS_WALL_CLOCK)
                .add(ModBlocks.UNDEAD_WALL_CLOCK)
                .add(ModBlocks.OMINOUS_WALL_CLOCK)

                // Grandfather Clock
                .add(ModBlocks.IRON_GRANDFATHER_CLOCK)
                .add(ModBlocks.GLASS_GRANDFATHER_CLOCK)
                .add(ModBlocks.UNDEAD_GRANDFATHER_CLOCK)
                .add(ModBlocks.OMINOUS_GRANDFATHER_CLOCK)
        ;
    }
}
