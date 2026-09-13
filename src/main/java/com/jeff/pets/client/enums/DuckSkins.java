package com.jeff.pets.client.enums;


public enum DuckSkins implements NameableEnum {

    mallard,
    pekin,
    rubber,
    bronze;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
