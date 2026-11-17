//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.option.Option;
import net.minecraft.client.resource.language.I18n;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LongSliderEntry extends TooltipListEntry {
    protected Slider sliderWidget;
    protected ButtonWidget resetButton;
    protected AtomicLong value;
    private long minimum;
    private long maximum;
    private Consumer<Long> saveConsumer;
    private Supplier<Long> defaultValue;
    private Function<Long, String> textGetter;
    private List<GuiEventListener> widgets;

    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer) {
        this(fieldName, minimum, maximum, value, saveConsumer, "text.cloth-config.reset_value", (Supplier)null);
    }

    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer, String resetButtonKey, Supplier<Long> defaultValue) {
        this(fieldName, minimum, maximum, value, saveConsumer, resetButtonKey, defaultValue, (Supplier)null, false);
    }

    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer, String resetButtonKey, Supplier<Long> defaultValue, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.textGetter = (valuex) -> {
            return String.format("Value: %d", valuex);
        };
        this.defaultValue = defaultValue;
        this.value = new AtomicLong(value);
        this.saveConsumer = saveConsumer;
        this.maximum = maximum;
        this.minimum = minimum;
        this.sliderWidget = new Slider(0, 0, 152, 20, ((double)this.value.get() - (double)minimum) / (double)Math.abs(maximum - minimum));
        this.resetButton = new ButtonWidget(0, 0, Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (widget) -> {
            long def = (Long)this.defaultValue.get();
            long clamped = Math.min(Math.max(def, minimum), maximum);
            this.sliderWidget.setValue((double)(clamped - minimum) / (double)Math.abs(maximum - minimum));
            this.value.set(clamped);
            this.sliderWidget.update();
            this.getScreen().setEdited(true);
        });
        this.sliderWidget.text = (String)this.textGetter.apply(this.value.get());
        this.widgets = Lists.newArrayList(new GuiEventListener[]{this.sliderWidget, this.resetButton});
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public Function<Long, String> getTextGetter() {
        return this.textGetter;
    }

    public LongSliderEntry setTextGetter(Function<Long, String> textGetter) {
        this.textGetter = textGetter;
        return this;
    }

    public Long getValue() {
        return this.value.get();
    }

    public Optional<Object> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public List<? extends GuiEventListener> children() {
        return this.widgets;
    }

    public LongSliderEntry setMaximum(long maximum) {
        this.maximum = maximum;
        return this;
    }

    public LongSliderEntry setMinimum(long minimum) {
        this.minimum = minimum;
        return this;
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.resetButton.active = this.isEditable() && this.getDefaultValue().isPresent() && (Long)this.defaultValue.get() != this.value.get();
        this.resetButton.y = y;
        this.sliderWidget.active = this.isEditable();
        this.sliderWidget.y = y;
        if (Utils.isBidirectional()) {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), WindowUtil.getScaledWidth() - x - Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(this.getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.sliderWidget.x = x + this.resetButton.width + 1;
        } else {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), x, y + 5, 16777215);
            this.resetButton.x = x + entryWidth - this.resetButton.width;
            this.sliderWidget.x = x + entryWidth - 150;
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.sliderWidget.render(mouseX, mouseY, delta);
    }

    public void setValue(long i) {
        this.value = new AtomicLong(i);
    }

    private class Slider extends SliderWidget implements GuiEventListener {
        private double progress;

        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(0, int_2, int_3, Option.AMBIENT_OCCLUSION, "hi", (float)LongSliderEntry.this.maximum);
            this.progress = double_1;
        }

        public void update() {
            this.text = (String)LongSliderEntry.this.textGetter.apply(LongSliderEntry.this.value.get());
            LongSliderEntry.this.value.set((long)((double)LongSliderEntry.this.minimum + (double)Math.abs(LongSliderEntry.this.maximum - LongSliderEntry.this.minimum) * this.progress));
            LongSliderEntry.this.getScreen().setEdited(true);
        }

        public void render(int mouseX, int mouseY, float delta) {
            super.render(Minecraft.INSTANCE, mouseX, mouseY);
        }

        public double getValue() {
            return this.progress;
        }

        public void setValue(double integer) {
            this.progress = integer;
        }
    }
}
