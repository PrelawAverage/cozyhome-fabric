package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.custom.clocks.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

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

    // Used for blocks that break when there isn't a block under them or block of the same type.
    // If you want your block to break when there is not a solid flat surface use Block.sideCoversSmallSquare();
    public static boolean isBlockBelowOrSame(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockStateBelow = world.getBlockState(pos.down());
        Direction direction = Direction.DOWN;
        return state.getBlock() == blockStateBelow.getBlock() || Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite());
    }

    public static int getColorFromContainsState(BlockState state, BlockRenderView world, BlockPos pos) {
        if (state.get(ModProperties.CONTAINS) == ContainsBlock.WATER){
            return BiomeColors.getWaterColor(world, pos);
        } else if (state.get(ModProperties.CONTAINS) == ContainsBlock.GRASS) {
            return BiomeColors.getGrassColor(world, pos);
        }
        return 0xFFFFFF;
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
