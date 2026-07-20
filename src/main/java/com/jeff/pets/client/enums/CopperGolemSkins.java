package com.jeff.pets.client.enums;


public enum CopperGolemSkins implements NameableEnum {
    exposed,
    oxidized,
    unoxidized,
    weathered;

    @Override
    public net.minecraft.text.LiteralText getDisplayName() {
        return new net.minecraft.text.LiteralText(String.valueOf(this).replace("_", " "));
    }
}
