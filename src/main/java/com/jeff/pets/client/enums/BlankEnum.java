package com.jeff.pets.client.enums;


public enum BlankEnum implements NameableEnum {
    no_skins_are_available;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
