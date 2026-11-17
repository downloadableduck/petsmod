//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.compat.CompatTextFieldWidget;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractTextFieldListListEntry<T, C extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<T, C, SELF>, SELF extends AbstractTextFieldListListEntry<T, C, SELF>> extends AbstractListListEntry<T, C, SELF> {
    @Internal
    public AbstractTextFieldListListEntry(String fieldName, List<T> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<T>> saveConsumer, Supplier<List<T>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront, BiFunction<T, SELF, C> createNewCell) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, createNewCell);
    }

    @Internal
    public abstract static class AbstractTextFieldListCell<T, SELF extends AbstractTextFieldListCell<T, SELF, OUTER_SELF>, OUTER_SELF extends AbstractTextFieldListListEntry<T, SELF, OUTER_SELF>> extends AbstractListCell<T, SELF, OUTER_SELF> {
        protected CompatTextFieldWidget widget;
        private boolean isSelected;

        public AbstractTextFieldListCell(@Nullable T value, OUTER_SELF listListEntry) {
            super(value, listListEntry);
            T finalValue = this.substituteDefault(value);
            this.widget = new CompatTextFieldWidget(((AbstractTextFieldListListEntry)this.listListEntry).getScreen(), 0, Minecraft.INSTANCE.textRenderer, 0, 0, 100, 18) {
                public void render() {
                    super.render();
                }
            };
            this.widget.setMaxLength(Integer.MAX_VALUE);
            this.widget.setText(Objects.toString(finalValue));
        }

        public void updateSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        protected abstract @Nullable T substituteDefault(@Nullable T var1);

        protected abstract boolean isValidText(@NotNull String var1);

        public int getCellHeight() {
            return 20;
        }

        public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
            this.widget.width = entryWidth - 12;
            this.widget.x = x;
            this.widget.y = y + 1;
            this.widget.enabled = ((AbstractTextFieldListListEntry)this.listListEntry).isEditable();
            this.widget.render();
            if (isSelected && ((AbstractTextFieldListListEntry)this.listListEntry).isEditable()) {
                this.fill(x, y + 12, x + entryWidth - 12, y + 13, this.getConfigError().isPresent() ? -43691 : -2039584);
            }

        }

        public List<? extends GuiEventListener> children() {
            return Collections.singletonList(this.widget);
        }
    }
}
