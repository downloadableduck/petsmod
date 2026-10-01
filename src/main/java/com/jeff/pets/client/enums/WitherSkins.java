package com.jeff.pets.client.enums;


public enum WitherSkins implements NameableEnum {
    normal,
    invulnerable;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
