package me.shedaniel.forge.clothconfig2.gui;

import net.minecraft.client.gui.GuiButton;


import java.util.Random;


public class ClothConfigTabButton extends GuiButton {
    
    private final int index;
    private final ClothConfigScreen screen;
    
    public ClothConfigTabButton(ClothConfigScreen screen, int index, int int_1, int int_2, int int_3, int int_4, String string_1) {
        super(new Random().nextInt(), int_1, int_2, int_3, int_4, string_1);
        this.index = index;
        this.screen = screen;
    }
    
    @Override
    public boolean mouseClicked(double d, double u, int v) {
        return visible && enabled && isMouseOver(d, u);
    }

    @Override
    public void mousePressed(double d, double u) {
        if (index != -1)
            screen.nextTabIndex = index;
        screen.tabsScrollVelocity = 0d;
        screen.initGui();
        super.mousePressed(d, u);
    }
    
    @Override
    public void drawButton(int int_1, int int_2, float float_1) {
        enabled = index != screen.selectedTabIndex;
        super.drawButton(int_1, int_2, float_1);
    }


    public boolean isMouseOver(double double_1, double double_2) {
        return this.enabled && this.visible && double_1 >= this.x && double_2 >= this.y && double_1 < this.x + this.width && double_2 < this.y + this.height && double_1 >= 20 && double_1 < screen.width - 20;
    }
}
