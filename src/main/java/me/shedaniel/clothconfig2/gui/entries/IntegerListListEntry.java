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

public class IntegerListListEntry extends AbstractTextFieldListListEntry<Integer, IntegerListListEntry.IntegerListCell, IntegerListListEntry> {
    private int minimum;
    private int maximum;

    /** @deprecated */
    @Deprecated
    @Internal
    public IntegerListListEntry(String fieldName, List<Integer> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Integer>> saveConsumer, Supplier<List<Integer>> defaultValue, String resetButtonKey) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public IntegerListListEntry(String fieldName, List<Integer> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Integer>> saveConsumer, Supplier<List<Integer>> defaultValue, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public IntegerListListEntry(String fieldName, List<Integer> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Integer>> saveConsumer, Supplier<List<Integer>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, IntegerListCell::new);
        this.minimum = Integer.MIN_VALUE;
        this.maximum = Integer.MAX_VALUE;
    }

    public IntegerListListEntry setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }

    public IntegerListListEntry setMinimum(int minimum) {
        this.minimum = minimum;
        return this;
    }

    public IntegerListListEntry self() {
        return this;
    }

    public static class IntegerListCell extends AbstractTextFieldListCell<Integer, IntegerListCell, IntegerListListEntry> {
        public IntegerListCell(Integer value, IntegerListListEntry listListEntry) {
            super(value, listListEntry);
        }

        protected @Nullable Integer substituteDefault(@Nullable Integer value) {
            return value == null ? 0 : value;
        }

        protected boolean isValidText(@NotNull String text) {
            return text.chars().allMatch((c) -> {
                return Character.isDigit(c) || c == 45;
            });
        }

        public Integer getValue() {
            try {
                return Integer.valueOf(this.widget.getText());
            } catch (NumberFormatException var2) {
                return 0;
            }
        }

        public Optional<String> getError() {
            try {
                int i = Integer.parseInt(this.widget.getText());
                if (i > ((IntegerListListEntry)this.listListEntry).maximum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{((IntegerListListEntry)this.listListEntry).maximum}));
                }

                if (i < ((IntegerListListEntry)this.listListEntry).minimum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{((IntegerListListEntry)this.listListEntry).minimum}));
                }
            } catch (NumberFormatException var2) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_int"));
            }

            return Optional.empty();
        }

        public List<? extends GuiEventListener> children() {
            return super.children();
        }
    }
}
