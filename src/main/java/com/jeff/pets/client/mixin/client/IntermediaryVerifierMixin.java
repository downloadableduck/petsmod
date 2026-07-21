package com.jeff.pets.client.mixin.client;

import net.fabricmc.loader.impl.FormattedException;
import net.minecraft.client.Minecraft;
import net.ornithemc.osl.networking.impl.mixin.common.MinecraftServerMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, priority = 1)
public class IntermediaryVerifierMixin {
    @Inject(at = @At("HEAD"), method = "run")
    private void checkForV1Intemediary(CallbackInfo ci) {

    }
}
