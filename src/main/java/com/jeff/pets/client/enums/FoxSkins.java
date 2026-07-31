package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
