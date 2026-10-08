package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public abstract class TextFieldListEntry<T> extends TooltipListEntry<T> {

    protected GuiTextField textFieldWidget;
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
        this.textFieldWidget = new GuiTextField(Minecraft.getMinecraft().fontRendererObj, 0, 0, 148, 20) {
            @Override
            public void drawTextBox() {
                //boolean f = isFocused();
                //setFocused(isSelected);
                textFieldPreRender(this);
                super.drawTextBox();
                //setFocused(f);
            }

            @Override
            public void setText(String string_1) {
                super.writeText(stripAddText(string_1));
            }
        };
        textFieldWidget.setMaxStringLength(999999);
        textFieldWidget.setText(String.valueOf(original));
        /*textFieldWidget.setTextPredicate((s) -> {
            if (getScreen() != null && !original.equals(s)) {
                getScreen().setEdited(true, isRequiresRestart());
            }
            return true;
        });*/
        this.resetButton = new ResetButton(0, 0, Minecraft.getMinecraft().fontRendererObj.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey), widget -> {
            TextFieldListEntry.this.textFieldWidget.setText(String.valueOf(defaultValue.get()));
            getScreen().setEdited(true, isRequiresRestart());
        });
        this.widgets = (List) Lists.newArrayList(textFieldWidget, resetButton);
    }

    protected static void setTextFieldWidth(GuiTextField widget, int width) {
        widget.width = width;
    }

    @Deprecated
    public void setValue(String s) {
        textFieldWidget.setText(String.valueOf(s));
    }

    protected String stripAddText(String s) {
        return s;
    }

    protected void textFieldPreRender(GuiTextField widget) {

    }

    @Override
    public void updateSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        int windowWidth = (new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight)).getScaledWidth();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && !isMatchDefault(textFieldWidget.getText());
        this.resetButton.yPosition = y;
        this.textFieldWidget.setEnabled(isEditable());
        this.textFieldWidget.yPosition = y + 1;
        if (Minecraft.getMinecraft().fontRendererObj.getBidiFlag()) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(I18n.format(getFieldName()), windowWidth - x - Minecraft.getMinecraft().fontRendererObj.getStringWidth(I18n.format(getFieldName())), y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x;
            this.textFieldWidget.xPosition = x + resetButton.width;
        } else {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(I18n.format(getFieldName()), x, y + 5, getPreferredTextColor());
            this.resetButton.xPosition = x + entryWidth - resetButton.width;
            this.textFieldWidget.xPosition = x + entryWidth - 148;
        }
        setTextFieldWidth(textFieldWidget, 148 - resetButton.width - 4);
        resetButton.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        textFieldWidget.drawTextBox();
    }

    protected abstract boolean isMatchDefault(String text);

    @Override
    public Optional<T> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    protected static class ResetButton extends GuiButton {
        private IPressable onPress;

        public ResetButton(int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
            super(new Random().nextInt(), widthIn, heightIn, width, height, text);
            this.onPress = onPress;
        }

        @Override
        public boolean mousePressed(Minecraft mc,  int mouseX, int mouseY) {
            
             boolean bl = super.mousePressed(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
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
        boolean clickedReset = this.resetButton.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
        if (clickedReset) {
            return true;
        }

        boolean isInsideField = mouseX >= this.textFieldWidget.xPosition && mouseX < this.textFieldWidget.xPosition + this.textFieldWidget.width
                && mouseY >= this.textFieldWidget.yPosition && mouseY < this.textFieldWidget.yPosition + this.textFieldWidget.height;

        this.textFieldWidget.setFocused(isInsideField);
        if (isInsideField) {
            this.textFieldWidget.mouseClicked(mouseX, mouseY, mouseButton);
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean keyTyped(char cha, int i) {
        if (this.textFieldWidget.isFocused()) {
            String textBefore = this.textFieldWidget.getText();
            boolean handled = this.textFieldWidget.textboxKeyTyped(cha, i);
            String textAfter = this.textFieldWidget.getText();

            if (!Objects.equals(textBefore, textAfter) && getScreen() != null) {
                getScreen().setEdited(true, isRequiresRestart());
            }
            return handled;
        }
        return false;
    }
}
