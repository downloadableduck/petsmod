package com.jeff.pets.client.enums;


public enum CamelSkins implements NameableEnum {
    camel,
    husk;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
