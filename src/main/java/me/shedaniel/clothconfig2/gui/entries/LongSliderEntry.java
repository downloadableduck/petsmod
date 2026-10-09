package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;

public class LongSliderEntry extends TooltipListEntry<Long> {

    protected Slider sliderWidget;
    protected ButtonWidget resetButton;
    protected AtomicLong value;
    private long minimum, maximum;
    private final Consumer<Long> saveConsumer;
    private final Supplier<Long> defaultValue;
    private Function<Long, String> textGetter = value -> String.format("Value: %d", value);
    private final List<ButtonWidget> widgets;


    @Deprecated
    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer) {
        this(fieldName, minimum, maximum, value, saveConsumer, "text.cloth-config.reset_value", null);
    }


    @Deprecated
    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer, String resetButtonKey, Supplier<Long> defaultValue) {
        this(fieldName, minimum, maximum, value, saveConsumer, resetButtonKey, defaultValue, null);
    }


    @Deprecated
    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer, String resetButtonKey, Supplier<Long> defaultValue, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, minimum, maximum, value, saveConsumer, resetButtonKey, defaultValue, tooltipSupplier, false);
    }


    @Deprecated
    public LongSliderEntry(String fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer, String resetButtonKey, Supplier<Long> defaultValue, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = new AtomicLong(value);
        this.saveConsumer = saveConsumer;
        this.maximum = maximum;
        this.minimum = minimum;
        this.sliderWidget = new Slider(0, 0, 152, 20, ((double) LongSliderEntry.this.value.get() - minimum) / Math.abs(maximum - minimum));
        this.resetButton = new ButtonWidget(new Random().nextInt(), 0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey)) {
            @Override
            public boolean method_21893(MinecraftClient mc, int mouseX, int mouseY) {
                if (this.field_22511 && this.field_22512 && this.method_21885()) {
                    setValue(LongSliderEntry.this.defaultValue.get());
                    getScreen().setEdited(true, isRequiresRestart());
                    this.method_21888(mc.getSoundManager());
                    return true;
                }
                return false;
            }
        };

        this.sliderWidget.updateMessage();
        this.widgets = Lists.newArrayList(sliderWidget, resetButton);
    }

    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }

    public Function<Long, String> getTextGetter() {
        return textGetter;
    }

    public LongSliderEntry setTextGetter(Function<Long, String> textGetter) {
        this.textGetter = textGetter;
        this.sliderWidget.updateMessage();
        return this;
    }

    @Override
    public Long getValue() {
        return value.get();
    }

    @Deprecated
    public void setValue(double value) {
        sliderWidget.setValue(value);
    }

    @Override
    public Optional<Long> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    public LongSliderEntry setMaximum(long maximum) {
        this.maximum = maximum;
        return this;
    }

    public LongSliderEntry setMinimum(long minimum) {
        this.minimum = minimum;
        return this;
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        int windowWidth = (new Window(MinecraftClient.getInstance(), MinecraftClient.getInstance().width, MinecraftClient.getInstance().height)).getWidth();
        this.resetButton.field_22511 = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.y = y;
        this.sliderWidget.field_22511 = isEditable();
        this.sliderWidget.y = y;
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.draw(I18n.translate(getFieldName()), windowWidth - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.sliderWidget.x = x + resetButton.method_21890() + 1;
        } else {
            MinecraftClient.getInstance().textRenderer.draw(I18n.translate(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.method_21890();
            this.sliderWidget.x = x + entryWidth - 150;
        }
        this.sliderWidget.setWidth(150 - resetButton.method_21890() - 2);
        resetButton.method_21887(MinecraftClient.getInstance(), mouseX, mouseY);
        sliderWidget.method_21887(MinecraftClient.getInstance(), mouseX, mouseY);
    }

    private class Slider extends ButtonWidget {
        protected double value;
        protected boolean dragging;

        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, int_4, 20, "");
            this.value = double_1;
        }

        public void updateMessage() {
            this.field_22510 = (textGetter.apply(LongSliderEntry.this.value.get()));
        }

        protected void applyValue() {
            LongSliderEntry.this.value.set(minimum + (long) (Math.abs(maximum - minimum) * value));
            getScreen().setEdited(true, isRequiresRestart());
        }

        public double getValue() {
            return value;
        }

        public void setValue(double progress) {
            this.value = progress;
            LongSliderEntry.this.value.set(minimum + (long) (Math.abs(maximum - minimum) * progress));
            updateMessage();
        }

        public void setWidth(int wid) {
            this.field_22508 = wid;
        }

        protected void setValueFromMouse(int mouseX) {
            this.value = (double) (mouseX - (this.x + 4)) / (double) (this.field_22508 - 8);
            if (this.value < 0.0D) {
                this.value = 0.0D;
            }
            if (this.value > 1.0D) {
                this.value = 1.0D;
            }
            updateMessage();
            applyValue();
        }

        @Override
        public boolean method_21893(MinecraftClient mc, int mouseX, int mouseY) {
            if (this.field_22511 && this.field_22512 && this.method_21885()) {
                this.dragging = true;
                setValueFromMouse(mouseX);
                return true;
            }
            return super.method_21893(mc, mouseX, mouseY);
        }

        @Override
        protected void method_21892(MinecraftClient mc, int mouseX, int mouseY) {
            if (this.dragging) {
                setValueFromMouse(mouseX);
            }
        }

        @Override
        public void method_21886(int mouseX, int mouseY) {
            this.dragging = false;
        }

        @Override
        public void method_21887(MinecraftClient mc, int mouseX, int mouseY) {
            if (!this.field_22512) {
                return;
            }
            this.field_22513 = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.field_22508 && mouseY < this.y + this.field_22509;
            method_21878(this.x, this.y, this.x + this.field_22508, this.y + this.field_22509, this.field_22513 ? 0xFF767676 : 0xFF3A3A3A);
            int knobX = this.x + 2 + (int) (this.value * (double) (this.field_22508 - 8));
            method_21878(knobX, this.y + 1, knobX + 4, this.y + this.field_22509 - 1, 0xFFE0E0E0);
            mc.textRenderer.method_956(this.field_22510, this.x + 5, this.y + (this.field_22509 - 8) / 2, 0xFFFFFF);
            this.method_21892(mc, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.sliderWidget.method_21893(MinecraftClient.getInstance(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetButton.method_21893(MinecraftClient.getInstance(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}