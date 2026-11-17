//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.IntegerSliderEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class IntSliderBuilder extends FieldBuilder<Integer, IntegerSliderEntry> {
    private Consumer<Integer> saveConsumer = null;
    private Function<Integer, Optional<String[]>> tooltipSupplier = (i) -> {
        return Optional.empty();
    };
    private final int value;
    private int max;
    private int min;
    private Function<Integer, String> textGetter = null;

    public IntSliderBuilder(String resetButtonKey, String fieldNameKey, int value, int min, int max) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
        this.max = max;
        this.min = min;
    }

    public IntSliderBuilder setErrorSupplier(Function<Integer, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public IntSliderBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public IntSliderBuilder setTextGetter(Function<Integer, String> textGetter) {
        this.textGetter = textGetter;
        return this;
    }

    public IntSliderBuilder setSaveConsumer(Consumer<Integer> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public IntSliderBuilder setDefaultValue(Supplier<Integer> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntSliderBuilder setDefaultValue(int defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public IntSliderBuilder setTooltipSupplier(Function<Integer, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public IntSliderBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (i) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public IntSliderBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (i) -> {
            return tooltip;
        };
        return this;
    }

    public IntSliderBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (i) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public IntSliderBuilder setMax(int max) {
        this.max = max;
        return this;
    }

    public IntSliderBuilder setMin(int min) {
        this.min = min;
        return this;
    }

    public @NotNull IntegerSliderEntry build() {
        IntegerSliderEntry entry = new IntegerSliderEntry(this.getFieldNameKey(), this.min, this.max, this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null, false);
        if (this.textGetter != null) {
            entry.setTextGetter(this.textGetter);
        }

        entry.setTooltipSupplier(() -> {
            return this.tooltipSupplier.apply(entry.getValue());
        });
        if (this.errorSupplier != null) {
            entry.setErrorSupplier(() -> {
                return this.errorSupplier.apply(entry.getValue());
            });
        }

        return entry;
    }
}
