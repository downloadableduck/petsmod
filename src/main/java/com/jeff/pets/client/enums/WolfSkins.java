package com.jeff.pets.client.enums;


public enum WolfSkins implements NameableEnum {
    ashen,
    black,
    chestnut,
    pale,
    rusty,
    snowy,
    spotted,
    striped,
    woods;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
