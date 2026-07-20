package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.handler.CommandManager;
import net.minecraft.server.command.source.CommandSourceStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Shadow
    @Final
    private CommandDispatcher<CommandSourceStack> dispatcher;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void onInit(boolean isDedicatedServer, CallbackInfo ci) {
        Central.get().createPetSkinCommand(this.dispatcher);
        Central.get().createPetNameCommand(this.dispatcher);
        Central.get().createPetHelpCommand(this.dispatcher);
        Central.get().createPetSpeciesCommand(this.dispatcher);
        Central.get().createPetTeleportCommand(this.dispatcher);
        Central.get().createToggleCommand(this.dispatcher);
    }
}
