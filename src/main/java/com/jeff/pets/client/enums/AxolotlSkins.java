package com.jeff.pets.client.enums;

import net.minecraft.network.chat.TextComponent;

public enum AxolotlSkins implements NameableEnum {
    blue,
    brown,
    cyan,
    gold,
    pink;

    @Override
    public TextComponent getDisplayName() {
        return new TextComponent(String.valueOf(this).replace("_", " "));
    }
}
