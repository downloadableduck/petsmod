package com.jeff.pets.client.enums;


public enum BeeSkins implements NameableEnum {
    happy,
    angry;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
