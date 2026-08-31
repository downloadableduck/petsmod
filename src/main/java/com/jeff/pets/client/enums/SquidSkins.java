package com.jeff.pets.client.enums;


public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public net.minecraft.util.text.ITextComponent getDisplayName() {
        return new net.minecraft.util.text.TextComponentString(String.valueOf(this).replace("_", " "));
    }
}
