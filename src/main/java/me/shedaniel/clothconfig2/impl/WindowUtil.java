package me.shedaniel.clothconfig2.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

/**
 * Feather 1.12.2 helper: the named {@code net.minecraft.client.render.Window}
 * class is unreachable in this toolchain and there is no {@code getWindow()}/
 * {@code window} accessor on {@link Minecraft}. These helpers boot the scaled
 * screen dimensions from the LWJGL display and the configured GUI scale factor.
 */
public final class WindowUtil {

    private WindowUtil() {
    }

    public static int getGuiScale() {
        Minecraft client = Minecraft.getInstance();
        try {
            // Prefer the configured GUI scale, clamping so a valid factor is returned.
            int option = client.options.guiScale;
            if (option <= 0) {
                return guessScale(Display.getWidth(), Display.getHeight());
            }
            return Math.max(1, option);
        } catch (Throwable t) {
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
