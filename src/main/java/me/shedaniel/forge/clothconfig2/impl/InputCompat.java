package me.shedaniel.forge.clothconfig2.impl;

public final class InputCompat {
    private InputCompat() {
    }

    public static boolean isKeyDown(KeyInput k) {
        if (k.getType() == KeyInput.Type.KEYSYM)
            return org.lwjgl.input.Keyboard.isKeyDown(k.getKeyCode());
        if (k.getType() == KeyInput.Type.MOUSE)
            return org.lwjgl.input.Mouse.isButtonDown(k.getKeyCode());
        return false;
    }

    public static int getEventKey() {
        return org.lwjgl.input.Keyboard.getEventKey();
    }

    public static boolean isKeyPressed() {
        return org.lwjgl.input.Keyboard.getEventKeyState();
    }
}
