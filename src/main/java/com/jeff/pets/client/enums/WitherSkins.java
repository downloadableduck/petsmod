package com.jeff.pets.client.enums;


public enum WitherSkins implements NameableEnum {
    normal,
    invulnerable;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
