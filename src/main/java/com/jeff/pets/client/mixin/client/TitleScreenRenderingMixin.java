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
    //@Inject(at = @At("HEAD"), method = "render")
    private void init(int p_render_1_, int p_render_2_, float p_render_3_, CallbackInfo ci) {
        //Central.reassignLogo(CONFIG.customTitleEnabled);
    }
}
