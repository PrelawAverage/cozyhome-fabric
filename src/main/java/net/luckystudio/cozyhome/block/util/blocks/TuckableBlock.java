package net.luckystudio.cozyhome.block.util.blocks;

import net.luckystudio.cozyhome.block.custom.ChairBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface TuckableBlock {
    BooleanProperty TUCKED = ModProperties.TUCKED;
    DirectionProperty FACING = Properties.FACING;

    // This is where we try and tuck the block in.
    static boolean tryTuck(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        // Saving the tuck state of the block.
        boolean tucked = state.get(TUCKED);
        if (!player.isSneaking() || !canTuckUnderBlockInfront(state, world, pos)) return false;

        if (tucked) {
            world.setBlockState(pos, state.with(TUCKED, true), 3);
            world.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        }

        if (!isBlockedFromTucking(state, world, pos)) {
            world.setBlockState(pos, state.with(TUCKED, true));
            world.playSound(null, pos, SoundEvents.BLOCK_BARREL_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        }

        return false;
    }

    static boolean canTuckUnderBlockInfront(BlockState state, World world, BlockPos pos) {
        Direction forward = state.get(FACING);
        BlockState forwardState = world.getBlockState(pos.offset(forward));
        return forwardState.isIn(ModTags.Blocks.TUCKABLE);
    }

    // This method will prevent two chairs from tucking into the same block.
    static boolean isBlockedFromTucking(BlockState state, World world, BlockPos pos) {

        Direction facing = state.get(FACING);
        BlockState left = world.getBlockState(pos.offset(facing).offset(facing.rotateCounterclockwise(Direction.Axis.Y)));
        BlockState right = world.getBlockState(pos.offset(facing).offset(facing.rotateClockwise(Direction.Axis.Y)));

        if (left.getBlock() instanceof ChairBlock && left.get(TUCKED)
                && left.get(FACING) == facing.rotateClockwise(Direction.Axis.Y)) return true;
        if (right.getBlock() instanceof ChairBlock && right.get(TUCKED)
                && right.get(FACING) == facing.rotateCounterclockwise(Direction.Axis.Y)) return true;

        return false;
    }

}
