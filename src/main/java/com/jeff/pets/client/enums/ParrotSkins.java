package com.jeff.pets.client.enums;


public enum ParrotSkins implements NameableEnum {
    blue,
    cyan,
    gray,
    green,
    red;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
