package com.jeff.pets.client.enums;


public enum BlankEnum implements NameableEnum {
    no_skins_are_available;

    @Override
    public net.minecraft.util.ChatComponentText getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
