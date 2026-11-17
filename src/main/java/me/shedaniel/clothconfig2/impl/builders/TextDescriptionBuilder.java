//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class TextDescriptionBuilder extends FieldBuilder<String, TextListEntry> {
    private int color = -1;
    private @Nullable Supplier<Optional<String[]>> tooltipSupplier = null;
    private final String value;

    public TextDescriptionBuilder(String resetButtonKey, String fieldNameKey, String value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }

    public void requireRestart(boolean requireRestart) {
        throw new UnsupportedOperationException();
    }

    public TextDescriptionBuilder setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public TextDescriptionBuilder setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = () -> {
            return tooltip;
        };
        return this;
    }

    public TextDescriptionBuilder setTooltip(String... tooltip) {
        this.tooltipSupplier = () -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public TextDescriptionBuilder setColor(int color) {
        this.color = color;
        return this;
    }

    public @NotNull TextListEntry build() {
        return new TextListEntry(this.getFieldNameKey(), this.value, this.color, this.tooltipSupplier);
    }
}
