package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum FrogSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
