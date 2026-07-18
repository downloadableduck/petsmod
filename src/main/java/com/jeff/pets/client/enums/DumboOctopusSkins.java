package com.jeff.pets.client.enums;


public enum DumboOctopusSkins implements NameableEnum {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this));
    }
}
