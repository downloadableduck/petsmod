package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum MooshroomSkins implements NameableEnum {
    red,
    brown;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
