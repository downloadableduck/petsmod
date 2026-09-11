package me.shedaniel.clothconfig2.impl;

public class KeyInput {
    public static final KeyInput INVALID = new KeyInput(Type.KEYSYM, -1);
    private final Type type;
    private final int keyCode;

    public KeyInput(Type type, int keyCode) {
        this.type = type;
        this.keyCode = keyCode;
    }

    public static KeyInput of(Type type, int keyCode) {
        return new KeyInput(type, keyCode);
    }

    public int getKeyCode() {
        return keyCode;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof KeyInput))
            return false;
        KeyInput keyInput = (KeyInput) o;
        return keyCode == keyInput.keyCode && type == keyInput.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + keyCode;
        return result;
    }

    @Override
    public String toString() {
        return "KeyInput{type=" + type + ", keyCode=" + keyCode + "}";
    }

    public enum Type {
        KEYSYM,
        SCANCODE,
        MOUSE
    }
}
