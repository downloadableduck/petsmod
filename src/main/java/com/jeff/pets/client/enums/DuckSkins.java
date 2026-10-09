package com.jeff.pets.client.enums;


public enum DuckSkins implements NameableEnum {

    mallard,
    pekin,
    rubber,
    bronze;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
