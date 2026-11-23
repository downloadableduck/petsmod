//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;
import net.minecraft.client.font.TextRenderer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SelectionListEntry<T> extends TooltipListEntry<T> {
    private ImmutableList<T> values;
    private AtomicInteger index;
    private ButtonWidget buttonWidget;
    private ButtonWidget resetButton;
    private Consumer<T> saveConsumer;
    private Supplier<T> defaultValue;
    private List<GuiEventListener> widgets;
    private Function<T, String> nameProvider;

    /** @deprecated */
    @Deprecated
    @Internal
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, Consumer<T> saveConsumer) {
        this(fieldName, valuesArray, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
        this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, (Function)null);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<T, String> nameProvider) {
        this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, nameProvider, (Supplier)null);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<T, String> nameProvider, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, nameProvider, tooltipSupplier, false);
    }

    /** @deprecated */
    @Deprecated
    @Internal
    public SelectionListEntry(String fieldName, T[] valuesArray, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<T, String> nameProvider, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
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
            this.getScreen().setEdited(true, this.isRequiresRestart());
        });
        this.resetButton = new ButtonWidget(0, 0, TextRenderer.of(Minecraft.getMinecraft().font).getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (widget) -> {
            this.index.set(this.getDefaultIndex());
            this.getScreen().setEdited(true, this.isRequiresRestart());
        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new GuiEventListener[]{this.buttonWidget, this.resetButton});
        this.nameProvider = nameProvider == null ? (t) -> {
            return I18n.getTranslation(t instanceof Translatable ? ((Translatable)t).getKey() : t.toString());
        } : nameProvider;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public T getValue() {
        return this.values.get(this.index.get());
    }

    public Optional<T> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.resetButton.active = this.isEditable() && this.getDefaultValue().isPresent() && this.getDefaultIndex() != this.index.get();
        this.resetButton.y = y;
        this.buttonWidget.active = this.isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.text = (String)this.nameProvider.apply(this.getValue());
        if (Utils.isBidirectional()) {
            TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow(I18n.getTranslation(this.getFieldName()), WindowUtil.getScaledWidth() - x - TextRenderer.of(Minecraft.getMinecraft().font).getWidth(I18n.getTranslation(this.getFieldName())), y + 5, this.getPreferredTextColor());
            this.resetButton.x = x;
            this.buttonWidget.x = x + this.resetButton.width + 2;
        } else {
            TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow(I18n.getTranslation(this.getFieldName()), x, y + 5, this.getPreferredTextColor());
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
        @NotNull String getKey();
    }
}
