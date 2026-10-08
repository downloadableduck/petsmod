package me.shedaniel.clothconfig2.gui.widget;

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

    public void render(int mouseX, int mouseY, float delta) {
        drawGradientRect(this.xPosition, this.yPosition, this.xPosition + size, this.yPosition + size, -0x5F5F60, -0x5F5F60);
        drawGradientRect(this.xPosition + 1, this.yPosition + 1, this.xPosition + size - 1, this.yPosition + size - 1, 0xffffffff, 0xffffffff);
        drawGradientRect(this.xPosition + 1, this.yPosition + 1, this.xPosition + size - 1, this.yPosition + size - 1, color, color);
    }

    public void setColor(int color) {
        this.color = color;
    }
}
