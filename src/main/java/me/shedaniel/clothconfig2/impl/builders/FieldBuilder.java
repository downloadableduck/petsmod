//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class FieldBuilder<T, A extends AbstractConfigListEntry> {
    private final @NotNull String fieldNameKey;
    private final @NotNull String resetButtonKey;
    protected boolean requireRestart = false;
    protected @Nullable Supplier<T> defaultValue = null;
    protected @Nullable Function<T, Optional<String>> errorSupplier;

    protected FieldBuilder(String resetButtonKey, String fieldNameKey) {
        this.resetButtonKey = (String)Objects.requireNonNull(resetButtonKey);
        this.fieldNameKey = (String)Objects.requireNonNull(fieldNameKey);
    }

    public final @Nullable Supplier<T> getDefaultValue() {
        return this.defaultValue;
    }

    /** @deprecated */
    @Deprecated
    public final AbstractConfigListEntry buildEntry() {
        return this.build();
    }

    public abstract @NotNull A build();

    public final @NotNull String getFieldNameKey() {
        return this.fieldNameKey;
    }

    public final @NotNull String getResetButtonKey() {
        return this.resetButtonKey;
    }

    public boolean isRequireRestart() {
        return this.requireRestart;
    }

    public void requireRestart(boolean requireRestart) {
        this.requireRestart = requireRestart;
    }
}
