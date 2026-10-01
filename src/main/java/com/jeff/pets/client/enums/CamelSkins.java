package com.jeff.pets.client.enums;


public enum CamelSkins implements NameableEnum {
    camel,
    husk;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
