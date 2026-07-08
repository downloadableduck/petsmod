package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum PandaSkins implements NameableEnum {
    agressive,
    brown,
    lazy,
    normal,
    playful,
    weak,
    worried;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
