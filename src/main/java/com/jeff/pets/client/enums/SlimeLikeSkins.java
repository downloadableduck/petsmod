package com.jeff.pets.client.enums;


public enum SlimeLikeSkins implements NameableEnum {
    small,
    medium,
    large;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
