package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsConfig;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.MainMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Re-assign the title screen and edition locations to custom ones, if {@link PetsConfig#customTitleEnabled} is {@code true}.
 */
@Mixin(MainMenuScreen.class)
public class TitleScreenRenderingMixin {
    @Inject(at = @At("HEAD"), method = "render")
    private void init(MatrixStack p_96739_, int p_96740_, int p_96741_, float p_96742_, CallbackInfo ci) {
        Central.reassignLogo(CONFIG.customTitleEnabled);
    }
}
