package me.shedaniel.clothconfig2.api;

import net.minecraft.client.render.platform.InputConstants;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.clothconfig2.impl.ModifierKeyCodeImpl;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.clothconfig2.mixin.MouseHooks;
import net.minecraft.client.render.platform.Window;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.platform.Window;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;

@Environment(EnvType.CLIENT)
public interface ModifierKeyCode {
    static ModifierKeyCode of(InputConstants.Key keyCode, Modifier modifier) {
        return new ModifierKeyCodeImpl().setKeyCodeAndModifier(keyCode, modifier);
    }
    
    static ModifierKeyCode copyOf(ModifierKeyCode code) {
        return of(code.getKeyCode(), code.getModifier());
    }
    
    static ModifierKeyCode unknown() {
        return of(InputConstants.UNKNOWN, Modifier.none());
    }
    
    InputConstants.Key getKeyCode();
    
    ModifierKeyCode setKeyCode(InputConstants.Key keyCode);
    
    default InputConstants.Type getType() {
        return getKeyCode().getType();
    }
    
    Modifier getModifier();
    
    ModifierKeyCode setModifier(Modifier modifier);
    
    default boolean matchesMouse(int button) {
        return !isUnknown() && getType() == InputConstants.Type.MOUSE && getKeyCode().getValue() == button && getModifier().matchesCurrent();
    }
    
    default boolean matchesKey(int keyCode, int scanCode) {
        if (isUnknown())
            return false;
        if (keyCode == InputConstants.UNKNOWN.getValue()) {
            return getType() == InputConstants.Type.SCANCODE && getKeyCode().getValue() == scanCode && getModifier().matchesCurrent();
        } else {
            return getType() == InputConstants.Type.KEYSYM && getKeyCode().getValue() == keyCode && getModifier().matchesCurrent();
        }
    }
    
    default boolean matchesCurrentMouse() {
        if (!isUnknown() && getType() == InputConstants.Type.MOUSE && getModifier().matchesCurrent()) {
            switch (getKeyCode().getValue()) {
                case 0:
                    return Minecraft.getInstance().mouseHandler.isLeftPressed();
                case 1:
                    return Minecraft.getInstance().mouseHandler.isRightPressed();
                case 2:
                    return ((MouseHooks) Minecraft.getInstance().mouseHandler).middleButtonClicked();
            }
        }
        return false;
    }
    
    default boolean matchesCurrentKey() {
        return !isUnknown() && getType() == InputConstants.Type.KEYSYM && getModifier().matchesCurrent() && InputConstants.getKey(getKeyCode().getValue());
    }
    
    default ModifierKeyCode setKeyCodeAndModifier(InputConstants.Key keyCode, Modifier modifier) {
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
        return getKeyCode().equals(InputConstants.UNKNOWN);
    }
}
