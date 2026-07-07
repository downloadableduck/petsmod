package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum PiglinSkins implements NameableEnum {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
