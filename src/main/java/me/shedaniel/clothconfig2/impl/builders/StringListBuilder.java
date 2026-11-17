//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StringListBuilder extends FieldBuilder<List<String>, StringListListEntry> {
    private Function<String, Optional<String>> cellErrorSupplier;
    private Consumer<List<String>> saveConsumer = null;
    private Function<List<String>, Optional<String[]>> tooltipSupplier = (list) -> {
        return Optional.empty();
    };
    private final List<String> value;
    private boolean expanded = false;
    private Function<StringListListEntry, StringListListEntry.StringListCell> createNewInstance;
    private String addTooltip = I18n.getTranslation("text.cloth-config.list.add");
    private String removeTooltip = I18n.getTranslation("text.cloth-config.list.remove");
    private boolean deleteButtonEnabled = true;
    private boolean insertInFront = true;

    public StringListBuilder(String resetButtonKey, String fieldNameKey, List<String> value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public Function<String, Optional<String>> getCellErrorSupplier() {
        return this.cellErrorSupplier;
    }

    public StringListBuilder setCellErrorSupplier(Function<String, Optional<String>> cellErrorSupplier) {
        this.cellErrorSupplier = cellErrorSupplier;
        return this;
    }

    public StringListBuilder setErrorSupplier(Function<List<String>, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public StringListBuilder setDeleteButtonEnabled(boolean deleteButtonEnabled) {
        this.deleteButtonEnabled = deleteButtonEnabled;
        return this;
    }

    public StringListBuilder setInsertInFront(boolean insertInFront) {
        this.insertInFront = insertInFront;
        return this;
    }

    public StringListBuilder setAddButtonTooltip(String addTooltip) {
        this.addTooltip = addTooltip;
        return this;
    }

    public StringListBuilder setRemoveButtonTooltip(String removeTooltip) {
        this.removeTooltip = removeTooltip;
        return this;
    }

    public StringListBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public StringListBuilder setCreateNewInstance(Function<StringListListEntry, StringListListEntry.StringListCell> createNewInstance) {
        this.createNewInstance = createNewInstance;
        return this;
    }

    public StringListBuilder setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    /** @deprecated */
    @Deprecated
    @ScheduledForRemoval
    public StringListBuilder setExpended(boolean expanded) {
        return this.setExpanded(expanded);
    }

    public StringListBuilder setSaveConsumer(Consumer<List<String>> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public StringListBuilder setDefaultValue(Supplier<List<String>> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public StringListBuilder setDefaultValue(List<String> defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public StringListBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (list) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public StringListBuilder setTooltipSupplier(Function<List<String>, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public StringListBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (list) -> {
            return tooltip;
        };
        return this;
    }

    public StringListBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (list) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull StringListListEntry build() {
        StringListListEntry entry = new StringListListEntry(this.getFieldNameKey(), this.value, this.expanded, (Supplier)null, this.saveConsumer, this.defaultValue, this.getResetButtonKey(), this.isRequireRestart(), this.deleteButtonEnabled, this.insertInFront);
        if (this.createNewInstance != null) {
            entry.setCreateNewInstance(this.createNewInstance);
        }

        entry.setCellErrorSupplier(this.cellErrorSupplier);
        entry.setTooltipSupplier(() -> {
            return (Optional)this.tooltipSupplier.apply(entry.getValue());
        });
        entry.setAddTooltip(this.addTooltip);
        entry.setRemoveTooltip(this.removeTooltip);
        if (this.errorSupplier != null) {
            entry.setErrorSupplier(() -> {
                return (Optional)this.errorSupplier.apply(entry.getValue());
            });
        }

        return entry;
    }
}
