package me.shedaniel.forge.math.impl;

import me.shedaniel.forge.math.Point;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class PointHelper {
    public static Point ofMouse() {
        Minecraft client = Minecraft.getInstance();
        ScaledResolution sr = new ScaledResolution(client);
        double scaledWidth = sr.func_78326_a();
        double scaledHeight = sr.func_78328_b();
        double mx = Mouse.getX() * scaledWidth / (double) client.field_71443_c;
        double my = (client.field_71440_d - Mouse.getY()) * scaledHeight / (double) client.field_71443_c;
        return new Point(mx, my);
    }

    public static int getMouseX() {
        return ofMouse().x;
    }

    public static int getMouseY() {
        return ofMouse().y;
    }
}
