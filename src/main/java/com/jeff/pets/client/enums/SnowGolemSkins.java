package com.jeff.pets.client.enums;


public enum SnowGolemSkins implements NameableEnum {
    pumpkin_on,
    pumpkin_off;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
