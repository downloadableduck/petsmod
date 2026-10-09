package com.jeff.pets.client.enums;


public enum MooshroomSkins implements NameableEnum {
    red,
    brown;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
