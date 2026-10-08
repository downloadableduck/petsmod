package com.jeff.pets.client.enums;


public enum HoglinSkins implements NameableEnum {
    hoglin,
    zoglin;

    @Override
    public net.minecraft.util.ChatComponentText getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
