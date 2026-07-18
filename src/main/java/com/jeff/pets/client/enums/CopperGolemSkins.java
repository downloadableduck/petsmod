package com.jeff.pets.client.enums;


public enum CopperGolemSkins implements NameableEnum {
    exposed,
    oxidized,
    unoxidized,
    weathered;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
