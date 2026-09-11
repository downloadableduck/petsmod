package me.shedaniel.clothconfig2.compat;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.TextRenderer;

/**
 * Feather 1.12.2 adapter: wraps the native {@link TextFieldWidget} so it can be
 * placed inside the vendored {@code List<GuiEventListener> children()} lists used
 * by the ClothConfig entry widgets. All keyboard/char/mouse dispatch is delegated
 * to the underlying text field's 1.12 APIs.
 */
public class CompatTextFieldWidget extends TextFieldWidget implements me.shedaniel.clothconfig2.compat.GuiEventListener {

    public CompatTextFieldWidget(int id, TextRenderer textRenderer, int x, int y, int width, int height) {
        super(id, textRenderer, x, y, width, height);
    }

    @Override
    public boolean keyPressed(char chr, int key) {
        return super.keyPressed(chr, key);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return GuiEventListener.super.mouseClicked((int) mouseX, (int) mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (isFocused()) {
            return super.keyPressed((char) 0, keyCode);
        }
        return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (isFocused() && chr != 0) {
            return super.keyPressed(chr, 0);
        }
        return false;
    }
}
