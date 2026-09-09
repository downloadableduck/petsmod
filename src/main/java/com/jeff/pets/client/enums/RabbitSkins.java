package com.jeff.pets.client.enums;

import com.jeff.pets.client.enums.EnumImpl;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum RabbitSkins implements NameableEnum, EnumImpl {
    black,
    brown,
    gold,
    killer,
    salt,
    splotched,
    toast,
    white;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(black, brown, gold, killer, salt, splotched, toast, white);
    }
}
