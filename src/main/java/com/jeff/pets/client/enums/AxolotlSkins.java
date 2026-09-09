package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum AxolotlSkins implements NameableEnum, EnumImpl {
    blue,
    brown,
    cyan,
    gold,
    pink;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(blue, brown, cyan, gold, pink);
    }
}
