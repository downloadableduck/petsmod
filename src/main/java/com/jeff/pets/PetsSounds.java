package com.jeff.pets;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 * Registers the custom sounds for the duck and the penguin. More are likely coming as more
 * Mob Packs get introduced.
 */
public class PetsSounds {

    public static final SoundEvent DUCK_AMBIENT = new SoundEvent(new ResourceLocation("pets_mod", "duck_ambient"));

    public static final SoundEvent PENGUIN_AMBIENT = new SoundEvent(new ResourceLocation("pets_mod", "penguin_ambient"));

    /**
     * Static initializer
     */
    public static void initialize() {
    }
}
