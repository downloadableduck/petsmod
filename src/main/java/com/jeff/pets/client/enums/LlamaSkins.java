package com.jeff.pets.client.enums;


public enum LlamaSkins implements NameableEnum {
    brown,
    creamy,
    gray,
    white;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
