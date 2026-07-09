package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum RabbitSkins implements NameableEnum {
    black,
    brown,
    gold,
    killer,
    salt,
    splotched,
    toast,
    white;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
