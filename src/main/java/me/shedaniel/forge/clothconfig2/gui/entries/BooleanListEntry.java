package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class BooleanListEntry extends TooltipListEntry<Boolean> {
    
    private final AtomicBoolean bool;
    private final GuiButton buttonWidget;
    private final GuiButton resetButton;
    private final Consumer<Boolean> saveConsumer;
    private final Supplier<Boolean> defaultValue;
    private final List<IGuiEventListener> widgets;
    
    
    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, Consumer<Boolean> saveConsumer) {
        this(fieldName, bool, "text.cloth-config.reset_value", null, saveConsumer);
    }
    
    
    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer) {
        this(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, null);
    }
    
    
    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
    }
    
    
    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.bool = new AtomicBoolean(bool);
        this.buttonWidget = new GuiButton(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                BooleanListEntry.this.bool.set(!BooleanListEntry.this.bool.get());
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
            }
        };
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                BooleanListEntry.this.bool.set(defaultValue.get());
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
            }
        };
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(buttonWidget, resetButton);
    }
    
    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }
    
    @Override
    public Boolean getValue() {
        return bool.get();
    }
    
    @Override
    public Optional<Boolean> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }
    
    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        MainWindow window = Minecraft.getMinecraft().mainWindow;
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != bool.get();
        this.resetButton.y = y;
        this.buttonWidget.enabled = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.displayString = (getYesNoText(bool.get()));
        if (Minecraft.getMinecraft().fontRenderer.getBidiFlag()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), window.getScaledWidth() - x - Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.buttonWidget.x = x + resetButton.getButtonWidth() + 2;
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getButtonWidth();
            this.buttonWidget.x = x + entryWidth - 150;
        }
        this.buttonWidget.setWidth(150 - resetButton.getButtonWidth() - 2);
        resetButton.drawButton(mouseX, mouseY, delta);
        buttonWidget.drawButton(mouseX, mouseY, delta);
    }
    
    public String getYesNoText(boolean bool) {
        return I18n.format("text.cloth-config.boolean.value." + bool);
    }
    
    @Override
    public List<? extends IGuiEventListener> getEventListeners() {
        return widgets;
    }
    
}
