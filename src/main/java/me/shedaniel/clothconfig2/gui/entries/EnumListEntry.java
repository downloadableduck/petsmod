//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class EnumListEntry<T extends Enum<?>> extends TooltipListEntry {
    public static final Function<Enum, String> DEFAULT_NAME_PROVIDER = (t) -> {
        return I18n.getTranslation(t instanceof Translatable ? ((Translatable)t).getKey() : t.toString());
    };
    private ImmutableList<T> values;
    private AtomicInteger index;
    private ButtonWidget buttonWidget;
    private ButtonWidget resetButton;
    private Consumer<T> saveConsumer;
    private Supplier<T> defaultValue;
    private List<GuiEventListener> widgets;
    private Function<Enum, String> enumNameProvider;

    public EnumListEntry(String fieldName, Class<T> clazz, T value, Consumer<T> saveConsumer) {
        this(fieldName, clazz, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
        this(fieldName, clazz, value, resetButtonKey, defaultValue, saveConsumer, DEFAULT_NAME_PROVIDER);
    }

    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<Enum, String> enumNameProvider) {
        this(fieldName, clazz, value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider, (Supplier)null, false);
    }

    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<Enum, String> enumNameProvider, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        T[] valuesArray = (T[])clazz.getEnumConstants();
        if (valuesArray != null) {
            this.values = ImmutableList.copyOf(valuesArray);
        } else {
            this.values = ImmutableList.of(value);
        }

        this.defaultValue = defaultValue;
        this.index = new AtomicInteger(this.values.indexOf(value));
        this.index.compareAndSet(-1, 0);
        this.buttonWidget = new ButtonWidget(0, 0, 150, 20, "", (widget) -> {
            this.index.incrementAndGet();
            this.index.compareAndSet(this.values.size(), 0);
            this.getScreen().setEdited(true);
        });
        this.resetButton = new ButtonWidget(0, 0, Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (widget) -> {
            this.index.set(this.getDefaultIndex());
            this.getScreen().setEdited(true);
        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new GuiEventListener[]{this.buttonWidget, this.resetButton});
        this.enumNameProvider = enumNameProvider;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public T getValue() {
        return (T)this.values.get(this.index.get());
    }

    public Optional<Object> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.resetButton.active = this.isEditable() && this.getDefaultValue().isPresent() && this.getDefaultIndex() != this.index.get();
        this.resetButton.y = y;
        this.buttonWidget.active = this.isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.text = (String)this.enumNameProvider.apply(this.getValue());
        if (Utils.isBidirectional()) {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), WindowUtil.getScaledWidth() - x - Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(this.getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.buttonWidget.x = x + this.resetButton.width + 2;
        } else {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), x, y + 5, 16777215);
            this.resetButton.x = x + entryWidth - this.resetButton.width;
            this.buttonWidget.x = x + entryWidth - 150;
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.buttonWidget.render(mouseX, mouseY, delta);
    }

    private int getDefaultIndex() {
        return Math.max(0, this.values.indexOf(this.defaultValue.get()));
    }

    public List<? extends GuiEventListener> children() {
        return this.widgets;
    }

    public interface Translatable {
        String getKey();
    }
}
