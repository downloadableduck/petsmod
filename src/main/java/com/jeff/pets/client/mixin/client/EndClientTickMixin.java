package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.network.PetsNetworked;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class EndClientTickMixin {
    @Inject(at = @At("TAIL"), method = "tick")
    private void onEndTick(CallbackInfo ci) {
        Central.createTickWatcher();
        if (PetsClientInitializer.keyMapping.consumeClick()) {
            Minecraft.getInstance().openScreen(new PetsConfigScreen());
        }
    }

    @Inject(at = @At("HEAD"), method = "setWorld(Lnet/minecraft/client/world/ClientWorld;)V")
    private void onSetWorld(ClientWorld world, CallbackInfo ci) {
        PetsNetworked.createLevelChangeHandler();
    }
}
