package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.PetsInitializer;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;

public class BooleanListEntry extends TooltipListEntry<Boolean> {

    private final AtomicBoolean bool;
    private final ButtonWidget buttonWidget;
    private final ButtonWidget resetButton;
    private final Consumer<Boolean> saveConsumer;
    private final Supplier<Boolean> defaultValue;
    private final List<ButtonWidget> widgets;


    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, Consumer<Boolean> saveConsumer) {
        this(fieldName, bool, "text.cloth-config.reset_value", null, saveConsumer);
    }


    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer) {
        this(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, null);
    }


    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
        this(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
    }


    @Deprecated
    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, tooltipSupplier, requiresRestart);
        this.defaultValue = defaultValue;
        this.bool = new AtomicBoolean(bool);
        this.buttonWidget = new ButtonWidget(new Random().nextInt(), 0, 0, 150, 20, "") {
            @Override
            public boolean method_21893(MinecraftClient mc, int mouseX, int mouseY) {
                
                 boolean bl = super.method_21893(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    PetsInitializer.LOGGER.info("mouse pressed");
                    BooleanListEntry.this.bool.set(!BooleanListEntry.this.bool.get());
                    getScreen().setEdited(true, isRequiresRestart());
                } return bl;
            }
        };
        this.resetButton = new ButtonWidget(new Random().nextInt(), 0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey)) {
            @Override
            public boolean method_21893(MinecraftClient mc,  int mouseX, int mouseY) {
                 boolean bl = super.method_21893(mc, mouseX, mouseY); if (bl) { PetsInitializer.LOGGER.info("mouse pressed");
                    PetsInitializer.LOGGER.info("mouse pressed");
                    BooleanListEntry.this.bool.set(defaultValue.get());
                    getScreen().setEdited(true, isRequiresRestart());
                } return bl;
            }
        };
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(buttonWidget, resetButton);
    }

    @Override
    public void save() {
        if (saveConsumer != null)
            saveConsumer.accept(getValue());
    }

    @Override
    public Boolean getValue() {
        return bool.get();
    }

    @Override
    public Optional<Boolean> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        int windowWidth = (new Window(MinecraftClient.getInstance().options, MinecraftClient.getInstance().width, MinecraftClient.getInstance().height)).getWidth();
        this.resetButton.field_22511 = isEditable() && getDefaultValue().isPresent() && defaultValue.get() != bool.get();
        this.resetButton.y = y;
        this.buttonWidget.field_22511 = isEditable();
        this.buttonWidget.y = y;
        this.buttonWidget.field_22510 = (getYesNoText(bool.get()));
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

    public String getYesNoText(boolean bool) {
        return I18n.translate("text.cloth-config.boolean.value." + bool);
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
