package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum DumboOctopusSkins implements NameableEnum {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this));
    }
}
