package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.block.custom.drawer.DeskBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public interface TuckableBlock {
    BooleanProperty TUCKED = ModProperties.TUCKED;

    // This is where we try and tuck the block in.
    static ItemActionResult toggleTuck(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (isFacingDirection(state)) { // Make sure the block is facing a direction.
            // If the block is already tucked, untuck it.
            if (state.get(TUCKED)) {
                world.setBlockState(pos, state.with(TUCKED, false), 3);
                playMoveSound(player, world, pos, state);
                return ItemActionResult.SUCCESS;
            }

            boolean isTuckable = !isAnotherTuckedBlockInTheWay(state, world, pos) && canTuckUnderBlockInFront(state, world, pos);

            if (isTuckable) {
                world.setBlockState(pos, state.with(TUCKED, true));
                playMoveSound(player, world, pos, state);
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    static boolean isFacingDirection(BlockState state) {
        int rotation = state.get(Properties.ROTATION);
        return RotationPropertyHelper.toDirection(rotation).isPresent();
    }

    // NEEDS FIXING
    static boolean canTuckUnderBlockInFront(BlockState state, World world, BlockPos pos) {
        BlockState targetState = world.getBlockState(pos.offset(direction(state)));
        if (targetState.getBlock() instanceof TrapdoorBlock && targetState.get(Properties.BLOCK_HALF) == BlockHalf.TOP && !targetState.get(Properties.OPEN)) return true;
        if (targetState.getBlock() instanceof DeskBlock && targetState.get(Properties.FACING) == direction(state)) return true;
        return targetState.isReplaceable() || targetState.isOf(Blocks.AIR);
    }

    // This method will prevent two chairs from tucking into the same block.
    static boolean isAnotherTuckedBlockInTheWay(BlockState state, World world, BlockPos pos) {
        Direction facing = direction(state);
        BlockPos leftPos = pos.offset(facing).offset(facing.rotateCounterclockwise(Direction.Axis.Y));
        BlockPos rightPos = pos.offset(facing).offset(facing.rotateClockwise(Direction.Axis.Y));
        BlockState left = world.getBlockState(leftPos);
        BlockState right = world.getBlockState(rightPos);
        if (left.contains(TUCKED)) {
            Direction leftDir = direction(left);
            return left.get(TUCKED) && leftDir == facing.rotateClockwise(Direction.Axis.Y);
        }
        if (right.contains(TUCKED)) {
            Direction rightDir = direction(right);
            return right.get(TUCKED) && rightDir == facing.rotateCounterclockwise(Direction.Axis.Y);
        }
        return false;
    }

    static void playMoveSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        // Just alters the pitch when the lamp is being turned on and off.
        float f = state.get(TUCKED) ? 1.4F : 1.2F;
        world.playSound(player, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 1F, f);
    }

    static Direction direction(BlockState state) {
        int rotation = state.get(Properties.ROTATION);
        return RotationPropertyHelper.toDirection(rotation).orElse(null);
    }
}
