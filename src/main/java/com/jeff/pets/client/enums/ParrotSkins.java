package com.jeff.pets.client.enums;


public enum ParrotSkins implements NameableEnum {
    blue,
    cyan,
    gray,
    green,
    red;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
