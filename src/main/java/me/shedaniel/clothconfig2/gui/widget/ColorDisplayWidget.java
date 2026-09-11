package me.shedaniel.clothconfig2.gui.widget;

import net.minecraft.client.gui.widget.ButtonWidget;

import java.util.Random;

public class ColorDisplayWidget extends ButtonWidget {

    protected int color;
    protected int size;

    public ColorDisplayWidget(int x, int y, int size, int color) {
        super(new Random().nextInt(), x, y, size, size, "");
        this.color = color;
        this.size = size;
    }

    public void render(int mouseX, int mouseY, float delta) {
        fillGradient(this.x, this.y, this.x + size, this.y + size, -0x5F5F60, -0x5F5F60);
        fillGradient(this.x + 1, this.y + 1, this.x + size - 1, this.y + size - 1, 0xffffffff, 0xffffffff);
        fillGradient(this.x + 1, this.y + 1, this.x + size - 1, this.y + size - 1, color, color);
    }

    public void setColor(int color) {
        this.color = color;
    }
}
