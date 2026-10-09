package com.jeff.pets.client.mixin.client;

import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.client.resource.language.LanguageManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LanguageManager.class)
public abstract class BullshitMinecraftMixin {
    @Shadow
    public abstract LanguageDefinition getLanguage();

    @Inject(at = @At("HEAD"), method = "isRightToLeft", cancellable = true)
    private void pets$onIsRightToLeft(CallbackInfoReturnable<Boolean> cir) {
        if (this.getLanguage() == null) {
            cir.setReturnValue(false);
        }
    }
}
