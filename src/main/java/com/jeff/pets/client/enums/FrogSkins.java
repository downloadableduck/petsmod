package com.jeff.pets.client.enums;


public enum FrogSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
