package me.shedaniel.forge.math.impl;

import me.shedaniel.forge.math.Point;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PointHelper {
    public static Point ofMouse() {
        Minecraft client = Minecraft.getInstance();
        double mx = client.mouseHandler.xpos() * (double) client.window.getGuiScaledWidth() / (double) client.window.getWidth();
        double my = client.mouseHandler.ypos() * (double) client.window.getGuiScaledHeight() / (double) client.window.getHeight();
        return new Point(mx, my);
    }
    
    public static int getMouseX() {
        return ofMouse().x;
    }
    
    public static int getMouseY() {
        return ofMouse().y;
    }
}
