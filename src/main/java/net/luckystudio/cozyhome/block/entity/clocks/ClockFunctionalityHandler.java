package net.luckystudio.cozyhome.block.entity.clocks;

import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.OminousBlock;
import net.luckystudio.cozyhome.block.util.interfaces.ClockBlock;
import net.luckystudio.cozyhome.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * This interface handles all the functionality of a typical clock block of any kind.
 * Inside of
 */
public class ClockFunctionalityHandler {
    public static void handleHandRotations(World world, BlockState state, ClockBlock blockEntity) {
        boolean isNether = world.getRegistryKey() == World.NETHER; // Check if we are in the nether
        if (isNether && !isNetherClock(state)) {
            Random random = world.getRandom();

            if (blockEntity.getTicks() % 20 == 0) {
                blockEntity.setCurrentHourHandAngle(random.nextFloat() * 10.0f - 5.0f);
            }

            blockEntity.setLastHourHandAngle(blockEntity.getCurrentHourHandAngle());
            blockEntity.setCurrentHourHandAngle(
                    wrapAngle(blockEntity.getCurrentHourHandAngle() + blockEntity.getCurrentHourHandAngle())
            );

            blockEntity.setLastMinuteHandAngle(blockEntity.getCurrentMinuteHandAngle());
            float targetMinuteHandAngle = wrapAngle(blockEntity.getCurrentHourHandAngle() * 12.0f);
            blockEntity.setCurrentMinuteHandAngle(
                    lerpWrappedAngle(blockEntity.getCurrentMinuteHandAngle(), targetMinuteHandAngle, 0.2f)
            );
        } else {
            long worldTime = blockEntity.getWorld().getTimeOfDay() % 24000;
            float hour = (worldTime / 1000.0f) % 12;
            float minute = (worldTime % 1000) / 16.6667f;

            blockEntity.setLastHourHandAngle(blockEntity.getCurrentHourHandAngle());
            blockEntity.setCurrentHourHandAngle(wrapAngle(hour * 30.0f + 180.0f));

            blockEntity.setLastMinuteHandAngle(blockEntity.getCurrentMinuteHandAngle());
            blockEntity.setCurrentMinuteHandAngle(wrapAngle(minute * 6.0f));
        }
    }

    private static boolean isNetherClock(BlockState state) {
        return state.isOf(ModBlocks.CRIMSON_GRANDFATHER_CLOCK) ||
                state.isOf(ModBlocks.WARPED_GRANDFATHER_CLOCK) ||
                state.isOf(ModBlocks.CRIMSON_WALL_CLOCK) ||
                state.isOf(ModBlocks.WARPED_WALL_CLOCK);
    }

    // Wrap angles to keep them in the 0–360 range
    private static float wrapAngle(float angle) {
        return (angle % 360.0f + 360.0f) % 360.0f; // Ensures positive values
    }

    // Interpolates between two angles, wrapping around the 360° boundary smoothly
    private static float lerpWrappedAngle(float from, float to, float progress) {
        float diff = wrapAngle(to - from); // Calculate difference, respecting wrap-around
        if (diff > 180.0f) diff -= 360.0f; // Shortest path correction
        if (diff < -180.0f) diff += 360.0f;

        float interpolated = from + diff * progress; // Interpolate angle smoothly
        return wrapAngle(interpolated); // Normalize to 0–360 range
    }

    public static void handlePendulumMotion(World world, BlockPos pos, ClockBlock blockEntity, float pendulumAmplitude) {
        // Duration of one full pendulum swing cycle in ticks
        float pendulumCycleDuration = 40.0f; // Adjust as necessary

        // Save the last pendulum angle for smooth transitions
        blockEntity.setLastPendulumAngle(blockEntity.getCurrentPendulumAngle());

        // Calculate the current pendulum angle using a sine wave
        float pendulumAngle = (float) (Math.sin((blockEntity.getTicks() % pendulumCycleDuration)
                / pendulumCycleDuration * Math.PI * 2) * pendulumAmplitude);
        blockEntity.setCurrentPendulumAngle(pendulumAngle);

        // Check if the pendulum passes through the center (0 angle)
        if ((blockEntity.getLastPendulumAngle() > 0 && blockEntity.getCurrentPendulumAngle() <= 0) ||
                (blockEntity.getLastPendulumAngle() < 0 && blockEntity.getCurrentPendulumAngle() >= 0)) {
            // The pendulum has crossed the center, play the ticking sound
            world.playSound(
                    null, // Null source means it won't be played from a specific entity
                    pos,
                    ModSoundEvents.GRANDFATHER_CLOCK_TICK,
                    SoundCategory.BLOCKS,
                    0.25f, // Volume
                    1.0f  // Pitch
            );
        }
    }

    private static void handleMidnightTune(World world, BlockPos pos, BlockState state, float worldTime) {
        if (worldTime == 18000 && state.getBlock() == ModBlocks.OMINOUS_GRANDFATHER_CLOCK) {
            world.setBlockState(pos, state.with(ModProperties.OMINOUS, OminousBlock.ACTIVE));
        }
    }
}
