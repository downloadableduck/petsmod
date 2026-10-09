package com.jeff.pets.client.enums;


public enum CowSkins implements NameableEnum {
    cold,
    temperate,
    warm;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
