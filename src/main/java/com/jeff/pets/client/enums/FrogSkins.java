package com.jeff.pets.client.enums;


public enum FrogSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
