package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S01PacketJoinGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetHandlerPlayClient.class)
public class ClientPlayJoinHandlerMixin {
    @Inject(at = @At("HEAD"), method = "handleJoinGame")
    private void onGameJoin(S01PacketJoinGame par1, CallbackInfo ci) {
        Central.createJoinHandler();
    }
}
