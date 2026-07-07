package com.jeff.pets.enums;


import net.minecraft.network.chat.Component;

public enum DuckSkins implements NameableEnum {

    mallard,
    pekin,
    rubber,
    bronze;

    @Override
    public Component getDisplayName() {
        return Component.literal(String.valueOf(this).replace("_", " "));
    }
}
