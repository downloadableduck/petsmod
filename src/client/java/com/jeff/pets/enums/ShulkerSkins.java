package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum ShulkerSkins implements NameableEnum {
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
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
