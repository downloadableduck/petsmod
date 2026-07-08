package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum NautilusSkins implements NameableEnum {
    nautilus,
    coral_zombie,
    zombie;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
