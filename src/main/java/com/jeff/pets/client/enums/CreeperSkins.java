package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum CreeperSkins implements NameableEnum {
    normal,
    charged;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
