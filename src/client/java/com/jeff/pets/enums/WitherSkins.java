package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum WitherSkins implements NameableEnum {
    normal,
    invulnerable;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
