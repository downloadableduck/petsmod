package com.jeff.pets;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class PetsSounds {
    public static final SoundEvent DUCK_AMBIENT = registerSound();
    public static final SoundEvent PENGUIN_AMBIENT = registerPenguinSound();

    private static SoundEvent registerSound() {
        Identifier identifier = Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "duck_ambient");
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    private static SoundEvent registerPenguinSound() {
        Identifier identifier = Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "penguin_ambient");
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void initialize() {
    }
}
