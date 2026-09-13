package com.jeff.pets.client.enums;


public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
