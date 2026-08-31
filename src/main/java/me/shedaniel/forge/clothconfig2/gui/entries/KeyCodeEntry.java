package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.shedaniel.forge.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("DuplicatedCode")

public class KeyCodeEntry extends TooltipListEntry<ModifierKeyCode> {
    
    private ModifierKeyCode value;
    private GuiButton buttonWidget, resetButton;
    private Consumer<ModifierKeyCode> saveConsumer;
    private Supplier<ModifierKeyCode> defaultValue;
    private List<IGuiEventListener> widgets;
    private boolean allowMouse = true, allowKey = true, allowModifiers = true;
    
    @Deprecated
    public KeyCodeEntry(String fieldName, ModifierKeyCode value, String resetButtonKey, Supplier<ModifierKeyCode> defaultValue, Consumer<ModifierKeyCode> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = value;
        this.buttonWidget = new GuiButton(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                getScreen().setFocusedBinding(KeyCodeEntry.this);
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
            }
        };
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public void mousePressed(double p_194829_1_, double p_194829_3_) {
                KeyCodeEntry.this.value = getDefaultValue().orElse(null);
                getScreen().setFocusedBinding(null);
                getScreen().setEdited(true, isRequiresRestart());
                super.mousePressed(p_194829_1_, p_194829_3_);
            }
        };
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(buttonWidget, resetButton);
    }
    
    public boolean isAllowModifiers() {
        return allowModifiers;
    }
    
    public void setAllowModifiers(boolean allowModifiers) {
        this.allowModifiers = allowModifiers;
    }
    
    public boolean isAllowKey() {
        return allowKey;
    }
    
    public void setAllowKey(boolean allowKey) {
        this.allowKey = allowKey;
    }
    
    public boolean isAllowMouse() {
        return allowMouse;
    }
    
    public void setAllowMouse(boolean allowMouse) {
        this.allowMouse = allowMouse;
    }
    
    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }
    
    @Override
    public ModifierKeyCode getValue() {
        return value;
    }
    
    public void setValue(ModifierKeyCode value) {
        this.value = value;
    }
    
    @Override
    public Optional<ModifierKeyCode> getDefaultValue() {
        return Optional.ofNullable(defaultValue).map(Supplier::get);
    }
    
    private String getLocalizedName() {
        return this.value.getLocalizedName();
    }
    
    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        MainWindow window = Minecraft.getMinecraft().mainWindow;
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && !getDefaultValue().get().equals(value);
        this.resetButton.y = y;
        this.buttonWidget.enabled = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.displayString = (getLocalizedName());
        if (getScreen().getFocusedBinding() == this)
            this.buttonWidget.displayString = (ChatFormatting.WHITE + "> " + ChatFormatting.YELLOW + this.buttonWidget.displayString + ChatFormatting.WHITE + " <");
        if (Minecraft.getMinecraft().fontRenderer.getBidiFlag()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), window.getScaledWidth() - x - Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, 16777215);
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
    
    @Override
    public List<? extends IGuiEventListener> getEventListeners() {
        return widgets;
    }
    
}
