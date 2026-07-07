package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum WitherSkins implements NameableEnum {
    normal,
    invulnerable;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
