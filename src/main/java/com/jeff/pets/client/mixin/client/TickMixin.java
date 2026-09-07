package com.jeff.pets.client.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class TickMixin {
    @Inject(at = @At("HEAD"), method = "func_71407_l", require = 0) //tick
    private void onFunc_71407_l(CallbackInfo ci) {
        //Central.createTickWatcher();
    }
}
