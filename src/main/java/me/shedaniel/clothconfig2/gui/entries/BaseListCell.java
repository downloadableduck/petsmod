//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.compat.AbstractContainerEventHandler;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class BaseListCell extends AbstractContainerEventHandler {
    private Supplier<Optional<String>> errorSupplier;

    public BaseListCell() {
    }

    public final int getPreferredTextColor() {
        return this.getConfigError().isPresent() ? 16733525 : 14737632;
    }

    public final Optional<String> getConfigError() {
        return this.errorSupplier != null && ((Optional)this.errorSupplier.get()).isPresent() ? (Optional)this.errorSupplier.get() : this.getError();
    }

    public void setErrorSupplier(Supplier<Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
    }

    public abstract Optional<String> getError();

    public abstract int getCellHeight();

    public abstract void render(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9);

    public void updateSelected(boolean isSelected) {
    }
}
