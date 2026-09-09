package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum SheepSkins implements NameableEnum, EnumImpl {
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
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(black,
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
