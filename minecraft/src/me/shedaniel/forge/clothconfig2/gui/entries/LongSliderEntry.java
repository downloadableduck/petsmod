package me.shedaniel.forge.clothconfig2.gui.entries;

import com.jeff.pets.PetsInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.I18n;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.*;
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
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean mousePressed(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.mousePressed(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    setValue(defaultValue.get());
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };

        this.sliderWidget.displayString = (textGetter.apply(LongSliderEntry.this.value.get()));
        this.widgets = new ArrayList<>(Arrays.asList(sliderWidget, resetButton));
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
        this.sliderWidget.displayString = (textGetter.apply(LongSliderEntry.this.value.get()));
        return this;
    }

    @Override
    public Long getValue() {
        return value.get();
    }

    @Deprecated
    public void setValue(long value) {
        sliderWidget.setValue((MathHelper.clamp_float(value, minimum, maximum) - minimum) / (double) Math.abs(maximum - minimum));
        this.value.set(Math.min(Math.max(value, minimum), maximum));
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
        int windowWidth = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight).getScaledWidth();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.yPosition = y;
        this.sliderWidget.enabled = isEditable();
        this.sliderWidget.yPosition = y;
        if (Minecraft.getMinecraft().fontRenderer.getBidiFlag()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), windowWidth - x - Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x;
            this.sliderWidget.xPosition = x + resetButton.width + 1;
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x + entryWidth - resetButton.width;
            this.sliderWidget.xPosition = x + entryWidth - 150;
        }
        this.sliderWidget.width = (150 - resetButton.width - 2);
        resetButton.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        sliderWidget.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    private class Slider extends GuiButton {
        protected double value;
        protected boolean dragging;

        protected Slider(int par1, int par2, int par3, int par4, double par5) {
            super(new Random().nextInt(), par1, par2, par3, 20, "");
            this.value = par5;
        }

        @Override
        protected int getHoverState(boolean par1) {
            return 0;
        }

        @Override
        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
            if (super.mousePressed(mc, mouseX, mouseY)) {
                this.value = (mouseX - (double) (this.xPosition + 4)) / (double) (this.width - 8);
                if (this.value < 0.0D) this.value = 0.0D;
                if (this.value > 1.0D) this.value = 1.0D;
                applyValue(this.value);
                this.dragging = true;
                return true;
            }
            return false;
        }

        @Override
        public void mouseReleased(int par1, int par2) {
            this.dragging = false;
        }

        @Override
        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
            if (this.drawButton) {
                if (this.dragging) {
                    this.value = (mouseX - (double) (this.xPosition + 4)) / (double) (this.width - 8);
                    if (this.value < 0.0D) this.value = 0.0D;
                    if (this.value > 1.0D) this.value = 1.0D;
                    applyValue(this.value);
                }
                mc.renderEngine.bindTexture("/gui/gui.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.drawTexturedModalRect(this.xPosition + (int) (this.value * (double) (this.width - 8)), this.yPosition, 0, 66, 4, 20);
                this.drawTexturedModalRect(this.xPosition + (int) (this.value * (double) (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
            }
        }

        private void applyValue(double progress) {
            LongSliderEntry.this.value.set((long) Math.round(minimum + (maximum - minimum) * progress));
            displayString = (textGetter.apply(LongSliderEntry.this.value.get()));
            getScreen().setEdited(true, isRequiresRestart());
        }

        public double getValue() {
            return value;
        }

        public void setValue(double sliderValue) {
            this.value = sliderValue;
            applyValue(this.value);
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
