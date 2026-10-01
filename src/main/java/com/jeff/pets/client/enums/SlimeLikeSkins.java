package com.jeff.pets.client.enums;


public enum SlimeLikeSkins implements NameableEnum {
    small,
    medium,
    large;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
