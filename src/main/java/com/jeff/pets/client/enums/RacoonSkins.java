package com.jeff.pets.client.enums;


public enum RacoonSkins implements NameableEnum {
    normal,
    albino;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
