package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum RacoonSkins implements NameableEnum {
    normal,
    albino;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
