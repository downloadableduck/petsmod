package com.jeff.pets.client.mixin;

import com.jeff.pets.client.Central;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.protocol.game.ClientboundCommandsPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(at = @At(value = "RETURN"), method = "handleCommands")
    private void pets$onHandleCommandSuggestions(ClientboundCommandsPacket packet, CallbackInfo ci) {
        CommandDispatcher<ClientSuggestionProvider> serverDispatcher = ((ClientPacketListener) (Object) this).getCommands();
        CommandNode clientPetNode = Central.dispatcher.getRoot().getChild("pet");
        if (clientPetNode != null) {
            serverDispatcher.getRoot().getChildren().removeIf(node -> node.getName().equals("pet"));
            serverDispatcher.getRoot().addChild(clientPetNode);
        }
    }
}
