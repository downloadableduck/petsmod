//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.IntegerListListEntry;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class IntListBuilder extends FieldBuilder<List<Integer>, IntegerListListEntry> {
    protected Function<Integer, Optional<String>> cellErrorSupplier;
    private Consumer<List<Integer>> saveConsumer = null;
    private Function<List<Integer>, Optional<String[]>> tooltipSupplier = (list) -> {
        return Optional.empty();
    };
    private final List<Integer> value;
    private boolean expanded = false;
    private Integer min = null;
    private Integer max = null;
    private Function<IntegerListListEntry, IntegerListListEntry.IntegerListCell> createNewInstance;
    private String addTooltip = I18n.getTranslation("text.cloth-config.list.add");
    private String removeTooltip = I18n.getTranslation("text.cloth-config.list.remove");
    private boolean deleteButtonEnabled = true;
    private boolean insertInFront = true;

    public IntListBuilder(String resetButtonKey, String fieldNameKey, List<Integer> value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public Function<Integer, Optional<String>> getCellErrorSupplier() {
        return this.cellErrorSupplier;
    }

    public IntListBuilder setCellErrorSupplier(Function<Integer, Optional<String>> cellErrorSupplier) {
        this.cellErrorSupplier = cellErrorSupplier;
        return this;
    }

    public IntListBuilder setErrorSupplier(Function<List<Integer>, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public IntListBuilder setDeleteButtonEnabled(boolean deleteButtonEnabled) {
        this.deleteButtonEnabled = deleteButtonEnabled;
        return this;
    }

    public IntListBuilder setInsertInFront(boolean insertInFront) {
        this.insertInFront = insertInFront;
        return this;
    }

    public IntListBuilder setAddButtonTooltip(String addTooltip) {
        this.addTooltip = addTooltip;
        return this;
    }

    public IntListBuilder setRemoveButtonTooltip(String removeTooltip) {
        this.removeTooltip = removeTooltip;
        return this;
    }

    public IntListBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public IntListBuilder setCreateNewInstance(Function<IntegerListListEntry, IntegerListListEntry.IntegerListCell> createNewInstance) {
        this.createNewInstance = createNewInstance;
        return this;
    }

    public IntListBuilder setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    /** @deprecated */
    @Deprecated
    @ScheduledForRemoval
    public IntListBuilder setExpended(boolean expanded) {
        return this.setExpanded(expanded);
    }

    public IntListBuilder setSaveConsumer(Consumer<List<Integer>> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public IntListBuilder setDefaultValue(Supplier<List<Integer>> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntListBuilder setMin(int min) {
        this.min = min;
        return this;
    }

    public IntListBuilder setMax(int max) {
        this.max = max;
        return this;
    }

    public IntListBuilder removeMin() {
        this.min = null;
        return this;
    }

    public IntListBuilder removeMax() {
        this.max = null;
        return this;
    }

    public IntListBuilder setDefaultValue(List<Integer> defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public IntListBuilder setTooltipSupplier(Function<List<Integer>, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public IntListBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (list) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public IntListBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (list) -> {
            return tooltip;
        };
        return this;
    }

    public IntListBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (list) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull IntegerListListEntry build() {
        IntegerListListEntry entry = new IntegerListListEntry(this.getFieldNameKey(), this.value, this.expanded, (Supplier)null, this.saveConsumer, this.defaultValue, this.getResetButtonKey(), this.isRequireRestart(), this.deleteButtonEnabled, this.insertInFront);
        if (this.min != null) {
            entry.setMinimum(this.min);
        }

        if (this.max != null) {
            entry.setMaximum(this.max);
        }

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
