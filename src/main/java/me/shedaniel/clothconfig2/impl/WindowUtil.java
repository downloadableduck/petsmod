//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

public final class WindowUtil {
    private WindowUtil() {
    }

    public static int getGuiScale() {
        Minecraft client = Minecraft.INSTANCE;

        try {
            int option = client.options.guiScale;
            return option <= 0 ? guessScale(Display.getWidth(), Display.getHeight()) : Math.max(1, option);
        } catch (Throwable var2) {
            return guessScale(Display.getWidth(), Display.getHeight());
        }
    }

    public static int getScaledWidth() {
        int scale = Math.max(1, getGuiScale());
        return Math.max(1, Display.getWidth() / scale);
    }

    public static int getScaledHeight() {
        int scale = Math.max(1, getGuiScale());
        return Math.max(1, Display.getHeight() / scale);
    }

    private static int guessScale(int width, int height) {
        int scale = 1;
        if (width >= 3200) {
            scale = 4;
        } else if (width >= 1600) {
            scale = 3;
        } else if (width >= 800) {
            scale = 2;
        }

        return scale;
    }
}
