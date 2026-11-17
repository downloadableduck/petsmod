//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import me.shedaniel.clothconfig2.compat.InputConstants;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.ConfigEntryBuilderImpl;
import me.shedaniel.clothconfig2.impl.builders.*;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder.TopCellElementBuilder;
import net.minecraft.client.gui.screen.Screen;

import java.util.List;

public interface ConfigEntryBuilder {
    static ConfigEntryBuilder create() {
        return ConfigEntryBuilderImpl.create();
    }

    String getResetButtonKey();

    ConfigEntryBuilder setResetButtonKey(String var1);

    IntListBuilder startIntList(String var1, List<Integer> var2);

    LongListBuilder startLongList(String var1, List<Long> var2);

    FloatListBuilder startFloatList(String var1, List<Float> var2);

    DoubleListBuilder startDoubleList(String var1, List<Double> var2);

    StringListBuilder startStrList(String var1, List<String> var2);

    SubCategoryBuilder startSubCategory(String var1);

    SubCategoryBuilder startSubCategory(String var1, List<AbstractConfigListEntry> var2);

    BooleanToggleBuilder startBooleanToggle(String var1, boolean var2);

    StringFieldBuilder startStrField(String var1, String var2);

    ColorFieldBuilder startColorField(String var1, int var2);

    default ColorFieldBuilder startAlphaColorField(String fieldNameKey, int value) {
        return this.startColorField(fieldNameKey, value).setAlphaMode(true);
    }

    TextFieldBuilder startTextField(String var1, String var2);

    TextDescriptionBuilder startTextDescription(String var1);

    <T extends Enum<?>> EnumSelectorBuilder<T> startEnumSelector(String var1, Class<T> var2, T var3);

    <T> SelectorBuilder<T> startSelector(String var1, T[] var2, T var3);

    IntFieldBuilder startIntField(String var1, int var2);

    LongFieldBuilder startLongField(String var1, long var2);

    FloatFieldBuilder startFloatField(String var1, float var2);

    DoubleFieldBuilder startDoubleField(String var1, double var2);

    IntSliderBuilder startIntSlider(String var1, int var2, int var3, int var4);

    LongSliderBuilder startLongSlider(String var1, long var2, long var4, long var6);

    KeyCodeBuilder startModifierKeyCodeField(String var1, ModifierKeyCode var2);

    default KeyCodeBuilder startKeyCodeField(String fieldNameKey, InputConstants.Key value) {
        return this.startModifierKeyCodeField(fieldNameKey, ModifierKeyCode.of(value, Modifier.none())).setAllowModifiers(false);
    }

    <T> DropdownMenuBuilder<T> startDropdownMenu(String var1, DropdownBoxEntry.SelectionTopCellElement<T> var2, DropdownBoxEntry.SelectionCellCreator<T> var3);

    default <T> DropdownMenuBuilder<T> startDropdownMenu(String fieldNameKey, DropdownBoxEntry.SelectionTopCellElement<T> topCellElement) {
        return this.startDropdownMenu(fieldNameKey, topCellElement, new DropdownBoxEntry.DefaultSelectionCellCreator());
    }

    default DropdownMenuBuilder<String> startStringDropdownMenu(Screen screen, String fieldNameKey, String value) {
        return this.startDropdownMenu(fieldNameKey, TopCellElementBuilder.of(screen, value, (s) -> {
            return s;
        }, (s) -> {
            return s;
        }), new DropdownBoxEntry.DefaultSelectionCellCreator());
    }
}
