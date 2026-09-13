package com.jeff.pets.client.enums;


public enum BeeSkins implements NameableEnum {
    happy,
    angry;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
