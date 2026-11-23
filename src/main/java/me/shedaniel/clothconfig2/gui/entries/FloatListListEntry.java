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

public class FloatListListEntry extends AbstractTextFieldListListEntry<Float, FloatListListEntry.FloatListCell, FloatListListEntry> {
    private float minimum;
    private float maximum;

    /** @deprecated */
    @Deprecated
    @Internal
    public FloatListListEntry(String fieldName, List<Float> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Float>> saveConsumer, Supplier<List<Float>> defaultValue, String resetButtonKey) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public FloatListListEntry(String fieldName, List<Float> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Float>> saveConsumer, Supplier<List<Float>> defaultValue, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public FloatListListEntry(String fieldName, List<Float> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Float>> saveConsumer, Supplier<List<Float>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, FloatListCell::new);
        this.minimum = Float.NEGATIVE_INFINITY;
        this.maximum = Float.POSITIVE_INFINITY;
    }

    public FloatListListEntry setMaximum(float maximum) {
        this.maximum = maximum;
        return this;
    }

    public FloatListListEntry setMinimum(float minimum) {
        this.minimum = minimum;
        return this;
    }

    public FloatListListEntry self() {
        return this;
    }

    public static class FloatListCell extends AbstractTextFieldListCell<Float, FloatListCell, FloatListListEntry> {
        public FloatListCell(Float value, FloatListListEntry listListEntry) {
            super(value, listListEntry);
        }

        protected @Nullable Float substituteDefault(@Nullable Float value) {
            return value == null ? 0.0F : value;
        }

        protected boolean isValidText(@NotNull String text) {
            return text.chars().allMatch((c) -> {
                return Character.isDigit(c) || c == 45 || c == 46;
            });
        }

        public Float getValue() {
            try {
                return Float.valueOf(this.widget.getText());
            } catch (NumberFormatException var2) {
                return 0.0F;
            }
        }

        public Optional<String> getError() {
            try {
                float i = Float.parseFloat(this.widget.getText());
                if (i > ((FloatListListEntry)this.listListEntry).maximum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{((FloatListListEntry)this.listListEntry).maximum}));
                }

                if (i < ((FloatListListEntry)this.listListEntry).minimum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{((FloatListListEntry)this.listListEntry).minimum}));
                }
            } catch (NumberFormatException var2) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_float"));
            }

            return Optional.empty();
        }

        public List<? extends GuiEventListener> children() {
            return super.children();
        }
    }
}
