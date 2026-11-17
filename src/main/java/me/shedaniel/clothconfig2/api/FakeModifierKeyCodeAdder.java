//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import me.shedaniel.clothconfig2.impl.FakeModifierKeyCodeAdderImpl;
import net.minecraft.client.option.KeyBinding;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface FakeModifierKeyCodeAdder {
    FakeModifierKeyCodeAdder INSTANCE = new FakeModifierKeyCodeAdderImpl();

    void registerModifierKeyCode(String var1, String var2, Supplier<ModifierKeyCode> var3, Supplier<ModifierKeyCode> var4, Consumer<ModifierKeyCode> var5);

    default void registerModifierKeyCode(String category, String translationKey, Supplier<ModifierKeyCode> keyCode, Consumer<ModifierKeyCode> onChanged) {
        this.registerModifierKeyCode(category, translationKey, keyCode, keyCode, onChanged);
    }

    List<KeyBinding> getFakeBindings();
}
