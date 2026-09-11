package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.handler.CommandManager;
import net.minecraft.server.command.handler.CommandRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    private void onInit(MinecraftServer server, CallbackInfo ci) {
        CommandRegistry registry = (CommandRegistry) (Object) this;
        Central.get().createPetSkinCommand(registry);
        Central.get().createPetNameCommand(registry);
        Central.get().createPetHelpCommand(registry);
        Central.get().createPetSpeciesCommand(registry);
        Central.get().createPetTeleportCommand(registry);
        Central.get().createToggleCommand(registry);
    }
}
