package com.jeff.pets.client.mixin;

import com.jeff.pets.client.autoupdate.impl.CustomClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.ClickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(at = @At("HEAD"), method = "defaultHandleClickEvent", cancellable = true)
    private static void pets$handleClickEvent(ClickEvent event, Minecraft minecraft, Screen activeScreen, CallbackInfo ci) {
        if (event instanceof CustomClickEvent customClickEvent) {
            customClickEvent.run();
            ci.cancel();
        }
    }
}
