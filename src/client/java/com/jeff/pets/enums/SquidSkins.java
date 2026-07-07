package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
