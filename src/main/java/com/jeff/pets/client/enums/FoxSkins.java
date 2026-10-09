package com.jeff.pets.client.enums;


public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
