package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.class)
public class CommandManagerMixin {
    @Shadow
    @Final
    private CommandDispatcher<CommandSource> field_197062_b;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void onInit(boolean isDedicatedServer, CallbackInfo ci) {
        Central.createPetSkinCommand(this.field_197062_b);
        Central.createPetNameCommand(this.field_197062_b);
        Central.createPetHelpCommand(this.field_197062_b);
        Central.createPetSpeciesCommand(this.field_197062_b);
        Central.createPetTeleportCommand(this.field_197062_b);
        Central.createToggleCommand(this.field_197062_b);
    }
}