package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayJoinHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onGameJoin")
    private void onGameJoin(GameJoinS2CPacket clientboundLoginPacket, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
