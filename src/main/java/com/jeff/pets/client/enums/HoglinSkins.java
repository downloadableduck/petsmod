package com.jeff.pets.client.enums;


public enum HoglinSkins implements NameableEnum {
    hoglin,
    zoglin;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
