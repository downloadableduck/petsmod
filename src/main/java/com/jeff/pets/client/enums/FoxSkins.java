package com.jeff.pets.client.enums;


public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public net.minecraft.util.ChatComponentText getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
