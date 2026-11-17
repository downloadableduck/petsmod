//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.option.KeyBinding;

import java.util.UUID;
import java.util.function.Consumer;

public class FakeKeyBindings extends KeyBinding {
    private final UUID uuid;
    private final ModifierKeyCode keyCode;
    private final ModifierKeyCode defaultKeyCode;
    private final Consumer<ModifierKeyCode> onChanged;

    public FakeKeyBindings(String key, ModifierKeyCode keyCode, ModifierKeyCode defaultKeyCode, String category, Consumer<ModifierKeyCode> onChanged) {
        super(UUID.randomUUID().toString(), -1);
        this.uuid = UUID.fromString(this.translationKey);
        ((KeyBindingHooks)this).cloth_setId("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + key);
        this.keyCode = keyCode;
        this.defaultKeyCode = ModifierKeyCode.copyOf(defaultKeyCode);
        this.onChanged = onChanged;
    }
}
