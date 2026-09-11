package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import me.shedaniel.forge.clothconfig2.api.QueuedTooltip;
import me.shedaniel.forge.math.Point;
import me.shedaniel.forge.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @param <T>    the configuration object type
 * @param <C>    the cell type
 * @param <SELF> the "curiously recurring template pattern" type parameter
 * @implNote See <a href="https://stackoverflow.com/questions/7354740/is-there-a-way-to-refer-to-the-current-type-with-a-type-variable">Is there a way to refer to the current type with a type variable?</href> on Stack Overflow.
 */
@SideOnly(Side.CLIENT)
public abstract class BaseListEntry<T, C extends BaseListCell, SELF extends BaseListEntry<T, C, SELF>> extends TooltipListEntry<List<T>> {

    protected static final ResourceLocation CONFIG_TEX = new ResourceLocation("cloth-config2", "textures/gui/cloth_config.png");
    protected final List<C> cells;
    protected final List<Object> widgets;
    protected boolean expanded;
    protected boolean deleteButtonEnabled;
    protected boolean insertInFront;
    @Nullable
    protected Consumer<List<T>> saveConsumer;
    protected ListLabelWidget labelWidget;
    protected GuiButton resetWidget;
    protected Function<SELF, C> createNewInstance;
    protected Supplier<List<T>> defaultValue;
    @Nullable
    protected String addTooltip = I18n.format("text.cloth-config.list.add"), removeTooltip = I18n.format("text.cloth-config.list.remove");


    @Deprecated
    public BaseListEntry(String fieldName,
                         @Nullable Supplier<Optional<String[]>> tooltipSupplier, Supplier<List<T>> defaultValue, Function<SELF, C> createNewInstance,
                         @Nullable Consumer<List<T>> saveConsumer, String resetButtonKey) {
        this(fieldName, tooltipSupplier, defaultValue, createNewInstance, saveConsumer, resetButtonKey, false);
    }


    @Deprecated
    public BaseListEntry(String fieldName,
                         @Nullable Supplier<Optional<String[]>> tooltipSupplier, Supplier<List<T>> defaultValue, Function<SELF, C> createNewInstance,
                         @Nullable Consumer<List<T>> saveConsumer, String resetButtonKey, boolean requiresRestart) {
        this(fieldName, tooltipSupplier, defaultValue, createNewInstance, saveConsumer, resetButtonKey, requiresRestart, true, true);
    }


    @Deprecated
    public BaseListEntry(String fieldName,
                         @Nullable Supplier<Optional<String[]>> tooltipSupplier, Supplier<List<T>> defaultValue, Function<SELF, C> createNewInstance,
                         @Nullable Consumer<List<T>> saveConsumer, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.deleteButtonEnabled = deleteButtonEnabled;
        this.insertInFront = insertInFront;
        this.cells = Lists.newArrayList();
        this.labelWidget = new ListLabelWidget();
        this.widgets = Lists.newArrayList(labelWidget);
        this.resetWidget = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
                
                 boolean bl = super.func_146116_c(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    widgets.removeAll(cells);
                    cells.clear();
                    defaultValue.get().stream().map((val) -> createNewInstance.apply((SELF) val)).forEach(cells::add);
                    widgets.addAll(cells);
                    getScreen().setEdited(true, isRequiresRestart());
                } return bl;
            }
        };
        this.widgets.add(resetWidget);
        this.saveConsumer = saveConsumer;
        this.createNewInstance = createNewInstance;
        this.defaultValue = defaultValue;
    }

    public abstract SELF self();

    public boolean isDeleteButtonEnabled() {
        return deleteButtonEnabled;
    }

    protected abstract C getFromValue(T value);


    public Function<SELF, C> getCreateNewInstance() {
        return createNewInstance;
    }

    public void setCreateNewInstance(Function<SELF, C> createNewInstance) {
        this.createNewInstance = createNewInstance;
    }

    @Nullable
    public String getAddTooltip() {
        return addTooltip;
    }

    public void setAddTooltip(@Nullable String addTooltip) {
        this.addTooltip = addTooltip;
    }

    @Nullable
    public String getRemoveTooltip() {
        return removeTooltip;
    }

    public void setRemoveTooltip(@Nullable String removeTooltip) {
        this.removeTooltip = removeTooltip;
    }

    @Override
    public Optional<List<T>> getDefaultValue() {
        return Optional.ofNullable(defaultValue.get());
    }

    @Override
    public int getItemHeight() {
        if (expanded) {
            int i = 24;
            for (BaseListCell entry : cells)
                i += entry.getCellHeight();
            return i;
        }
        return 24;
    }

    @Override
    public Optional<String> getError() {
        List<String> errors = cells.stream().map(C::getConfigError).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        if (errors.size() > 1)
            return Optional.of(I18n.format("text.cloth-config.multi_error"));
        else
            return errors.stream().findFirst();
    }

    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }

    @Override
    public boolean isMouseInside(int mouseX, int mouseY, int x, int y, int entryWidth, int entryHeight) {
        labelWidget.rectangle.x = x - 15;
        labelWidget.rectangle.y = y;
        labelWidget.rectangle.width = entryWidth + 15;
        labelWidget.rectangle.height = 24;
        return labelWidget.rectangle.contains(mouseX, mouseY) && getParent().isMouseOver(mouseX, mouseY) && !resetWidget.isMouseOver();
    }

    protected boolean isInsideCreateNew(double mouseX, double mouseY) {
        return mouseX >= labelWidget.rectangle.x + 12 && mouseY >= labelWidget.rectangle.y + 3 && mouseX <= labelWidget.rectangle.x + 12 + 11 && mouseY <= labelWidget.rectangle.y + 3 + 11;
    }

    protected boolean isInsideDelete(double mouseX, double mouseY) {
        return isDeleteButtonEnabled() && mouseX >= labelWidget.rectangle.x + 25 && mouseY >= labelWidget.rectangle.y + 3 && mouseX <= labelWidget.rectangle.x + 25 + 11 && mouseY <= labelWidget.rectangle.y + 3 + 11;
    }

    public Optional<String[]> getTooltip(int mouseX, int mouseY) {
        if (addTooltip != null && isInsideCreateNew(mouseX, mouseY))
            return Optional.of(new String[]{addTooltip});
        if (removeTooltip != null && isInsideDelete(mouseX, mouseY))
            return Optional.of(new String[]{removeTooltip});
        if (getTooltipSupplier() != null)
            return getTooltipSupplier().get();
        return Optional.empty();
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        labelWidget.rectangle.x = x - 19;
        labelWidget.rectangle.y = y;
        labelWidget.rectangle.width = entryWidth + 19;
        labelWidget.rectangle.height = 24;
        if (isMouseInside(mouseX, mouseY, x, y, entryWidth, entryHeight)) {
            Optional<String[]> tooltip = getTooltip(mouseX, mouseY);
            if (tooltip.isPresent() && tooltip.get().length > 0)
                getScreen().queueTooltip(QueuedTooltip.create(new Point(mouseX, mouseY), tooltip.get()));
        }
        Minecraft.getInstance().getTextureManager().bindTexture(CONFIG_TEX);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color4f(1, 1, 1, 1);
        boolean insideCreateNew = isInsideCreateNew(mouseX, mouseY);
        boolean insideDelete = isInsideDelete(mouseX, mouseY);
        drawTexturedModalRect(x - 15, y + 4, 24 + 9, (labelWidget.rectangle.contains(mouseX, mouseY) && !insideCreateNew && !insideDelete ? 18 : 0) + (expanded ? 9 : 0), 9, 9);
        drawTexturedModalRect(x - 15 + 13, y + 4, 24 + 18, insideCreateNew ? 9 : 0, 9, 9);
        if (isDeleteButtonEnabled())
            drawTexturedModalRect(x - 15 + 26, y + 4, 24 + 27, insideDelete ? 18 : 9, 9, 9);
        resetWidget.x = x + entryWidth - resetWidget.getWidth();
        resetWidget.y = y;
        resetWidget.enabled = isEditable() && getDefaultValue().isPresent();
        resetWidget.func_146112_a(Minecraft.getInstance(), mouseX, mouseY);
        Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), isDeleteButtonEnabled() ? x + 24 : x + 24 - 9, y + 5, labelWidget.rectangle.contains(mouseX, mouseY) && !resetWidget.isMouseOver() && !insideDelete && !insideCreateNew ? 0xffe6fe16 : getPreferredTextColor());
        if (expanded) {
            int yy = y + 24;
            for (BaseListCell cell : cells) {
                cell.render(-1, yy, x + 14, entryWidth - 14, cell.getCellHeight(), mouseX, mouseY, isSelected, delta);
                yy += cell.getCellHeight();
            }
        }
    }

    @Override
    public void updateSelected(boolean isSelected) {
        for (C cell : cells) {
            cell.updateSelected(isSelected && expanded);
        }
    }

    public boolean insertInFront() {
        return insertInFront;
    }

    public class ListLabelWidget {
        protected Rectangle rectangle = new Rectangle();

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            if (resetWidget.isMouseOver()) {
                return false;
            } else if (isInsideCreateNew(double_1, double_2)) {
                expanded = true;
                C cell;
                if (insertInFront()) {
                    cells.add(0, cell = createNewInstance.apply(BaseListEntry.this.self()));
                    widgets.add(0, cell);
                } else {
                    cells.add(cell = createNewInstance.apply(BaseListEntry.this.self()));
                    widgets.add(cell);
                }
                getScreen().setEdited(true, isRequiresRestart());
                Minecraft.getInstance().getSoundHandler().play(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            } else if (isDeleteButtonEnabled() && isInsideDelete(double_1, double_2)) {
                if (expanded && !cells.isEmpty()) {
                    C cell = cells.get(cells.size() - 1);
                    cells.remove(cell);
                    widgets.remove(cell);
                    getScreen().setEdited(true, isRequiresRestart());
                    Minecraft.getInstance().getSoundHandler().play(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                }
                return true;
            } else if (rectangle.contains(double_1, double_2)) {
                expanded = !expanded;
                Minecraft.getInstance().getSoundHandler().play(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }
            return false;
        }
    }
    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.resetWidget.func_146116_c(Minecraft.getInstance(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetWidget.func_146116_c(Minecraft.getInstance(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
