//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BooleanToggleBuilder extends FieldBuilder<Boolean, BooleanListEntry> {
    private @Nullable Consumer<Boolean> saveConsumer = null;
    private @NotNull Function<Boolean, Optional<String[]>> tooltipSupplier = (bool) -> {
        return Optional.empty();
    };
    private final boolean value;
    private @Nullable Function<Boolean, String> yesNoTextSupplier = null;

    public BooleanToggleBuilder(String resetButtonKey, String fieldNameKey, boolean value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public BooleanToggleBuilder setErrorSupplier(@Nullable Function<Boolean, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public BooleanToggleBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public BooleanToggleBuilder setSaveConsumer(Consumer<Boolean> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public BooleanToggleBuilder setDefaultValue(Supplier<Boolean> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public BooleanToggleBuilder setDefaultValue(boolean defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public BooleanToggleBuilder setTooltipSupplier(@NotNull Function<Boolean, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public BooleanToggleBuilder setTooltipSupplier(@NotNull Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (bool) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public BooleanToggleBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (bool) -> {
            return tooltip;
        };
        return this;
    }

    public BooleanToggleBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (bool) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @Nullable Function<Boolean, String> getYesNoTextSupplier() {
        return this.yesNoTextSupplier;
    }

    public BooleanToggleBuilder setYesNoTextSupplier(@Nullable Function<Boolean, String> yesNoTextSupplier) {
        this.yesNoTextSupplier = yesNoTextSupplier;
        return this;
    }

    public @NotNull BooleanListEntry build() {
        BooleanListEntry entry = new BooleanListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null) {
            public String getYesNoText(boolean bool) {
                return BooleanToggleBuilder.this.yesNoTextSupplier == null ? super.getYesNoText(bool) : (String)BooleanToggleBuilder.this.yesNoTextSupplier.apply(bool);
            }
        };
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
