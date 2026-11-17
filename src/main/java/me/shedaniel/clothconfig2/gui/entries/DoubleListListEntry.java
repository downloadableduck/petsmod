//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoubleListListEntry extends AbstractTextFieldListListEntry<Double, DoubleListListEntry.DoubleListCell, DoubleListListEntry> {
    private double minimum;
    private double maximum;

    /** @deprecated */
    @Deprecated
    @Internal
    public DoubleListListEntry(String fieldName, List<Double> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Double>> saveConsumer, Supplier<List<Double>> defaultValue, String resetButtonKey) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public DoubleListListEntry(String fieldName, List<Double> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Double>> saveConsumer, Supplier<List<Double>> defaultValue, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public DoubleListListEntry(String fieldName, List<Double> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Double>> saveConsumer, Supplier<List<Double>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, DoubleListCell::new);
        this.minimum = Double.NEGATIVE_INFINITY;
        this.maximum = Double.POSITIVE_INFINITY;
    }

    public DoubleListListEntry setMaximum(Double maximum) {
        this.maximum = maximum;
        return this;
    }

    public DoubleListListEntry setMinimum(Double minimum) {
        this.minimum = minimum;
        return this;
    }

    public DoubleListListEntry self() {
        return this;
    }

    public Optional<GuiEventListener> hoveredElement(double d, double e) {
        return super.hoveredElement(d, e);
    }

    public static class DoubleListCell extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<Double, DoubleListCell, DoubleListListEntry> {
        public DoubleListCell(Double value, DoubleListListEntry listListEntry) {
            super(value, listListEntry);
        }

        protected @Nullable Double substituteDefault(@Nullable Double value) {
            return value == null ? 0.0 : value;
        }

        protected boolean isValidText(@NotNull String text) {
            return text.chars().allMatch((c) -> {
                return Character.isDigit(c) || c == 45 || c == 46;
            });
        }

        public Double getValue() {
            try {
                return Double.valueOf(this.widget.getText());
            } catch (NumberFormatException var2) {
                return 0.0;
            }
        }

        public Optional<String> getError() {
            try {
                double i = Double.parseDouble(this.widget.getText());
                if (i > ((DoubleListListEntry)this.listListEntry).maximum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{((DoubleListListEntry)this.listListEntry).maximum}));
                }

                if (i < ((DoubleListListEntry)this.listListEntry).minimum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{((DoubleListListEntry)this.listListEntry).minimum}));
                }
            } catch (NumberFormatException var3) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_double"));
            }

            return Optional.empty();
        }

        public List<? extends GuiEventListener> children() {
            return super.children();
        }
    }
}
