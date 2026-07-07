package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum LlamaSkins implements NameableEnum {
    brown,
    creamy,
    gray,
    white;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
