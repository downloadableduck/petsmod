//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class NestedListListEntry<T, INNER extends AbstractConfigListEntry<T>> extends AbstractListListEntry<T, NestedListListEntry.NestedListCell<T, INNER>, NestedListListEntry<T, INNER>> {
    /** @deprecated */
    @Deprecated
    @Internal
    public NestedListListEntry(String fieldName, List<T> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<T>> saveConsumer, Supplier<List<T>> defaultValue, String resetButtonKey, boolean deleteButtonEnabled, boolean insertInFront, BiFunction<T, NestedListListEntry<T, INNER>, INNER> createNewCell) {
        super(fieldName, value, defaultExpanded, (Supplier)null, (Consumer)null, defaultValue, resetButtonKey, false, deleteButtonEnabled, insertInFront, (t, nestedListListEntry) -> {
            return new NestedListCell(t, nestedListListEntry, (AbstractConfigListEntry)createNewCell.apply(t, nestedListListEntry));
        });
    }

    public boolean isRequiresRestart() {
        return this.cells.stream().anyMatch((rec$) -> {
            return rec$.isRequiresRestart();
        });
    }

    public void setRequiresRestart(boolean requiresRestart) {
    }

    public NestedListListEntry<T, INNER> self() {
        return this;
    }

    public static class NestedListCell<T, INNER extends AbstractConfigListEntry<T>> extends AbstractListCell<T, NestedListCell<T, INNER>, NestedListListEntry<T, INNER>> {
        private final INNER nestedEntry;

        @Internal
        public NestedListCell(@Nullable T value, NestedListListEntry<T, INNER> listListEntry, INNER nestedEntry) {
            super(value, listListEntry);
            this.nestedEntry = nestedEntry;
        }

        public T getValue() {
            return this.nestedEntry.getValue();
        }

        public Optional<String> getError() {
            return this.nestedEntry.getError();
        }

        public int getCellHeight() {
            return this.nestedEntry.getItemHeight();
        }

        public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
            this.nestedEntry.setScreen(((NestedListListEntry)this.listListEntry).getScreen());
            this.nestedEntry.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        }

        public List<? extends GuiEventListener> children() {
            return Collections.singletonList(this.nestedEntry);
        }

        private boolean isRequiresRestart() {
            return this.nestedEntry.isRequiresRestart();
        }
    }
}
