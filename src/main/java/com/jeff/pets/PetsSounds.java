package com.jeff.pets;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

/**
 * Registers the custom sounds for the duck and the penguin. More are likely coming as more
 * Mob Packs get introduced.
 */
public class PetsSounds {
    public static final SoundEvent DUCK_AMBIENT = registerSound();
    public static final SoundEvent PENGUIN_AMBIENT = registerPenguinSound();

    private static SoundEvent registerSound() {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "duck_ambient");
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    private static SoundEvent registerPenguinSound() {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "penguin_ambient");
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    /**
     * Static initializer
     */
    public static void initialize() {
    }
}
