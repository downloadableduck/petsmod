package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
public class SelectionListEntry<T> extends TooltipListEntry<T> {

    private final ImmutableList<T> values;
    private final AtomicInteger index;
    private final GuiButton buttonWidget;
    private final GuiButton resetButton;
    private final Consumer<T> saveConsumer;
    private final Supplier<T> defaultValue;
    private final List<GuiButton> widgets;
    private final Function<T, String> nameProvider;


    @Deprecated
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, Consumer<T> saveConsumer) {
        this(fieldName, valuesArray, value, "text.cloth-config.reset_value", null, saveConsumer);
    }


    @Deprecated
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
        this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, null);
    }


    @Deprecated
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<T, String> nameProvider) {
        this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, nameProvider, null);
    }


    @Deprecated
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<T, String> nameProvider, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, nameProvider, tooltipSupplier, false);
    }


    @Deprecated
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<T, String> nameProvider, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        if (valuesArray != null)
            this.values = ImmutableList.copyOf(valuesArray);
        else
            this.values = ImmutableList.of(value);
        this.defaultValue = defaultValue;
        this.index = new AtomicInteger(this.values.indexOf(value));
        this.index.compareAndSet(-1, 0);
        this.buttonWidget = new GuiButton(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public boolean func_146116_c(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.func_146116_c(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    SelectionListEntry.this.index.incrementAndGet();
                    SelectionListEntry.this.index.compareAndSet(SelectionListEntry.this.values.size(), 0);
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean func_146116_c(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.func_146116_c(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    SelectionListEntry.this.index.set(getDefaultIndex());
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(buttonWidget, resetButton);
        this.nameProvider = nameProvider == null ? (t -> I18n.format(t instanceof Translatable ? ((Translatable) t).getKey() : t.toString())) : nameProvider;
    }

    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }

    @Override
    public T getValue() {
        return this.values.get(this.index.get());
    }

    @Override
    public Optional<T> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        int windowWidth = new ScaledResolution(Minecraft.getInstance()).func_78326_a();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && getDefaultIndex() != this.index.get();
        this.resetButton.y = y;
        this.buttonWidget.enabled = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.displayString = (nameProvider.apply(getValue()));
        if (Minecraft.getInstance().fontRenderer.getBidiFlag()) {
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), windowWidth - x - Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.buttonWidget.x = x + resetButton.getWidth() + 2;
        } else {
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getWidth();
            this.buttonWidget.x = x + entryWidth - 150;
        }
        this.buttonWidget.setWidth(150 - resetButton.getWidth() - 2);
        resetButton.func_146112_a(Minecraft.getInstance(), mouseX, mouseY);
        buttonWidget.func_146112_a(Minecraft.getInstance(), mouseX, mouseY);
    }

    private int getDefaultIndex() {
        return Math.max(0, this.values.indexOf(this.defaultValue.get()));
    }

    public interface Translatable {
        String getKey();
    }
    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.buttonWidget.func_146116_c(Minecraft.getInstance(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetButton.func_146116_c(Minecraft.getInstance(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
