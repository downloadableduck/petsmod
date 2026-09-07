package me.shedaniel.forge.clothconfig2.api;

import me.shedaniel.forge.clothconfig2.gui.ClothConfigScreen;
import me.shedaniel.forge.clothconfig2.gui.widget.DynamicElementListWidget;


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

    public void lateRender(int mouseX, int mouseY, float delta) {
    }

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

    @Deprecated
    public final void setScreen(ClothConfigScreen screen) {
        this.screen = screen;
    }

    public void updateSelected(boolean isSelected) {
    }

    public abstract void save();

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        return super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
        super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public int getItemHeight() {
        return 24;
    }
}
