package com.jeff.pets;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;

/**
 * Registers the custom sounds for the duck and the penguin. More are likely coming as more
 * Mob Packs get introduced.
 */
public class PetsSounds {
    public static final SoundEvent DUCK_AMBIENT = registerSound();
    public static final SoundEvent PENGUIN_AMBIENT = registerPenguinSound();

    private static SoundEvent registerSound() {
        Identifier identifier = new Identifier(PetsInitializer.MOD_ID, "duck_ambient");
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent registerPenguinSound() {
        Identifier identifier = new Identifier(PetsInitializer.MOD_ID, "penguin_ambient");
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    /**
     * Static initializer
     */
    public static void initialize() {
    }
}
