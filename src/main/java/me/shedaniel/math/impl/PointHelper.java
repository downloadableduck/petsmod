package me.shedaniel.math.impl;

import me.shedaniel.math.Point;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.input.Mouse;

public class PointHelper {
    public static Point ofMouse() {
        MinecraftClient client = MinecraftClient.getInstance();
        Window sr = new Window(client, client.width, client.height);
        double scaledWidth = sr.getWidth();
        double scaledHeight = sr.getHeight();
        double mx = Mouse.getX() * scaledWidth / (double) (double) client.width;
        double my = ((double) client.height - Mouse.getY()) * scaledHeight / (double) (double) client.width;
        return new Point(mx, my);
    }

    public static int getMouseX() {
        return ofMouse().x;
    }

    public static int getMouseY() {
        return ofMouse().y;
    }
}
