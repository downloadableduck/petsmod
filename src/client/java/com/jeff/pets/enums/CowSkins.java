package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum CowSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
