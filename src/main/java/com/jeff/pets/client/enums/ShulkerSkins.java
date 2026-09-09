package com.jeff.pets.client.enums;

import com.jeff.pets.client.enums.EnumImpl;

import net.minecraft.network.chat.Component;

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
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(normal,
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
