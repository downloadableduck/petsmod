//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.LongListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LongFieldBuilder extends FieldBuilder<Long, LongListEntry> {
    private Consumer<Long> saveConsumer = null;
    private Function<Long, Optional<String[]>> tooltipSupplier = (l) -> {
        return Optional.empty();
    };
    private final long value;
    private Long min = null;
    private Long max = null;

    public LongFieldBuilder(String resetButtonKey, String fieldNameKey, long value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public LongFieldBuilder setErrorSupplier(Function<Long, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public LongFieldBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public LongFieldBuilder setSaveConsumer(Consumer<Long> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public LongFieldBuilder setDefaultValue(Supplier<Long> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public LongFieldBuilder setDefaultValue(long defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public LongFieldBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (l) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public LongFieldBuilder setTooltipSupplier(Function<Long, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public LongFieldBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (l) -> {
            return tooltip;
        };
        return this;
    }

    public LongFieldBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (l) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public LongFieldBuilder setMin(long min) {
        this.min = min;
        return this;
    }

    public LongFieldBuilder setMax(long max) {
        this.max = max;
        return this;
    }

    public LongFieldBuilder removeMin() {
        this.min = null;
        return this;
    }

    public LongFieldBuilder removeMax() {
        this.max = null;
        return this;
    }

    public @NotNull LongListEntry build() {
        LongListEntry entry = new LongListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null);
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
