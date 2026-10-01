package com.jeff.pets.client.enums;

import com.google.common.collect.ImmutableList;
import net.minecraft.text.Text; import net.minecraft.text.LiteralText;

import java.util.List;

public enum DumboOctopusSkins implements NameableEnum, EnumImpl {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public Text getDisplayName() {
        return new LiteralText(String.valueOf(this));
    }

    @Override
    public List<Enum> getAllValues() {
        return ImmutableList.of(blue, green, orange, pink, red, yellow);
    }
}
