//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.jeff.pets.client.Utils;
import com.jeff.pets.compat.GlStateManager;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.compat.*;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat.Builder;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DropdownBoxEntry<T> extends TooltipListEntry<T> {
    protected ButtonWidget resetButton;
    protected SelectionElement<T> selectionElement;
    private @NotNull Supplier<T> defaultValue;
    private @Nullable Consumer<T> saveConsumer;
    private boolean suggestionMode = true;

    /** @deprecated */
    @Deprecated
    @Internal
    public DropdownBoxEntry(String fieldName, @NotNull String resetButtonKey, @Nullable Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart, @Nullable Supplier<T> defaultValue, @Nullable Consumer<T> saveConsumer, @Nullable Iterable<T> selections, @NotNull SelectionTopCellElement<T> topRenderer, @NotNull SelectionCellCreator<T> cellCreator) {
        super(I18n.getTranslation(fieldName), tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.saveConsumer = saveConsumer;
        this.resetButton = new ButtonWidget(0, 0, TextRenderer.of(Minecraft.getMinecraft().font).getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (w) -> {
            if (this.defaultValue != null) {
                this.selectionElement.topRenderer.setValue(this.defaultValue.get());
                this.getScreen().setEdited(true, requiresRestart);
            }
        });
        this.selectionElement = new SelectionElement(this, new Rectangle(0, 0, 150, 20), new DefaultDropdownMenuElement(this.getScreen(), selections == null ? ImmutableList.of() : ImmutableList.copyOf(selections)), topRenderer, cellCreator);
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.resetButton.active = this.isEditable() && this.getDefaultValue().isPresent() && (!this.defaultValue.get().equals(this.getValue()) || this.getConfigError().isPresent());
        this.resetButton.y = y;
        this.selectionElement.active = this.isEditable();
        this.selectionElement.bounds.y = y;
        if (Utils.isBidirectional()) {
            TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow(I18n.getTranslation(this.getFieldName()), WindowUtil.getScaledWidth() - x - TextRenderer.of(Minecraft.getMinecraft().font).getWidth(I18n.getTranslation(this.getFieldName())), y + 5, this.getPreferredTextColor());
            this.resetButton.x = x;
            this.selectionElement.bounds.x = x + this.resetButton.width + 1;
        } else {
            TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow(I18n.getTranslation(this.getFieldName()), x, y + 5, this.getPreferredTextColor());
            this.resetButton.x = x + entryWidth - this.resetButton.width;
            this.selectionElement.bounds.x = x + entryWidth - 150 + 1;
        }

        this.selectionElement.bounds.width = 150 - this.resetButton.width - 4;
        this.resetButton.render(mouseX, mouseY, delta);
        this.selectionElement.render(mouseX, mouseY, delta);
    }

    public boolean isSuggestionMode() {
        return this.suggestionMode;
    }

    public void setSuggestionMode(boolean suggestionMode) {
        this.suggestionMode = suggestionMode;
    }

    public void updateSelected(boolean isSelected) {
        this.selectionElement.topRenderer.isSelected = isSelected;
        this.selectionElement.menu.isSelected = isSelected;
    }

    public @NotNull ImmutableList<T> getSelections() {
        return this.selectionElement.menu.getSelections();
    }

    public T getValue() {
        return this.selectionElement.getValue();
    }

    /** @deprecated */
    @Deprecated
    public SelectionElement<T> getSelectionElement() {
        return this.selectionElement;
    }

    public Optional<T> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public List<? extends GuiEventListener> children() {
        return Lists.newArrayList(new GuiEventListener[]{this.selectionElement, this.resetButton});
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.resetButton.mouseClicked(mouseX, mouseY, button)) {
            this.setFocused(this.resetButton);
            return true;
        } else {
            return this.selectionElement.mouseClicked(mouseX, mouseY, button);
        }
    }

    public Optional<String> getError() {
        return this.selectionElement.topRenderer.getError();
    }

    public void lateRender(int mouseX, int mouseY, float delta) {
        this.selectionElement.lateRender(mouseX, mouseY, delta);
    }

    public int getMorePossibleHeight() {
        return this.selectionElement.getMorePossibleHeight();
    }

    public boolean mouseScrolled(double double_1) {
        return this.selectionElement.mouseScrolled(double_1);
    }

    public static class SelectionElement<R> extends AbstractContainerEventHandler {
        protected Rectangle bounds;
        protected boolean active;
        protected SelectionTopCellElement<R> topRenderer;
        protected DropdownBoxEntry<R> entry;
        protected DropdownMenuElement<R> menu;
        protected boolean dontReFocus = false;

        public SelectionElement(DropdownBoxEntry<R> entry, Rectangle bounds, DropdownMenuElement<R> menu, SelectionTopCellElement<R> topRenderer, SelectionCellCreator<R> cellCreator) {
            this.bounds = bounds;
            this.entry = entry;
            this.menu = (DropdownMenuElement)Objects.requireNonNull(menu);
            this.menu.entry = entry;
            this.menu.cellCreator = (SelectionCellCreator)Objects.requireNonNull(cellCreator);
            this.menu.initCells();
            this.topRenderer = (SelectionTopCellElement)Objects.requireNonNull(topRenderer);
            this.topRenderer.entry = entry;
        }

        public List<? extends GuiEventListener> children() {
            return Lists.newArrayList(new AbstractContainerEventHandler[]{this.topRenderer, this.menu});
        }

        public void render(int mouseX, int mouseY, float delta) {
            this.fill(this.bounds.x, this.bounds.y, this.bounds.x + this.bounds.width, this.bounds.y + this.bounds.height, -6250336);
            this.fill(this.bounds.x + 1, this.bounds.y + 1, this.bounds.x + this.bounds.width - 1, this.bounds.y + this.bounds.height - 1, -16777216);
            this.topRenderer.render(mouseX, mouseY, this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height, delta);
            if (this.menu.isExpanded()) {
                this.menu.render(mouseX, mouseY, this.bounds, delta);
            }

        }

        /** @deprecated */
        @Deprecated
        public SelectionTopCellElement<R> getTopRenderer() {
            return this.topRenderer;
        }

        public boolean mouseScrolled(double double_1) {
            return this.menu.isExpanded() ? this.menu.mouseScrolled(double_1) : false;
        }

        public void lateRender(int mouseX, int mouseY, float delta) {
            if (this.menu.isExpanded()) {
                this.menu.lateRender(mouseX, mouseY, delta);
            }

        }

        public int getMorePossibleHeight() {
            return this.menu.isExpanded() ? this.menu.getHeight() : -1;
        }

        public R getValue() {
            return this.topRenderer.getValue();
        }

        public List<? extends GuiEventListener> getChildren() {
            return Lists.newArrayList(new AbstractContainerEventHandler[]{this.topRenderer, this.menu});
        }

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            this.dontReFocus = false;
            boolean inside = this.bounds.contains(double_1, double_2);
            boolean b = super.mouseClicked(double_1, double_2, int_1);
            if (this.dontReFocus) {
                this.setFocused((GuiEventListener)null);
                this.entry.setFocused((GuiEventListener)null);
                this.dontReFocus = false;
                return b;
            } else {
                if (inside) {
                    this.setFocused(this);
                    this.entry.setFocused(this);
                    b = true;
                }

                return b;
            }
        }

        public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
            return this.topRenderer.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }

        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            return this.topRenderer.keyPressed(keyCode, scanCode, modifiers);
        }

        public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
            return this.topRenderer.keyReleased(keyCode, scanCode, modifiers);
        }

        public boolean charTyped(char chr, int modifiers) {
            return this.topRenderer.charTyped(chr, modifiers);
        }
    }

    public static class DefaultDropdownMenuElement<R> extends DropdownMenuElement<R> {
        protected @NotNull ImmutableList<R> selections;
        protected @NotNull List<SelectionCellElement<R>> cells;
        protected @NotNull List<SelectionCellElement<R>> currentElements;
        protected String lastSearchKeyword = "";
        protected Rectangle lastRectangle;
        protected boolean scrolling;
        protected double scroll;
        protected double target;
        protected long start;
        protected long duration;

        public DefaultDropdownMenuElement(Screen screen, @NotNull ImmutableList<R> selections) {
            this.selections = selections;
            this.cells = Lists.newArrayList();
            this.currentElements = Lists.newArrayList();
        }

        public double getMaxScroll() {
            return (double)(this.getCellCreator().getCellHeight() * this.currentElements.size());
        }

        protected double getMaxScrollPosition() {
            return Math.max(0.0, this.getMaxScroll() - (double)this.getHeight());
        }

        public @NotNull ImmutableList<R> getSelections() {
            return this.selections;
        }

        public void initCells() {
            UnmodifiableIterator var1 = this.getSelections().iterator();

            while(var1.hasNext()) {
                R selection = (R)var1.next();
                this.cells.add(this.getCellCreator().create(selection));
            }

            Iterator var3 = this.cells.iterator();

            while(var3.hasNext()) {
                SelectionCellElement<R> cell = (SelectionCellElement)var3.next();
                cell.entry = this.getEntry();
            }

            this.search();
        }

        public void search() {
            if (this.isSuggestionMode()) {
                this.currentElements.clear();
                String keyword = this.lastSearchKeyword.toLowerCase();
                Iterator var2 = this.cells.iterator();

                while(true) {
                    SelectionCellElement cell;
                    String key;
                    do {
                        if (!var2.hasNext()) {
                            if (!keyword.isEmpty()) {
                                Comparator<SelectionCellElement<?>> c = Comparator.comparingDouble((i) -> {
                                    return i.getSearchKey() == null ? Double.MAX_VALUE : this.similarity(i.getSearchKey(), keyword);
                                });
                                this.currentElements.sort(c.reversed());
                            }

                            this.scrollTo(0.0, false);
                            return;
                        }

                        cell = (SelectionCellElement)var2.next();
                        key = cell.getSearchKey();
                    } while(key != null && !key.toLowerCase().contains(keyword));

                    this.currentElements.add(cell);
                }
            } else {
                this.currentElements.clear();
                this.currentElements.addAll(this.cells);
            }
        }

        protected int editDistance(String s1, String s2) {
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();
            int[] costs = new int[s2.length() + 1];

            for(int i = 0; i <= s1.length(); ++i) {
                int lastValue = i;

                for(int j = 0; j <= s2.length(); ++j) {
                    if (i == 0) {
                        costs[j] = j;
                    } else if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }

                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }

                if (i > 0) {
                    costs[s2.length()] = lastValue;
                }
            }

            return costs[s2.length()];
        }

        protected double similarity(String s1, String s2) {
            String longer = s1;
            String shorter = s2;
            if (s1.length() < s2.length()) {
                longer = s2;
                shorter = s1;
            }

            int longerLength = longer.length();
            return longerLength == 0 ? 1.0 : (double)(longerLength - this.editDistance(longer, shorter)) / (double)longerLength;
        }

        public void render(int mouseX, int mouseY, Rectangle rectangle, float delta) {
            if (!this.getEntry().selectionElement.topRenderer.getSearchTerm().equals(this.lastSearchKeyword)) {
                this.lastSearchKeyword = this.getEntry().selectionElement.topRenderer.getSearchTerm();
                this.search();
            }

            this.updatePosition(delta);
            this.lastRectangle = rectangle.clone();
            this.lastRectangle.translate(0, -1);
        }

        private void updatePosition(float delta) {
            double[] target = new double[]{this.target};
            this.scroll = ClothConfigInitializer.handleScrollingPosition(target, this.scroll, this.getMaxScroll(), delta, (double)this.start, (double)this.duration);
            this.target = target[0];
        }

        public void lateRender(int mouseX, int mouseY, float delta) {
            int last10Height = this.getHeight();
            int cWidth = this.getCellCreator().getCellWidth();
            this.fill(this.lastRectangle.x, this.lastRectangle.y + this.lastRectangle.height, this.lastRectangle.x + cWidth, this.lastRectangle.y + this.lastRectangle.height + last10Height + 1, -6250336);
            this.fill(this.lastRectangle.x + 1, this.lastRectangle.y + this.lastRectangle.height + 1, this.lastRectangle.x + cWidth - 1, this.lastRectangle.y + this.lastRectangle.height + last10Height, -16777216);
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 0.0F, 300.0F);
            ScissorsHandler.INSTANCE.scissor(new Rectangle(this.lastRectangle.x, this.lastRectangle.y + this.lastRectangle.height + 1, cWidth - 6, last10Height - 1));
            double yy = (double)(this.lastRectangle.y + this.lastRectangle.height) - this.scroll;

            for(Iterator var8 = this.currentElements.iterator(); var8.hasNext(); yy += (double)this.getCellCreator().getCellHeight()) {
                SelectionCellElement<R> cell = (SelectionCellElement)var8.next();
                if (yy + (double)this.getCellCreator().getCellHeight() >= (double)(this.lastRectangle.y + this.lastRectangle.height) && yy <= (double)(this.lastRectangle.y + this.lastRectangle.height + last10Height + 1)) {
                    cell.render(mouseX, mouseY, this.lastRectangle.x, (int)yy, this.getMaxScrollPosition() > 6.0 ? this.getCellCreator().getCellWidth() - 6 : this.getCellCreator().getCellWidth(), this.getCellCreator().getCellHeight(), delta);
                } else {
                    cell.dontRender(delta);
                }
            }

            ScissorsHandler.INSTANCE.removeLastScissor();
            if (this.currentElements.isEmpty()) {
                TextRenderer textRenderer = TextRenderer.of(Minecraft.getMinecraft().font);
                String s = I18n.getTranslation("text.cloth-config.dropdown.value.unknown");
                textRenderer.drawWithShadow(s, (int)((float)this.lastRectangle.x + (float)this.getCellCreator().getCellWidth() / 2.0F - (float)textRenderer.getWidth(s) / 2.0F), this.lastRectangle.y + this.lastRectangle.height + 3, -1);
            }

            if (this.getMaxScrollPosition() > 6.0) {
                int scrollbarPositionMinX = this.lastRectangle.x + this.getCellCreator().getCellWidth() - 6;
                int scrollbarPositionMaxX = scrollbarPositionMinX + 6;
                int height = (int)((double)(last10Height * last10Height) / this.getMaxScrollPosition());
                height = Math.max(32, Math.min(last10Height - 8, height));
                height = (int)((double)height - Math.min(this.scroll < 0.0 ? (double)((int)(-this.scroll)) : (this.scroll > this.getMaxScrollPosition() ? (double)((int)this.scroll) - this.getMaxScrollPosition() : 0.0), (double)height * 0.95));
                height = Math.max(10, height);
                int minY = (int)Math.min(Math.max((double)((int)this.scroll * (last10Height - height)) / this.getMaxScrollPosition() + (double)(this.lastRectangle.y + this.lastRectangle.height + 1), (double)(this.lastRectangle.y + this.lastRectangle.height + 1)), (double)(this.lastRectangle.y + this.lastRectangle.height + 1 + last10Height - height));
                int bottomc = (new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height)).contains(new Point(MouseInput.getX(), MouseInput.getY())) ? 168 : 128;
                int topc = (new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height)).contains(new Point(MouseInput.getX(), MouseInput.getY())) ? 222 : 172;
                Tessellator tesselator = Tessellator.INSTANCE;
                Builder buffer = Builder.of(tesselator);
                buffer.begin(7, GuiVertexCompat.POSITION_TEX_COLOR);
                buffer.vertex((double)scrollbarPositionMinX, (double)(minY + height), 0.0).texture(0.0, 1.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
                buffer.vertex((double)scrollbarPositionMaxX, (double)(minY + height), 0.0).texture(1.0, 1.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
                buffer.vertex((double)scrollbarPositionMaxX, (double)minY, 0.0).texture(1.0, 0.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
                buffer.vertex((double)scrollbarPositionMinX, (double)minY, 0.0).texture(0.0, 0.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
                tesselator.draw();
                buffer.begin(7, GuiVertexCompat.POSITION_TEX_COLOR);
                buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)(minY + height - 1), 0.0).texture(1.0, 1.0).color(topc, topc, topc, 255).nextVertex();
                buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)minY, 0.0).texture(1.0, 0.0).color(topc, topc, topc, 255).nextVertex();
                buffer.vertex((double)scrollbarPositionMinX, (double)minY, 0.0).texture(0.0, 0.0).color(topc, topc, topc, 255).nextVertex();
                tesselator.draw();
            }

            GlStateManager.translatef(0.0F, 0.0F, -300.0F);
            GlStateManager.popMatrix();
        }

        public int getHeight() {
            return Math.max(Math.min(this.getCellCreator().getDropBoxMaxHeight(), (int)this.getMaxScroll()), 14);
        }

        public boolean isMouseOver(double mouseX, double mouseY) {
            return this.isExpanded() && mouseX >= (double)this.lastRectangle.x && mouseX <= (double)(this.lastRectangle.x + this.getCellCreator().getCellWidth()) && mouseY >= (double)(this.lastRectangle.y + this.lastRectangle.height) && mouseY <= (double)(this.lastRectangle.y + this.lastRectangle.height + this.getHeight() + 1);
        }

        public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
            if (!this.isExpanded()) {
                return false;
            } else if (int_1 == 0 && this.scrolling) {
                if (double_2 < (double)this.lastRectangle.y + (double)this.lastRectangle.height) {
                    this.scrollTo(0.0, false);
                } else if (double_2 > (double)this.lastRectangle.y + (double)this.lastRectangle.height + (double)this.getHeight()) {
                    this.scrollTo(this.getMaxScrollPosition(), false);
                } else {
                    double double_5 = Math.max(1.0, this.getMaxScrollPosition());
                    int int_2 = this.getHeight();
                    int int_3 = Math.max(32, Math.min(int_2 - 8, (int)((float)(int_2 * int_2) / (float)this.getMaxScrollPosition())));
                    double double_6 = Math.max(1.0, double_5 / (double)(int_2 - int_3));
                    this.offset(double_4 * double_6, false);
                }

                this.target = (double)Math.max(0, Math.min((int)this.getMaxScrollPosition(), (int)this.target));
                return true;
            } else {
                return false;
            }
        }

        public boolean mouseScrolled(double double_1) {
            if (this.isExpanded()) {
                this.offset(ClothConfigInitializer.getScrollStep() * -double_1, true);
                return true;
            } else {
                return false;
            }
        }

        protected void updateScrollingState(double double_1, double double_2, int int_1) {
            this.scrolling = this.isExpanded() && this.lastRectangle != null && int_1 == 0 && double_1 >= (double)this.lastRectangle.x + (double)this.getCellCreator().getCellWidth() - 6.0 && double_1 < (double)(this.lastRectangle.x + this.getCellCreator().getCellWidth());
        }

        public List<SelectionCellElement<R>> getChildren() {
            return this.children();
        }

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            if (!this.isExpanded()) {
                return false;
            } else {
                this.updateScrollingState(double_1, double_2, int_1);
                return super.mouseClicked(double_1, double_2, int_1) || this.scrolling;
            }
        }

        public void offset(double value, boolean animated) {
            this.scrollTo(this.target + value, animated);
        }

        public void scrollTo(double value, boolean animated) {
            this.scrollTo(value, animated, ClothConfigInitializer.getScrollDuration());
        }

        public void scrollTo(double value, boolean animated, long duration) {
            this.target = ClothConfigInitializer.clamp(value, this.getMaxScrollPosition());
            if (animated) {
                this.start = System.currentTimeMillis();
                this.duration = duration;
            } else {
                this.scroll = this.target;
            }

        }

        public List<SelectionCellElement<R>> children() {
            return this.currentElements;
        }
    }

    public abstract static class DropdownMenuElement<R> extends AbstractContainerEventHandler {
        /** @deprecated */
        @Deprecated
        @NotNull
        private SelectionCellCreator<R> cellCreator;
        /** @deprecated */
        @Deprecated
        private @NotNull DropdownBoxEntry<R> entry;
        private boolean isSelected;

        public DropdownMenuElement() {
        }

        @NotNull
        public SelectionCellCreator<R> getCellCreator() {
            return this.cellCreator;
        }

        public final @NotNull DropdownBoxEntry<R> getEntry() {
            return this.entry;
        }

        public abstract @NotNull ImmutableList<R> getSelections();

        public abstract void initCells();

        public abstract void render(int var1, int var2, Rectangle var3, float var4);

        public abstract void lateRender(int var1, int var2, float var3);

        public abstract int getHeight();

        public final boolean isExpanded() {
            return this.isSelected && this.getEntry().getFocused() == this.getEntry().selectionElement;
        }

        public final boolean isSuggestionMode() {
            return this.entry.isSuggestionMode();
        }

        public abstract List<SelectionCellElement<R>> getChildren();
    }

    public abstract static class SelectionTopCellElement<R> extends AbstractContainerEventHandler {
        /** @deprecated */
        @Deprecated
        private DropdownBoxEntry<R> entry;
        protected boolean isSelected = false;

        public SelectionTopCellElement() {
        }

        public abstract R getValue();

        public abstract void setValue(R var1);

        public abstract String getSearchTerm();

        public abstract Optional<String> getError();

        public final Optional<String> getConfigError() {
            return this.entry.getConfigError();
        }

        public DropdownBoxEntry<R> getParent() {
            return this.entry;
        }

        public final boolean hasConfigError() {
            return this.getConfigError().isPresent();
        }

        public final boolean hasError() {
            return this.getError().isPresent();
        }

        public final int getPreferredTextColor() {
            return this.getConfigError().isPresent() ? 16733525 : 16777215;
        }

        public final boolean isSuggestionMode() {
            return this.getParent().isSuggestionMode();
        }

        public void selectFirstRecommendation() {
            List<SelectionCellElement<R>> children = this.getParent().selectionElement.menu.getChildren();
            Iterator var2 = children.iterator();

            while(var2.hasNext()) {
                SelectionCellElement<R> child = (SelectionCellElement)var2.next();
                if (child.getSelection() != null) {
                    this.setValue(child.getSelection());
                    break;
                }
            }

        }

        public abstract void render(int var1, int var2, int var3, int var4, int var5, int var6, float var7);
    }

    public abstract static class SelectionCellCreator<R> {
        public SelectionCellCreator() {
        }

        public abstract SelectionCellElement<R> create(R var1);

        public abstract int getCellHeight();

        public abstract int getDropBoxMaxHeight();

        public int getCellWidth() {
            return 132;
        }
    }

    public static class DefaultSelectionTopCellElement<R> extends SelectionTopCellElement<R> {
        protected CompatTextFieldWidget textFieldWidget;
        protected Function<String, R> toObjectFunction;
        protected Function<R, String> toStringFunction;
        protected R value;
        protected int lastX;
        protected int lastY;
        protected int lastWidth;
        protected int lastHeight;

        public DefaultSelectionTopCellElement(Screen screen, R value, Function<String, R> toObjectFunction, Function<R, String> toStringFunction) {
            this.value = Objects.requireNonNull(value);
            this.toObjectFunction = (Function)Objects.requireNonNull(toObjectFunction);
            this.toStringFunction = (Function)Objects.requireNonNull(toStringFunction);
            this.textFieldWidget = new CompatTextFieldWidget(screen, 0, TextRenderer.of(Minecraft.getMinecraft().font), 0, 0, 148, 18) {
                public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
                    if (keyCode != 257 && keyCode != 335) {
                        return DefaultSelectionTopCellElement.this.isSuggestionMode() && super.keyPressed(keyCode, scanCode, modifiers);
                    } else {
                        DefaultSelectionTopCellElement.this.selectFirstRecommendation();
                        return true;
                    }
                }

                public void keyPressed(char chr, int key) {
                    if (key != 257 && key != 335) {
                        DefaultSelectionTopCellElement.this.isSuggestionMode();
                        super.keyPressed(chr, key);
                    } else {
                        DefaultSelectionTopCellElement.this.selectFirstRecommendation();
                    }
                }
            };
            this.textFieldWidget.setMaxLength(999999);
            this.textFieldWidget.setText((String)toStringFunction.apply(value));
        }

        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
            this.lastX = x;
            this.lastY = y;
            this.lastWidth = width;
            this.lastHeight = height;
            this.textFieldWidget.x = x + 1;
            this.textFieldWidget.y = y + 1;
            this.textFieldWidget.width = Math.max(10, width - 2);
            this.textFieldWidget.enabled = this.getParent().isEditable();
            this.textFieldWidget.render();
        }

        public R getValue() {
            return this.hasError() ? this.value : this.toObjectFunction.apply(this.textFieldWidget.getText());
        }

        public void setValue(R value) {
            this.textFieldWidget.setText((String)this.toStringFunction.apply(value));
        }

        public String getSearchTerm() {
            return this.textFieldWidget.getText();
        }

        public Optional<String> getError() {
            return this.toObjectFunction.apply(this.textFieldWidget.getText()) != null ? Optional.empty() : Optional.of("Invalid Value!");
        }

        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (mouseX >= (double)this.lastX && mouseX <= (double)(this.lastX + this.lastWidth) && mouseY >= (double)this.lastY && mouseY <= (double)(this.lastY + this.lastHeight)) {
                this.textFieldWidget.mouseClicked((int)mouseX, (int)mouseY, button);
                this.textFieldWidget.setFocused(true);
                this.setFocused(this.textFieldWidget);
                return true;
            } else {
                return super.mouseClicked(mouseX, mouseY, button);
            }
        }

        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            return this.textFieldWidget.keyPressed(keyCode, scanCode, modifiers);
        }

        public boolean charTyped(char chr, int modifiers) {
            return this.textFieldWidget.charTyped(chr, modifiers);
        }

        public List<? extends GuiEventListener> children() {
            return Collections.singletonList(this.textFieldWidget);
        }
    }

    public static class DefaultSelectionCellElement<R> extends SelectionCellElement<R> {
        protected R r;
        protected int x;
        protected int y;
        protected int width;
        protected int height;
        protected boolean rendering;
        protected Function<R, String> toStringFunction;

        public DefaultSelectionCellElement(R r, Function<R, String> toStringFunction) {
            this.r = r;
            this.toStringFunction = toStringFunction;
        }

        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
            this.rendering = true;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            if (b) {
                this.fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
            }

            TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow((String)this.toStringFunction.apply(this.r), x + 6, y + 3, b ? 16777215 : 8947848);
        }

        public void dontRender(float delta) {
            this.rendering = false;
        }

        public @Nullable String getSearchKey() {
            return (String)this.toStringFunction.apply(this.r);
        }

        public @Nullable R getSelection() {
            return this.r;
        }

        public List<? extends GuiEventListener> children() {
            return Collections.emptyList();
        }

        public boolean mouseClicked(double mouseX, double mouseY, int int_1) {
            boolean b = this.rendering && mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
            if (b) {
                this.getEntry().selectionElement.topRenderer.setValue(this.r);
                this.getEntry().selectionElement.dontReFocus = true;
                return true;
            } else {
                return false;
            }
        }
    }

    public abstract static class SelectionCellElement<R> extends AbstractContainerEventHandler {
        /** @deprecated */
        @Deprecated
        private @NotNull DropdownBoxEntry<R> entry;

        public SelectionCellElement() {
        }

        public final @NotNull DropdownBoxEntry<R> getEntry() {
            return this.entry;
        }

        public abstract void render(int var1, int var2, int var3, int var4, int var5, int var6, float var7);

        public abstract void dontRender(float var1);

        public abstract @Nullable String getSearchKey();

        public abstract @Nullable R getSelection();
    }

    public static class DefaultSelectionCellCreator<R> extends SelectionCellCreator<R> {
        protected Function<R, String> toStringFunction;

        public DefaultSelectionCellCreator(Function<R, String> toStringFunction) {
            this.toStringFunction = toStringFunction;
        }

        public DefaultSelectionCellCreator() {
            this(Object::toString);
        }

        public SelectionCellElement<R> create(R selection) {
            return new DefaultSelectionCellElement(selection, this.toStringFunction);
        }

        public int getCellHeight() {
            return 14;
        }

        public int getDropBoxMaxHeight() {
            return this.getCellHeight() * 7;
        }
    }
}
