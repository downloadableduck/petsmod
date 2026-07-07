package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum DumboOctopusSkins implements NameableEnum {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this));
    }
}
