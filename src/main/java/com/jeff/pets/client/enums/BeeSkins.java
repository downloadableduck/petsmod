package com.jeff.pets.client.enums;


public enum BeeSkins implements NameableEnum {
    happy,
    angry;

    @Override
    public String getDisplayName() {
        return new String(String.valueOf(this).replace("_", " "));
    }
}
