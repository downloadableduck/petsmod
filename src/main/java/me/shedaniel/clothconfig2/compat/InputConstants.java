package me.shedaniel.clothconfig2.compat;

import java.util.Objects;

public final class InputConstants {
    public static final Key UNKNOWN = new Key(Type.KEYSYM, -1, "key.unknown");

    private InputConstants() {
    }

    public enum Type {
        KEYSYM,
        MOUSE,
        SCANCODE;

        public Key getOrCreate(int code) {
            return new Key(this, code, "");
        }
    }

    public static class Key {
        private final Type type;
        private final int code;
        private final String name;

        public Key(Type type, int code, String name) {
            this.type = type;
            this.code = code;
            this.name = name;
        }

        public Type getType() {
            return type;
        }

        public int getValue() {
            return code;
        }

        public String getName() {
            return name != null && !name.isEmpty() ? name : ("key." + type.name().toLowerCase() + "." + code);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return code == key.code && type == key.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, code);
        }
    }

    public static boolean getKey(int keyCode) {
        return org.lwjgl.input.Keyboard.isKeyDown(keyCode);
    }

    public static Key getKey(int keyCode, int scanCode) {
        return new Key(Type.KEYSYM, keyCode, "");
    }
}
