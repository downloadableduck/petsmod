package me.shedaniel.forge.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.LiteModPetsMod;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.shedaniel.forge.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("DuplicatedCode")

public class KeyCodeEntry extends TooltipListEntry<ModifierKeyCode> {

    private ModifierKeyCode value;
    private final GuiButton buttonWidget;
    private final GuiButton resetButton;
    private final Consumer<ModifierKeyCode> saveConsumer;
    private final Supplier<ModifierKeyCode> defaultValue;
    private final List<GuiButton> widgets;
    private boolean allowMouse = true, allowKey = true, allowModifiers = true;

    @Deprecated
    public KeyCodeEntry(String fieldName, ModifierKeyCode value, String resetButtonKey, Supplier<ModifierKeyCode> defaultValue, Consumer<ModifierKeyCode> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = value;
        this.buttonWidget = new GuiButton(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public boolean func_146116_c(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.func_146116_c(mc, mouseX, mouseY); if (bl) { LiteModPetsMod.LOGGER.info("mouse pressed");
                    getScreen().setFocusedBinding(KeyCodeEntry.this);
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.resetButton = new GuiButton(new Random().nextInt(), 0, 0, Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(resetButtonKey)) + 6, 20, I18n.format(resetButtonKey)) {
            @Override
            public boolean func_146116_c(Minecraft mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.func_146116_c(mc, mouseX, mouseY); if (bl) { LiteModPetsMod.LOGGER.info("mouse pressed");
                    KeyCodeEntry.this.value = getDefaultValue().orElse(null);
                    getScreen().setFocusedBinding(null);
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
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
        int windowWidth = new ScaledResolution(Minecraft.getInstance()).func_78326_a();
        this.resetButton.enabled = isEditable() && getDefaultValue().isPresent() && !getDefaultValue().get().equals(value);
        this.resetButton.y = y;
        this.buttonWidget.enabled = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.displayString = (getLocalizedName());
        if (getScreen().getFocusedBinding() == this)
            this.buttonWidget.displayString = (ChatFormatting.WHITE + "> " + ChatFormatting.YELLOW + this.buttonWidget.displayString + ChatFormatting.WHITE + " <");
        if (Minecraft.getInstance().fontRenderer.getBidiFlag()) {
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format(getFieldName()), windowWidth - x - Minecraft.getInstance().fontRenderer.getStringWidth(I18n.format(getFieldName())), y + 5, 16777215);
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
