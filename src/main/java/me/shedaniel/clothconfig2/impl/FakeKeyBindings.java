package me.shedaniel.clothconfig2.impl;

import net.minecraft.class_4107;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;

import java.util.UUID;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class FakeKeyBindings extends KeyBinding {
    private final UUID uuid;
    private final ModifierKeyCode keyCode;
    private final ModifierKeyCode defaultKeyCode;
    private final Consumer<ModifierKeyCode> onChanged;
    
    public FakeKeyBindings(String key, ModifierKeyCode keyCode, ModifierKeyCode defaultKeyCode, String category, Consumer<ModifierKeyCode> onChanged) {
        super(UUID.randomUUID().toString(), class_4107.class_4109.KEYSYM, -1, category);
        uuid = UUID.fromString(getTranslationKey());
        ((KeyBindingHooks) this).cloth_setId("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + key);
        this.keyCode = keyCode;
        this.defaultKeyCode = ModifierKeyCode.copyOf(defaultKeyCode);
        this.onChanged = onChanged;
    }
    
    @Override
    public class_4107.class_4108 method_18172() {
        return defaultKeyCode.getKeyCode();
    }
    
    @Override
    public void method_18170(class_4107.class_4108 inputUtil$KeyCode_1) {
        keyCode.setKeyCode(inputUtil$KeyCode_1);
        keyCode.setModifier(Modifier.none());
        onChanged.accept(keyCode);
    }
    
    @Override
    public boolean method_18173() {
        return keyCode.isUnknown();
    }
    
    @Override
    public boolean method_18166(int int_1, int int_2) {
        return keyCode.matchesKey(int_1, int_2);
    }
    
    @Override
    public boolean method_18165(int int_1) {
        return keyCode.matchesMouse(int_1);
    }
    
    @Override
    public String method_18176() {
        return keyCode.getLocalizedName();
    }
    
    @Override
    public boolean method_18175() {
        return keyCode.equals(defaultKeyCode);
    }
    
    @Override
    public String method_18174() {
        return keyCode.getLocalizedName();
    }
    
    @Override
    public String getTranslationKey() {
        if (uuid == null)
            return super.getTranslationKey();
        return super.getTranslationKey().substring(77);
    }
}
