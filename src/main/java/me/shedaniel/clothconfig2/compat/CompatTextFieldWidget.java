package me.shedaniel.clothconfig2.compat;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.TextRenderer;

/**
 * Feather 1.12.2 adapter: wraps the native {@link TextFieldWidget} so it can be
 * placed inside the vendored {@code List<GuiEventListener> children()} lists used
 * by the ClothConfig entry widgets. All keyboard/char/mouse dispatch is delegated
 * to the underlying text field's 1.12 APIs.
 */
public class CompatTextFieldWidget extends TextFieldWidget implements GuiEventListener {

    private final Screen screen;

    public CompatTextFieldWidget(Screen screen, int id, TextRenderer textRenderer, int x, int y, int width, int height) {
        super(textRenderer, x, y, width, height, "");
        this.screen = screen;
    }

    @Override
    public void keyPressed(char chr, int key) {
        // TextFieldWidget assumes a screen exists for Tab focus cycling; the config
        // screen can be opened from in-game where Minecraft.screen is null.
        if (chr == '\t' && screen == null) {
            return;
        }
        super.keyPressed(chr, key);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return GuiEventListener.super.mouseClicked((int) mouseX, (int) mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (focused) {
            super.keyPressed((char) 0, keyCode);
        }
        return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (focused && chr != 0) {
             super.keyPressed(chr, 0);
        }
        return false;
    }
}
