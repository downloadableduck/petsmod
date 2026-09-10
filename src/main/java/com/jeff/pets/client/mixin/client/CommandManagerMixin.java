package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks the construction of the (integrated server's) {@link CommandManager} and hands
 * its raw Brigadier dispatcher to {@link Central#registerCommands}, where all of the
 * pets commands ({@code /pet}, {@code /petskin}, ...) are registered against the
 * {@code class_3965} command source used by 1.13.2.
 */
@Mixin(CommandManager.class)
public class CommandManagerMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstruct(boolean dedicated, CallbackInfo ci) {
        Central.registerCommands(((CommandManager) (Object) this).method_17518());
    }
}