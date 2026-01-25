package com.jeff.duck;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class DuckSounds {
    public static final SoundEvent DUCK_AMBIENT = registerSound();

    private static SoundEvent registerSound() {
        Identifier identifier = Identifier.fromNamespaceAndPath(DuckInitializer.MOD_ID, "duck_ambient");
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void initialize() {
    }
}
