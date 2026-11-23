package me.shedaniel.clothconfig2.compat;

import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.TextRenderer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Feather 1.12.2 adapter: wraps the native {@link TextFieldWidget} so it can be
 * placed inside the vendored {@code List<GuiEventListener> children()} lists used
 * by the ClothConfig entry widgets. All keyboard/char/mouse dispatch is delegated
 * to the underlying text field's 1.12 APIs.
 */
public class CompatTextFieldWidget extends GuiElement implements GuiEventListener {

    private final TextRenderer textRenderer;
    public int x;
    public int y;
    public int width;
    private final int height;
    private String text;
    private int maxLength;
    private int focusedTicks;
    public boolean focused = false;
    public boolean editable = true;
    private final Screen screen;


    public CompatTextFieldWidget(Screen screen, TextRenderer textRenderer, int x, int y, int width, int height, String text) {
        this.textRenderer = textRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setText(text);
        this.screen = screen;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void tick() {
        ++this.focusedTicks;
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean i = this.editable && mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height;
        if (i && !this.focused) {
            this.focusedTicks = 0;
        }

        this.focused = i;
    }

    public void render() {
        this.fill(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
        this.fill(this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
        if (this.editable) {
            boolean i = this.focused && this.focusedTicks / 6 % 2 == 0;
            this.drawString(this.textRenderer, this.text + (i ? "_" : ""), this.x + 4, this.y + (this.height - 8) / 2, 14737632);
        } else {
            this.drawString(this.textRenderer, this.text, this.x + 4, this.y + (this.height - 8) / 2, 7368816);
        }

    }

    public void setMaxLength(int maximumLength) {
        this.maxLength = maximumLength;
    }

    public CompatTextFieldWidget(Screen screen, int id, TextRenderer textRenderer, int x, int y, int width, int height) {
        this(screen, textRenderer, x, y, width, height, "");
    }

    public void keyPressed(char chr, int key) {
        // TextFieldWidget assumes a screen exists for Tab focus cycling; the config
        // screen can be opened from in-game where Minecraft.screen is null.
        if (chr == '\t' && screen == null) {
            return;
        }
        if (this.editable && this.focused) {
            if (chr == 22) {
                String string = Screen.getClipboard();
                if (string == null) {
                    string = "";
                }

                int i = 32 - this.text.length();
                if (i > string.length()) {
                    i = string.length();
                }

                if (i > 0) {
                    this.text = this.text + string.substring(0, i);
                }
            }

            if (key == 14 && this.text.length() > 0) {
                this.text = this.text.substring(0, this.text.length() - 1);
            }

            if (getValidChatCharString().indexOf(chr) >= 0 && (this.text.length() < this.maxLength || this.maxLength == 0)) {
                this.text = this.text + chr;
            }

        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean inside = mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height;
        this.mouseClicked((int) mouseX, (int) mouseY, button);
        return inside;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (focused && editable && keyCode == 14 && !this.text.isEmpty()) {
            this.text = this.text.substring(0, this.text.length() - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!focused || !editable || chr == 0) {
            return false;
        }
        if (chr == 22) {
            String string = Screen.getClipboard();
            if (string == null) {
                string = "";
            }

            int i = 32 - this.text.length();
            if (i > string.length()) {
                i = string.length();
            }

            if (i > 0) {
                this.text = this.text + string.substring(0, i);
            }
            return true;
        }
        if (chr >= 32 && getValidChatCharString().indexOf(chr) >= 0 && (this.text.length() < this.maxLength || this.maxLength == 0)) {
            this.text = this.text + chr;
            return true;
        }
        return false;
    }

    private static String getValidChatCharString() {
        String string0 = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(CompatTextFieldWidget.class.getResourceAsStream("/font.txt"), "UTF-8"));
            String string3 = "";

            while((string3 = bufferedReader.readLine()) != null) {
                if (!string3.startsWith("#")) {
                    string0 = string0 + string3;
                }
            }

            bufferedReader.close();
        } catch (Exception var3) {
        }

        return string0;
    }
}
