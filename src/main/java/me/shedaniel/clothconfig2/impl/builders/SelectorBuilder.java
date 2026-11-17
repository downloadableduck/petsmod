//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SelectorBuilder<T> extends FieldBuilder<T, SelectionListEntry<T>> {
    private Consumer<T> saveConsumer = null;
    private Function<T, Optional<String[]>> tooltipSupplier = (e) -> {
        return Optional.empty();
    };
    private final T value;
    private final T[] valuesArray;
    private Function<T, String> nameProvider = null;

    public SelectorBuilder(String resetButtonKey, String fieldNameKey, T[] valuesArray, T value) {
        super(resetButtonKey, fieldNameKey);
        Objects.requireNonNull(value);
        this.valuesArray = valuesArray;
        this.value = value;
    }

    public SelectorBuilder<T> setErrorSupplier(Function<T, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public SelectorBuilder<T> requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public SelectorBuilder<T> setSaveConsumer(Consumer<T> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public SelectorBuilder<T> setDefaultValue(Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public SelectorBuilder<T> setDefaultValue(T defaultValue) {
        Objects.requireNonNull(defaultValue);
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public SelectorBuilder<T> setTooltipSupplier(Function<T, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public SelectorBuilder<T> setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (e) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public SelectorBuilder<T> setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (e) -> {
            return tooltip;
        };
        return this;
    }

    public SelectorBuilder<T> setTooltip(String... tooltip) {
        this.tooltipSupplier = (e) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public SelectorBuilder<T> setNameProvider(Function<T, String> enumNameProvider) {
        this.nameProvider = enumNameProvider;
        return this;
    }

    public @NotNull SelectionListEntry<T> build() {
        SelectionListEntry<T> entry = new SelectionListEntry(this.getFieldNameKey(), this.valuesArray, this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, this.nameProvider, (Supplier)null);
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
