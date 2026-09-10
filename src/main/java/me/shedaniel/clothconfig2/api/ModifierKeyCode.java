package me.shedaniel.clothconfig2.api;

import net.minecraft.class_4107;
import me.shedaniel.clothconfig2.impl.ModifierKeyCodeImpl;
import me.shedaniel.clothconfig2.mixin.MouseHooks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public interface ModifierKeyCode {
    static ModifierKeyCode of(class_4107.class_4108 keyCode, Modifier modifier) {
        return new ModifierKeyCodeImpl().setKeyCodeAndModifier(keyCode, modifier);
    }
    
    static ModifierKeyCode copyOf(ModifierKeyCode code) {
        return of(code.getKeyCode(), code.getModifier());
    }
    
    static ModifierKeyCode unknown() {
        return of(class_4107.field_19910, Modifier.none());
    }
    
    class_4107.class_4108 getKeyCode();
    
    ModifierKeyCode setKeyCode(class_4107.class_4108 keyCode);
    
    default class_4107.class_4109 getType() {
        return getKeyCode().method_18158();
    }
    
    Modifier getModifier();
    
    ModifierKeyCode setModifier(Modifier modifier);
    
    default boolean matchesMouse(int button) {
        return !isUnknown() && getType() == class_4107.class_4109.MOUSE && getKeyCode().method_18159() == button && getModifier().matchesCurrent();
    }
    
    default boolean matchesKey(int keyCode, int scanCode) {
        if (isUnknown())
            return false;
        if (keyCode == class_4107.field_19910.method_18159()) {
            return getType() == class_4107.class_4109.SCANCODE && getKeyCode().method_18159() == scanCode && getModifier().matchesCurrent();
        } else {
            return getType() == class_4107.class_4109.KEYSYM && getKeyCode().method_18159() == keyCode && getModifier().matchesCurrent();
        }
    }
    
    default boolean matchesCurrentKey() {
        return !isUnknown() && getType() == class_4107.class_4109.KEYSYM && getModifier().matchesCurrent() && class_4107.method_18154(getKeyCode().method_18159());
    }
    
    default ModifierKeyCode setKeyCodeAndModifier(class_4107.class_4108 keyCode, Modifier modifier) {
        setKeyCode(keyCode);
        setModifier(modifier);
        return this;
    }
    
    default ModifierKeyCode clearModifier() {
        return setModifier(Modifier.none());
    }
    
    String toString();
    
    default String getLocalizedName() {
        return toString();
    }
    
    default boolean isUnknown() {
        return getKeyCode().equals(class_4107.field_19910.method_18159());
    }
}
