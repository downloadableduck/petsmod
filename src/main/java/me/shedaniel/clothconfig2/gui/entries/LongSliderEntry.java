package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LongSliderEntry extends TooltipListEntry<Long> {

    protected Slider sliderWidget;
    protected GuiButton resetButton;
    protected AtomicLong value;
    private long minimum, maximum;
    private final Consumer<Long> saveConsumer;
    private final Supplier<Long> defaultValue;
    private Function<Long, String> textGetter = value -> String.format("Value: %d", value);
    private final List<GuiButton> widgets;


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
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getMinecraft().fontRendererObj.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                if (this.enabled && this.visible && this.isMouseOver()) {
                    setValue(LongSliderEntry.this.defaultValue.get());
                    getScreen().setEdited(true, isRequiresRestart());
                    this.playPressSound(mc.getSoundHandler());
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
        int windowWidth = (new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight)).getScaledWidth();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.yPosition = y;
        this.sliderWidget.enabled = isEditable();
        this.sliderWidget.yPosition = y;
        if (Minecraft.getMinecraft().fontRendererObj.getBidiFlag()) {
            Minecraft.getMinecraft().fontRendererObj.drawString(I18n.format(getFieldName()), windowWidth - x - Minecraft.getMinecraft().fontRendererObj.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x;
            this.sliderWidget.xPosition = x + resetButton.getButtonWidth() + 1;
        } else {
            Minecraft.getMinecraft().fontRendererObj.drawString(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x + entryWidth - resetButton.getButtonWidth();
            this.sliderWidget.xPosition = x + entryWidth - 150;
        }
        this.sliderWidget.setWidth(150 - resetButton.getButtonWidth() - 2);
        resetButton.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        sliderWidget.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    private class Slider extends GuiButton {
        protected double value;
        protected boolean dragging;

        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, int_4, 20, "");
            this.value = double_1;
        }

        public void updateMessage() {
            this.displayString = (textGetter.apply(LongSliderEntry.this.value.get()));
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
            this.value = (double) (mouseX - (this.xPosition + 4)) / (double) (this.width - 8);
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
        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
            if (this.enabled && this.visible && this.isMouseOver()) {
                this.dragging = true;
                setValueFromMouse(mouseX);
                return true;
            }
            return super.mousePressed(mc, mouseX, mouseY);
        }

        @Override
        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
            if (this.dragging) {
                setValueFromMouse(mouseX);
            }
        }

        @Override
        public void mouseReleased(int mouseX, int mouseY) {
            this.dragging = false;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if (!this.visible) {
                return;
            }
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.hovered ? 0xFF767676 : 0xFF3A3A3A);
            int knobX = this.xPosition + 2 + (int) (this.value * (double) (this.width - 8));
            drawRect(knobX, this.yPosition + 1, knobX + 4, this.yPosition + this.height - 1, 0xFFE0E0E0);
            mc.fontRendererObj.drawStringWithShadow(this.displayString, this.xPosition + 5, this.yPosition + (this.height - 8) / 2, 0xFFFFFF);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.sliderWidget.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetButton.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}