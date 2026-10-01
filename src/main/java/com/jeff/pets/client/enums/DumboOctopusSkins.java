package com.jeff.pets.client.enums;


public enum DumboOctopusSkins implements NameableEnum {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this));
    }
}
