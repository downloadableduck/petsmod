package me.shedaniel.forge.clothconfig2.impl;

import me.shedaniel.forge.clothconfig2.api.Modifier;
import me.shedaniel.forge.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.resources.I18n;

import org.lwjgl.input.Keyboard;


public class ModifierKeyCodeImpl implements ModifierKeyCode {
    private KeyInput keyCode;
    private Modifier modifier;

    public ModifierKeyCodeImpl() {
    }

    private static String getKeyTranslationKey(KeyInput keyCode) {
        String name = Keyboard.getKeyName(keyCode.getKeyCode());
        if (name != null) {
            name = name.toLowerCase();
            if (name.startsWith("key."))
                name = name.substring(4);
        } else {
            name = "unknown";
        }
        return "key.keyboard." + name;
    }

    @Override
    public KeyInput getKeyCode() {
        return keyCode;
    }

    @Override
    public Modifier getModifier() {
        return modifier;
    }

    @Override
    public ModifierKeyCode setKeyCode(KeyInput keyCode) {
        this.keyCode = KeyInput.of(keyCode.getType(), keyCode.getKeyCode());
        if (keyCode.equals(KeyInput.INVALID))
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
        int int_1 = this.keyCode.getKeyCode();
        String base;
        switch (this.keyCode.getType()) {
            case MOUSE:
                base = I18n.format("key.mouse", int_1 + 1);
                break;
            case SCANCODE:
            case KEYSYM:
            default:
                base = I18n.format(getKeyTranslationKey(this.keyCode));
                break;
        }
        if (modifier.hasShift())
            base = I18n.format("modifier.cloth-config.shift", base);
        if (modifier.hasControl())
            base = I18n.format("modifier.cloth-config.ctrl", base);
        if (modifier.hasAlt())
            base = I18n.format("modifier.cloth-config.alt", base);
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
