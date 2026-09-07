package me.shedaniel.forge.clothconfig2.gui.entries;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This class represents config entry lists that use one {@link GuiTextField} per entry.
 *
 * @param <T>    the configuration object type
 * @param <C>    the cell type
 * @param <SELF> the "curiously recurring template pattern" type parameter
 * @see AbstractListListEntry
 */
@SideOnly(Side.CLIENT)
public abstract class AbstractTextFieldListListEntry<T, C extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<T, C, SELF>, SELF extends AbstractTextFieldListListEntry<T, C, SELF>> extends AbstractListListEntry<T, C, SELF> {


    public AbstractTextFieldListListEntry(String fieldName, List<T> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<T>> saveConsumer, Supplier<List<T>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront, BiFunction<T, SELF, C> createNewCell) {
        super(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront, createNewCell);
    }

    /**
     * @param <T>           the configuration object type
     * @param <SELF>        the "curiously recurring template pattern" type parameter for this class
     * @param <OUTER_SELF>> the "curiously recurring template pattern" type parameter for the outer class
     * @see AbstractTextFieldListListEntry
     */

    public static abstract class AbstractTextFieldListCell<T, SELF extends AbstractTextFieldListCell<T, SELF, OUTER_SELF>, OUTER_SELF extends AbstractTextFieldListListEntry<T, SELF, OUTER_SELF>> extends AbstractListListEntry.AbstractListCell<T, SELF, OUTER_SELF> {

        protected GuiTextField widget;
        private boolean isSelected;

        public AbstractTextFieldListCell(@Nullable T value, OUTER_SELF listListEntry) {
            super(value, listListEntry);

            final T finalValue = substituteDefault(value);

            widget = new GuiTextField(0, Minecraft.getInstance().fontRenderer, 0, 100, 18, 0) {
                @Override
                public void func_146194_f() {
                    setFocused(isSelected);
                    super.func_146194_f();
                }
            };
            widget.func_175205_a(this::isValidText);
            widget.setMaxStringLength(Integer.MAX_VALUE);
            widget.setEnableBackgroundDrawing(false);
            widget.setText(Objects.toString(finalValue));
            widget.func_175205_a((s) -> {
                widget.setTextColor(getPreferredTextColor());
                if (listListEntry.getScreen() != null && !Objects.equals(s, Objects.toString(finalValue))) {
                    this.listListEntry.getScreen().setEdited(true, this.listListEntry.isRequiresRestart());
                }
                return false;
            });
        }

        @Override
        public void updateSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        /**
         * Allows subclasses to substitute default values.
         *
         * @param value the (possibly null) value to substitute
         * @return a substitution
         */
        @Nullable
        protected abstract T substituteDefault(@Nullable T value);

        /**
         * Tests if the text entered is valid. If not, the text is not changed.
         *
         * @param text the text to test
         * @return {@code true} if the text may be changed, {@code false} to prevent the change
         */
        protected abstract boolean isValidText(String text);

        @Override
        public int getCellHeight() {
            return 20;
        }

        @Override
        public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
            widget.width = (entryWidth - 12);
            widget.x = x;
            widget.y = y + 1;
            widget.setEnabled(listListEntry.isEditable());
            widget.func_146194_f();
            if (isSelected && listListEntry.isEditable())
                Gui.drawRect(x, y + 12, x + entryWidth - 12, y + 13, getConfigError().isPresent() ? 0xffff5555 : 0xffe0e0e0);
        }

    }

}
