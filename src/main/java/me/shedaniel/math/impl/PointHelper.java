package me.shedaniel.math.impl;

import me.shedaniel.math.Point;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public class PointHelper {
    public static Point ofMouse() {
        Minecraft client = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(client, client.displayWidth, client.displayHeight);
        double scaledWidth = sr.getScaledWidth();
        double scaledHeight = sr.getScaledHeight();
        double mx = Mouse.getX() * scaledWidth / (double) (double) client.displayWidth;
        double my = ((double) client.displayHeight - Mouse.getY()) * scaledHeight / (double) (double) client.displayWidth;
        return new Point(mx, my);
    }

    public static int getMouseX() {
        return ofMouse().x;
    }

    public static int getMouseY() {
        return ofMouse().y;
    }
}
