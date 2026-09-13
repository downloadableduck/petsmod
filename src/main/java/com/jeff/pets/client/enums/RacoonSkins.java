package com.jeff.pets.client.enums;


public enum RacoonSkins implements NameableEnum {
    normal,
    albino;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
