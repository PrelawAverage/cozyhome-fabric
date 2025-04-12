package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public interface WaterHoldingBlock {
    // Used to know where to spawn particles based on the water level of the block.
    // Each block has a different level amounts and heights, so they need to be defined in each block.
    float getLiquidLevelHeight(BlockState state);

    // Each block cna pull liquid from different sides, so this method is used to define which sides can be pulled.
    List<Direction> sidesToPull(BlockState state);

    //
    default boolean hasLiquidToPull(BlockState state, World world, BlockPos pos) {
        ContainsBlock contains = state.get(ModProperties.CONTAINS);

        Set<Fluid> validFluids = switch (contains) {
            case NONE -> Set.of(Fluids.WATER, Fluids.LAVA);
            case GRASS, ICE -> Set.of();
            case WATER -> Set.of(Fluids.WATER);
            case LAVA -> Set.of(Fluids.LAVA);
        };

        for (Direction direction : sidesToPull(state)) {
            BlockPos offset = pos.offset(direction);
            FluidState fluidState = world.getBlockState(offset).getFluidState();
            if (fluidState.isStill() && validFluids.contains(fluidState.getFluid())) {
                return true;
            }
        }
        return false;
    }

    boolean isFull(BlockState state);

    int getLevel(BlockState state);

    void addLiquid(BlockState state, World world, BlockPos pos, int amount, ContainsBlock contains);

    void removeLiquid(BlockState state, World world, BlockPos pos, int amount);
}

