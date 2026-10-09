package com.jeff.pets.client.enums;


public enum MooshroomSkins implements NameableEnum {
    red,
    brown;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
