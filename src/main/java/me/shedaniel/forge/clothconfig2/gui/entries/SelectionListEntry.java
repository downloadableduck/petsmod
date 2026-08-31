package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class SelectionListEntry<T> extends TooltipListEntry<T> {
    
    private ImmutableList<T> values;
    private AtomicInteger index;
    private GuiButton buttonWidget, resetButton;
    private Consumer<T> saveConsumer;
    private Supplier<T> defaultValue;
    private List<IGuiEventListener> widgets;
    private Function<T, String> nameProvider;
    
    
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
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                SelectionListEntry.this.index.incrementAndGet();
                SelectionListEntry.this.index.compareAndSet(SelectionListEntry.this.values.size(), 0);
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
            }
        };
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                SelectionListEntry.this.index.set(getDefaultIndex());
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
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
        MainWindow window = Minecraft.getMinecraft().mainWindow;
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && getDefaultIndex() != this.index.get();
        this.resetButton.y = y;
        this.buttonWidget.enabled = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.displayString = (nameProvider.apply(getValue()));
        if (Minecraft.getMinecraft().fontRenderer.getBidiFlag()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), window.getScaledWidth() - x - Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.buttonWidget.x = x + resetButton.getButtonWidth() + 2;
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getButtonWidth();
            this.buttonWidget.x = x + entryWidth - 150;
        }
        this.buttonWidget.setWidth(150 - resetButton.getButtonWidth() - 2);
        resetButton.drawButton(mouseX, mouseY, delta);
        buttonWidget.drawButton(mouseX, mouseY, delta);
    }
    
    private int getDefaultIndex() {
        return Math.max(0, this.values.indexOf(this.defaultValue.get()));
    }
    
    @Override
    public List<? extends IGuiEventListener> getEventListeners() {
        return widgets;
    }
    
    public interface Translatable {
        String getKey();
    }
    
}
