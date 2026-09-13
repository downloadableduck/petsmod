package com.jeff.pets.client.enums;


public enum AxolotlSkins implements NameableEnum {
    blue,
    brown,
    cyan,
    gold,
    pink;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
