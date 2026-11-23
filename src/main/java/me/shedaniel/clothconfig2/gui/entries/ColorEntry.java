//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.compat.CompatTextFieldWidget;
import me.shedaniel.clothconfig2.gui.widget.ColorDisplayWidget;
import net.minecraft.client.resource.language.I18n;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ColorEntry extends TextFieldListEntry<Integer> {
    private ColorDisplayWidget colorDisplayWidget;
    private Consumer<Integer> saveConsumer;
    private boolean alpha = true;

    /** @deprecated */
    @Deprecated
    @Internal
    public ColorEntry(String fieldName, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, 0, resetButtonKey, defaultValue, tooltipSupplier);
        ColorValue colorValue = this.getColorValue(String.valueOf(value));
        if (colorValue.hasError()) {
            throw new IllegalArgumentException("Invalid Color: " + colorValue.getError().name());
        } else {
            this.alpha = false;
            this.saveConsumer = saveConsumer;
            this.textFieldWidget.setText(this.getHexColorString(value));
            this.colorDisplayWidget = new ColorDisplayWidget(0, 0, 20, this.getColorValueColor(this.textFieldWidget.getText()));
            this.original = value;
        }
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.colorDisplayWidget.y = y;
        ColorValue value = this.getColorValue(this.textFieldWidget.getText());
        if (!value.hasError()) {
            this.colorDisplayWidget.setColor(this.alpha ? value.getColor() : -16777216 | value.getColor());
        }

        if (Utils.isBidirectional()) {
            this.colorDisplayWidget.x = x + this.resetButton.width + 148;
        } else {
            this.colorDisplayWidget.x = this.textFieldWidget.x - 23;
        }

        this.colorDisplayWidget.render(mouseX, mouseY, delta);
    }

    protected void textFieldPreRender(CompatTextFieldWidget widget) {
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    protected boolean isMatchDefault(String text) {
        if (!this.getDefaultValue().isPresent()) {
            return false;
        } else {
            ColorValue colorValue = this.getColorValue(text);
            return colorValue.hasError() && colorValue.color == (Integer)this.getDefaultValue().get();
        }
    }

    public Integer getValue() {
        return this.getColorValueColor(this.textFieldWidget.getText());
    }

    /** @deprecated */
    @Deprecated
    public void setValue(int color) {
        this.textFieldWidget.setText(this.getHexColorString(color));
    }

    public Optional<String> getError() {
        ColorValue colorValue = this.getColorValue(this.textFieldWidget.getText());
        return colorValue.hasError() ? Optional.of(I18n.getTranslation("text.cloth-config.error.color." + colorValue.getError().name().toLowerCase(Locale.ROOT))) : super.getError();
    }

    public void withAlpha() {
        if (!this.alpha) {
            this.alpha = true;
            this.textFieldWidget.setText(this.getHexColorString((Integer)this.original));
        }

    }

    public void withoutAlpha() {
        if (this.alpha) {
            this.alpha = false;
            this.textFieldWidget.setText(this.getHexColorString((Integer)this.original));
        }

    }

    protected String stripHexStarter(String hex) {
        return hex.startsWith("#") ? hex.substring(1) : hex;
    }

    protected boolean isValidColorString(String str) {
        return !this.getColorValue(str).hasError();
    }

    protected int getColorValueColor(String str) {
        return this.getColorValue(str).getColor();
    }

    protected ColorValue getColorValue(String str) {
        try {
            int color;
            if (str.startsWith("#")) {
                String stripHexStarter = this.stripHexStarter(str);
                if (stripHexStarter.length() > 8) {
                    return ColorError.INVALID_COLOR.toValue();
                }

                if (!this.alpha && stripHexStarter.length() > 6) {
                    return ColorError.NO_ALPHA_ALLOWED.toValue();
                }

                color = (int)Long.parseLong(stripHexStarter, 16);
            } else {
                color = (int)Long.parseLong(str);
            }

            int a = color >> 24 & 255;
            if (!this.alpha && a > 0) {
                return ColorError.NO_ALPHA_ALLOWED.toValue();
            } else if (a >= 0 && a <= 255) {
                int r = color >> 16 & 255;
                if (r >= 0 && r <= 255) {
                    int g = color >> 8 & 255;
                    if (g >= 0 && g <= 255) {
                        int b = color & 255;
                        return b >= 0 && b <= 255 ? new ColorValue(color) : ColorError.INVALID_BLUE.toValue();
                    } else {
                        return ColorError.INVALID_GREEN.toValue();
                    }
                } else {
                    return ColorError.INVALID_RED.toValue();
                }
            } else {
                return ColorError.INVALID_ALPHA.toValue();
            }
        } catch (NumberFormatException var7) {
            return ColorError.INVALID_COLOR.toValue();
        }
    }

    protected String getHexColorString(int color) {
        return "#" + StringUtils.leftPad(Integer.toHexString(color), this.alpha ? 8 : 6, '0');
    }

    protected static class ColorValue {
        private int color = -1;
        @Nullable
        private ColorError error = null;

        public ColorValue(int color) {
            this.color = color;
        }

        public ColorValue(ColorError error) {
            this.error = error;
        }

        public int getColor() {
            return this.color;
        }

        @Nullable
        public ColorError getError() {
            return this.error;
        }

        public boolean hasError() {
            return this.getError() != null;
        }
    }

    protected static enum ColorError {
        NO_ALPHA_ALLOWED,
        INVALID_ALPHA,
        INVALID_RED,
        INVALID_GREEN,
        INVALID_BLUE,
        INVALID_COLOR;

        private ColorValue value = new ColorValue(this);

        private ColorError() {
        }

        public ColorValue toValue() {
            return this.value;
        }
    }
}
