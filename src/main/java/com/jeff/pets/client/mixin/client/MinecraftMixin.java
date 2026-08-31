package com.jeff.pets.client.mixin.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(at = @At("TAIL"), method = "am")
    private void onInit(CallbackInfo ci) {
        new Central().onInitialization();
        new PetsInitializer().onInitialization();
    }
}
