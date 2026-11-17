//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.compat;

import org.lwjgl.input.Mouse;

public final class MouseInput {
    private MouseInput() {
    }

    public static boolean isLeftPressed() {
        return Mouse.isButtonDown(0);
    }

    public static boolean isRightPressed() {
        return Mouse.isButtonDown(1);
    }

    public static boolean isMiddlePressed() {
        return Mouse.isButtonDown(2);
    }

    public static double getX() {
        return (double)Mouse.getX();
    }

    public static double getY() {
        return (double)Mouse.getY();
    }

    public static boolean wasLeftClicked() {
        return Mouse.isButtonDown(0);
    }
}
