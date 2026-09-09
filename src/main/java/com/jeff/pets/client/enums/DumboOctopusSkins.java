package com.jeff.pets.client.enums;

import com.jeff.pets.client.enums.EnumImpl;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum DumboOctopusSkins implements NameableEnum, EnumImpl {
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

    @Override
    public List<Enum> getAllValues() {
        return List.of(blue, green, orange, pink, red, yellow);
    }
}
