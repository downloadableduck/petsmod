package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.input.InputDevice;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public class GameSettingsMixin {
    @Inject(at = @At("TAIL"), method = "<clinit>")
    private static void pets$onGameSettingsclinit(CallbackInfo ci) {
        PetsClientInitializer.keyMapping = GameSettings.register(new KeyBinding("Open Pets Menu").setDefault(InputDevice.keyboard, Keyboard.KEY_I));
    }
}
