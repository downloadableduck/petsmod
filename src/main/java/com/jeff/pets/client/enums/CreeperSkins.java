package com.jeff.pets.client.enums;


public enum CreeperSkins implements NameableEnum {
    normal,
    charged;

    @Override
    public net.minecraft.util.ChatComponentText getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
