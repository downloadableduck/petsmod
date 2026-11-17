//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.client.network.MultiplayerClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jeff.pets.client.Central;

@Mixin({MultiplayerClientPlayerEntity.class})
public class PlayerSendMessageMixin {
    public PlayerSendMessageMixin() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"sendChatMessage"},
        cancellable = true
    )
    private void pets$onPlayerSendChat(String message, CallbackInfo ci) {
        String preference;
        if (message.startsWith("/petspecies")) {
            preference = message.replace("/petspecies ", "");
            Central.get().executePetSpeciesCommand(preference);
            ci.cancel();
        } else if (message.equals("/pethelp")) {
            Central.get().executePetHelpCommand();
            ci.cancel();
        } else if (message.contains("/petskin")) {
            preference = message.replace("/petskin ", "");
            Central.get().executePetSkinCommand(preference);
            ci.cancel();
        } else if (message.contains("/petname")) {
            preference = message.replace("/petname ", "");
            Central.get().executePetNameCommand(preference);
            ci.cancel();
        } else if (message.equals("/teleportpet")) {
            Central.get().executePetTeleportCommand();
            ci.cancel();
        } else if (message.contains("/pet")) {
            preference = message.replace("/pet ", "");
            Central.get().executeToggleCommand(preference);
            ci.cancel();
        }

    }
}
