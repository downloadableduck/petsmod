package com.jeff.pets.client.enums;


public enum PigSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
