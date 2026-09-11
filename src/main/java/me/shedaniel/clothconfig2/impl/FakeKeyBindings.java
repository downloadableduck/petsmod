package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.KeyBinding;

import java.util.UUID;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class FakeKeyBindings extends KeyBinding {
    private final UUID uuid;
    private final ModifierKeyCode keyCode;
    private final ModifierKeyCode defaultKeyCode;
    private final Consumer<ModifierKeyCode> onChanged;
    
    public FakeKeyBindings(String key, ModifierKeyCode keyCode, ModifierKeyCode defaultKeyCode, String category, Consumer<ModifierKeyCode> onChanged) {
        super(UUID.randomUUID().toString(), -1, category);
        uuid = UUID.fromString(getName());
        ((KeyBindingHooks) this).cloth_setId("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + key);
        this.keyCode = keyCode;
        this.defaultKeyCode = ModifierKeyCode.copyOf(defaultKeyCode);
        this.onChanged = onChanged;
    }
    
    @Override
    public String getName() {
        if (uuid == null)
            return super.getName();
        return super.getName().substring(77);
    }
}
