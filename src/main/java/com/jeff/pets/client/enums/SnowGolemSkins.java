package com.jeff.pets.client.enums;


public enum SnowGolemSkins implements NameableEnum {
    pumpkin_on,
    pumpkin_off;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
