package com.jeff.pets.client.enums;


public enum DuckSkins implements NameableEnum {

    mallard,
    pekin,
    rubber,
    bronze;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
