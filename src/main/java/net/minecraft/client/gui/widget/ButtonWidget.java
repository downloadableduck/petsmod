package net.minecraft.client.gui.widget;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;

import java.util.function.Consumer;

/**
 * Modern button widget API implemented on top of BTA's ButtonElement.
 */
public class ButtonWidget extends net.minecraft.client.gui.ButtonElement implements GuiEventListener {
    public int x;
    public int y;
    public boolean active = true;
    public String text;

    protected Consumer<ButtonWidget> pressAction;

    public ButtonWidget(int id, int x, int y, int width, int height, String text) {
        super(id, x, y, width, height, text);
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public ButtonWidget(int x, int y, int width, int height, String text, Consumer<ButtonWidget> pressAction) {
        this(0, x, y, width, height, text);
        this.pressAction = pressAction;
    }

    protected void sync() {
        this.xPosition = this.x;
        this.yPosition = this.y;
        this.displayString = this.text;
        this.enabled = this.active;
        this.visible = true;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.sync();
        super.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(Minecraft mc, int mouseX, int mouseY) {
        this.sync();
        return super.mouseClicked(mc, mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.sync();
        super.mouseReleased(mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.sync();
        if (button == 0 && this.active && this.isMouseOver(mouseX, mouseY)) {
            this.onPress();
            return true;
        }
        return false;
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.render(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    public void render(Minecraft mc, int mouseX, int mouseY) {
        this.sync();
        this.drawButton(mc, mouseX, mouseY);
    }

    public void drawTexture(int x, int y, int u, int v, int width, int height) {
        this.drawTexturedModalRect(x, y, u, v, width, height);
    }

    public void fillGradient(int x1, int y1, int x2, int y2, int startColor, int endColor) {
        this.drawGradientRect(x1, y1, x2, y2, startColor, endColor);
    }

    public void onPress() {
        if (this.pressAction != null) {
            this.pressAction.accept(this);
        }
    }

    public int getYImage(boolean hovered) {
        if (!this.active) {
            return 0;
        }
        return hovered ? 2 : 1;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width)
                && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.xPosition = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.yPosition = y;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
