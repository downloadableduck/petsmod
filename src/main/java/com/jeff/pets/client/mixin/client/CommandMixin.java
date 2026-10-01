package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class CommandMixin {
    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    public void onSendChatMessage(String message, CallbackInfo ci) {
        if (message.startsWith("/petspecies")) {
            String species = message.replace("/petspecies ", "");
            Central.executePetSpeciesCommand(species);
            ci.cancel();
        } else if (message.equals("/pethelp")) {
            Central.executePetHelpCommand();
            ci.cancel();
        } else if (message.contains("/petskin")) {
            String skin = message.replace("/petskin ", "");
            Central.executePetSkinCommand(skin);
            ci.cancel();
        } else if (message.contains("/petname")) {
            String name = message.replace("/petname ", "");
            Central.executePetNameCommand(name);
            ci.cancel();
        } else if (message.equals("/teleportpet")) {
            Central.executePetTeleportCommand();
            ci.cancel();
        } else if (message.contains("/pet")) {
            String preference = message.replace("/pet ", "");
            Central.executeToggleCommand(preference);
            ci.cancel();
        }
    }
}
