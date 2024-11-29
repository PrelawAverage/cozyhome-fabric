package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.block.custom.chairs.ChairBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.BlockState;
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
    static ItemActionResult tryTuck(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (isFacingDirection(state,world,pos)) {
            if (state.get(TUCKED)) {
                world.setBlockState(pos, state.with(TUCKED, false), 3);
                playMoveSound(player, world, pos, state);
                return ItemActionResult.SUCCESS;
            }
            boolean isTuckable = player.isSneaking() && !isBlockedFromTucking(state, world, pos) && canTuckUnderBlockInFront(state, world, pos);

            if (isTuckable) {
                world.setBlockState(pos, state.with(TUCKED, true));
                playMoveSound(player, world, pos, state);
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    // NEEDS FIXING
    static boolean canTuckUnderBlockInFront(BlockState state, World world, BlockPos pos) {
        BlockState forwardState = world.getBlockState(pos.offset(direction(state)));
        return true;
    }

    // This method will prevent two chairs from tucking into the same block.
    static boolean isBlockedFromTucking(BlockState state, World world, BlockPos pos) {
        Direction facing = direction(state);
        BlockState left = world.getBlockState(pos.offset(facing).offset(facing.rotateCounterclockwise(Direction.Axis.Y)));
        BlockState right = world.getBlockState(pos.offset(facing).offset(facing.rotateClockwise(Direction.Axis.Y)));

        if (left.getBlock() instanceof ChairBlock) {
            Direction leftDir = direction(left);
            return left.get(TUCKED) && leftDir == facing.rotateClockwise(Direction.Axis.Y);
        }
        if (right.getBlock() instanceof ChairBlock) {
            Direction rightDir = direction(left);
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

    static boolean isFacingDirection(BlockState state, World world, BlockPos pos) {
        int rotation = state.get(Properties.ROTATION);
        return RotationPropertyHelper.toDirection(rotation).isPresent();
    }
}
