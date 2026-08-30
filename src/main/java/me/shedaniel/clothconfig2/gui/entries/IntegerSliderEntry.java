package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.clothconfig2.ButtonWidget;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.gui.GuiEventListener;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.gui.widget.OptionSliderWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.render.platform.Window;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.platform.Window;

import java.util.List;
import net.minecraft.client.render.platform.Window;
import java.util.Optional;
import net.minecraft.client.render.platform.Window;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.render.platform.Window;
import java.util.function.Consumer;
import net.minecraft.client.render.platform.Window;
import java.util.function.Function;
import net.minecraft.client.render.platform.Window;
import java.util.function.Supplier;
import net.minecraft.client.render.platform.Window;

public class IntegerSliderEntry extends TooltipListEntry {
    
    protected Slider sliderWidget;
    protected ButtonWidget resetButton;
    protected AtomicInteger value;
    private int minimum, maximum;
    private Consumer<Integer> saveConsumer;
    private Supplier<Integer> defaultValue;
    private Function<Integer, String> textGetter = integer -> String.format("Value: %d", integer);
    private List<GuiEventListener> widgets;
    
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, Consumer<Integer> saveConsumer) {
        this(fieldName, minimum, maximum, value, "text.cloth-config.reset_value", null, saveConsumer);
    }
    
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer) {
        this(fieldName, minimum, maximum, value, resetButtonKey, defaultValue, saveConsumer, null, false);
    }
    
    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = new AtomicInteger(value);
        this.saveConsumer = saveConsumer;
        this.maximum = maximum;
        this.minimum = minimum;
        this.sliderWidget = new Slider(0, 0, 152, 20, ((double) this.value.get() - minimum) / Math.abs(maximum - minimum));
        this.resetButton = new ButtonWidget(0, 0, Minecraft.getInstance().textRenderer.getWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey), widget -> {
            sliderWidget.setProgress((MathHelper.clamp(this.defaultValue.get(), minimum, maximum) - minimum) / (double) Math.abs(maximum - minimum));
            this.value.set(MathHelper.clamp(this.defaultValue.get(), minimum, maximum));
            sliderWidget.update();
            getScreen().setEdited(true);
        });
        this.sliderWidget.message = textGetter.apply(IntegerSliderEntry.this.value.get());
        this.widgets = Lists.newArrayList(sliderWidget, resetButton);
    }
    
    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getItemHeight());
    }
    
    public Function<Integer, String> getTextGetter() {
        return textGetter;
    }
    
    public IntegerSliderEntry setTextGetter(Function<Integer, String> textGetter) {
        this.textGetter = textGetter;
        return this;
    }
    
    @Override
    public Integer getValue() {
        return value.get();
    }
    
    @Override
    public Optional<Object> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }
    
    @Override
    public List<? extends GuiEventListener> children() {
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
        Window window = Minecraft.getInstance().window;
        this.resetButton.active = isEditable() && getDefaultValue().isPresent() && defaultValue.get().intValue() != value.get();
        this.resetButton.y = y;
        this.sliderWidget.active = isEditable();
        this.sliderWidget.y = y;
        if (Minecraft.getInstance().textRenderer.isBidirectional()) {
            Minecraft.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), window.getGuiScaledWidth() - x - Minecraft.getInstance().textRenderer.getWidth(I18n.translate(getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.sliderWidget.x = x + resetButton.getWidth() + 1;
            // this.sliderWidget.setWidth(150 - resetButton.getWidth() - 2); // Not available in 1.13
        } else {
            Minecraft.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), x, y + 5, 16777215);
            this.resetButton.x = x + entryWidth - resetButton.getWidth();
            this.sliderWidget.x = x + entryWidth - 150;
            // this.sliderWidget.setWidth(150 - resetButton.getWidth() - 2); // Not available in 1.13
        }
        resetButton.render(mouseX, mouseY, delta);
        sliderWidget.render(mouseX, mouseY, delta);
    }
    
    private class Slider extends OptionSliderWidget {
        private double progress;
        
        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(0, int_2, int_3, GameOptions.Option.FOV, minimum, maximum);
            this.progress = double_1;
        }
        
        public void update() {
            this.message = textGetter.apply(IntegerSliderEntry.this.value.get());
            IntegerSliderEntry.this.value.set((int) (minimum + Math.abs(maximum - minimum) * this.progress));
            getScreen().setEdited(true);
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
            return this.progress;
        }
        
        public void setProgress(double integer) {
            this.progress = integer;
        }
    }
    
}
