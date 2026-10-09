package me.shedaniel.clothconfig2.gui;

import java.util.Random;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;

public class ClothConfigTabButton extends ButtonWidget {

    private final int index;
    private final ClothConfigScreen screen;

    public ClothConfigTabButton(ClothConfigScreen screen, int index, int int_1, int int_2, int int_3, int int_4, String string_1) {
        super(new Random().nextInt(), int_1, int_2, int_3, int_4, string_1);
        this.index = index;
        this.screen = screen;
    }

    public void method_21887(MinecraftClient mc, int mouseX, int mouseY) {
        field_22511 = index != screen.selectedTabIndex;
        super.method_21887(MinecraftClient.getInstance(), mouseX, mouseY);
    }

    public void onClick() {
        if (index != -1)
            screen.nextTabIndex = index;
        screen.tabsScrollVelocity = 0d;
        screen.method_21947();
    }

    public boolean method_21885(int mouseX, int mouseY) {
        return this.field_22511 && this.field_22512 && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.field_22508 && mouseY < this.y + this.field_22509 && mouseX >= 20 && mouseX < screen.field_22535 - 20;
    }
}
