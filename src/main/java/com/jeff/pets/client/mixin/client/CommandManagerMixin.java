package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks the construction of the (integrated server's) {@link CommandManager} and hands
 * it to {@link Central#registerCommands}, where all of the pets commands
 * ({@code /pet}, {@code /petskin}, ...) are registered against the vanilla
 * 1.12.2 command registry.
 */
@Mixin(CommandManager.class)
public class CommandManagerMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstruct(MinecraftServer server, CallbackInfo ci) {
        Central.registerCommands(((CommandRegistry) (Object) this));
    }
}