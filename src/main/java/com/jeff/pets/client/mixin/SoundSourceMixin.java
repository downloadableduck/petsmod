package com.jeff.pets.client.mixin;

import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SoundSource.class)
public enum SoundSourceMixin {
    PETS("pets");

    @Shadow
    SoundSourceMixin(String name) {
    }
}
