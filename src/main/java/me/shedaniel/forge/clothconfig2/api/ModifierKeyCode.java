package me.shedaniel.forge.clothconfig2.api;

import me.shedaniel.forge.clothconfig2.impl.InputCompat;
import me.shedaniel.forge.clothconfig2.impl.KeyInput;
import me.shedaniel.forge.clothconfig2.impl.ModifierKeyCodeImpl;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ModifierKeyCode {
    static ModifierKeyCode of(KeyInput keyCode, Modifier modifier) {
        return new ModifierKeyCodeImpl().setKeyCodeAndModifier(keyCode, modifier);
    }

    static ModifierKeyCode copyOf(ModifierKeyCode code) {
        return of(code.getKeyCode(), code.getModifier());
    }

    static ModifierKeyCode unknown() {
        return of(KeyInput.INVALID, Modifier.none());
    }

    KeyInput getKeyCode();

    ModifierKeyCode setKeyCode(KeyInput keyCode);

    default KeyInput.Type getType() {
        return getKeyCode().getType();
    }

    Modifier getModifier();

    ModifierKeyCode setModifier(Modifier modifier);

    default boolean matchesMouse(int button) {
        return !isUnknown() && getType() == KeyInput.Type.MOUSE && getKeyCode().getKeyCode() == button && getModifier().matchesCurrent();
    }

    default boolean matchesKey(int keyCode, int scanCode) {
        if (isUnknown())
            return false;
        if (keyCode == KeyInput.INVALID.getKeyCode()) {
            return getType() == KeyInput.Type.SCANCODE && getKeyCode().getKeyCode() == scanCode && getModifier().matchesCurrent();
        } else {
            return getType() == KeyInput.Type.KEYSYM && getKeyCode().getKeyCode() == keyCode && getModifier().matchesCurrent();
        }
    }

    default boolean matchesCurrentMouse() {
        if (!isUnknown() && getType() == KeyInput.Type.MOUSE && getModifier().matchesCurrent()) {
            return org.lwjgl.input.Mouse.isButtonDown(getKeyCode().getKeyCode());
        }
        return false;
    }

    default boolean matchesCurrentKey() {
        return !isUnknown() && getType() == KeyInput.Type.KEYSYM && getModifier().matchesCurrent() && InputCompat.isKeyDown(getKeyCode());
    }

    default ModifierKeyCode setKeyCodeAndModifier(KeyInput keyCode, Modifier modifier) {
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
        return getKeyCode().equals(KeyInput.INVALID);
    }
}
