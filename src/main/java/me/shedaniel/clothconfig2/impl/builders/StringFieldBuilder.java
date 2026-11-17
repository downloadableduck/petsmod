//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.StringListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StringFieldBuilder extends FieldBuilder<String, StringListEntry> {
    private Consumer<String> saveConsumer = null;
    private Function<String, Optional<String[]>> tooltipSupplier = (str) -> {
        return Optional.empty();
    };
    private final String value;

    public StringFieldBuilder(String resetButtonKey, String fieldNameKey, String value) {
        super(resetButtonKey, fieldNameKey);
        Objects.requireNonNull(value);
        this.value = value;
    }

    public StringFieldBuilder setErrorSupplier(Function<String, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public StringFieldBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public StringFieldBuilder setSaveConsumer(Consumer<String> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public StringFieldBuilder setDefaultValue(Supplier<String> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public StringFieldBuilder setDefaultValue(String defaultValue) {
        this.defaultValue = () -> {
            return (String)Objects.requireNonNull(defaultValue);
        };
        return this;
    }

    public StringFieldBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (str) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public StringFieldBuilder setTooltipSupplier(Function<String, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public StringFieldBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (str) -> {
            return tooltip;
        };
        return this;
    }

    public StringFieldBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (str) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull StringListEntry build() {
        StringListEntry entry = new StringListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null);
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
