package com.jeff.pets.client.enums;


public enum CamelSkins implements NameableEnum {
    camel,
    husk;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
