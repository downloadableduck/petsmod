package com.jeff.pets.client.enums;


public enum PiglinSkins implements NameableEnum {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
