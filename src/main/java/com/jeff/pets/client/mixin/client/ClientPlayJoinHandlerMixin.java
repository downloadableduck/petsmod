package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.network.PetsNetworked;
import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.LoginS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayJoinHandlerMixin {
    @Inject(at = @At("TAIL"), method = "handleLogin")
    private void onGameJoin(LoginS2CPacket packet, CallbackInfo ci) {
        Central.createJoinHandler();
        PetsNetworked.createConnectHandler();
    }

    @Inject(at = @At("HEAD"), method = "handleDisconnect")
    private void onHandleDisconnect(DisconnectS2CPacket packet, CallbackInfo ci) {
        PetsNetworked.createDisconnectHandler();
    }
}
