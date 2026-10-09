package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;

public class SelectionListEntry<T> extends TooltipListEntry<T> {

    private final ImmutableList<T> values;
    private final AtomicInteger index;
    private final ButtonWidget buttonWidget;
    private final ButtonWidget resetButton;
    private final Consumer<T> saveConsumer;
    private final Supplier<T> defaultValue;
    private final List<ButtonWidget> widgets;
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
        this.buttonWidget = new ButtonWidget(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public boolean method_21893(MinecraftClient mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.method_21893(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    SelectionListEntry.this.index.incrementAndGet();
                    SelectionListEntry.this.index.compareAndSet(SelectionListEntry.this.values.size(), 0);
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.resetButton = new ButtonWidget(new Random().nextInt(), 0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey)) {
            @Override
            public boolean method_21893(MinecraftClient mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.method_21893(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    SelectionListEntry.this.index.set(getDefaultIndex());
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(buttonWidget, resetButton);
        this.nameProvider = nameProvider == null ? (t -> I18n.translate(t instanceof Translatable ? ((Translatable) t).getKey() : t.toString())) : nameProvider;
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
        int windowWidth = (new Window(MinecraftClient.getInstance(), MinecraftClient.getInstance().width, MinecraftClient.getInstance().height)).getWidth();
        this.resetButton.field_22511 = isEditable() && getDefaultValue().isPresent() && getDefaultIndex() != this.index.get();
        this.resetButton.y = y;
        this.buttonWidget.field_22511 = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.field_22510 = (nameProvider.apply(getValue()));
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.method_956(I18n.translate(getFieldName()), windowWidth - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.buttonWidget.x = x + resetButton.method_21890() + 2;
        } else {
            MinecraftClient.getInstance().textRenderer.method_956(I18n.translate(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.method_21890();
            this.buttonWidget.x = x + entryWidth - 150;
        }
        this.buttonWidget.field_22508 = (150 - resetButton.method_21890() - 2);
        resetButton.method_21887(MinecraftClient.getInstance(), mouseX, mouseY);
        buttonWidget.method_21887(MinecraftClient.getInstance(), mouseX, mouseY);
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
            if (this.buttonWidget.method_21893(MinecraftClient.getInstance(), mouseX, mouseY)) {
                return true;
            }
            if (this.resetButton.method_21893(MinecraftClient.getInstance(), mouseX, mouseY)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
