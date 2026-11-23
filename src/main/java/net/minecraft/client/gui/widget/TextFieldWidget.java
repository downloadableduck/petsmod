package net.minecraft.client.gui.widget;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.text.ITextField;
import net.minecraft.client.gui.text.TextFieldEditor;
import org.lwjgl.input.Keyboard;

/**
 * Modern text field API backed by BTA's TextFieldEditor and rendering helpers.
 */
public class TextFieldWidget extends Gui implements ITextField, GuiEventListener {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean active = true;
    public boolean enabled = true;
    public boolean focused;
    public String text = "";

    private final Screen parent;
    private final TextRenderer textRenderer;
    private final TextFieldEditor editor;
    private int maxLength = 32767;

    public TextFieldWidget(Screen parent, TextRenderer textRenderer, int x, int y, int width, int height, String text) {
        this(parent, textRenderer, 0, x, y, width, height, text);
    }

    public TextFieldWidget(Screen parent, TextRenderer textRenderer, int id, int x, int y, int width, int height, String text) {
        this.parent = parent;
        this.textRenderer = textRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.editor = new TextFieldEditor(this);
        this.setText(text == null ? "" : text);
        this.editor.setCursor(this.text.length());
    }

    @Override
    public void setText(String text) {
        this.text = text == null ? "" : text;
        if (this.text.length() > this.maxLength) {
            this.text = this.text.substring(0, this.maxLength);
        }
        if (this.editor.getCursor() > this.text.length()) {
            this.editor.setCursor(this.text.length());
        }
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public int maxLength() {
        return this.maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = Math.max(0, maxLength);
        if (this.text.length() > this.maxLength) {
            this.setText(this.text.substring(0, this.maxLength));
        }
    }

    public void setMaxStringLength(int maxLength) {
        this.setMaxLength(maxLength);
    }

    public boolean isFocused() {
        return this.focused;
    }

    public void setFocused(boolean focused) {
        if (focused && !this.focused) {
            this.editor.setCursor(this.text.length());
        }
        this.focused = focused;
    }

    public boolean isEnabled() {
        return this.enabled && this.active;
    }

    public void render() {
        this.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -6250336);
        this.drawRect(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, -16777216);
        TextRenderer renderer = this.textRenderer != null ? this.textRenderer : TextRenderer.of(Minecraft.getMinecraft().font);
        renderer.drawWithShadow(this.text, this.x + 4, this.y + (this.height - 8) / 2, 0xE0E0E0);
        if (this.focused) {
            int cursorX = this.x + 4 + renderer.getWidth(this.text.substring(0, Math.min(this.editor.getCursor(), this.text.length())));
            this.drawRect(cursorX, this.y + 2, cursorX + 1, this.y + this.height - 2, -1);
        }
    }

    /** True for keys BTA's editor handles before it would append the char argument. */
    private static boolean isEditorKey(int key) {
        switch (key) {
            case Keyboard.KEY_BACK:
            case Keyboard.KEY_DELETE:
            case Keyboard.KEY_HOME:
            case Keyboard.KEY_END:
            case Keyboard.KEY_LEFT:
            case Keyboard.KEY_RIGHT:
                return true;
            case Keyboard.KEY_C:
            case Keyboard.KEY_V:
                return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
            default:
                return false;
        }
    }

    private static boolean isPrintable(char chr) {
        return chr >= 32 && chr != 127;
    }

    public void keyPressed(char chr, int key) {
        if (!this.isEnabled() || !this.focused) {
            return;
        }
        if (isEditorKey(key)) {
            this.editor.handleInput(key, '\0');
        } else if (isPrintable(chr)) {
            this.editor.handleInput(0, chr);
        } else {
            return;
        }
        this.text = this.editor.getText();
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.isEnabled() && this.focused && isEditorKey(keyCode)) {
            this.editor.handleInput(keyCode, '\0');
            this.text = this.editor.getText();
            return true;
        }
        return false;
    }

    public boolean charTyped(char chr, int modifiers) {
        if (this.isEnabled() && this.focused && isPrintable(chr)) {
            this.editor.handleInput(0, chr);
            this.text = this.editor.getText();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && this.isEnabled() && this.isMouseOver(mouseX, mouseY)) {
            this.setFocused(true);
            this.updateCursorFromMouseX(mouseX);
            return true;
        }
        return false;
    }

    private void updateCursorFromMouseX(double mouseX) {
        TextRenderer renderer = this.textRenderer != null ? this.textRenderer : TextRenderer.of(Minecraft.getMinecraft().font);
        double offset = mouseX - this.x - 4;
        int cursor = 0;
        while (cursor < this.text.length() && renderer.getWidth(this.text.substring(0, cursor + 1)) <= offset) {
            cursor++;
        }
        this.editor.setCursor(cursor);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        return this.mouseClicked((double)mouseX, (double)mouseY, button);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width)
                && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
    }

    public Screen getParent() {
        return this.parent;
    }
}
