package com.jeff.pets.client.enums;


public enum SlimeLikeSkins implements NameableEnum {
    small,
    medium,
    large;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
