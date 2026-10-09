package com.jeff.pets.client.enums;


public enum StriderSkins implements NameableEnum {
    cold,
    warm;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
