package com.jeff.pets.client.enums;


public enum HorseSkins implements NameableEnum {
    black,
    brown,
    chestnut,
    creamy,
    dark_brown,
    gray,
    skeleton,
    white,
    zombie;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
