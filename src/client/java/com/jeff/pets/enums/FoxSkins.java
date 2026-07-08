package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
