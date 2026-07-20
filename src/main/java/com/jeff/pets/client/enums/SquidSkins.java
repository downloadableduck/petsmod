package com.jeff.pets.client.enums;


public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
