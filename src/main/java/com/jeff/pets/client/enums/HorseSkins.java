package com.jeff.pets.client.enums;


public enum HorseSkins implements NameableEnum {
    black,
    brown,
    chestnut,
    creamy,
    dark_brown,
    gray,
    skeleton,
    white,
    zombie;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
