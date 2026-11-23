//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.client.net.handler.PacketHandlerClient;
import net.minecraft.core.net.packet.PacketMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jeff.pets.client.Central;

@Mixin(value = {PacketHandlerClient.class}, remap = false)
public class ClientPlayJoinHandlerMixin {
    public ClientPlayJoinHandlerMixin() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"handlePlayerJoin"}
    )
    private void onGameJoin(PacketMessage chatPacket, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
