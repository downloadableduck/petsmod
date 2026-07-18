package com.jeff.pets.client.enums;


public enum WitherSkins implements NameableEnum {
    normal,
    invulnerable;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
