package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum LlamaSkins implements NameableEnum {
    brown,
    creamy,
    gray,
    white;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
