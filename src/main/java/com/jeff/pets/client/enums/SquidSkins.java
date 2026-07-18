package com.jeff.pets.client.enums;


public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
