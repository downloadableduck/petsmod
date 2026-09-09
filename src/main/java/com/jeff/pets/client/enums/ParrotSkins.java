package com.jeff.pets.client.enums;

import com.jeff.pets.client.enums.EnumImpl;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum ParrotSkins implements NameableEnum, EnumImpl {
    blue,
    cyan,
    gray,
    green,
    red;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(blue, cyan, gray, green, red);
    }
}
