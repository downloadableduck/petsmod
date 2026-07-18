package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class EndClientTickMixin {
    @Inject(at = @At("TAIL"), method = "tick")
    private void onEndTick(CallbackInfo ci) {
        Central.createTickWatcher();
        if (PetsClientInitializer.keyMapping.wasPressed()) {
            MinecraftClient.getInstance().openScreen(PetsConfigScreen.getInstance().getModConfigScreenFactory());
        }
    }
}
