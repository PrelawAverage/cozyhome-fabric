package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.custom.clocks.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.function.ToIntFunction;

public class ModBlockUtilities {

    // Returning a light level if the block is LIT
    public static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return state -> state.get(Properties.LIT) ? litLevel : 0;
    }

    // Returning a light level if the block contain LAVA
    public static ToIntFunction<BlockState> createLightLevelFromContainsBlockState(int litLevel) {
        return state -> state.get(ModProperties.CONTAINS) == ContainsBlock.LAVA ? litLevel : 0;
    }

    // Returning a light level if the block contain LAVA
    public static ToIntFunction<BlockState> createLightLevelFromOminousBehaviour(int litLevel) {
        return state -> state.get(ModProperties.OMINOUS) == OminousBlock.OMINOUS ? litLevel : 6;
    }

    // Returning whether a block is LIT, world and pos are still needed even though it states they aren't used.
    public static boolean ifLit(BlockState state, BlockView world, BlockPos pos) {
        return state.get(Properties.LIT);
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

    public static float getRotationAngle(BlockEntity entity) {
        // Check if the block state contains the ROTATION property
        if (entity.getCachedState().contains(Properties.ROTATION)) {
            int rotation = entity.getCachedState().get(GrandfatherClockBlock.ROTATION);
            return rotation * 22.5f + 180;
        } else if (entity.getCachedState().contains(Properties.HORIZONTAL_FACING)) {
            // Get the direction from the HORIZONTAL_FACING property
            Direction facing = entity.getCachedState().get(Properties.HORIZONTAL_FACING);
            // Use a switch statement to determine rotation based on the facing direction
            switch (facing) {
                case NORTH:
                    return 180.0f; // No rotation
                case EAST:
                    return 270.0f;
                case SOUTH:
                    return 0.0f;
                case WEST:
                    return 90.0f;
                default:
                    return 0.0f; // Fallback rotation
            }
        }
        // Fallback for cases where neither property exists
        return 0.0f;
    }
}
