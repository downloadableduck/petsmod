package com.jeff.pets.client.enums;

import net.minecraft.util.ChatComponentText;

public enum AxolotlSkins implements NameableEnum {
    blue,
    brown,
    cyan,
    gold,
    pink;

    @Override
    public ChatComponentText getDisplayName() {
        return new ChatComponentText(String.valueOf(this).replace("_", " "));
    }
}
