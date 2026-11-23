package com.jeff.pets.client.mixin.client;

import net.minecraft.core.lang.I18n;
import net.minecraft.core.lang.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(I18n.class)
public class LanguagePackMixin {
    @Inject(at = @At("TAIL"), method = "reload")
    private void onReload(String languageCode, CallbackInfo ci) {
        Language.Default.INSTANCE.loadNamespace("pets-mod");
    }
}
