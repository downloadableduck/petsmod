package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.GameSettings;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class IntegerSliderEntry extends TooltipListEntry<Integer> {
    
    protected Slider sliderWidget;
    protected GuiButton resetButton;
    protected AtomicInteger value;
    private int minimum, maximum;
    private Consumer<Integer> saveConsumer;
    private Supplier<Integer> defaultValue;
    private Function<Integer, String> textGetter = integer -> String.format("Value: %d", integer);
    private List<IGuiEventListener> widgets;
    
    
    @Deprecated
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, Consumer<Integer> saveConsumer) {
        this(fieldName, minimum, maximum, value, "text.cloth-config.reset_value", null, saveConsumer);
    }
    
    
    @Deprecated
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer) {
        this(fieldName, minimum, maximum, value, resetButtonKey, defaultValue, saveConsumer, null);
    }
    
    
    @Deprecated
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, minimum, maximum, value, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
    }
    
    
    @Deprecated
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = new AtomicInteger(value);
        this.saveConsumer = saveConsumer;
        this.maximum = maximum;
        this.minimum = minimum;
        this.sliderWidget = new Slider(0, 0, 152, 20, ((double) this.value.get() - minimum) / Math.abs(maximum - minimum));
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                sliderWidget.setProgress((MathHelper.clamp(IntegerSliderEntry.this.defaultValue.get(), minimum, maximum) - minimum) / (double) Math.abs(maximum - minimum));
                IntegerSliderEntry.this.value.set(MathHelper.clamp(IntegerSliderEntry.this.defaultValue.get(), minimum, maximum));
                sliderWidget.updateMessage();
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
            }
        };
        this.sliderWidget.displayString = (textGetter.apply(IntegerSliderEntry.this.value.get()));
        this.widgets = Lists.newArrayList(sliderWidget, resetButton);
    }
    
    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }
    
    public Function<Integer, String> getTextGetter() {
        return textGetter;
    }
    
    public IntegerSliderEntry setTextGetter(Function<Integer, String> textGetter) {
        this.textGetter = textGetter;
        this.sliderWidget.displayString = (textGetter.apply(IntegerSliderEntry.this.value.get()));
        return this;
    }
    
    @Override
    public Integer getValue() {
        return value.get();
    }
    
    @Override
    public Optional<Integer> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }
    
    @Override
    public List<? extends IGuiEventListener> getEventListeners() {
        return widgets;
    }
    
    public IntegerSliderEntry setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }
    
    public IntegerSliderEntry setMinimum(int minimum) {
        this.minimum = minimum;
        return this;
    }
    
    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        MainWindow window = Minecraft.getMinecraft().mainWindow;
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.y = y;
        this.sliderWidget.enabled = isEditable();
        this.sliderWidget.y = y;
        if (Minecraft.getMinecraft().fontRenderer.getBidiFlag()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), window.getScaledWidth() - x - Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.sliderWidget.x = x + resetButton.getButtonWidth() + 1;
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getButtonWidth();
            this.sliderWidget.x = x + entryWidth - 150;
        }
        this.sliderWidget.setWidth(150 - resetButton.getButtonWidth() - 2);
        resetButton.drawButton(mouseX, mouseY, delta);
        sliderWidget.drawButton(mouseX, mouseY, delta);
    }
    
    private class Slider extends GuiOptionSlider {
        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, GameSettings.Options.MIPMAP_LEVELS, int_4, double_1);
        }

        public void updateMessage() {
            displayString = (textGetter.apply(IntegerSliderEntry.this.value.get()));
        }
        
        @Override
        public boolean mouseClicked(double d, double c, int e) {
            IntegerSliderEntry.this.value.set((minimum + Math.abs(maximum - minimum) * value.get()));
            getScreen().setEdited(true, isRequiresRestart());
            return super.mouseClicked(d, c, e);
        }
        
        @Override
        public boolean keyPressed(int int_1, int int_2, int int_3) {
            if (!isEditable())
                return false;
            return super.keyPressed(int_1, int_2, int_3);
        }
        
        @Override
        public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
            if (!isEditable())
                return false;
            return super.mouseDragged(double_1, double_2, int_1, double_3, double_4);
        }
        
        public double getProgress() {
            return value.doubleValue();
        }
        
        public void setProgress(double integer) {
            value.set((int) integer);
        }
    }
    
}
