package com.jeff.pets.client.enums;


public enum PiglinSkins implements NameableEnum {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
