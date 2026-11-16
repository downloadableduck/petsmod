package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class EndClientTickMixin {
    @Unique
    private boolean petsmod$menuKeyDown;

    @Inject(at = @At("TAIL"), method = "tick")
    private void onEndTick(CallbackInfo ci) {
        Central.createTickWatcher();
        boolean down = PetsClientInitializer.keyMapping != null && Keyboard.isKeyDown(PetsClientInitializer.keyMapping.keyCode);
        if (down && !this.petsmod$menuKeyDown) {
            Minecraft mc = (Minecraft)(Object)this;
            if (mc != null && mc.screen == null) {
                mc.openScreen(PetsConfigScreen.getInstance().getModConfigScreenFactory());
            }
        }
        this.petsmod$menuKeyDown = down;
    }
}
