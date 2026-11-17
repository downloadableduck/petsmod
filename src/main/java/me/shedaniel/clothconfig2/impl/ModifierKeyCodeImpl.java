//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.compat.InputConstants;
import net.minecraft.client.resource.language.I18n;

import java.util.Objects;

public class ModifierKeyCodeImpl implements ModifierKeyCode {
    private InputConstants.Key keyCode;
    private Modifier modifier;

    public ModifierKeyCodeImpl() {
    }

    public InputConstants.Key getKeyCode() {
        return this.keyCode;
    }

    public Modifier getModifier() {
        return this.modifier;
    }

    public ModifierKeyCode setKeyCode(InputConstants.Key keyCode) {
        this.keyCode = keyCode.getType().getOrCreate(keyCode.getValue());
        if (keyCode.equals(InputConstants.UNKNOWN)) {
            this.setModifier(Modifier.none());
        }

        return this;
    }

    public ModifierKeyCode setModifier(Modifier modifier) {
        this.modifier = Modifier.of(modifier.getValue());
        return this;
    }

    public String toString() {
        String string_1 = this.keyCode.getName();
        int int_1 = this.keyCode.getValue();
        String string_2 = null;
        String base;
        switch (this.keyCode.getType()) {
            case KEYSYM:
                string_2 = string_1;
                break;
            case SCANCODE:
                string_2 = string_1;
                break;
            case MOUSE:
                base = I18n.getTranslation(string_1);
                string_2 = Objects.equals(base, string_1) ? I18n.getTranslation("key.mouse", new Object[]{int_1 + 1}) : base;
        }

        base = string_2 == null ? I18n.getTranslation(string_1) : string_2;
        if (this.modifier.hasShift()) {
            base = I18n.getTranslation("modifier.cloth-config.shift", new Object[]{base});
        }

        if (this.modifier.hasControl()) {
            base = I18n.getTranslation("modifier.cloth-config.ctrl", new Object[]{base});
        }

        if (this.modifier.hasAlt()) {
            base = I18n.getTranslation("modifier.cloth-config.alt", new Object[]{base});
        }

        return base;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ModifierKeyCode)) {
            return false;
        } else {
            ModifierKeyCode that = (ModifierKeyCode)o;
            return this.keyCode.equals(that.getKeyCode()) && this.modifier.equals(that.getModifier());
        }
    }

    public int hashCode() {
        int result = this.keyCode != null ? this.keyCode.hashCode() : 0;
        result = 31 * result + (this.modifier != null ? this.modifier.hashCode() : 0);
        return result;
    }
}
