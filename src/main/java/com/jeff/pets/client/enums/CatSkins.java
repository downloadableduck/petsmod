package com.jeff.pets.client.enums;


import net.minecraft.network.chat.Component;

public enum CatSkins implements NameableEnum {
    black,
    british_shorthair,
    calico,
    jellie,
    ocelot,
    persian,
    ragdoll,
    red,
    siamese,
    tabby,
    tuxedo,
    white;

    @Override
    public Component getDisplayName() {
        return new net.minecraft.network.chat.TextComponent(String.valueOf(this).replace("_", " "));
    }
}
