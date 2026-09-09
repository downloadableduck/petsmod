package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum PandaSkins implements NameableEnum, EnumImpl {
    agressive,
    brown,
    lazy,
    normal,
    playful,
    weak,
    worried;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(agressive, brown, lazy, normal, playful, weak, worried);
    }
}
