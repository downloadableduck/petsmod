package me.shedaniel.clothconfig2.impl;

import net.minecraft.class_4107;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;

import java.util.Objects;

import static net.minecraft.class_4107.class_4109.KEYSYM;
import static net.minecraft.class_4107.class_4109.SCANCODE;

@Environment(EnvType.CLIENT)
public class ModifierKeyCodeImpl implements ModifierKeyCode {
    private class_4107.class_4108 keyCode;
    private Modifier modifier;
    
    public ModifierKeyCodeImpl() {
    }
    
    @Override
    public class_4107.class_4108 getKeyCode() {
        return keyCode;
    }

    @Override
    public Modifier getModifier() {
        return modifier;
    }
    
    @Override
    public ModifierKeyCode setKeyCode(class_4107.class_4108 keyCode) {
        this.keyCode = keyCode.method_18158().method_18162(keyCode.method_18159());
        if (keyCode.equals(class_4107.field_19910.method_18159()))
            setModifier(Modifier.none());
        return this;
    }
    
    @Override
    public ModifierKeyCode setModifier(Modifier modifier) {
        this.modifier = Modifier.of(modifier.getValue());
        return this;
    }
    
    @Override
    public String toString() {
        String string_1 = this.keyCode.method_18157();
        int int_1 = this.keyCode.method_18159();
        String string_2 = null;
        switch (this.keyCode.method_18158()) {
            case KEYSYM:
                string_2 = KEYSYM.toString();
                break;
            case SCANCODE:
                string_2 = SCANCODE.toString();
                break;
            case MOUSE:
                String string_3 = I18n.translate(string_1);
                string_2 = Objects.equals(string_3, string_1) ? I18n.translate(class_4107.class_4109.MOUSE.name(), int_1 + 1) : string_3;
        }
        String base = string_2 == null ? I18n.translate(string_1) : string_2;
        if (modifier.hasShift())
            base = I18n.translate("modifier.cloth-config2.shift", base);
        if (modifier.hasControl())
            base = I18n.translate("modifier.cloth-config2.ctrl", base);
        if (modifier.hasAlt())
            base = I18n.translate("modifier.cloth-config2.alt", base);
        return base;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ModifierKeyCode))
            return false;
        ModifierKeyCode that = (ModifierKeyCode) o;
        return keyCode.equals(that.getKeyCode()) && modifier.equals(that.getModifier());
    }
    
    @Override
    public int hashCode() {
        int result = keyCode != null ? keyCode.hashCode() : 0;
        result = 31 * result + (modifier != null ? modifier.hashCode() : 0);
        return result;
    }
}
