package com.jeff.pets.client.enums;


public enum ParrotSkins implements NameableEnum {
    blue,
    cyan,
    gray,
    green,
    red;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
