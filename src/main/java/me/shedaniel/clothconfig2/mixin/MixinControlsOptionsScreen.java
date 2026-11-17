//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.mixin;

import me.shedaniel.clothconfig2.api.FakeModifierKeyCodeAdder;
import me.shedaniel.clothconfig2.impl.FakeKeyBindings;
import me.shedaniel.clothconfig2.impl.GameOptionsHooks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin({KeybindsScreen.class})
public class MixinControlsOptionsScreen extends Screen {
    public MixinControlsOptionsScreen(String text_1) {
    }

    @Inject(
        method = {"init"},
        at = {@At("HEAD")}
    )
    private void initHead(CallbackInfo info) {
        List<KeyBinding> newKeysAll = new ArrayList();
        KeyBinding[] var3 = this.minecraft.options.allKeys;
        KeyBinding[] var4 = var3;
        int var5 = var3.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            KeyBinding binding = var4[var6];
            if (!(binding instanceof FakeKeyBindings)) {
                newKeysAll.add(binding);
            }
        }

        newKeysAll.addAll(FakeModifierKeyCodeAdder.INSTANCE.getFakeBindings());
        ((GameOptionsHooks)this.minecraft.options).cloth_setKeysAll((KeyBinding[])newKeysAll.toArray(new KeyBinding[0]));
    }

    @Inject(
        method = {"init"},
        at = {@At("RETURN")}
    )
    private void initReturn(CallbackInfo info) {
        List<KeyBinding> newKeysAll = new ArrayList();
        KeyBinding[] var3 = this.minecraft.options.allKeys;
        KeyBinding[] var4 = var3;
        int var5 = var3.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            KeyBinding binding = var4[var6];
            if (!(binding instanceof FakeKeyBindings)) {
                newKeysAll.add(binding);
            }
        }

        ((GameOptionsHooks)this.minecraft.options).cloth_setKeysAll((KeyBinding[])newKeysAll.toArray(new KeyBinding[0]));
    }
}
