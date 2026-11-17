//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.Internal;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KeyCodeEntry extends TooltipListEntry<ModifierKeyCode> {
    private ModifierKeyCode value;
    private ButtonWidget buttonWidget;
    private ButtonWidget resetButton;
    private Consumer<ModifierKeyCode> saveConsumer;
    private Supplier<ModifierKeyCode> defaultValue;
    private List<GuiEventListener> widgets;
    private boolean allowMouse = true;
    private boolean allowKey = true;
    private boolean allowModifiers = true;

    /** @deprecated */
    @Deprecated
    @Internal
    public KeyCodeEntry(String fieldName, ModifierKeyCode value, String resetButtonKey, Supplier<ModifierKeyCode> defaultValue, Consumer<ModifierKeyCode> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.value = value;
        this.buttonWidget = new ButtonWidget(0, 0, 150, 20, "", (widget) -> {
            this.getScreen().setFocusedBinding(this);
            this.getScreen().setEdited(true, this.isRequiresRestart());
        });
        this.resetButton = new ButtonWidget(0, 0, Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(resetButtonKey)) + 6, 20, I18n.getTranslation(resetButtonKey), (widget) -> {
            this.value = (ModifierKeyCode)(Object)this.getDefaultValue().orElse(null);
            this.getScreen().setFocusedBinding((KeyCodeEntry)null);
            this.getScreen().setEdited(true, this.isRequiresRestart());
        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new GuiEventListener[]{this.buttonWidget, this.resetButton});
    }

    public boolean isAllowModifiers() {
        return this.allowModifiers;
    }

    public void setAllowModifiers(boolean allowModifiers) {
        this.allowModifiers = allowModifiers;
    }

    public boolean isAllowKey() {
        return this.allowKey;
    }

    public void setAllowKey(boolean allowKey) {
        this.allowKey = allowKey;
    }

    public boolean isAllowMouse() {
        return this.allowMouse;
    }

    public void setAllowMouse(boolean allowMouse) {
        this.allowMouse = allowMouse;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public ModifierKeyCode getValue() {
        return this.value;
    }

    public void setValue(ModifierKeyCode value) {
        this.value = value;
    }

    public Optional<ModifierKeyCode> getDefaultValue() {
        return Optional.ofNullable(this.defaultValue).map(Supplier::get);
    }

    private String getLocalizedName() {
        return this.value.getLocalizedName();
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.resetButton.active = this.isEditable() && this.getDefaultValue().isPresent() && !((ModifierKeyCode)this.getDefaultValue().get()).equals(this.value);
        this.resetButton.y = y;
        this.buttonWidget.active = this.isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.text = this.getLocalizedName();
        if (this.getScreen().getFocusedBinding() == this) {
            this.buttonWidget.text = "\u00a7f" + "> " + "\u00a7e" + this.buttonWidget.text + "\u00a7f" + " <";
        }

        if (Utils.isBidirectional()) {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), WindowUtil.getScaledWidth() - x - Minecraft.INSTANCE.textRenderer.getWidth(I18n.getTranslation(this.getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.buttonWidget.x = x + this.resetButton.width + 2;
        } else {
            Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.getFieldName()), x, y + 5, this.getPreferredTextColor());
            this.resetButton.x = x + entryWidth - this.resetButton.width;
            this.buttonWidget.x = x + entryWidth - 150;
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.buttonWidget.render(mouseX, mouseY, delta);
    }

    public List<? extends GuiEventListener> children() {
        return this.widgets;
    }
}
