//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.command.commands.CommandHelp;
import org.checkerframework.checker.units.qual.A;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfigScreen;

@Mixin({Minecraft.class})
public class EndClientTickMixin {

    public EndClientTickMixin() {
    }

    @Inject(
        at = {@At("TAIL")},
        method = {"runTick"}
    )
    private void onEndTick(CallbackInfo ci) {
        Central.createTickWatcher();
        boolean down = PetsClientInitializer.keyMapping.isReleaseEvent();
        if (down) {
            Minecraft mc = (Minecraft)(Object)this;
            if (mc != null && mc.currentScreen == null) {
                mc.displayScreen(PetsConfigScreen.getInstance().getModConfigScreenFactory());
            }
        }
    }
}
