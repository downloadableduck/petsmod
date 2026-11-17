//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.LongSliderEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LongSliderBuilder extends FieldBuilder<Long, LongSliderEntry> {
    private Consumer<Long> saveConsumer = null;
    private Function<Long, Optional<String[]>> tooltipSupplier = (l) -> {
        return Optional.empty();
    };
    private final long value;
    private final long max;
    private final long min;
    private Function<Long, String> textGetter = null;

    public LongSliderBuilder(String resetButtonKey, String fieldNameKey, long value, long min, long max) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
        this.max = max;
        this.min = min;
    }

    public LongSliderBuilder setErrorSupplier(Function<Long, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public LongSliderBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public LongSliderBuilder setTextGetter(Function<Long, String> textGetter) {
        this.textGetter = textGetter;
        return this;
    }

    public LongSliderBuilder setSaveConsumer(Consumer<Long> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public LongSliderBuilder setDefaultValue(Supplier<Long> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public LongSliderBuilder setDefaultValue(long defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public LongSliderBuilder setTooltipSupplier(Function<Long, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public LongSliderBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (i) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public LongSliderBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (i) -> {
            return tooltip;
        };
        return this;
    }

    public LongSliderBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (i) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull LongSliderEntry build() {
        LongSliderEntry entry = new LongSliderEntry(this.getFieldNameKey(), this.min, this.max, this.value, this.saveConsumer, this.getResetButtonKey(), this.defaultValue, (Supplier)null, this.isRequireRestart());
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
