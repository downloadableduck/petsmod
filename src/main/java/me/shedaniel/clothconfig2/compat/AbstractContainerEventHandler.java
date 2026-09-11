package me.shedaniel.clothconfig2.compat;

import me.shedaniel.clothconfig2.ParentElement;
import net.minecraft.client.gui.GuiElement;
import me.shedaniel.clothconfig2.compat.GuiEventListener;

public abstract class AbstractContainerEventHandler extends GuiElement implements ParentElement {
    private GuiEventListener focused;
    private boolean dragging;

    public boolean isDragging() {
        return this.dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public GuiEventListener getFocused() {
        return this.focused;
    }

    public void setFocused(GuiEventListener focused) {
        this.focused = focused;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return this.getFocused() != null && this.isDragging() && button == 0 ? this.getFocused().mouseDragged(mouseX, mouseY, button, deltaX, deltaY) : false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return this.getFocused() != null && this.getFocused().keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return this.getFocused() != null && this.getFocused().keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return this.getFocused() != null && this.getFocused().charTyped(chr, modifiers);
    }

    @Override
    public void changeFocus(boolean lookForwards) {
    }
}
