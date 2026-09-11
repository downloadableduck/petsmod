package me.shedaniel.clothconfig2.compat;

public interface GuiEventListener {
    default void mouseMoved(double mouseX, double mouseY) {
    }

    default boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    default boolean mouseReleased(double mouseX, double mouseY, int button) {
        return false;
    }

    default boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }

    default boolean mouseScrolled(double amount) {
        return false;
    }

    default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default boolean charTyped(char chr, int modifiers) {
        return false;
    }

    default void changeFocus(boolean lookForwards) {
    }

    default boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }
}
