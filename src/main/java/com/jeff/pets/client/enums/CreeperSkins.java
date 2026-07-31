package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum CreeperSkins implements NameableEnum {
    normal,
    charged;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
