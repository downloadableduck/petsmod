package me.shedaniel.forge.math.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ScaledResolution;
import me.shedaniel.forge.math.Point;
import org.lwjgl.input.Mouse;


public class PointHelper {
    public static Point ofMouse() {
        Minecraft client = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(client.gameSettings, client.displayWidth, client.displayHeight);
        double scaledWidth = sr.getScaledWidth();
        double scaledHeight = sr.getScaledHeight();
        double mx = Mouse.getX() * scaledWidth / (double) client.displayWidth;
        double my = (client.displayHeight - Mouse.getY()) * scaledHeight / (double) client.displayWidth;
        return new Point(mx, my);
    }

    public static int getMouseX() {
        return ofMouse().x;
    }

    public static int getMouseY() {
        return ofMouse().y;
    }
}
