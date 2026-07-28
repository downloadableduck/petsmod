package me.shedaniel.forge.clothconfig2.api;

import me.shedaniel.forge.clothconfig2.impl.ModifierKeyCodeImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface ModifierKeyCode {
    static ModifierKeyCode of(InputMappings.Input keyCode, Modifier modifier) {
        return new ModifierKeyCodeImpl().setKeyCodeAndModifier(keyCode, modifier);
    }
    
    static ModifierKeyCode copyOf(ModifierKeyCode code) {
        return of(code.getKeyCode(), code.getModifier());
    }
    
    static ModifierKeyCode unknown() {
        return of(InputMappings.UNKNOWN, Modifier.none());
    }
    
    InputMappings.Input getKeyCode();
    
    ModifierKeyCode setKeyCode(InputMappings.Input keyCode);
    
    default InputMappings.Type getType() {
        return getKeyCode().getType();
    }
    
    Modifier getModifier();
    
    ModifierKeyCode setModifier(Modifier modifier);
    
    default boolean matchesMouse(int button) {
        return !isUnknown() && getType() == InputMappings.Type.MOUSE && getKeyCode().getValue() == button && getModifier().matchesCurrent();
    }
    
    default boolean matchesKey(int keyCode, int scanCode) {
        if (isUnknown())
            return false;
        if (keyCode == InputMappings.UNKNOWN.getValue()) {
            return getType() == InputMappings.Type.SCANCODE && getKeyCode().getValue() == scanCode && getModifier().matchesCurrent();
        } else {
            return getType() == InputMappings.Type.KEYSYM && getKeyCode().getValue() == keyCode && getModifier().matchesCurrent();
        }
    }
    
    default boolean matchesCurrentMouse() {
        if (!isUnknown() && getType() == InputMappings.Type.MOUSE && getModifier().matchesCurrent()) {
            switch (getKeyCode().getValue()) {
                case 0:
                    return Minecraft.getInstance().mouseHandler.isLeftPressed();
                case 1:
                    return Minecraft.getInstance().mouseHandler.isRightPressed();
                case 2:
                    return Minecraft.getInstance().mouseHandler.isMiddleDown();
            }
        }
        return false;
    }
    
    default boolean matchesCurrentKey() {
        return !isUnknown() && getType() == InputMappings.Type.KEYSYM && getModifier().matchesCurrent() && InputMappings.isKeyDown(Minecraft.getInstance().window.getWindow(), getKeyCode().getValue());
    }
    
    default ModifierKeyCode setKeyCodeAndModifier(InputMappings.Input keyCode, Modifier modifier) {
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
        return getKeyCode().equals(InputMappings.UNKNOWN);
    }
}
