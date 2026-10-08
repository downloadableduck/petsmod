package me.shedaniel.clothconfig2.gui;

import net.minecraft.client.Minecraft;
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

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        enabled = index != screen.selectedTabIndex;
        super.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    public void onClick() {
        if (index != -1)
            screen.nextTabIndex = index;
        screen.tabsScrollVelocity = 0d;
        screen.initGui();
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height && mouseX >= 20 && mouseX < screen.width - 20;
    }
}
