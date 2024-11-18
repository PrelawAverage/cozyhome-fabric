package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.primary.GrandfatherClockBlock;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
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
        // Rotate the model based on the block's facing direction
        int rotation = entity.getCachedState().get(GrandfatherClockBlock.ROTATION);
        return rotation * 22.5f + 180;
    }

}
