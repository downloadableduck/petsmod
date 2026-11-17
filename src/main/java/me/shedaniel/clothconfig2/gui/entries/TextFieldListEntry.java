//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.compat.CompatTextFieldWidget;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class TextFieldListEntry<T> extends TooltipListEntry<T> {
    protected CompatTextFieldWidget textFieldWidget;
    protected ButtonWidget resetButton;
    protected Supplier<T> defaultValue;
    protected T original;
    protected List<GuiEventListener> widgets;

    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue) {
        this(fieldName, original, resetButtonKey, defaultValue, (Supplier)null);
    }

    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, tooltipSupplier, false);
        this.defaultValue = defaultValue;
        this.original = original;
        this.textFieldWidget = new CompatTextFieldWidget(this.getScreen(), 0, Minecraft.INSTANCE.textRenderer, 0, 0, 148, 18) {
            public void render() {
                TextFieldListEntry.this.textFieldPreRender(this);
                super.render();
            }
        };
        this.textFieldWidget.setMaxLength(999999);
        this.textFieldWidget.setText(String.valueOf(original));
        this.resetButton = new ButtonWidget(0, 0, Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (widget) -> {
            this.textFieldWidget.setText(String.valueOf(defaultValue.get()));
            this.getScreen().setEdited(true);
        });
        this.widgets = Lists.newArrayList(new GuiEventListener[]{this.textFieldWidget, this.resetButton});
    }

    protected static void setTextFieldWidth(CompatTextFieldWidget widget, int width) {
    }

    protected String stripAddText(String s) {
        return s;
    }

    protected void textFieldPreRender(CompatTextFieldWidget widget) {
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.resetButton.active = this.isEditable() && this.getDefaultValue().isPresent() && !this.isMatchDefault(this.textFieldWidget.getText());
        this.resetButton.y = y;
        this.textFieldWidget.enabled = this.isEditable();
        this.textFieldWidget.y = y + 1;
        if (Utils.isBidirectional()) {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), WindowUtil.getScaledWidth() - x - Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(this.getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.textFieldWidget.x = x + this.resetButton.width;
            setTextFieldWidth(this.textFieldWidget, 148 - this.resetButton.width - 4);
        } else {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), x, y + 5, 16777215);
            this.resetButton.x = x + entryWidth - this.resetButton.width;
            this.textFieldWidget.x = x + entryWidth - 148;
            setTextFieldWidth(this.textFieldWidget, 148 - this.resetButton.width - 4);
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.textFieldWidget.render();
    }

    protected abstract boolean isMatchDefault(String var1);

    public Optional<T> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public List<? extends GuiEventListener> children() {
        return this.widgets;
    }
}
