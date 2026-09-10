package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsConfig;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Re-assign the title screen and edition locations to custom ones, if {@link PetsConfig#customTitleEnabled} is {@code true}.
 * 604 doesn't map {@code TitleScreen.render}, so this runs once when the title screen is created instead.
 */
@Mixin(TitleScreen.class)
public class TitleScreenRenderingMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    private void onConstruct(CallbackInfo ci) {
        Central.reassignLogo(CONFIG.customTitleEnabled);
    }
}
