package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPlayJoinHandlerMixin {
    @Inject(at = @At("HEAD"), method = "handleLogin")
    private void onGameJoin(ClientboundLoginPacket clientboundLoginPacket, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
