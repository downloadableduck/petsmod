package com.jeff.pets.client.enums;


public enum RabbitSkins implements NameableEnum {
    black,
    brown,
    gold,
    killer,
    salt,
    splotched,
    toast,
    white;

    @Override
    public String getDisplayName() {
        return (String.valueOf(this).replace("_", " "));
    }
}
