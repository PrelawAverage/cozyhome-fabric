package net.luckystudio.cozyhome.block.util.interfaces;

import net.minecraft.world.World;

/**
 * Implement into any block that will be a clock block.
 */
public interface ClockBlock {
    int getTicks();
    void incrementTicks();

    float getCurrentHourHandAngle();
    void setCurrentHourHandAngle(float angle);

    float getLastHourHandAngle();
    void setLastHourHandAngle(float angle);

    float getCurrentMinuteHandAngle();
    void setCurrentMinuteHandAngle(float angle);

    float getLastMinuteHandAngle();
    void setLastMinuteHandAngle(float angle);

    float getCurrentPendulumAngle();
    void setCurrentPendulumAngle(float angle);

    float getLastPendulumAngle();
    void setLastPendulumAngle(float angle);

    World getWorld();
}
