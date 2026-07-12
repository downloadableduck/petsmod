package com.jeff.pets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registers the custom sounds for the duck and the penguin. More are likely coming as more
 * Mob Packs get introduced.
 */
public class PetsSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PetsInitializer.MOD_ID);

    public static final RegistryObject<SoundEvent> DUCK_AMBIENT = SOUND_EVENTS.register("duck_ambient",
            () -> new SoundEvent(new ResourceLocation(PetsInitializer.MOD_ID, "duck_ambient"))
    );

    public static final RegistryObject<SoundEvent> PENGUIN_AMBIENT = SOUND_EVENTS.register("penguin_ambient",
            () -> new SoundEvent(new ResourceLocation(PetsInitializer.MOD_ID, "penguin_ambient")));

    /**
     * Static initializer
     */
    public static void initialize(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
