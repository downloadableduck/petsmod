package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.LiteModPetsMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
public class IntegerSliderEntry extends TooltipListEntry<Integer> {

    protected Slider sliderWidget;
    protected GuiButton resetButton;
    protected AtomicInteger value;
    private int minimum, maximum;
    private final Consumer<Integer> saveConsumer;
    private final Supplier<Integer> defaultValue;
    private Function<Integer, String> textGetter = integer -> String.format("Value: %d", integer);
    private final List<GuiButton> widgets;


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
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0,         Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean mousePressed(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.mousePressed(mc, mouseX, mouseY); if (bl) { LiteModPetsMod.LOGGER.info("mouse pressed");
                    sliderWidget.setProgress((MathHelper.clamp_float(IntegerSliderEntry.this.defaultValue.get(), minimum, maximum) - minimum) / (double) Math.abs(maximum - minimum));
                    IntegerSliderEntry.this.value.set(MathHelper.clamp_int(IntegerSliderEntry.this.defaultValue.get(), minimum, maximum));
                    sliderWidget.updateMessage();
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
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
        int windowWidth = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight).getScaledWidth();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.yPosition = y;
        this.sliderWidget.enabled = isEditable();
        this.sliderWidget.yPosition = y;
        if (Minecraft.getMinecraft().fontRenderer.getBidiFlag()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), windowWidth - x - Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x;
            this.sliderWidget.xPosition = x + resetButton.getButtonWidth() + 1;
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x + entryWidth - resetButton.getButtonWidth();
            this.sliderWidget.xPosition = x + entryWidth - 150;
        }
        this.sliderWidget.width = (150 - resetButton.getButtonWidth() - 2);
        resetButton.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        sliderWidget.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    private class Slider extends GuiOptionSlider {
        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, GameSettings.Options.MIPMAP_LEVELS, (float) int_4, (float) double_1);
        }

        public void updateMessage() {
            displayString = (textGetter.apply(IntegerSliderEntry.this.value.get()));
        }

        @Override
        public boolean mousePressed(Minecraft mc,  int mouseX, int mouseY) {
            
             boolean bl = super.mousePressed(mc, mouseX, mouseY); if (bl) { LiteModPetsMod.LOGGER.info("mouse pressed");
                IntegerSliderEntry.this.value.set((minimum + Math.abs(maximum - minimum) * value.get()));
                getScreen().setEdited(true, isRequiresRestart());
            }
            return bl;
        }

        public double getProgress() {
            return value.doubleValue();
        }

        public void setProgress(double integer) {
            value.set((int) integer);
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
