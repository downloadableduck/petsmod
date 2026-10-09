package com.jeff.pets.client.enums;


public enum CopperGolemSkins implements NameableEnum {
    exposed,
    oxidized,
    unoxidized,
    weathered;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
