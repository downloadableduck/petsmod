//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.FloatListListEntry;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FloatListBuilder extends FieldBuilder<List<Float>, FloatListListEntry> {
    protected Function<Float, Optional<String>> cellErrorSupplier;
    private Consumer<List<Float>> saveConsumer = null;
    private Function<List<Float>, Optional<String[]>> tooltipSupplier = (list) -> {
        return Optional.empty();
    };
    private final List<Float> value;
    private boolean expanded = false;
    private Float min = null;
    private Float max = null;
    private Function<FloatListListEntry, FloatListListEntry.FloatListCell> createNewInstance;
    private String addTooltip = I18n.getTranslation("text.cloth-config.list.add");
    private String removeTooltip = I18n.getTranslation("text.cloth-config.list.remove");
    private boolean deleteButtonEnabled = true;
    private boolean insertInFront = true;

    public FloatListBuilder(String resetButtonKey, String fieldNameKey, List<Float> value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public Function<Float, Optional<String>> getCellErrorSupplier() {
        return this.cellErrorSupplier;
    }

    public FloatListBuilder setCellErrorSupplier(Function<Float, Optional<String>> cellErrorSupplier) {
        this.cellErrorSupplier = cellErrorSupplier;
        return this;
    }

    public FloatListBuilder setDeleteButtonEnabled(boolean deleteButtonEnabled) {
        this.deleteButtonEnabled = deleteButtonEnabled;
        return this;
    }

    public FloatListBuilder setErrorSupplier(Function<List<Float>, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public FloatListBuilder setInsertInFront(boolean insertInFront) {
        this.insertInFront = insertInFront;
        return this;
    }

    public FloatListBuilder setAddButtonTooltip(String addTooltip) {
        this.addTooltip = addTooltip;
        return this;
    }

    public FloatListBuilder setRemoveButtonTooltip(String removeTooltip) {
        this.removeTooltip = removeTooltip;
        return this;
    }

    public FloatListBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public FloatListBuilder setCreateNewInstance(Function<FloatListListEntry, FloatListListEntry.FloatListCell> createNewInstance) {
        this.createNewInstance = createNewInstance;
        return this;
    }

    public FloatListBuilder setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    /** @deprecated */
    @Deprecated
    @ScheduledForRemoval
    public FloatListBuilder setExpended(boolean expanded) {
        return this.setExpanded(expanded);
    }

    public FloatListBuilder setSaveConsumer(Consumer<List<Float>> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public FloatListBuilder setDefaultValue(Supplier<List<Float>> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public FloatListBuilder setMin(float min) {
        this.min = min;
        return this;
    }

    public FloatListBuilder setMax(float max) {
        this.max = max;
        return this;
    }

    public FloatListBuilder removeMin() {
        this.min = null;
        return this;
    }

    public FloatListBuilder removeMax() {
        this.max = null;
        return this;
    }

    public FloatListBuilder setDefaultValue(List<Float> defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public FloatListBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (list) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public FloatListBuilder setTooltipSupplier(Function<List<Float>, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public FloatListBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (list) -> {
            return tooltip;
        };
        return this;
    }

    public FloatListBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (list) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull FloatListListEntry build() {
        FloatListListEntry entry = new FloatListListEntry(this.getFieldNameKey(), this.value, this.expanded, (Supplier)null, this.saveConsumer, this.defaultValue, this.getResetButtonKey(), this.isRequireRestart(), this.deleteButtonEnabled, this.insertInFront);
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
