package com.jeff.pets.client.enums;


public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
