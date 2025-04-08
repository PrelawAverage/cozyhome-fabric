package net.luckystudio.cozyhome.block.custom.clocks;

import net.luckystudio.cozyhome.block.ModBlocks;
import net.luckystudio.cozyhome.block.util.interfaces.ClockBlock;
import net.luckystudio.cozyhome.util.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

/**
 * This class handles all the functionality of a typical clock block of any kind.
 * Inside of
 */
public class ClockFunctionalityHandler {
    public static void handleHandRotations(World world, BlockPos pos, BlockState state, ClockBlock blockEntity) {
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
                    lerpWrappedAngle(blockEntity.getCurrentMinuteHandAngle(), targetMinuteHandAngle)
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
    private static float lerpWrappedAngle(float from, float to) {
        float diff = wrapAngle(to - from); // Calculate difference, respecting wrap-around
        if (diff > 180.0f) diff -= 360.0f; // Shortest path correction
        if (diff < -180.0f) diff += 360.0f;

        float interpolated = from + diff * (float) 0.2; // Interpolate angle smoothly
        return wrapAngle(interpolated); // Normalize to 0–360 range
    }

    public static void handleGrandfatherClock(World world, BlockPos pos, BlockState state, ClockBlock blockEntity, float pendulumAmplitude) {
        long worldTime = blockEntity.getWorld().getTimeOfDay() % 24000;

        if (worldTime == 18000 && world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) {
            world.setBlockState(pos, state.with(Properties.TRIGGERED, true));
            if (state.getBlock() == ModBlocks.OMINOUS_GRANDFATHER_CLOCK) {
                world.playSound(
                        null, // Null source means it won't be played from a specific entity
                        pos,
                        SoundEvents.BLOCK_VAULT_ACTIVATE,
                        SoundCategory.BLOCKS,
                        0.25f, // Volume
                        1.0f  // Pitch
                );
            }
            world.playSound(
                    null, // Null source means it won't be played from a specific entity
                    pos,
                    ModSoundEvents.GRANDFATHER_CLOCK_MIDNIGHT,
                    SoundCategory.BLOCKS,
                    1.0f, // Volume
                    1.0f  // Pitch
            );
        }

        if (worldTime == 18360) {
            world.setBlockState(pos, state.with(Properties.TRIGGERED, false));
            if (state.getBlock() == ModBlocks.OMINOUS_GRANDFATHER_CLOCK) {
                world.playSound(
                        null, // Null source means it won't be played from a specific entity
                        pos,
                        SoundEvents.BLOCK_VAULT_DEACTIVATE,
                        SoundCategory.BLOCKS,
                        1.0f, // Volume
                        1.0f  // Pitch
                );
            }
        }

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
}
