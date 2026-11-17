//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.DoubleListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DoubleFieldBuilder extends FieldBuilder<Double, DoubleListEntry> {
    private Consumer<Double> saveConsumer = null;
    private Function<Double, Optional<String[]>> tooltipSupplier = (d) -> {
        return Optional.empty();
    };
    private final double value;
    private Double min = null;
    private Double max = null;

    public DoubleFieldBuilder(String resetButtonKey, String fieldNameKey, double value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public DoubleFieldBuilder setErrorSupplier(Function<Double, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public DoubleFieldBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public DoubleFieldBuilder setSaveConsumer(Consumer<Double> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public DoubleFieldBuilder setDefaultValue(Supplier<Double> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public DoubleFieldBuilder setDefaultValue(double defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public DoubleFieldBuilder setMin(double min) {
        this.min = min;
        return this;
    }

    public DoubleFieldBuilder setMax(double max) {
        this.max = max;
        return this;
    }

    public DoubleFieldBuilder removeMin() {
        this.min = null;
        return this;
    }

    public DoubleFieldBuilder removeMax() {
        this.max = null;
        return this;
    }

    public DoubleFieldBuilder setTooltipSupplier(Function<Double, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public DoubleFieldBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (d) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public DoubleFieldBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (d) -> {
            return tooltip;
        };
        return this;
    }

    public DoubleFieldBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (d) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull DoubleListEntry build() {
        DoubleListEntry entry = new DoubleListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null);
        if (this.min != null) {
            entry.setMinimum(this.min);
        }

        if (this.max != null) {
            entry.setMaximum(this.max);
        }

        entry.setTooltipSupplier(() -> {
            return (Optional)this.tooltipSupplier.apply(entry.getValue());
        });
        if (this.errorSupplier != null) {
            entry.setErrorSupplier(() -> {
                return (Optional)this.errorSupplier.apply(entry.getValue());
            });
        }

        return entry;
    }
}
