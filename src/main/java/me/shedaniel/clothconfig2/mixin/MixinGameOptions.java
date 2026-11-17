//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.mixin;

import me.shedaniel.clothconfig2.impl.GameOptionsHooks;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GameOptions.class})
public class MixinGameOptions implements GameOptionsHooks {
    @Shadow
    @Mutable
    @Final
    public KeyBinding[] allKeys;

    public MixinGameOptions() {
    }

    public void cloth_setKeysAll(KeyBinding[] all) {
        this.allKeys = all;
    }
}
