//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.widget;

import me.shedaniel.clothconfig2.ButtonWidget;

public class ColorDisplayWidget extends ButtonWidget {
    protected int color;
    protected int size;

    public ColorDisplayWidget(int x, int y, int size, int color) {
        super(x, y, size, size, "", (w) -> {
        });
        this.color = color;
        this.size = size;
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.fillGradient(this.x, this.y, this.x + this.size, this.y + this.size, -6250336, -6250336);
        this.fillGradient(this.x + 1, this.y + 1, this.x + this.size - 1, this.y + this.size - 1, -1, -1);
        this.fillGradient(this.x + 1, this.y + 1, this.x + this.size - 1, this.y + this.size - 1, this.color, this.color);
    }

    public void setColor(int color) {
        this.color = color;
    }
}
