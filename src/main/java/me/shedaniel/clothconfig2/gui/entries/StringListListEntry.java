//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StringListListEntry extends AbstractTextFieldListListEntry<String, StringListListEntry.StringListCell, StringListListEntry> {
    /** @deprecated */
    @Deprecated
    @Internal
    public StringListListEntry(String fieldName, List<String> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<String>> saveConsumer, Supplier<List<String>> defaultValue, String resetButtonKey) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public StringListListEntry(String fieldName, List<String> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<String>> saveConsumer, Supplier<List<String>> defaultValue, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public StringListListEntry(String fieldName, List<String> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<String>> saveConsumer, Supplier<List<String>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, StringListCell::new);
    }

    public StringListListEntry self() {
        return this;
    }

    public static class StringListCell extends AbstractTextFieldListCell<String, StringListCell, StringListListEntry> {
        public StringListCell(String value, StringListListEntry listListEntry) {
            super(value, listListEntry);
        }

        protected @Nullable String substituteDefault(@Nullable String value) {
            return value == null ? "" : value;
        }

        protected boolean isValidText(@NotNull String text) {
            return true;
        }

        public String getValue() {
            return this.widget.getText();
        }

        public Optional<String> getError() {
            return Optional.empty();
        }

        public List<? extends GuiEventListener> children() {
            return super.children();
        }
    }
}
