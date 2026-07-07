package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum SnowGolemSkins implements NameableEnum {
    pumpkin_on,
    pumpkin_off;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
