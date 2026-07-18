package com.jeff.pets.client.enums;


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
    public net.minecraft.network.chat.TextComponent getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
