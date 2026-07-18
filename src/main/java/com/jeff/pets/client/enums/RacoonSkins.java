package com.jeff.pets.client.enums;


public enum RacoonSkins implements NameableEnum {
    normal,
    albino;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
