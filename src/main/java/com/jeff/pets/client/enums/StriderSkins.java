package com.jeff.pets.client.enums;


public enum StriderSkins implements NameableEnum {
    cold,
    warm;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
