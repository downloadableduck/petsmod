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

public class LongListListEntry extends AbstractTextFieldListListEntry<Long, LongListListEntry.LongListCell, LongListListEntry> {
    private long minimum;
    private long maximum;

    /** @deprecated */
    @Deprecated
    @Internal
    public LongListListEntry(String fieldName, List<Long> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Long>> saveConsumer, Supplier<List<Long>> defaultValue, String resetButtonKey) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public LongListListEntry(String fieldName, List<Long> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Long>> saveConsumer, Supplier<List<Long>> defaultValue, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public LongListListEntry(String fieldName, List<Long> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<Long>> saveConsumer, Supplier<List<Long>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, LongListCell::new);
        this.minimum = Long.MIN_VALUE;
        this.maximum = Long.MAX_VALUE;
    }

    public LongListListEntry setMaximum(long maximum) {
        this.maximum = maximum;
        return this;
    }

    public LongListListEntry setMinimum(long minimum) {
        this.minimum = minimum;
        return this;
    }

    public LongListListEntry self() {
        return this;
    }

    public static class LongListCell extends AbstractTextFieldListCell<Long, LongListCell, LongListListEntry> {
        public LongListCell(Long value, LongListListEntry listListEntry) {
            super(value, listListEntry);
        }

        protected @Nullable Long substituteDefault(@Nullable Long value) {
            return value == null ? 0L : value;
        }

        protected boolean isValidText(@NotNull String text) {
            return text.chars().allMatch((c) -> {
                return Character.isDigit(c) || c == 45;
            });
        }

        public Long getValue() {
            try {
                return Long.valueOf(this.widget.getText());
            } catch (NumberFormatException var2) {
                return 0L;
            }
        }

        public Optional<String> getError() {
            try {
                long l = Long.parseLong(this.widget.getText());
                if (l > ((LongListListEntry)this.listListEntry).maximum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{((LongListListEntry)this.listListEntry).maximum}));
                }

                if (l < ((LongListListEntry)this.listListEntry).minimum) {
                    return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{((LongListListEntry)this.listListEntry).minimum}));
                }
            } catch (NumberFormatException var3) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_long"));
            }

            return Optional.empty();
        }

        public List<? extends GuiEventListener> children() {
            return super.children();
        }
    }
}
