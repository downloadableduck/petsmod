package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.Window;
import net.minecraft.client.resource.language.I18n;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public abstract class TextFieldListEntry<T> extends TooltipListEntry<T> {

    protected TextFieldWidget textFieldWidget;
    protected ResetButton resetButton;
    protected Supplier<T> defaultValue;
    protected T original;
    protected List<Object> widgets;
    private boolean isSelected = false;


    @Deprecated
    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue) {
        this(fieldName, original, resetButtonKey, defaultValue, null);
    }


    @Deprecated
    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, original, resetButtonKey, defaultValue, tooltipSupplier, false);
    }


    @Deprecated
    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier);
        this.defaultValue = defaultValue;
        this.original = original;
        this.textFieldWidget = new TextFieldWidget(0, MinecraftClient.getInstance().textRenderer, 0, 0, 148, 20) {
            @Override
            public void render() {
                //boolean f = isFocused();
                //setFocused(isSelected);
                textFieldPreRender(this);
                super.render();
                //setFocused(f);
            }

            @Override
            public void setText(String string_1) {
                super.write(stripAddText(string_1));
            }
        };
        textFieldWidget.setMaxLength(999999);
        textFieldWidget.setText(String.valueOf(original));
        textFieldWidget.setTextPredicate((s) -> {
            if (getScreen() != null && !original.equals(s)) {
                getScreen().setEdited(true, isRequiresRestart());
            }
            return true;
        });
        this.resetButton = new ResetButton(0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey), widget -> {
            TextFieldListEntry.this.textFieldWidget.setText(String.valueOf(defaultValue.get()));
            getScreen().setEdited(true, isRequiresRestart());
        });
        this.widgets = (List) Lists.newArrayList(textFieldWidget, resetButton);
    }

    protected static void setTextFieldWidth(TextFieldWidget widget, int width) {
        widget.width = width;
    }

    @Deprecated
    public void setValue(String s) {
        textFieldWidget.setText(String.valueOf(s));
    }

    protected String stripAddText(String s) {
        return s;
    }

    protected void textFieldPreRender(TextFieldWidget widget) {

    }

    @Override
    public void updateSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        int windowWidth = (int) new Window(MinecraftClient.getInstance()).getScaledWidth();
        this.resetButton.active = isEditable() && getDefaultValue().isPresent() && !isMatchDefault(textFieldWidget.getText());
        this.resetButton.y = y;
        this.textFieldWidget.setEditable(isEditable());
        this.textFieldWidget.y = y + 1;
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), windowWidth - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.x = x;
            this.textFieldWidget.x = x + resetButton.getWidth();
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.x = x + entryWidth - resetButton.getWidth();
            this.textFieldWidget.x = x + entryWidth - 148;
        }
        setTextFieldWidth(textFieldWidget, 148 - resetButton.getWidth() - 4);
        resetButton.method_891(MinecraftClient.getInstance(), mouseX, mouseY, delta);
        textFieldWidget.render();
    }

    protected abstract boolean isMatchDefault(String text);

    @Override
    public Optional<T> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    protected static class ResetButton extends ButtonWidget {
        private IPressable onPress;

        public ResetButton(int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
            super(new Random().nextInt(), widthIn, heightIn, width, height, text);
            this.onPress = onPress;
        }

        @Override
        public boolean isMouseOver(MinecraftClient mc,  int mouseX, int mouseY) {
            
             boolean bl = super.isMouseOver(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                onPress.onPress(this);
            }
            return bl;
        }

        public void setOnPress(IPressable onPress) {
            this.onPress = onPress;
        }

        public interface IPressable {
            void onPress(ResetButton p_onPress_1_);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean clickedReset = this.resetButton.isMouseOver(MinecraftClient.getInstance(), mouseX, mouseY);
        if (clickedReset) {
            return true;
        }

        boolean isInsideField = mouseX >= this.textFieldWidget.x && mouseX < this.textFieldWidget.x + this.textFieldWidget.width
                && mouseY >= this.textFieldWidget.y && mouseY < this.textFieldWidget.y + this.textFieldWidget.height;

        this.textFieldWidget.setFocused(isInsideField);
        if (isInsideField) {
            this.textFieldWidget.method_920(mouseX, mouseY, mouseButton);
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean keyTyped(char cha, int i) {
        if (this.textFieldWidget.isFocused()) {
            String textBefore = this.textFieldWidget.getText();
            boolean handled = this.textFieldWidget.keyPressed(cha, i);
            String textAfter = this.textFieldWidget.getText();

            if (!Objects.equals(textBefore, textAfter) && getScreen() != null) {
                getScreen().setEdited(true, isRequiresRestart());
            }
            return handled;
        }
        return false;
    }
}
