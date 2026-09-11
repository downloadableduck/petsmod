package me.shedaniel.clothconfig2.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;

import java.util.Random;

public class ClothConfigTabButton extends ButtonWidget {

    private final int index;
    private final ClothConfigScreen screen;

    public ClothConfigTabButton(ClothConfigScreen screen, int index, int int_1, int int_2, int int_3, int int_4, String string_1) {
        super(new Random().nextInt(), int_1, int_2, int_3, int_4, string_1);
        this.index = index;
        this.screen = screen;
    }

    public void render(int mouseX, int mouseY, float delta) {
        active = index != screen.selectedTabIndex;
        this.method_891(MinecraftClient.getInstance(), mouseX, mouseY, delta);
    }

    public void onClick() {
        if (index != -1)
            screen.nextTabIndex = index;
        screen.tabsScrollVelocity = 0d;
        screen.init();
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return this.active && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height && mouseX >= 20 && mouseX < screen.width - 20;
    }
}
