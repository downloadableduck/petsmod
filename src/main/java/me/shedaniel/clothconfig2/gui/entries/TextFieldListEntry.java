package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.clothconfig2.ButtonWidget;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.gui.GuiEventListener;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.render.platform.Window;

import java.util.List;
import net.minecraft.client.render.platform.Window;
import java.util.Optional;
import net.minecraft.client.render.platform.Window;
import java.util.function.Supplier;
import net.minecraft.client.render.platform.Window;

public abstract class TextFieldListEntry<T> extends TooltipListEntry<T> {
    
    protected TextFieldWidget textFieldWidget;
    protected ButtonWidget resetButton;
    protected Supplier<T> defaultValue;
    protected T original;
    protected List<GuiEventListener> widgets;
    
    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue) {
        this(fieldName, original, resetButtonKey, defaultValue, null);
    }
    
    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, tooltipSupplier, false);
        this.defaultValue = defaultValue;
        this.original = original;
        this.textFieldWidget = new TextFieldWidget(0, Minecraft.getInstance().textRenderer, 0, 0, 148, 18) {
            @Override
            public void render(int int_1, int int_2, float float_1) {
                textFieldPreRender(this);
                super.render(int_1, int_2, float_1);
            }
        };
        textFieldWidget.setMaxLength(999999);
        textFieldWidget.setText(String.valueOf(original));
        // textFieldWidget.setResponder(s -> { // Not available in 1.13
        //     if (!original.equals(s))
        //         getScreen().setEdited(true);
        // });
        this.resetButton = new ButtonWidget(0, 0, Minecraft.getInstance().textRenderer.getWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey), widget -> {
            TextFieldListEntry.this.textFieldWidget.setText(String.valueOf(defaultValue.get()));
            getScreen().setEdited(true);
        });
        this.widgets = Lists.newArrayList(textFieldWidget, resetButton);
    }
    
    protected static void setTextFieldWidth(TextFieldWidget widget, int width) {
        // widget.width = width; // width is private in 1.13 TextFieldWidget, no setter available
    }
    
    protected String stripAddText(String s) {
        return s;
    }
    
    protected void textFieldPreRender(TextFieldWidget widget) {
    
    }
    
    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        Window window = Minecraft.getInstance().window;
        this.resetButton.active = isEditable() && getDefaultValue().isPresent() && !isMatchDefault(textFieldWidget.getText());
        this.resetButton.y = y;
        this.textFieldWidget.setEditable(isEditable());
        this.textFieldWidget.y = y + 1;
        if (Minecraft.getInstance().textRenderer.isBidirectional()) {
            Minecraft.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), window.getGuiScaledWidth() - x - Minecraft.getInstance().textRenderer.getWidth(I18n.translate(getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.textFieldWidget.x = x + resetButton.getWidth();
            setTextFieldWidth(textFieldWidget, 148 - resetButton.getWidth() - 4);
        } else {
            Minecraft.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), x, y + 5, 16777215);
            this.resetButton.x = x + entryWidth - resetButton.getWidth();
            this.textFieldWidget.x = x + entryWidth - 148;
            setTextFieldWidth(textFieldWidget, 148 - resetButton.getWidth() - 4);
        }
        resetButton.render(mouseX, mouseY, delta);
        textFieldWidget.render(mouseX, mouseY, delta);
    }
    
    protected abstract boolean isMatchDefault(String text);
    
    @Override
    public Optional<T> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }
    
    @Override
    public List<? extends GuiEventListener> children() {
        return widgets;
    }
    
}
