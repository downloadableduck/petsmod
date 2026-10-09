package com.jeff.pets.client.enums;


public enum LlamaSkins implements NameableEnum {
    brown,
    creamy,
    gray,
    white;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
