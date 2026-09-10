package com.jeff.pets.client.enums;


public enum ChickenSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
