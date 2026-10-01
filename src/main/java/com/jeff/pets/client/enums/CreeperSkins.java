package com.jeff.pets.client.enums;


public enum CreeperSkins implements NameableEnum {
    normal,
    charged;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
