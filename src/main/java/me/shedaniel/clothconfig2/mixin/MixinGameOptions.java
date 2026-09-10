package me.shedaniel.clothconfig2.mixin;

import me.shedaniel.clothconfig2.impl.GameOptionsHooks;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameOptions.class)
public class MixinGameOptions implements GameOptionsHooks {
    @Shadow @Mutable @Final public KeyBinding[] keyBindings;
    
    @Override
    public void cloth_setKeysAll(KeyBinding[] all) {
        keyBindings = all;
    }
}
