package net.luckystudio.cozyhome.block.util;

import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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

    // Returning whether a block is LIT
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
}
