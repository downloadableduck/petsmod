//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.IntegerListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class IntFieldBuilder extends FieldBuilder<Integer, IntegerListEntry> {
    private Consumer<Integer> saveConsumer = null;
    private Function<Integer, Optional<String[]>> tooltipSupplier = (i) -> {
        return Optional.empty();
    };
    private final int value;
    private Integer min = null;
    private Integer max = null;

    public IntFieldBuilder(String resetButtonKey, String fieldNameKey, int value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public IntFieldBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public IntFieldBuilder setErrorSupplier(Function<Integer, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public IntFieldBuilder setSaveConsumer(Consumer<Integer> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public IntFieldBuilder setDefaultValue(Supplier<Integer> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntFieldBuilder setDefaultValue(int defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public IntFieldBuilder setTooltipSupplier(Function<Integer, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public IntFieldBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (i) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public IntFieldBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (i) -> {
            return tooltip;
        };
        return this;
    }

    public IntFieldBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (i) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public IntFieldBuilder setMin(int min) {
        this.min = min;
        return this;
    }

    public IntFieldBuilder setMax(int max) {
        this.max = max;
        return this;
    }

    public IntFieldBuilder removeMin() {
        this.min = null;
        return this;
    }

    public IntFieldBuilder removeMax() {
        this.max = null;
        return this;
    }

    public @NotNull IntegerListEntry build() {
        IntegerListEntry entry = new IntegerListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null);
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
