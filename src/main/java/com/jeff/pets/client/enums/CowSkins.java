package com.jeff.pets.client.enums;


public enum CowSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
