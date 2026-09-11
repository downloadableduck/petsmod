package me.shedaniel.clothconfig2.compat;

public final class MouseInput {
    private MouseInput() {
    }

    public static boolean isLeftPressed() {
        return org.lwjgl.input.Mouse.isButtonDown(0);
    }

    public static boolean isRightPressed() {
        return org.lwjgl.input.Mouse.isButtonDown(1);
    }

    public static boolean isMiddlePressed() {
        return org.lwjgl.input.Mouse.isButtonDown(2);
    }

    public static double getX() {
        return org.lwjgl.input.Mouse.getX();
    }

    public static double getY() {
        return org.lwjgl.input.Mouse.getY();
    }

    public static boolean wasLeftClicked() {
        return org.lwjgl.input.Mouse.isButtonDown(0);
    }
}
