package com.jeff.pets.client.enums;


public enum PandaSkins implements NameableEnum {
    agressive,
    brown,
    lazy,
    normal,
    playful,
    weak,
    worried;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
