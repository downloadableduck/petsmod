//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.builders.*;

import java.util.List;
import java.util.UUID;

public class ConfigEntryBuilderImpl implements ConfigEntryBuilder {
    private String resetButtonKey;

    private ConfigEntryBuilderImpl() {
        this.resetButtonKey = "text.cloth-config.reset_value";
    }

    public static ConfigEntryBuilderImpl create() {
        return new ConfigEntryBuilderImpl();
    }

    public static ConfigEntryBuilderImpl createImmutable() {
        return new ConfigEntryBuilderImpl() {
            public ConfigEntryBuilder setResetButtonKey(String resetButtonKey) {
                throw new UnsupportedOperationException("This is an immutable entry builder!");
            }
        };
    }

    public String getResetButtonKey() {
        return this.resetButtonKey;
    }

    public ConfigEntryBuilder setResetButtonKey(String resetButtonKey) {
        this.resetButtonKey = resetButtonKey;
        return this;
    }

    public IntListBuilder startIntList(String fieldNameKey, List<Integer> value) {
        return new IntListBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public LongListBuilder startLongList(String fieldNameKey, List<Long> value) {
        return new LongListBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public FloatListBuilder startFloatList(String fieldNameKey, List<Float> value) {
        return new FloatListBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public DoubleListBuilder startDoubleList(String fieldNameKey, List<Double> value) {
        return new DoubleListBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public StringListBuilder startStrList(String fieldNameKey, List<String> value) {
        return new StringListBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public SubCategoryBuilder startSubCategory(String fieldNameKey) {
        return new SubCategoryBuilder(this.resetButtonKey, fieldNameKey);
    }

    public SubCategoryBuilder startSubCategory(String fieldNameKey, List<AbstractConfigListEntry> entries) {
        SubCategoryBuilder builder = new SubCategoryBuilder(this.resetButtonKey, fieldNameKey);
        builder.addAll(entries);
        return builder;
    }

    public BooleanToggleBuilder startBooleanToggle(String fieldNameKey, boolean value) {
        return new BooleanToggleBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public StringFieldBuilder startStrField(String fieldNameKey, String value) {
        return new StringFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public ColorFieldBuilder startColorField(String fieldNameKey, int value) {
        return new ColorFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public TextFieldBuilder startTextField(String fieldNameKey, String value) {
        return new TextFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public TextDescriptionBuilder startTextDescription(String value) {
        return new TextDescriptionBuilder(this.resetButtonKey, UUID.randomUUID().toString(), value);
    }

    public <T extends Enum<?>> EnumSelectorBuilder<T> startEnumSelector(String fieldNameKey, Class<T> clazz, T value) {
        return new EnumSelectorBuilder(this.resetButtonKey, fieldNameKey, clazz, value);
    }

    public <T> SelectorBuilder<T> startSelector(String fieldNameKey, T[] valuesArray, T value) {
        return new SelectorBuilder(this.resetButtonKey, fieldNameKey, valuesArray, value);
    }

    public IntFieldBuilder startIntField(String fieldNameKey, int value) {
        return new IntFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public LongFieldBuilder startLongField(String fieldNameKey, long value) {
        return new LongFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public FloatFieldBuilder startFloatField(String fieldNameKey, float value) {
        return new FloatFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public DoubleFieldBuilder startDoubleField(String fieldNameKey, double value) {
        return new DoubleFieldBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public IntSliderBuilder startIntSlider(String fieldNameKey, int value, int min, int max) {
        return new IntSliderBuilder(this.resetButtonKey, fieldNameKey, value, min, max);
    }

    public LongSliderBuilder startLongSlider(String fieldNameKey, long value, long min, long max) {
        return new LongSliderBuilder(this.resetButtonKey, fieldNameKey, value, min, max);
    }

    public KeyCodeBuilder startModifierKeyCodeField(String fieldNameKey, ModifierKeyCode value) {
        return new KeyCodeBuilder(this.resetButtonKey, fieldNameKey, value);
    }

    public <T> DropdownMenuBuilder<T> startDropdownMenu(String fieldNameKey, DropdownBoxEntry.SelectionTopCellElement<T> topCellElement, DropdownBoxEntry.SelectionCellCreator<T> cellCreator) {
        return new DropdownMenuBuilder(this.resetButtonKey, fieldNameKey, topCellElement, cellCreator);
    }
}
