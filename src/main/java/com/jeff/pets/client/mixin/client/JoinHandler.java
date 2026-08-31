package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.SPacketJoinGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class JoinHandler {
    @Inject(at = @At("HEAD"), method = "a")
    private void onJoin(SPacketJoinGame p_handleJoinGame_1_, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
