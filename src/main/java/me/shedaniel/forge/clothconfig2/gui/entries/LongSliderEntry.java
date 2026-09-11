package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
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
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean func_146116_c(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.func_146116_c(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    setValue(defaultValue.get());
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };

        this.sliderWidget.displayString = (textGetter.apply(LongSliderEntry.this.value.get()));
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
        this.sliderWidget.displayString = (textGetter.apply(LongSliderEntry.this.value.get()));
        return this;
    }

    @Override
    public Long getValue() {
        return value.get();
    }

    @Deprecated
    public void setValue(long value) {
        sliderWidget.setValue((MathHelper.clamp(value, minimum, maximum) - minimum) / (double) Math.abs(maximum - minimum));
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
        int windowWidth = new ScaledResolution(Minecraft.getInstance()).func_78326_a();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != value.get();
        this.resetButton.y = y;
        this.sliderWidget.enabled = isEditable();
        this.sliderWidget.y = y;
        if (Minecraft.getInstance().fontRenderer.getBidiFlag()) {
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), windowWidth - x - Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.sliderWidget.x = x + resetButton.getWidth() + 1;
        } else {
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getWidth();
            this.sliderWidget.x = x + entryWidth - 150;
        }
        this.sliderWidget.setWidth(150 - resetButton.getWidth() - 2);
        resetButton.func_146112_a(Minecraft.getInstance(), mouseX, mouseY);
        sliderWidget.func_146112_a(Minecraft.getInstance(), mouseX, mouseY);
    }

    private class Slider extends GuiOptionSlider {

        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, GameSettings.Options.MIPMAP_LEVELS, (float) int_4, (float) double_1);
        }

        public double getValue() {
            return value.doubleValue();
        }

        public void setValue(double integer) {
            value.set((long) integer);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.sliderWidget.func_146116_c(Minecraft.getInstance(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetButton.func_146116_c(Minecraft.getInstance(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
