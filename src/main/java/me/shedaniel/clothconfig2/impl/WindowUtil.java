//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ScaledResolution;
import net.minecraft.client.option.GameSettings;

/**
 * BTA-backed window size helpers used by Cloth Config's scissors and widgets.
 */
public final class WindowUtil {
    private WindowUtil() {
    }

    public static int getGuiScale() {
        Minecraft client = Minecraft.getMinecraft();
        try {
            Integer option = GameSettings.GUI_SCALE.value;
            if (option > 0) {
                return Math.max(1, option);
            }
        } catch (Throwable ignored) {
            // Fall through to the active resolution's scale.
        }

        ScaledResolution resolution = client.resolution;
        return resolution != null ? Math.max(1, resolution.getScale()) : 1;
    }

    public static int getScaledWidth() {
        Minecraft client = Minecraft.getMinecraft();
        ScaledResolution resolution = client.resolution;
        if (resolution != null) {
            return Math.max(1, resolution.getScaledWidthScreenCoords());
        }
        return Math.max(1, client.gameWindow.getWidthScreenCoords() / Math.max(1, getGuiScale()));
    }

    public static int getScaledHeight() {
        Minecraft client = Minecraft.getMinecraft();
        ScaledResolution resolution = client.resolution;
        if (resolution != null) {
            return Math.max(1, resolution.getScaledHeightScreenCoords());
        }
        return Math.max(1, client.gameWindow.getHeightScreenCoords() / Math.max(1, getGuiScale()));
    }
}
