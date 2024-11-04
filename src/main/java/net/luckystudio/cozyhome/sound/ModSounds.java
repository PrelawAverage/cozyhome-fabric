package net.luckystudio.cozyhome.sound;

import net.luckystudio.cozyhome.CozyHome;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    // Sounds
    public static final SoundEvent LAMP_TOGGLE = registerSoundEvent("lamp_toggle");
    public static final SoundEvent FIREPLACE_LOOP = registerSoundEvent("fireplace_loop");
    public static final SoundEvent GRANDFATHER_CLOCK_TICK = registerSoundEvent("grandfather_clock_tick");
    public static final SoundEvent LIGHT_WATER_FLOW = registerSoundEvent("light_water_flow");
    public static final SoundEvent SPLASH = registerSoundEvent("splash");

    // Block Sound Group
//    public static final BlockSoundGroup EXAMPLE = new BlockSoundGroup(1,1,
//            ModSounds.SOUND_BLOCK_BREAK,
//            ModSounds.SOUND_BLOCK_STEP,
//            ModSounds.SOUND_BLOCK_PLACE,
//            ModSounds.SOUND_BLOCK_HIT,
//            ModSounds.SOUND_BLOCK_FALL);

    // Helper Method
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(CozyHome.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        CozyHome.LOGGER.info("Registering Sounds for " + CozyHome.MOD_ID);
    }
}
