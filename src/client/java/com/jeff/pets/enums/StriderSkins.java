package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum StriderSkins implements NameableEnum {
    cold,
    warm;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
