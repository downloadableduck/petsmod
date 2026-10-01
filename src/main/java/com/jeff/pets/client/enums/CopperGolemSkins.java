package com.jeff.pets.client.enums;


public enum CopperGolemSkins implements NameableEnum {
    exposed,
    oxidized,
    unoxidized,
    weathered;

    @Override
    public net.minecraft.util.IChatComponent getDisplayName() {
        return new net.minecraft.util.ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
