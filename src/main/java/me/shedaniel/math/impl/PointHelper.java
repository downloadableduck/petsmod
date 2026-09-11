package me.shedaniel.math.impl;

import me.shedaniel.math.Point;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.input.Mouse;

public class PointHelper {
    public static Point ofMouse() {
        MinecraftClient client = MinecraftClient.getInstance();
        Window sr = new Window(client);
        double scaledWidth = sr.getScaledWidth();
        double scaledHeight = sr.getScaledHeight();
        double mx = Mouse.getX() * scaledWidth / (double) (double) sr.getWidth();
        double my = ((double) sr.getHeight() - Mouse.getY()) * scaledHeight / (double) (double) sr.getWidth();
        return new Point(mx, my);
    }

    public static int getMouseX() {
        return ofMouse().x;
    }

    public static int getMouseY() {
        return ofMouse().y;
    }
}
