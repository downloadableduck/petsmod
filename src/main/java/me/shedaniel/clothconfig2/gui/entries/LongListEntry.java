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

public class LongListEntry extends TextFieldListEntry<Long> {
    private static Function<String, String> stripCharacters = (s) -> {
        StringBuilder stringBuilder_1 = new StringBuilder();
        char[] var2 = s.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            if (Character.isDigit(var2[var4]) || var2[var4] == '-') {
                stringBuilder_1.append(var2[var4]);
            }
        }

        return stringBuilder_1.toString();
    };
    private long minimum;
    private long maximum;
    private Consumer<Long> saveConsumer;

    public LongListEntry(String fieldName, Long value, Consumer<Long> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public LongListEntry(String fieldName, Long value, String resetButtonKey, Supplier<Long> defaultValue, Consumer<Long> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.minimum = -9223372036854775807L;
        this.maximum = Long.MAX_VALUE;
        this.saveConsumer = saveConsumer;
    }

    public LongListEntry(String fieldName, Long value, String resetButtonKey, Supplier<Long> defaultValue, Consumer<Long> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier);
        this.minimum = -9223372036854775807L;
        this.maximum = Long.MAX_VALUE;
        this.saveConsumer = saveConsumer;
    }

    protected String stripAddText(String s) {
        return (String)stripCharacters.apply(s);
    }

    protected void textFieldPreRender(CompatTextFieldWidget widget) {
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getValue());
        }

    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() ? text.equals(((Long)this.defaultValue.get()).toString()) : false;
    }

    public LongListEntry setMinimum(long minimum) {
        this.minimum = minimum;
        return this;
    }

    public LongListEntry setMaximum(long maximum) {
        this.maximum = maximum;
        return this;
    }

    public Long getValue() {
        try {
            return Long.valueOf(this.textFieldWidget.getText());
        } catch (Exception var2) {
            return 0L;
        }
    }

    public Optional<String> getError() {
        try {
            long i = Long.valueOf(this.textFieldWidget.getText());
            if (i > this.maximum) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.too_large", new Object[]{this.maximum}));
            }

            if (i < this.minimum) {
                return Optional.of(I18n.getTranslation("text.cloth-config.error.too_small", new Object[]{this.minimum}));
            }
        } catch (NumberFormatException var3) {
            return Optional.of(I18n.getTranslation("text.cloth-config.error.not_valid_number_long"));
        }

        return super.getError();
    }
}
