package me.shedaniel.forge.clothconfig2.api;

import me.shedaniel.forge.clothconfig2.gui.ClothConfigScreen;
import me.shedaniel.forge.clothconfig2.gui.widget.DynamicElementListWidget;
import net.minecraft.client.gui.IGuiEventListener;


import java.util.Optional;
import java.util.function.Supplier;


public abstract class AbstractConfigEntry<T> extends DynamicElementListWidget.ElementEntry<AbstractConfigEntry<T>> {
    private ClothConfigScreen screen;
    private Supplier<Optional<String>> errorSupplier;
    
    public abstract boolean isRequiresRestart();
    
    public abstract void setRequiresRestart(boolean requiresRestart);
    
    public abstract String getFieldName();
    
    public abstract T getValue();
    
    public final Optional<String> getConfigError() {
        if (errorSupplier != null && errorSupplier.get().isPresent())
            return errorSupplier.get();
        return getError();
    }
    
    public void lateRender(int mouseX, int mouseY, float delta) {}
    
    public void setErrorSupplier(Supplier<Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
    }
    
    public Optional<String> getError() {
        return Optional.empty();
    }
    
    public abstract Optional<T> getDefaultValue();
    
    public final ClothConfigScreen.ListWidget getParent() {
        return screen.listWidget;
    }
    
    public final ClothConfigScreen getScreen() {
        return screen;
    }
    
    public void updateSelected(boolean isSelected) {}
    
    @Deprecated
    public final void setScreen(ClothConfigScreen screen) {
        this.screen = screen;
    }
    
    public abstract void save();
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (IGuiEventListener child : getEventListeners()) {
            if (child.mouseClicked(mouseX, mouseY, button)) {
                setFocused(child);
                return true;
            }
        }
        setFocused(null);
        return false;
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        IGuiEventListener focused = getFocused();
        if (focused != null)
            return focused.mouseReleased(mouseX, mouseY, button);
        return false;
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        IGuiEventListener focused = getFocused();
        if (focused != null)
            return focused.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        return false;
    }
    
    @Override
    public boolean mouseScrolled(double amount) {
        IGuiEventListener focused = getFocused();
        if (focused != null)
            return focused.mouseScrolled(amount);
        return false;
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        IGuiEventListener focused = getFocused();
        if (focused != null)
            return focused.keyPressed(keyCode, scanCode, modifiers);
        return false;
    }
    
    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        IGuiEventListener focused = getFocused();
        if (focused != null)
            return focused.keyReleased(keyCode, scanCode, modifiers);
        return false;
    }
    
    @Override
    public boolean charTyped(char chr, int modifiers) {
        IGuiEventListener focused = getFocused();
        if (focused != null)
            return focused.charTyped(chr, modifiers);
        return false;
    }
    
    @Override
    public int getItemHeight() {
        return 24;
    }
}
