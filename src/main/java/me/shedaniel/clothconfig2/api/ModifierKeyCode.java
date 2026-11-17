//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import me.shedaniel.clothconfig2.compat.InputConstants;
import me.shedaniel.clothconfig2.compat.InputConstants.Type;
import me.shedaniel.clothconfig2.compat.MouseInput;
import me.shedaniel.clothconfig2.impl.ModifierKeyCodeImpl;

public interface ModifierKeyCode {
    static ModifierKeyCode of(InputConstants.Key keyCode, Modifier modifier) {
        return (new ModifierKeyCodeImpl()).setKeyCodeAndModifier(keyCode, modifier);
    }

    static ModifierKeyCode copyOf(ModifierKeyCode code) {
        return of(code.getKeyCode(), code.getModifier());
    }

    static ModifierKeyCode unknown() {
        return of(InputConstants.UNKNOWN, Modifier.none());
    }

    InputConstants.Key getKeyCode();

    ModifierKeyCode setKeyCode(InputConstants.Key var1);

    default Type getType() {
        return this.getKeyCode().getType();
    }

    Modifier getModifier();

    ModifierKeyCode setModifier(Modifier var1);

    default boolean matchesMouse(int button) {
        return !this.isUnknown() && this.getType() == Type.MOUSE && this.getKeyCode().getValue() == button && this.getModifier().matchesCurrent();
    }

    default boolean matchesKey(int keyCode, int scanCode) {
        if (this.isUnknown()) {
            return false;
        } else if (keyCode == InputConstants.UNKNOWN.getValue()) {
            return this.getType() == Type.SCANCODE && this.getKeyCode().getValue() == scanCode && this.getModifier().matchesCurrent();
        } else {
            return this.getType() == Type.KEYSYM && this.getKeyCode().getValue() == keyCode && this.getModifier().matchesCurrent();
        }
    }

    default boolean matchesCurrentMouse() {
        if (!this.isUnknown() && this.getType() == Type.MOUSE && this.getModifier().matchesCurrent()) {
            switch (this.getKeyCode().getValue()) {
                case 0:
                    return MouseInput.isLeftPressed();
                case 1:
                    return MouseInput.isRightPressed();
                case 2:
                    return MouseInput.isMiddlePressed();
            }
        }

        return false;
    }

    default boolean matchesCurrentKey() {
        return !this.isUnknown() && this.getType() == Type.KEYSYM && this.getModifier().matchesCurrent() && InputConstants.getKey(this.getKeyCode().getValue());
    }

    default ModifierKeyCode setKeyCodeAndModifier(InputConstants.Key keyCode, Modifier modifier) {
        this.setKeyCode(keyCode);
        this.setModifier(modifier);
        return this;
    }

    default ModifierKeyCode clearModifier() {
        return this.setModifier(Modifier.none());
    }

    String toString();

    default String getLocalizedName() {
        return this.toString();
    }

    default boolean isUnknown() {
        return this.getKeyCode().equals(InputConstants.UNKNOWN);
    }
}
