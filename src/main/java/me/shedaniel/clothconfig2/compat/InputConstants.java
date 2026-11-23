//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.compat;

import org.lwjgl.input.Keyboard;

import java.util.Objects;

public final class InputConstants {
    public static final Key UNKNOWN;

    private InputConstants() {
    }

    public static boolean getKey(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }

    public static Key getKey(int keyCode, int scanCode) {
        return new Key(Type.KEYSYM, keyCode, "");
    }

    static {
        UNKNOWN = new Key(Type.KEYSYM, -1, "key.unknown");
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
            return this.type;
        }

        public int getValue() {
            return this.code;
        }

        public String getName() {
            return this.name != null && !this.name.isEmpty() ? this.name : "key." + this.type.name().toLowerCase() + "." + this.code;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (!(o instanceof Key)) {
                return false;
            } else {
                Key key = (Key)o;
                return this.code == key.code && this.type == key.type;
            }
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.type, this.code});
        }
    }

    public static enum Type {
        KEYSYM,
        MOUSE,
        SCANCODE;

        private Type() {
        }

        public Key getOrCreate(int code) {
            return new Key(this, code, "");
        }
    }
}
