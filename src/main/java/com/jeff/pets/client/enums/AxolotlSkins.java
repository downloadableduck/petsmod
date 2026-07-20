package com.jeff.pets.client.enums;

import net.minecraft.text.LiteralText;

public enum AxolotlSkins implements NameableEnum {
    blue,
    brown,
    cyan,
    gold,
    pink;

    @Override
    public LiteralText getDisplayName() {
        return new LiteralText(String.valueOf(this).replace("_", " "));
    }
}
