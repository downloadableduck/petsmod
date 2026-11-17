//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.FloatListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FloatFieldBuilder extends FieldBuilder<Float, FloatListEntry> {
    private Consumer<Float> saveConsumer = null;
    private Function<Float, Optional<String[]>> tooltipSupplier = (f) -> {
        return Optional.empty();
    };
    private final float value;
    private Float min = null;
    private Float max = null;

    public FloatFieldBuilder(String resetButtonKey, String fieldNameKey, float value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public FloatFieldBuilder setErrorSupplier(Function<Float, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public FloatFieldBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public FloatFieldBuilder setSaveConsumer(Consumer<Float> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public FloatFieldBuilder setDefaultValue(Supplier<Float> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public FloatFieldBuilder setDefaultValue(float defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public FloatFieldBuilder setTooltipSupplier(Function<Float, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public FloatFieldBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (f) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public FloatFieldBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (f) -> {
            return tooltip;
        };
        return this;
    }

    public FloatFieldBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (f) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public FloatFieldBuilder setMin(float min) {
        this.min = min;
        return this;
    }

    public FloatFieldBuilder setMax(float max) {
        this.max = max;
        return this;
    }

    public FloatFieldBuilder removeMin() {
        this.min = null;
        return this;
    }

    public FloatFieldBuilder removeMax() {
        this.max = null;
        return this;
    }

    public @NotNull FloatListEntry build() {
        FloatListEntry entry = new FloatListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null);
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
