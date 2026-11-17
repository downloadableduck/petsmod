//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.FakeModifierKeyCodeAdder;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FakeModifierKeyCodeAdderImpl implements FakeModifierKeyCodeAdder {
    private final List<Entry> entryList = new ArrayList();

    public FakeModifierKeyCodeAdderImpl() {
    }

    public void registerModifierKeyCode(String category, String translationKey, Supplier<ModifierKeyCode> keyCode, Supplier<ModifierKeyCode> defaultKeyCode, Consumer<ModifierKeyCode> onChanged) {
        this.entryList.add(new Entry(category, translationKey, keyCode, defaultKeyCode, onChanged));
    }

    public List<KeyBinding> getFakeBindings() {
        if (FabricLoader.getInstance().isModLoaded("amecs")) {
            return Collections.emptyList();
        } else {
            List<KeyBinding> keyBindings = new ArrayList();
            Iterator var2 = this.entryList.iterator();

            while(var2.hasNext()) {
                Entry entry = (Entry)var2.next();
                keyBindings.add(new FakeKeyBindings(entry.translationKey, (ModifierKeyCode)entry.keyCode.get(), (ModifierKeyCode)entry.defaultKeyCode.get(), entry.category, entry.onChanged));
            }

            return keyBindings;
        }
    }

    private class Entry {
        private String category;
        private String translationKey;
        private Supplier<ModifierKeyCode> keyCode;
        private Supplier<ModifierKeyCode> defaultKeyCode;
        private Consumer<ModifierKeyCode> onChanged;

        private Entry(String category, String translationKey, Supplier<ModifierKeyCode> keyCode, Supplier<ModifierKeyCode> defaultKeyCode, Consumer<ModifierKeyCode> onChanged) {
            this.category = category;
            this.translationKey = translationKey;
            this.keyCode = keyCode;
            this.defaultKeyCode = defaultKeyCode;
            this.onChanged = onChanged;
        }
    }
}
