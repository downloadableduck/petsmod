package com.jeff.pets.client.enums;

import net.minecraft.network.chat.Component;

import java.util.List;

public enum NautilusSkins implements NameableEnum, EnumImpl {
    nautilus,
    coral_zombie,
    zombie;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }

    @Override
    public List<Enum> getAllValues() {
        return List.of(nautilus, coral_zombie, zombie);
    }
}
