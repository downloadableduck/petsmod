//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
    value = {Minecraft.class},
    priority = 1
)
public class IntermediaryVerifierMixin {
    public IntermediaryVerifierMixin() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"run"}
    )
    private void checkForV1Intemediary(CallbackInfo ci) {
    }
}
