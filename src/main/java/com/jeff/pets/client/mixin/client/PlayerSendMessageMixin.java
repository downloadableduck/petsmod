package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Objects;

@Mixin(EntityClientPlayerMP.class)
public class PlayerSendMessageMixin {
    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    private void pets$onPlayerSendChat(String message, CallbackInfo ci) {
        if (message.startsWith("/petspecies")) {
            String species = message.replace("/petspecies ", "");
            Central.get().executePetSpeciesCommand(species);
            ci.cancel();
        } else if (message.equals("/pethelp")) {
            Central.get().executePetHelpCommand();
            ci.cancel();
        } else if (message.contains("/petskin")) {
            String skin = message.replace("/petskin ", "");
            Central.get().executePetSkinCommand(skin);
            ci.cancel();
        } else if (message.contains("/petname")) {
            String name = message.replace("/petname ", "");
            Central.get().executePetNameCommand(name);
            ci.cancel();
        } else if (message.equals("/teleportpet")) {
            Central.get().executePetTeleportCommand();
            ci.cancel();
        } else if (message.contains("/pet")) {
            String preference = message.replace("/pet ", "");
            Central.get().executeToggleCommand(preference);
            ci.cancel();
        }
    }

}
