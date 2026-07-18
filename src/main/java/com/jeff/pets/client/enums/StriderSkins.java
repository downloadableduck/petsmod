package com.jeff.pets.client.enums;


public enum StriderSkins implements NameableEnum {
    cold,
    warm;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
