//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jeff.pets.client.Central;

@Mixin({TitleScreen.class})
public class TitleScreenRenderingMixin {
    public TitleScreenRenderingMixin() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"render"}
    )
    private void init(int i, int j, float f, CallbackInfo ci) {
        Central.reassignLogo(Central.CONFIG.customTitleEnabled);
    }
}
