package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum SlimeLikeSkins implements NameableEnum {
    small,
    medium,
    large;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
