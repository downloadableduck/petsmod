package com.jeff.pets.client.enums;


public enum SquidSkins implements NameableEnum {
    squid,
    glow_squid;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
