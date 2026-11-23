package me.shedaniel.clothconfig2.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Window;
import net.ornithemc.osl.lifecycle.api.client.MinecraftInstance;
import org.lwjgl.opengl.Display;

/**
 * b1.2 helper for the scaled GUI viewport.
 *
 * <p>b1.2 has no {@code getWindow()} accessor and no configurable GUI scale. Both
 * {@code Minecraft.resize} and {@code GameRenderer} derive the scaled screen from a
 * fresh {@link Window} built from the physical display size, so this helper uses the
 * same calculation. Otherwise the scissor rectangle is converted with the wrong
 * scale and lands outside the config list in fullscreen.
 */
public final class WindowUtil {

    private WindowUtil() {
    }

    private static Window currentWindow() {
        int width = Display.getWidth();
        int height = Display.getHeight();
        try {
            Minecraft client = MinecraftInstance.get();
            if (client != null && client.width > 0 && client.height > 0) {
                width = client.width;
                height = client.height;
            }
        } catch (Throwable ignored) {
            // Fall back to the LWJGL display size if the client is not available yet.
        }
        return new Window(Math.max(1, width), Math.max(1, height));
    }

    public static int getGuiScale() {
        return Math.max(1, currentWindow().scale);
    }

    public static int getScaledWidth() {
        return currentWindow().getWidth();
    }

    public static int getScaledHeight() {
        return currentWindow().getHeight();
    }
}
