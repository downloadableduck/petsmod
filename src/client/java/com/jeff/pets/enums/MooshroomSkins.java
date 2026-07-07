package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum MooshroomSkins implements NameableEnum {
    red,
    brown;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
