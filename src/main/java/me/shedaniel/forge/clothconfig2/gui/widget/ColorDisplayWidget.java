package me.shedaniel.forge.clothconfig2.gui.widget;

import net.minecraft.client.gui.GuiButton;

import java.util.Random;

public class ColorDisplayWidget extends GuiButton {
    
    protected int color;
    protected int size;
    
    public ColorDisplayWidget(int x, int y, int size, int color) {
        super(new Random().nextInt(), x, y, size, size, "");
        this.color = color;
        this.size = size;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float delta) {
        drawGradientRect(this.x, this.y, this.x + size, this.y + size, -0x5F5F60, -0x5F5F60);
        drawGradientRect(this.x + 1, this.y + 1, this.x + size - 1, this.y + size - 1, 0xffffffff, 0xffffffff);
        drawGradientRect(this.x + 1, this.y + 1, this.x + size - 1, this.y + size - 1, color, color);
    }
    
    @Override
    public void onClick(double mouseX, double mouseY) {
    }
    
    @Override
    public void onRelease(double mouseX, double mouseY) {
    }
    
    public void setColor(int color) {
        this.color = color;
    }
}
