package com.jeff.pets.client.enums;


public enum HorseSkins implements NameableEnum {
    black,
    brown,
    chestnut,
    creamy,
    dark_brown,
    gray,
    skeleton,
    white,
    zombie;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
