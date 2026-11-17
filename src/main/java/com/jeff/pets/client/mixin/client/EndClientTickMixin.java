//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfigScreen;

@Mixin({Minecraft.class})
public class EndClientTickMixin {
    @Unique
    private boolean petsmod$menuKeyDown;

    public EndClientTickMixin() {
    }

    @Inject(
        at = {@At("TAIL")},
        method = {"tick"}
    )
    private void onEndTick(CallbackInfo ci) {
        Central.createTickWatcher();
        boolean down = PetsClientInitializer.keyMapping != null && Keyboard.isKeyDown(PetsClientInitializer.keyMapping.code);
        if (down && !this.petsmod$menuKeyDown) {
            Minecraft mc = (Minecraft)(Object)this;
            if (mc != null && mc.currentScreen == null) {
                mc.setScreen(PetsConfigScreen.getInstance().getModConfigScreenFactory());
            }
        }

        this.petsmod$menuKeyDown = down;
    }
}
