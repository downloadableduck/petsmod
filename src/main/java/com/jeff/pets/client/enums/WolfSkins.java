package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum WolfSkins implements NameableEnum {
    ashen,
    black,
    chestnut,
    pale,
    rusty,
    snowy,
    spotted,
    striped,
    woods;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
