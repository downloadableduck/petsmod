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
            public boolean isMouseOver(MinecraftClient mc, int mouseX, int mouseY) {
                    setValue(LongSliderEntry.this.defaultValue.get());
                    getScreen().setEdited(true, isRequiresRestart());
                    //this.method_21888(mc.getSoundManager());
                    return true;
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
        int windowWidth = (new Window(MinecraftClient.getInstance().options, MinecraftClient.getInstance().width, MinecraftClient.getInstance().height)).getWidth();
        this.resetButton.active = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.y = y;
        this.sliderWidget.active = isEditable();
        this.sliderWidget.y = y;
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.draw(I18n.translate(getFieldName()), windowWidth - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.sliderWidget.x = x + resetButton.width + 1;
        } else {
            MinecraftClient.getInstance().textRenderer.draw(I18n.translate(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.width;
            this.sliderWidget.x = x + entryWidth - 150;
        }
        this.sliderWidget.setWidth(150 - resetButton.width - 2);
        resetButton.render(MinecraftClient.getInstance(), mouseX, mouseY);
        sliderWidget.render(MinecraftClient.getInstance(), mouseX, mouseY);
    }

    private class Slider extends ButtonWidget {
        protected double value;
        protected boolean dragging;

        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, int_4, 20, "");
            this.value = double_1;
        }

        public void updateMessage() {
            this.message = (textGetter.apply(LongSliderEntry.this.value.get()));
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
            this.width = wid;
        }

        protected void setValueFromMouse(int mouseX) {
            this.value = (double) (mouseX - (this.x + 4)) / (double) (this.width - 8);
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
        protected void mouseDragged(MinecraftClient mc, int mouseX, int mouseY) {
            if (this.dragging) {
                setValueFromMouse(mouseX);
            }
        }

        @Override
        public void mouseReleased(int mouseX, int mouseY) {
            this.dragging = false;
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.sliderWidget.isMouseOver(MinecraftClient.getInstance(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetButton.isMouseOver(MinecraftClient.getInstance(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}