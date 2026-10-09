package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.minecraft.util.Formatting.WHITE;

@SuppressWarnings("DuplicatedCode")
public class KeyCodeEntry extends TooltipListEntry<ModifierKeyCode> {

    private ModifierKeyCode value;
    private final ButtonWidget buttonWidget;
    private final ButtonWidget resetButton;
    private final Consumer<ModifierKeyCode> saveConsumer;
    private final Supplier<ModifierKeyCode> defaultValue;
    private final List<ButtonWidget> widgets;
    private boolean allowMouse = true, allowKey = true, allowModifiers = true;

    @Deprecated
    public KeyCodeEntry(String fieldName, ModifierKeyCode value, String resetButtonKey, Supplier<ModifierKeyCode> defaultValue, Consumer<ModifierKeyCode> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = value;
        this.buttonWidget = new ButtonWidget(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public boolean method_21893(MinecraftClient mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.method_21893(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    getScreen().setFocusedBinding(KeyCodeEntry.this);
                    getScreen().setEdited(true, isRequiresRestart());
                }
                return bl;
            }
        };
        this.resetButton = new ButtonWidget(new Random().nextInt(), 0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey)) {
            @Override
            public boolean method_21893(MinecraftClient mc,  int mouseX, int mouseY) {
                
                 boolean bl = super.method_21893(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
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
        int windowWidth = (new Window(MinecraftClient.getInstance(), MinecraftClient.getInstance().width, MinecraftClient.getInstance().height)).getWidth();
        this.resetButton.field_22511 = isEditable() && getDefaultValue().isPresent() && !getDefaultValue().get().equals(value);
        this.resetButton.y = y;
        this.buttonWidget.field_22511 = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.field_22510 = (getLocalizedName());
        if (getScreen().getFocusedBinding() == this)
            this.buttonWidget.field_22510 = (WHITE + "> " + Formatting.YELLOW + this.buttonWidget.field_22510 + Formatting.WHITE + " <");
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.method_956(I18n.translate(getFieldName()), windowWidth - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, 16777215);
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
