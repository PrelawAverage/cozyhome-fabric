package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.function.ToIntFunction;

public class ModBlockUtilities {

    // Returning a light level if the block contain LAVA
    public static ToIntFunction<BlockState> createLightLevelFromContainsBlockState(int litLevel) {
        return state -> state.get(ModProperties.CONTAINS) == ContainsBlock.LAVA ? litLevel : 0;
    }

    public static void tryMelt(BlockState state, World world, BlockPos pos, BlockState getMeltedState) {
        if (world.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos)) {
            if (world.getDimension().ultrawarm()) {
                world.removeBlock(pos, false);
            } else {
                world.setBlockState(pos, getMeltedState);
                world.updateNeighbor(pos, getMeltedState.getBlock(), pos);
            }
        }
    }

    public static void tryFreezeWater(BlockState state, ServerWorld world, BlockPos pos, BlockState getFrozenState) {
        // Check if the block is water
        Biome biome = world.getBiome(pos).value();
        float temperature = biome.getTemperature();
        if (world.getLightLevel(LightType.BLOCK, pos) <= 11 - state.getOpacity(world, pos) && temperature <= 0.15f) {
            if (world.getDimension().ultrawarm()) {
                world.removeBlock(pos, false);
            } else {
                world.setBlockState(pos, getFrozenState);
                world.updateNeighbor(pos, getFrozenState.getBlock(), pos);
            }
        }
    }


    public static boolean isEntityObstructing(World world, BlockPos pos) {
        Box box = new Box(pos);
        List<Entity> entitiesBelow = world.getEntitiesByClass(Entity.class, box, entity -> true);
        return !entitiesBelow.isEmpty();
    }

    public static boolean canPlaceBelow(World world, BlockPos pos) {
        return pos.down().getY() > world.getBottomY() + 1 && world.getBlockState(pos.down()).isReplaceable();
    }

    public static int getColorFromContainsState(BlockState state, BlockRenderView world, BlockPos pos) {
        if (state.get(ModProperties.CONTAINS) == ContainsBlock.WATER){
            return BiomeColors.getWaterColor(world, pos);
        }
        return -17170434;
    }
}
