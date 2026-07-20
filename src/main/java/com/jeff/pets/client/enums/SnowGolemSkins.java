package com.jeff.pets.client.enums;


public enum SnowGolemSkins implements NameableEnum {
    pumpkin_on,
    pumpkin_off;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
