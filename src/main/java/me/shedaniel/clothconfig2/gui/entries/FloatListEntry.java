//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.compat.CompatTextFieldWidget;
import net.minecraft.client.resource.language.I18n;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FloatListEntry extends TextFieldListEntry<Float> {
    private static Function<String, String> stripCharacters = (s) -> {
        StringBuilder stringBuilder_1 = new StringBuilder();
        char[] var2 = s.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            if (Character.isDigit(var2[var4]) || var2[var4] == '-' || var2[var4] == '.') {
                stringBuilder_1.append(var2[var4]);
            }
        }

        return stringBuilder_1.toString();
    };
    private float minimum;
    private float maximum;
    private Consumer<Float> saveConsumer;

    public FloatListEntry(String fieldName, Float value, Consumer<Float> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public FloatListEntry(String fieldName, Float value, String resetButtonKey, Supplier<Float> defaultValue, Consumer<Float> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.minimum = -3.4028235E38F;
        this.maximum = Float.MAX_VALUE;
        this.saveConsumer = saveConsumer;
    }

    public FloatListEntry(String fieldName, Float value, String resetButtonKey, Supplier<Float> defaultValue, Consumer<Float> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier);
        this.minimum = -3.4028235E38F;
        this.maximum = Float.MAX_VALUE;
        this.saveConsumer = saveConsumer;
    }

    protected String stripAddText(String s) {
        return (String)stripCharacters.apply(s);
    }

    protected void textFieldPreRender(CompatTextFieldWidget widget) {
    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() ? text.equals(((Float)this.defaultValue.get()).toString()) : false;
    }

    public FloatListEntry setMinimum(float minimum) {
        this.minimum = minimum;
        return this;
    }

    public FloatListEntry setMaximum(float maximum) {
        this.maximum = maximum;
        return this;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public Float getValue() {
        try {
            return Float.valueOf(this.textFieldWidget.getText());
        } catch (Exception var2) {
            return 0.0F;
        }
    }

    public Optional<String> getError() {
        try {
            float i = Float.valueOf(this.textFieldWidget.getText());
            if (i > this.maximum) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{this.maximum}));
            }

            if (i < this.minimum) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{this.minimum}));
            }
        } catch (NumberFormatException var2) {
            return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_float"));
        }

        return super.getError();
    }
}
