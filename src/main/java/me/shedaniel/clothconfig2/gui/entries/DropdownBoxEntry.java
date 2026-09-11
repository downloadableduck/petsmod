package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.math.Rectangle;
import me.shedaniel.math.impl.PointHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.Window;
import net.minecraft.client.render.BufferBuilder;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.math.MathHelper;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static me.shedaniel.clothconfig2.ClothConfigInitializer.handleScrollingPosition;

@SuppressWarnings("deprecation")
public class DropdownBoxEntry<T> extends TooltipListEntry<T> {

    protected ButtonWidget resetButton;
    protected SelectionElement<T> selectionElement;
    private final Supplier<T> defaultValue;
    private final Consumer<T> saveConsumer;


    @Deprecated
    public DropdownBoxEntry(String fieldName, String resetButtonKey,
                            Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart,
                            Supplier<T> defaultValue,
                            Consumer<T> saveConsumer, Iterable<T> selections, SelectionTopCellElement<T> topRenderer, SelectionCellCreator<T> cellCreator) {
        super(I18n.translate(fieldName), tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.saveConsumer = saveConsumer;
        this.resetButton = new ButtonWidget(new Random().nextInt(), 0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey)) {
            @Override
            public boolean isMouseOver(MinecraftClient mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.isMouseOver(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    selectionElement.topRenderer.setValue(defaultValue.get());

                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.selectionElement = new SelectionElement<>(this, new Rectangle(0, 0, 150, 20), new DefaultDropdownMenuElement<>(selections == null ? ImmutableList.of() : ImmutableList.copyOf(selections)), topRenderer, cellCreator);
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        int windowWidth = (int) new Window(MinecraftClient.getInstance()).getScaledWidth();
        this.resetButton.active = isEditable() && getDefaultValue().isPresent() && (!defaultValue.get().equals(getValue()) || getConfigError().isPresent());
        this.resetButton.y = y;
        this.selectionElement.active = isEditable();
        this.selectionElement.bounds.y = y;
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), windowWidth - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.selectionElement.bounds.x = x + resetButton.getWidth() + 1;
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getWidth();
            this.selectionElement.bounds.x = x + entryWidth - 150 + 1;
        }
        this.selectionElement.bounds.width = 150 - resetButton.getWidth() - 4;
        resetButton.method_891(MinecraftClient.getInstance(), mouseX, mouseY, delta);
        selectionElement.render(mouseX, mouseY, delta);
    }

    @Override
    public void updateSelected(boolean isSelected) {
        selectionElement.topRenderer.isSelected = isSelected;
        selectionElement.menu.isSelected = isSelected;
    }


    public ImmutableList<T> getSelections() {
        return selectionElement.menu.getSelections();
    }

    @Override
    public T getValue() {
        return selectionElement.getValue();
    }

    @Deprecated
    public SelectionElement<T> getSelectionElement() {
        return selectionElement;
    }

    @Override
    public Optional<T> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }

    @Override
    public Optional<String> getError() {
        return selectionElement.topRenderer.getError();
    }

    @Override
    public void lateRender(int mouseX, int mouseY, float delta) {
        selectionElement.lateRender(mouseX, mouseY, delta);
    }

    @Override
    public int getMorePossibleHeight() {
        return selectionElement.getMorePossibleHeight();
    }

    public boolean mouseScrolled(double double_1) {
        MinecraftClient client = MinecraftClient.getInstance();
        return selectionElement.mouseScrolled(double_1);
    }

    public static class SelectionElement<R> {
        protected Rectangle bounds;
        protected boolean active;
        protected SelectionTopCellElement<R> topRenderer;
        protected DropdownBoxEntry<R> entry;
        protected DropdownMenuElement<R> menu;
        protected boolean dontReFocus = false;

        public SelectionElement(DropdownBoxEntry<R> entry, Rectangle bounds, DropdownMenuElement<R> menu, SelectionTopCellElement<R> topRenderer, SelectionCellCreator<R> cellCreator) {
            this.bounds = bounds;
            this.entry = entry;
            this.menu = Objects.requireNonNull(menu);
            this.menu.entry = entry;
            this.menu.cellCreator = Objects.requireNonNull(cellCreator);
            this.menu.initCells();
            this.topRenderer = Objects.requireNonNull(topRenderer);
            this.topRenderer.entry = entry;
        }

        public void render(int mouseX, int mouseY, float delta) {
            DrawableHelper.fill(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, -6250336);
            DrawableHelper.fill(bounds.x + 1, bounds.y + 1, bounds.x + bounds.width - 1, bounds.y + bounds.height - 1, -16777216);
            topRenderer.render(mouseX, mouseY, bounds.x, bounds.y, bounds.width, bounds.height, delta);
            if (menu.isExpanded())
                menu.render(mouseX, mouseY, bounds, delta);
        }

        @Deprecated
        public SelectionTopCellElement<R> getTopRenderer() {
            return topRenderer;
        }

        public boolean mouseScrolled(double double_1) {
            //if (menu.isExpanded())
                //return menu.(double_1);
            return false;
        }

        public void lateRender(int mouseX, int mouseY, float delta) {
            if (menu.isExpanded())
                menu.lateRender(mouseX, mouseY, delta);
        }

        public int getMorePossibleHeight() {
            if (menu.isExpanded())
                return menu.getHeight();
            return -1;
        }

        public R getValue() {
            return topRenderer.getValue();
        }
    }

    public static abstract class DropdownMenuElement<R> {
        @Deprecated
        private SelectionCellCreator<R> cellCreator;
        @Deprecated
        private DropdownBoxEntry<R> entry;
        private boolean isSelected;


        public SelectionCellCreator<R> getCellCreator() {
            return cellCreator;
        }


        public final DropdownBoxEntry<R> getEntry() {
            return entry;
        }


        public abstract ImmutableList<R> getSelections();

        public abstract void initCells();

        public abstract void render(int mouseX, int mouseY, Rectangle rectangle, float delta);

        public abstract void lateRender(int mouseX, int mouseY, float delta);

        public abstract int getHeight();

        public abstract List<SelectionCellElement<R>> getCells();

        public final boolean isExpanded() {
            return isSelected && this.getEntry().selectionElement != null;
        }

    }

    public static class DefaultDropdownMenuElement<R> extends DropdownMenuElement<R> {
        protected ImmutableList<R> selections;
        protected List<SelectionCellElement<R>> cells;
        protected List<SelectionCellElement<R>> currentElements;
        protected String lastSearchKeyword = "";
        protected Rectangle lastRectangle;
        protected boolean scrolling;
        protected double scroll, target;
        protected long start;
        protected long duration;

        public DefaultDropdownMenuElement(ImmutableList<R> selections) {
            this.selections = selections;
            this.cells = Lists.newArrayList();
            this.currentElements = Lists.newArrayList();
        }

        public double getMaxScroll() {
            return getCellCreator().getCellHeight() * currentElements.size();
        }

        @Override
        public List<SelectionCellElement<R>> getCells() {
            return currentElements;
        }

        protected double getMaxScrollPosition() {
            return Math.max(0, this.getMaxScroll() - (getHeight()));
        }

        @Override

        public ImmutableList<R> getSelections() {
            return selections;
        }

        @Override
        public void initCells() {
            for (R selection : getSelections()) {
                cells.add(getCellCreator().create(selection));
            }
            for (SelectionCellElement<R> cell : cells) {
                cell.entry = getEntry();
            }
            search();
        }

        public void search() {
            currentElements.clear();
            String keyword = this.lastSearchKeyword.toLowerCase();
            for (SelectionCellElement<R> cell : cells) {
                String key = cell.getSearchKey();
                if (key == null || key.toLowerCase().contains(keyword))
                    currentElements.add(cell);
            }
            if (!keyword.isEmpty()) {
                Comparator<SelectionCellElement<?>> c = Comparator.comparingDouble(i -> i.getSearchKey() == null ? Double.MAX_VALUE : similarity(i.getSearchKey(), keyword));
                currentElements.sort(c.reversed());
            }
            scrollTo(0, false);
        }

        protected int editDistance(String s1, String s2) {
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();

            int[] costs = new int[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                int lastValue = i;
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0)
                        costs[j] = j;
                    else {
                        if (j > 0) {
                            int newValue = costs[j - 1];
                            if (s1.charAt(i - 1) != s2.charAt(j - 1))
                                newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                            costs[j - 1] = lastValue;
                            lastValue = newValue;
                        }
                    }
                }
                if (i > 0)
                    costs[s2.length()] = lastValue;
            }
            return costs[s2.length()];
        }

        protected double similarity(String s1, String s2) {
            String longer = s1, shorter = s2;
            if (s1.length() < s2.length()) { // longer should always have greater length
                longer = s2;
                shorter = s1;
            }
            int longerLength = longer.length();
            if (longerLength == 0) {
                return 1.0; /* both strings are zero length */
            }
            return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
        }

        @Override
        public void render(int mouseX, int mouseY, Rectangle rectangle, float delta) {
            if (!getEntry().selectionElement.topRenderer.getSearchTerm().equals(lastSearchKeyword)) {
                lastSearchKeyword = getEntry().selectionElement.topRenderer.getSearchTerm();
                search();
            }
            updatePosition(delta);
            lastRectangle = rectangle.clone();
            lastRectangle.translate(0, -1);
        }

        private void updatePosition(float delta) {
            double[] target = {this.target};
            scroll = handleScrollingPosition(target, scroll, getMaxScroll(), delta, start, duration);
            this.target = target[0];
        }

        @Override
        public void lateRender(int mouseX, int mouseY, float delta) {
            int last10Height = getHeight();
            int cWidth = getCellCreator().getCellWidth();
            DrawableHelper.fill(lastRectangle.x, lastRectangle.y + lastRectangle.height, lastRectangle.x + cWidth, lastRectangle.y + lastRectangle.height + last10Height + 1, -6250336);
            DrawableHelper.fill(lastRectangle.x + 1, lastRectangle.y + lastRectangle.height + 1, lastRectangle.x + cWidth - 1, lastRectangle.y + lastRectangle.height + last10Height, -16777216);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 300f);

            ScissorsHandler.INSTANCE.scissor(new Rectangle(lastRectangle.x, lastRectangle.y + lastRectangle.height + 1, cWidth - 6, last10Height - 1));
            double yy = lastRectangle.y + lastRectangle.height - scroll;
            for (SelectionCellElement<R> cell : currentElements) {
                if (yy + getCellCreator().getCellHeight() >= lastRectangle.y + lastRectangle.height && yy <= lastRectangle.y + lastRectangle.height + last10Height + 1)
                    cell.render(mouseX, mouseY, lastRectangle.x, (int) yy, getMaxScrollPosition() > 6 ? getCellCreator().getCellWidth() - 6 : getCellCreator().getCellWidth(), getCellCreator().getCellHeight(), delta);
                else
                    cell.dontRender(delta);
                yy += getCellCreator().getCellHeight();
            }
            ScissorsHandler.INSTANCE.removeLastScissor();

            if (currentElements.isEmpty()) {
                TextRenderer font = MinecraftClient.getInstance().textRenderer;
                String s = I18n.translate("text.cloth-config.dropdown.value.unknown");
                font.drawWithShadow(s, lastRectangle.x + getCellCreator().getCellWidth() / 2f - font.getStringWidth(s) / 2f, lastRectangle.y + lastRectangle.height + 3, -1);
            }

            if (getMaxScrollPosition() > 6) {
                GlStateManager.disableTexture();
                int scrollbarPositionMinX = lastRectangle.x + getCellCreator().getCellWidth() - 6;
                int scrollbarPositionMaxX = scrollbarPositionMinX + 6;
                int height = (int) (((last10Height) * (last10Height)) / this.getMaxScrollPosition());
                height = MathHelper.clamp(height, 32, last10Height - 8);
                height -= Math.min((scroll < 0 ? (int) -scroll : scroll > getMaxScrollPosition() ? (int) scroll - getMaxScrollPosition() : 0), height * .95);
                height = Math.max(10, height);
                int minY = (int) Math.min(Math.max((int) scroll * (last10Height - height) / getMaxScrollPosition() + (lastRectangle.y + lastRectangle.height + 1), (lastRectangle.y + lastRectangle.height + 1)), (lastRectangle.y + lastRectangle.height + 1 + last10Height) - height);

                int bottomc = new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height).contains(PointHelper.ofMouse()) ? 168 : 128;
                int topc = new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height).contains(PointHelper.ofMouse()) ? 222 : 172;

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();

                // Bottom
                buffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
                buffer.vertex(scrollbarPositionMinX, minY + height, 0.0D).texture(0, 1).color(bottomc, bottomc, bottomc, 255).next();
                buffer.vertex(scrollbarPositionMaxX, minY + height, 0.0D).texture(1, 1).color(bottomc, bottomc, bottomc, 255).next();
                buffer.vertex(scrollbarPositionMaxX, minY, 0.0D).texture(1, 0).color(bottomc, bottomc, bottomc, 255).next();
                buffer.vertex(scrollbarPositionMinX, minY, 0.0D).texture(0, 0).color(bottomc, bottomc, bottomc, 255).next();
                tessellator.draw();

                // Top
                buffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
                buffer.vertex(scrollbarPositionMinX, (minY + height - 1), 0.0D).texture(0, 1).color(topc, topc, topc, 255).next();
                buffer.vertex((scrollbarPositionMaxX - 1), (minY + height - 1), 0.0D).texture(1, 1).color(topc, topc, topc, 255).next();
                buffer.vertex((scrollbarPositionMaxX - 1), minY, 0.0D).texture(1, 0).color(topc, topc, topc, 255).next();
                buffer.vertex(scrollbarPositionMinX, minY, 0.0D).texture(0, 0).color(topc, topc, topc, 255).next();
                tessellator.draw();
                GlStateManager.enableTexture();
            }
            GlStateManager.translate(0, 0, -300f);
            GlStateManager.popMatrix();
        }

        @Override
        public int getHeight() {
            return Math.max(Math.min(getCellCreator().getDropBoxMaxHeight(), (int) getMaxScroll()), 14);
        }

        public boolean isMouseOver(double mouseX, double mouseY) {
            return isExpanded() && mouseX >= lastRectangle.x && mouseX <= lastRectangle.x + getCellCreator().getCellWidth() && mouseY >= lastRectangle.y + lastRectangle.height && mouseY <= lastRectangle.y + lastRectangle.height + getHeight() + 1;
        }

        public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
            if (!isExpanded())
                return false;
            if (int_1 == 0 && this.scrolling) {
                if (double_2 < (double) lastRectangle.y + lastRectangle.height) {
                    scrollTo(0, false);
                } else if (double_2 > (double) lastRectangle.y + lastRectangle.height + getHeight()) {
                    scrollTo(getMaxScrollPosition(), false);
                } else {
                    double double_5 = Math.max(1, this.getMaxScrollPosition());
                    int int_2 = getHeight();
                    int int_3 = MathHelper.clamp((int) ((float) (int_2 * int_2) / (float) this.getMaxScrollPosition()), 32, int_2 - 8);
                    double double_6 = Math.max(1.0D, double_5 / (double) (int_2 - int_3));
                    this.offset(double_4 * double_6, false);
                }
                target = MathHelper.clamp(target, 0, getMaxScrollPosition());
                return true;
            }
            return false;
        }

        protected void updateScrollingState(double double_1, double double_2, int int_1) {
            this.scrolling = isExpanded() && lastRectangle != null && int_1 == 0 && double_1 >= (double) lastRectangle.x + getCellCreator().getCellWidth() - 6 && double_1 < (double) (lastRectangle.x + getCellCreator().getCellWidth());
        }

        public void offset(double value, boolean animated) {
            scrollTo(target + value, animated);
        }

        public void scrollTo(double value, boolean animated) {
            scrollTo(value, animated, ClothConfigInitializer.getScrollDuration());
        }

        public void scrollTo(double value, boolean animated, long duration) {
            target = ClothConfigInitializer.clamp(value, getMaxScrollPosition());

            if (animated) {
                start = System.currentTimeMillis();
                this.duration = duration;
            } else
                scroll = target;
        }
    }

    public static abstract class SelectionCellCreator<R> {
        public abstract SelectionCellElement<R> create(R selection);

        public abstract int getCellHeight();

        public abstract int getDropBoxMaxHeight();

        public int getCellWidth() {
            return 132;
        }
    }

    public static class DefaultSelectionCellCreator<R> extends SelectionCellCreator<R> {
        protected Function<R, String> toStringFunction;

        public DefaultSelectionCellCreator(Function<R, String> toStringFunction) {
            this.toStringFunction = toStringFunction;
        }

        public DefaultSelectionCellCreator() {
            this(Object::toString);
        }

        @Override
        public SelectionCellElement<R> create(R selection) {
            return new DefaultSelectionCellElement<>(selection, toStringFunction);
        }

        @Override
        public int getCellHeight() {
            return 14;
        }

        @Override
        public int getDropBoxMaxHeight() {
            return getCellHeight() * 7;
        }
    }

    public static abstract class SelectionCellElement<R> {
        @Deprecated
        private DropdownBoxEntry<R> entry;


        public final DropdownBoxEntry<R> getEntry() {
            return entry;
        }

        public abstract void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta);

        public abstract void dontRender(float delta);

        public abstract String getSearchKey();

        public abstract R getSelection();
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

        @Override
        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
            rendering = true;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            if (b)
                DrawableHelper.fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
            MinecraftClient.getInstance().textRenderer.drawWithShadow(toStringFunction.apply(r), x + 6, y + 3, b ? 16777215 : 8947848);
        }

        @Override
        public void dontRender(float delta) {
            rendering = false;
        }

        @Override
        public String getSearchKey() {
            return toStringFunction.apply(r);
        }

        @Override
        public R getSelection() {
            return r;
        }

        public boolean mouseClicked(double mouseX, double mouseY, int int_1) {
            boolean b = rendering && mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            if (b) {
                getEntry().selectionElement.topRenderer.setValue(r);
                getEntry().selectionElement.dontReFocus = true;
                return true;
            }
            return false;
        }
    }

    public static abstract class SelectionTopCellElement<R> {
        protected boolean isSelected = false;
        @Deprecated
        private DropdownBoxEntry<R> entry;

        public abstract R getValue();

        public abstract void setValue(R value);

        public abstract String getSearchTerm();

        public abstract Optional<String> getError();

        public final Optional<String> getConfigError() {
            return entry.getConfigError();
        }

        public DropdownBoxEntry<R> getParent() {
            return entry;
        }

        public final boolean hasConfigError() {
            return getConfigError().isPresent();
        }

        public final int getPreferredTextColor() {
            return getConfigError().isPresent() ? 16733525 : 16777215;
        }

        public void selectFirstRecommendation() {
            List<SelectionCellElement<R>> children = getParent().selectionElement.menu.getCells();
            for (SelectionCellElement<R> child : children) {
                if (child.getSelection() != null) {
                    setValue(child.getSelection());
                    break;
                }
            }
        }

        public abstract void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta);
    }

    public static class DefaultSelectionTopCellElement<R> extends SelectionTopCellElement<R> {
        protected TextFieldWidget textFieldWidget;
        protected Function<String, R> toObjectFunction;
        protected Function<R, String> toStringFunction;
        protected R value;

        public DefaultSelectionTopCellElement(R value, Function<String, R> toObjectFunction, Function<R, String> toStringFunction) {
            this.value = Objects.requireNonNull(value);
            this.toObjectFunction = Objects.requireNonNull(toObjectFunction);
            this.toStringFunction = Objects.requireNonNull(toStringFunction);
            textFieldWidget = new TextFieldWidget(new Random().nextInt(), MinecraftClient.getInstance().textRenderer, 0, 0, 148, 18) {
                @Override
                public void render() {
                    setFocused(isSelected);
                    super.render();
                }

                @Override
                public boolean keyPressed(char typedChar, int keyCode) {
                    if (keyCode == 28 || keyCode == 156) {
                        DefaultSelectionTopCellElement.this.selectFirstRecommendation();
                        return true;
                    }
                    return super.keyPressed(typedChar, keyCode);
                }
            };
            textFieldWidget.setHasBorder(false);
            textFieldWidget.setMaxLength(999999);
            textFieldWidget.setText(toStringFunction.apply(value));
            textFieldWidget.setTextPredicate((s) -> {
                if (getParent() != null && getParent().getScreen() != null && !toStringFunction.apply(value).equals(s))
                    getParent().getScreen().setEdited(true, getParent().isRequiresRestart());
                return false;
            });
        }

        @Override
        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
            textFieldWidget.x = x + 4;
            textFieldWidget.y = y + 6;
            textFieldWidget.setVisible(getParent().isEditable());
            textFieldWidget.setEditable(getParent().isEditable());
            textFieldWidget.setEditableColor(getPreferredTextColor());
            textFieldWidget.render();
        }

        @Override
        public R getValue() {
            if (hasConfigError())
                return value;
            return toObjectFunction.apply(textFieldWidget.getText());
        }

        @Override
        public void setValue(R value) {
            textFieldWidget.setText(toStringFunction.apply(value));
            textFieldWidget.setCursor(0);
        }

        @Override
        public String getSearchTerm() {
            return textFieldWidget.getText();
        }

        @Override
        public Optional<String> getError() {
            if (toObjectFunction.apply(textFieldWidget.getText()) != null)
                return Optional.empty();
            return Optional.of("Invalid Value!");
        }
    }
}
