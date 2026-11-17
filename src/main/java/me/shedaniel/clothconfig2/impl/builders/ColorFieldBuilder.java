//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ColorFieldBuilder extends FieldBuilder<String, ColorEntry> {
    private Consumer<Integer> saveConsumer = null;
    private Function<Integer, Optional<String>> errorSupplier;
    private Function<Integer, Optional<String[]>> tooltipSupplier = (str) -> {
        return Optional.empty();
    };
    private final int value;
    private Supplier<Integer> defaultValue;
    private boolean alpha = false;

    public ColorFieldBuilder(String resetButtonKey, String fieldNameKey, int value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public ColorFieldBuilder setErrorSupplier(Function<Integer, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public ColorFieldBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public ColorFieldBuilder setSaveConsumer(Consumer<Integer> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public ColorFieldBuilder setDefaultValue(Supplier<Integer> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ColorFieldBuilder setAlphaMode(boolean withAlpha) {
        this.alpha = withAlpha;
        return this;
    }

    public ColorFieldBuilder setDefaultValue(int defaultValue) {
        this.defaultValue = () -> {
            return (Integer)Objects.requireNonNull(defaultValue);
        };
        return this;
    }

    public ColorFieldBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (str) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public ColorFieldBuilder setTooltipSupplier(Function<Integer, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public ColorFieldBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (str) -> {
            return tooltip;
        };
        return this;
    }

    public ColorFieldBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (str) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull ColorEntry build() {
        ColorEntry entry = new ColorEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null, false);
        if (this.alpha) {
            entry.withAlpha();
        } else {
            entry.withoutAlpha();
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
