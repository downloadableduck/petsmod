package com.jeff.pets;

import net.minecraft.util.registry.IdRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.resource.Identifier;
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
        SoundEvent event = new SoundEvent(identifier);
        IdRegistry.SOUND_EVENT.register(identifier, event);
        return event;
    }

    private static SoundEvent registerPenguinSound() {
        Identifier identifier = new Identifier(PetsInitializer.MOD_ID, "penguin_ambient");
        SoundEvent event = new SoundEvent(identifier);
        IdRegistry.SOUND_EVENT.register(identifier, event);
        return event;
    }

    /**
     * Static initializer
     */
    public static void initialize() {
    }
}
