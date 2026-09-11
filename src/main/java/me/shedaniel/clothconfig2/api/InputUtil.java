package me.shedaniel.clothconfig2.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.input.Keyboard;

import java.util.Objects;

/**
 * Local stand-in for the 1.13.2 {@code me.shedaniel.clothconfig2.api.InputUtil} (InputUtil) key registry.
 * 1.12.2 has no equivalent; keys are represented by an LWJGL2 key code.
 */
@Environment(EnvType.CLIENT)
public final class InputUtil {
    public enum Type {
        KEYSYM, SCANCODE, MOUSE;

        public Key method_18162(int code) {
            return new Key(this, code);
        }
    }

    public static final class Key {
        private final Type type;
        private final int code;

        public Key(Type type, int code) {
            this.type = type;
            this.code = code;
        }

        public Type method_18158() {
            return this.type;
        }

        public int method_18159() {
            return this.code;
        }

        public String method_18157() {
            if (this.type == Type.KEYSYM) {
                String name = Keyboard.getKeyName(this.code);
                return name == null ? "unknown" : name;
            }
            if (this.type == Type.MOUSE) {
                return "key.mouse." + (this.code + 1);
            }
            return "key.scancode." + this.code;
        }

        public boolean isUnknown() {
            return this == UNKNOWN_KEY || this.code == -1;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof Key)) {
                return false;
            }
            Key key = (Key) object;
            return this.code == key.code && this.type == key.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.type, this.code);
        }

        @Override
        public String toString() {
            return this.method_18157();
        }
    }

    public static final Key UNKNOWN_KEY = new Key(Type.KEYSYM, -1);

    public static boolean method_18154(int code) {
        if (code < 0 || code == Keyboard.KEY_NONE) {
            return false;
        }
        return Keyboard.isKeyDown(code);
    }

    public static Key method_18155(int scope, int code) {
        return UNKNOWN_KEY.method_18158().method_18162(code);
    }

    public static Key method_18156(String translationKey) {
        return UNKNOWN_KEY;
    }

    private InputUtil() {
    }
}