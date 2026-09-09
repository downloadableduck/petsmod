package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum SnowGolemSkins implements NameableEnum, EnumImpl {
    pumpkin_on,
    pumpkin_off;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(pumpkin_on, pumpkin_off);
    }
}
