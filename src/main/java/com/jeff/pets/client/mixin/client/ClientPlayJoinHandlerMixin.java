//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.login.LoginHelloPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jeff.pets.client.Central;

@Mixin({NetworkHandler.class})
public class ClientPlayJoinHandlerMixin {
    public ClientPlayJoinHandlerMixin() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"onHello"}
    )
    private void onGameJoin(LoginHelloPacket packet, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
