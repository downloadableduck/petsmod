package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum ShulkerSkins implements NameableEnum, EnumImpl {
    normal,
    black,
    blue,
    brown,
    cyan,
    gray,
    green,
    light_blue,
    light_gray,
    lime,
    magenta,
    orange,
    pink,
    purple,
    red,
    white,
    yellow;


    @Override
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(normal,
                black,
                blue,
                brown,
                cyan,
                gray,
                green,
                light_blue,
                light_gray,
                lime,
                magenta,
                orange,
                pink,
                purple,
                red,
                white,
                yellow);
    }
}
