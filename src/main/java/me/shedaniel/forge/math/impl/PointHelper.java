package me.shedaniel.forge.math.impl;

import me.shedaniel.forge.math.Point;
import net.minecraft.client.Minecraft;



public class PointHelper {
    public static Point ofMouse() {
        Minecraft client = Minecraft.getMinecraft();
        double mx = client.mouseHelper.getMouseX() * (double) client.mainWindow.getScaledWidth() / (double) client.mainWindow.getWidth();
        double my = client.mouseHelper.getMouseY() * (double) client.mainWindow.getScaledHeight() / (double) client.mainWindow.getHeight();
        return new Point(mx, my);
    }
    
    public static int getMouseX() {
        return ofMouse().x;
    }
    
    public static int getMouseY() {
        return ofMouse().y;
    }
}
