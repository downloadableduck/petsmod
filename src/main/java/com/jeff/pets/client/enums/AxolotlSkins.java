package com.jeff.pets.client.enums;



public enum AxolotlSkins implements NameableEnum {
    blue,
    brown,
    cyan,
    gold,
    pink;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
