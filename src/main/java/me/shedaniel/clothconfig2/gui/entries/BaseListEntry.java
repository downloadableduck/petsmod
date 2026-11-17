//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.compat.GlStateManager;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.api.QueuedTooltip;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseListEntry<T, C extends BaseListCell, SELF extends BaseListEntry<T, C, SELF>> extends TooltipListEntry<List<T>> {
    protected static final String CONFIG_TEX = "textures/gui/cloth_config.png";
    protected final @NotNull List<C> cells;
    protected final @NotNull List<GuiEventListener> widgets;
    protected boolean expanded;
    protected boolean deleteButtonEnabled;
    protected boolean insertInFront;
    protected @Nullable Consumer<List<T>> saveConsumer;
    protected ListLabelWidget labelWidget;
    protected ButtonWidget resetWidget;
    protected @NotNull Function<SELF, C> createNewInstance;
    protected @NotNull Supplier<List<T>> defaultValue;
    protected @Nullable String addTooltip;
    protected @Nullable String removeTooltip;

    /** @deprecated */
    @Deprecated
    @Internal
    public BaseListEntry(@NotNull String fieldName, @Nullable Supplier<Optional<String[]>> tooltipSupplier, @NotNull Supplier<List<T>> defaultValue, @NotNull Function<SELF, C> createNewInstance, @Nullable Consumer<List<T>> saveConsumer, String resetButtonKey) {
        this(fieldName, tooltipSupplier, defaultValue, createNewInstance, saveConsumer, resetButtonKey, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public BaseListEntry(@NotNull String fieldName, @Nullable Supplier<Optional<String[]>> tooltipSupplier, @NotNull Supplier<List<T>> defaultValue, @NotNull Function<SELF, C> createNewInstance, @Nullable Consumer<List<T>> saveConsumer, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, tooltipSupplier, defaultValue, createNewInstance, saveConsumer, resetButtonKey, requiresRestart, true, true);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public BaseListEntry(@NotNull String fieldName, @Nullable Supplier<Optional<String[]>> tooltipSupplier, @NotNull Supplier<List<T>> defaultValue, @NotNull Function<SELF, C> createNewInstance, @Nullable Consumer<List<T>> saveConsumer, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.addTooltip = I18n.getTranslation("text.cloth-config.list.add");
        this.removeTooltip = I18n.getTranslation("text.cloth-config.list.remove");
        this.deleteButtonEnabled = deleteButtonEnabled;
        this.insertInFront = insertInFront;
        this.cells = Lists.newArrayList();
        this.labelWidget = new ListLabelWidget();
        this.widgets = Lists.newArrayList(new GuiEventListener[]{this.labelWidget});
        this.resetWidget = new ButtonWidget(0, 0, Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (widget) -> {
            this.widgets.removeAll(this.cells);
            this.cells.clear();
            java.util.stream.Stream<C> var10000 = ((List<T>)defaultValue.get()).stream().map(this::getFromValue);
            var10000.forEach(this.cells::add);
            this.widgets.addAll(this.cells);
            this.getScreen().setEdited(true, this.isRequiresRestart());
        });
        this.widgets.add(this.resetWidget);
        this.saveConsumer = saveConsumer;
        this.createNewInstance = createNewInstance;
        this.defaultValue = defaultValue;
    }

    public abstract SELF self();

    public boolean isDeleteButtonEnabled() {
        return this.deleteButtonEnabled;
    }

    protected abstract C getFromValue(T var1);

    public @NotNull Function<SELF, C> getCreateNewInstance() {
        return this.createNewInstance;
    }

    public void setCreateNewInstance(@NotNull Function<SELF, C> createNewInstance) {
        this.createNewInstance = createNewInstance;
    }

    public @Nullable String getAddTooltip() {
        return this.addTooltip;
    }

    public void setAddTooltip(@Nullable String addTooltip) {
        this.addTooltip = addTooltip;
    }

    public @Nullable String getRemoveTooltip() {
        return this.removeTooltip;
    }

    public void setRemoveTooltip(@Nullable String removeTooltip) {
        this.removeTooltip = removeTooltip;
    }

    public Optional<List<T>> getDefaultValue() {
        return Optional.ofNullable((List)this.defaultValue.get());
    }

    public int getItemHeight() {
        if (!this.expanded) {
            return 24;
        } else {
            int i = 24;

            BaseListCell entry;
            for(Iterator var2 = this.cells.iterator(); var2.hasNext(); i += entry.getCellHeight()) {
                entry = (BaseListCell)var2.next();
            }

            return i;
        }
    }

    public List<? extends GuiEventListener> children() {
        if (!this.expanded) {
            List<GuiEventListener> elements = new ArrayList(this.widgets);
            elements.removeAll(this.cells);
            return elements;
        } else {
            return this.widgets;
        }
    }

    public Optional<String> getError() {
        List<String> errors = (List)this.cells.stream().map(BaseListCell::getConfigError).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return errors.size() > 1 ? Optional.of(I18n.getTranslation("text.cloth-config.multi_error")) : errors.stream().findFirst();
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept((List)this.getValue());
        }

    }

    public boolean isMouseInside(int mouseX, int mouseY, int x, int y, int entryWidth, int entryHeight) {
        this.labelWidget.rectangle.x = x - 15;
        this.labelWidget.rectangle.y = y;
        this.labelWidget.rectangle.width = entryWidth + 15;
        this.labelWidget.rectangle.height = 24;
        return this.labelWidget.rectangle.contains(mouseX, mouseY) && this.getParent().isMouseOver((double)mouseX, (double)mouseY) && !this.resetWidget.wrapDegrees((double)mouseX, (double)mouseY);
    }

    protected boolean isInsideCreateNew(double mouseX, double mouseY) {
        return mouseX >= (double)(this.labelWidget.rectangle.x + 12) && mouseY >= (double)(this.labelWidget.rectangle.y + 3) && mouseX <= (double)(this.labelWidget.rectangle.x + 12 + 11) && mouseY <= (double)(this.labelWidget.rectangle.y + 3 + 11);
    }

    protected boolean isInsideDelete(double mouseX, double mouseY) {
        return this.isDeleteButtonEnabled() && mouseX >= (double)(this.labelWidget.rectangle.x + 25) && mouseY >= (double)(this.labelWidget.rectangle.y + 3) && mouseX <= (double)(this.labelWidget.rectangle.x + 25 + 11) && mouseY <= (double)(this.labelWidget.rectangle.y + 3 + 11);
    }

    public Optional<String[]> getTooltip(int mouseX, int mouseY) {
        if (this.addTooltip != null && this.isInsideCreateNew((double)mouseX, (double)mouseY)) {
            return Optional.of(new String[]{this.addTooltip});
        } else if (this.removeTooltip != null && this.isInsideDelete((double)mouseX, (double)mouseY)) {
            return Optional.of(new String[]{this.removeTooltip});
        } else {
            return this.getTooltipSupplier() != null ? (Optional)this.getTooltipSupplier().get() : Optional.empty();
        }
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        this.labelWidget.rectangle.x = x - 19;
        this.labelWidget.rectangle.y = y;
        this.labelWidget.rectangle.width = entryWidth + 19;
        this.labelWidget.rectangle.height = 24;
        if (this.isMouseInside(mouseX, mouseY, x, y, entryWidth, entryHeight)) {
            Optional<String[]> tooltip = this.getTooltip(mouseX, mouseY);
            if (tooltip.isPresent() && ((String[])tooltip.get()).length > 0) {
                this.getScreen().queueTooltip(QueuedTooltip.create(new Point(mouseX, mouseY), (String[])tooltip.get()));
            }
        }

        Minecraft.INSTANCE.textureManager.getTextureId("textures/gui/cloth_config.png");
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        BaseListCell focused = this.expanded && this.getFocused() != null && this.getFocused() instanceof BaseListCell ? (BaseListCell)this.getFocused() : null;
        boolean insideCreateNew = this.isInsideCreateNew((double)mouseX, (double)mouseY);
        boolean insideDelete = this.isInsideDelete((double)mouseX, (double)mouseY);
        this.drawTexture(x - 15, y + 4, 33, (this.labelWidget.rectangle.contains(mouseX, mouseY) && !insideCreateNew && !insideDelete ? 18 : 0) + (this.expanded ? 9 : 0), 9, 9);
        this.drawTexture(x - 15 + 13, y + 4, 42, insideCreateNew ? 9 : 0, 9, 9);
        if (this.isDeleteButtonEnabled()) {
            this.drawTexture(x - 15 + 26, y + 4, 51, focused == null ? 0 : (insideDelete ? 18 : 9), 9, 9);
        }

        this.resetWidget.x = x + entryWidth - this.resetWidget.width;
        this.resetWidget.y = y;
        this.resetWidget.active = this.isEditable() && this.getDefaultValue().isPresent();
        this.resetWidget.render(mouseX, mouseY, delta);
        Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), this.isDeleteButtonEnabled() ? x + 24 : x + 24 - 9, y + 5, this.labelWidget.rectangle.contains(mouseX, mouseY) && !this.resetWidget.wrapDegrees((double)mouseX, (double)mouseY) && !insideDelete && !insideCreateNew ? -1638890 : this.getPreferredTextColor());
        if (this.expanded) {
            int yy = y + 24;

            C cell;
            for(Iterator<C> var14 = this.cells.iterator(); var14.hasNext(); yy += cell.getCellHeight()) {
                cell = var14.next();
                cell.render(-1, yy, x + 14, entryWidth - 14, cell.getCellHeight(), mouseX, mouseY, this.getParent().getFocused() != null && this.getParent().getFocused().equals(this) && this.getFocused() != null && this.getFocused().equals(cell), delta);
            }
        }

    }

    public void updateSelected(boolean isSelected) {
        Iterator<C> var2 = this.cells.iterator();

        while(var2.hasNext()) {
            C cell = var2.next();
            cell.updateSelected(isSelected && this.getFocused() == cell && this.expanded);
        }

    }

    public boolean insertInFront() {
        return this.insertInFront;
    }

    public class ListLabelWidget implements GuiEventListener {
        protected Rectangle rectangle = new Rectangle();

        public ListLabelWidget() {
        }

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            if (BaseListEntry.this.resetWidget.wrapDegrees(double_1, double_2)) {
                return false;
            } else if (BaseListEntry.this.isInsideCreateNew(double_1, double_2)) {
                BaseListEntry.this.expanded = true;
                C cell;
                if (BaseListEntry.this.insertInFront()) {
                    BaseListEntry.this.cells.add(0, cell = BaseListEntry.this.createNewInstance.apply(BaseListEntry.this.self()));
                    BaseListEntry.this.widgets.add(0, cell);
                } else {
                    BaseListEntry.this.cells.add(cell = BaseListEntry.this.createNewInstance.apply(BaseListEntry.this.self()));
                    BaseListEntry.this.widgets.add(cell);
                }

                BaseListEntry.this.getScreen().setEdited(true, BaseListEntry.this.isRequiresRestart());
                Minecraft.INSTANCE.soundManager.playSound("gui.button.press", 1.0F, 1.0F);
                return true;
            } else if (BaseListEntry.this.isDeleteButtonEnabled() && BaseListEntry.this.isInsideDelete(double_1, double_2)) {
                GuiEventListener focused = BaseListEntry.this.getFocused();
                if (BaseListEntry.this.expanded && focused instanceof BaseListCell) {
                    BaseListEntry.this.cells.remove(focused);
                    BaseListEntry.this.widgets.remove(focused);
                    BaseListEntry.this.getScreen().setEdited(true, BaseListEntry.this.isRequiresRestart());
                    Minecraft.INSTANCE.soundManager.playSound("gui.button.press", 1.0F, 1.0F);
                }

                return true;
            } else if (this.rectangle.contains(double_1, double_2)) {
                BaseListEntry.this.expanded = !BaseListEntry.this.expanded;
                Minecraft.INSTANCE.soundManager.playSound("gui.button.press", 1.0F, 1.0F);
                return true;
            } else {
                return false;
            }
        }
    }
}
