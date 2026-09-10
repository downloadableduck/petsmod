package com.jeff.pets;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.Sound;

/**
 * Registers the custom sounds for the duck and the penguin. More are likely coming as more
 * Mob Packs get introduced.
 */
public class PetsSounds {
    public static final Sound DUCK_AMBIENT = registerSound();
    public static final Sound PENGUIN_AMBIENT = registerPenguinSound();

    private static Sound registerSound() {
        Identifier identifier = new Identifier(PetsInitializer.MOD_ID, "duck_ambient");
        Sound sound = new Sound(identifier);
        Registry.SOUND_EVENT.add(identifier, sound);
        return sound;
    }

    private static Sound registerPenguinSound() {
        Identifier identifier = new Identifier(PetsInitializer.MOD_ID, "penguin_ambient");
        Sound sound = new Sound(identifier);
        Registry.SOUND_EVENT.add(identifier, sound);
        return sound;
    }

    /**
     * Static initializer
     */
    public static void initialize() {
    }
}