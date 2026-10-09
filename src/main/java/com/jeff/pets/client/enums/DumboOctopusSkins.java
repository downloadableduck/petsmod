package com.jeff.pets.client.enums;


public enum DumboOctopusSkins implements NameableEnum {
    blue,
    green,
    orange,
    pink,
    red,
    yellow;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this));
    }
}
