package com.jeff.pets.client.enums;


public enum StriderSkins implements NameableEnum {
    cold,
    warm;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
