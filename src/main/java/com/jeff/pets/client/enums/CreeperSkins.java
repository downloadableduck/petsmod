package com.jeff.pets.client.enums;


public enum CreeperSkins implements NameableEnum {
    normal,
    charged;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
