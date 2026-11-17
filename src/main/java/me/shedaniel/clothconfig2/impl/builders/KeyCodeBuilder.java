//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.compat.InputConstants;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class KeyCodeBuilder extends FieldBuilder<ModifierKeyCode, KeyCodeEntry> {
    private @Nullable Consumer<ModifierKeyCode> saveConsumer = null;
    private @NotNull Function<ModifierKeyCode, Optional<String[]>> tooltipSupplier = (bool) -> {
        return Optional.empty();
    };
    private final ModifierKeyCode value;
    private boolean allowKey = true;
    private boolean allowMouse = true;
    private boolean allowModifiers = true;

    public KeyCodeBuilder(String resetButtonKey, String fieldNameKey, ModifierKeyCode value) {
        super(resetButtonKey, fieldNameKey);
        this.value = ModifierKeyCode.copyOf(value);
    }

    public KeyCodeBuilder setAllowModifiers(boolean allowModifiers) {
        this.allowModifiers = allowModifiers;
        if (!allowModifiers) {
            this.value.setModifier(Modifier.none());
        }

        return this;
    }

    public KeyCodeBuilder setAllowKey(boolean allowKey) {
        if (!this.allowMouse && !allowKey) {
            throw new IllegalArgumentException();
        } else {
            this.allowKey = allowKey;
            return this;
        }
    }

    public KeyCodeBuilder setAllowMouse(boolean allowMouse) {
        if (!this.allowKey && !allowMouse) {
            throw new IllegalArgumentException();
        } else {
            this.allowMouse = allowMouse;
            return this;
        }
    }

    public KeyCodeBuilder setErrorSupplier(@Nullable Function<InputConstants.Key, Optional<String>> errorSupplier) {
        return this.setModifierErrorSupplier((keyCode) -> {
            return (Optional)errorSupplier.apply(keyCode.getKeyCode());
        });
    }

    public KeyCodeBuilder setModifierErrorSupplier(@Nullable Function<ModifierKeyCode, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }

    public KeyCodeBuilder requireRestart() {
        this.requireRestart(true);
        return this;
    }

    public KeyCodeBuilder setSaveConsumer(Consumer<InputConstants.Key> saveConsumer) {
        return this.setModifierSaveConsumer((keyCode) -> {
            saveConsumer.accept(keyCode.getKeyCode());
        });
    }

    public KeyCodeBuilder setDefaultValue(Supplier<InputConstants.Key> defaultValue) {
        return this.setModifierDefaultValue(() -> {
            return ModifierKeyCode.of((InputConstants.Key)defaultValue.get(), Modifier.none());
        });
    }

    public KeyCodeBuilder setModifierSaveConsumer(Consumer<ModifierKeyCode> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public KeyCodeBuilder setModifierDefaultValue(Supplier<ModifierKeyCode> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public KeyCodeBuilder setDefaultValue(InputConstants.Key defaultValue) {
        return this.setDefaultValue(ModifierKeyCode.of(defaultValue, Modifier.none()));
    }

    public KeyCodeBuilder setDefaultValue(ModifierKeyCode defaultValue) {
        this.defaultValue = () -> {
            return defaultValue;
        };
        return this;
    }

    public KeyCodeBuilder setTooltipSupplier(@NotNull Function<InputConstants.Key, Optional<String[]>> tooltipSupplier) {
        return this.setModifierTooltipSupplier((keyCode) -> {
            return (Optional)tooltipSupplier.apply(keyCode.getKeyCode());
        });
    }

    public KeyCodeBuilder setModifierTooltipSupplier(@NotNull Function<ModifierKeyCode, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public KeyCodeBuilder setTooltipSupplier(@NotNull Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (bool) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public KeyCodeBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (bool) -> {
            return tooltip;
        };
        return this;
    }

    public KeyCodeBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = (bool) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public @NotNull KeyCodeEntry build() {
        KeyCodeEntry entry = new KeyCodeEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, (Supplier)null, false);
        entry.setTooltipSupplier(() -> {
            return (Optional)this.tooltipSupplier.apply(entry.getValue());
        });
        if (this.errorSupplier != null) {
            entry.setErrorSupplier(() -> {
                return (Optional)this.errorSupplier.apply(entry.getValue());
            });
        }

        entry.setAllowKey(this.allowKey);
        entry.setAllowMouse(this.allowMouse);
        entry.setAllowModifiers(this.allowModifiers);
        return entry;
    }
}
