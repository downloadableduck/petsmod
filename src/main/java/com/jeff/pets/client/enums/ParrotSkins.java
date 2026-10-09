package com.jeff.pets.client.enums;


public enum ParrotSkins implements NameableEnum {
    blue,
    cyan,
    gray,
    green,
    red;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
