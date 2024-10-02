package net.luckystudio.cozyhome.block.util.blocks;

import net.luckystudio.cozyhome.block.custom.ChairBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public interface TuckableBlock {
    BooleanProperty TUCKED = ModProperties.TUCKED;
    DirectionProperty FACING = Properties.FACING;


    // This is where we try and tuck the block in.
    static ActionResult tryTuck(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (state.get(TUCKED)) {
            world.setBlockState(pos, state.with(TUCKED, false), 3);
            playMoveSound(player, world, pos, state);
            return ActionResult.SUCCESS;
        }
        boolean isTuckable = player.isSneaking() && !isBlockedFromTucking(state, world, pos) && canTuckUnderBlockInfront(state, world, pos);

        if (isTuckable) {
            world.setBlockState(pos, state.with(TUCKED, true));
            playMoveSound(player, world, pos, state);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
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

    static void playMoveSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        // Just alters the pitch when the lamp is being turned on and off.
        float f = state.get(TUCKED) ? 0.8F : 1F;
        world.playSound(player, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 1F, f);
    }

}
