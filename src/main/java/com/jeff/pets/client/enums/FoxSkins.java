package com.jeff.pets.client.enums;


public enum FoxSkins implements NameableEnum {
    red,
    snow;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
