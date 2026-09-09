package com.jeff.pets.client.enums;

import com.jeff.pets.client.enums.EnumImpl;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum PiglinSkins implements NameableEnum, EnumImpl {
    piglin,
    piglin_brute,
    zombified_piglin;


    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(piglin, piglin_brute, zombified_piglin);
    }
}
