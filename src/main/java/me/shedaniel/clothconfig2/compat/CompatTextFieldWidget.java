//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.compat;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;

public class CompatTextFieldWidget extends TextFieldWidget {
    private final Screen screen;

    public CompatTextFieldWidget(Screen screen, int id, TextRenderer textRenderer, int x, int y, int width, int height) {
        super(screen, textRenderer, x, y, width, height, "");
        this.screen = screen;
    }

    public void keyPressed(char chr, int key) {
        if (chr != '\t' || this.screen != null) {
            super.keyPressed(chr, key);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.focused) {
            super.keyPressed('\u0000', keyCode);
        }

        return false;
    }

    public boolean charTyped(char chr, int modifiers) {
        if (this.focused && chr != 0) {
            super.keyPressed(chr, 0);
        }

        return false;
    }
}
