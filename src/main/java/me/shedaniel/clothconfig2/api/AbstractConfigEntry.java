//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import me.shedaniel.clothconfig2.gui.widget.DynamicElementListWidget;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class AbstractConfigEntry<T> extends DynamicElementListWidget.ElementEntry<AbstractConfigEntry<T>> {
    private ClothConfigScreen screen;
    private Supplier<Optional<String>> errorSupplier;

    public AbstractConfigEntry() {
    }

    public abstract boolean isRequiresRestart();

    public abstract void setRequiresRestart(boolean var1);

    public abstract String getFieldName();

    public abstract T getValue();

    public final Optional<String> getConfigError() {
        return this.errorSupplier != null && ((Optional)this.errorSupplier.get()).isPresent() ? (Optional)this.errorSupplier.get() : this.getError();
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
        return this.screen.listWidget;
    }

    public final ClothConfigScreen getScreen() {
        return this.screen;
    }

    public void updateSelected(boolean isSelected) {
    }

    /** @deprecated */
    @Deprecated
    public final void setScreen(ClothConfigScreen screen) {
        this.screen = screen;
    }

    public abstract void save();

    public int getItemHeight() {
        return 24;
    }
}
