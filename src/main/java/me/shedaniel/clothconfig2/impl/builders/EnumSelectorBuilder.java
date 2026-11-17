//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class EnumSelectorBuilder<T extends Enum<?>> extends FieldBuilder<T, EnumListEntry<T>> {
    private Consumer<T> saveConsumer = null;
    private Function<T, Optional<String[]>> tooltipSupplier = (e) -> {
        return Optional.empty();
    };
    private final T value;
    private final Class<T> clazz;
    private Function<Enum, String> enumNameProvider;

    public EnumSelectorBuilder(String resetButtonKey, String fieldNameKey, Class<T> clazz, T value) {
        super(resetButtonKey, fieldNameKey);
        this.enumNameProvider = EnumListEntry.DEFAULT_NAME_PROVIDER;
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(value);
        this.value = value;
        this.clazz = clazz;
    }

    public EnumSelectorBuilder<T> setErrorSupplier(Function<T, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public EnumSelectorBuilder<T> requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public EnumSelectorBuilder<T> setSaveConsumer(Consumer<T> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public EnumSelectorBuilder<T> setDefaultValue(Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public EnumSelectorBuilder<T> setDefaultValue(T defaultValue) {
        Objects.requireNonNull(defaultValue);
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public EnumSelectorBuilder<T> setTooltipSupplier(Function<T, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public EnumSelectorBuilder<T> setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (e) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public EnumSelectorBuilder<T> setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (e) -> {
            return tooltip;
        };
        return this;
    }

    public EnumSelectorBuilder<T> setTooltip(String... tooltip) {
        this.tooltipSupplier = (e) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public EnumSelectorBuilder<T> setEnumNameProvider(Function<Enum, String> enumNameProvider) {
        Objects.requireNonNull(enumNameProvider);
        this.enumNameProvider = enumNameProvider;
        return this;
    }

    public @NotNull EnumListEntry<T> build() {
        EnumListEntry<T> entry = new EnumListEntry(this.getFieldNameKey(), this.clazz, this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, this.enumNameProvider, (Supplier)null, false);
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
