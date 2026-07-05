package com.jeff.pets;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registers the custom sounds for the duck and the penguin. More are likely coming as more
 * Mob Packs get introduced.
 */
public class PetsSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, PetsInitializer.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> DUCK_AMBIENT = SOUND_EVENTS.register("duck_ambient",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PetsInitializer.MOD_ID, "duck_ambient"))
    );

    public static final DeferredHolder<SoundEvent, SoundEvent> PENGUIN_AMBIENT = SOUND_EVENTS.register("penguin_ambient",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PetsInitializer.MOD_ID, "penguin_ambient")));

    /**
     * Static initializer
     */
    public static void initialize(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
