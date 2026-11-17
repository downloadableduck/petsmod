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

public class DoubleListEntry extends TextFieldListEntry<Double> {
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
    private double minimum;
    private double maximum;
    private Consumer<Double> saveConsumer;

    public DoubleListEntry(String fieldName, Double value, Consumer<Double> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public DoubleListEntry(String fieldName, Double value, String resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.minimum = -1.7976931348623157E308;
        this.maximum = Double.MAX_VALUE;
        this.saveConsumer = saveConsumer;
    }

    public DoubleListEntry(String fieldName, Double value, String resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier);
        this.minimum = -1.7976931348623157E308;
        this.maximum = Double.MAX_VALUE;
        this.saveConsumer = saveConsumer;
    }

    protected String stripAddText(String s) {
        return (String)stripCharacters.apply(s);
    }

    protected void textFieldPreRender(CompatTextFieldWidget widget) {
    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() ? text.equals(((Double)this.defaultValue.get()).toString()) : false;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    public DoubleListEntry setMinimum(double minimum) {
        this.minimum = minimum;
        return this;
    }

    public DoubleListEntry setMaximum(double maximum) {
        this.maximum = maximum;
        return this;
    }

    public Double getValue() {
        try {
            return Double.valueOf(this.textFieldWidget.getText());
        } catch (Exception var2) {
            return 0.0;
        }
    }

    public Optional<String> getError() {
        try {
            double i = Double.valueOf(this.textFieldWidget.getText());
            if (i > this.maximum) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{this.maximum}));
            }

            if (i < this.minimum) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{this.minimum}));
            }
        } catch (NumberFormatException var3) {
            return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_double"));
        }

        return super.getError();
    }

    public void setValue(String s) {
        this.defaultValue = () -> {
            return Double.valueOf(s);
        };
    }
}
