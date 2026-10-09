package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketHandler;
import net.minecraft.network.packet.LoginPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PacketHandler.class)
public class ClientPlayJoinHandlerMixin {
    @Inject(at = @At("HEAD"), method = "handleLogin")
    private void onGameJoin(LoginPacket packet, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
