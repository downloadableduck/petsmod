package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum CopperGolemSkins implements NameableEnum {
    exposed,
    oxidized,
    unoxidized,
    weathered;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
